package tree.element;

import math.element.object.Formula;
import math.element.object.MathObj;
import math.element.object.MathType;
import math.element.object.NumberObj;
import math.element.object.Operator;
import math.element.object.Operand;
import math.element.object.OperatorType;

/**
 * 
 */
public final class FormulaTreeBuilder {

	public static float EvaluateTree(Tree aTree) {
		if (aTree == null)
			return 0;

		float tmpResult = 0;

		if (aTree.getRoot().getMathType() == MathType.OPERAND) {
			tmpResult = ((NumberObj) aTree.getRoot()).getValue();
		} else {
			Operator tmpOperator = (Operator) aTree.getRoot();
			if (tmpOperator.getOperatorType() == OperatorType.ADDITION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())
						+ FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.DIVISION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())
						/ FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.MULTIPLICATION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())
						* FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.SUBTRACTION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())
						- FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			}
		}

		return tmpResult;
	}

	private Tree InsertOperandIntoTree(Tree anExistingTree, Operand anOperand) throws Exception {

		// check whether there is an operator in the root of the tree
		if (anExistingTree.getRoot().getMathType() == MathType.OPERAND) {

			// this is an error situation, the tree would look like the
			// following: Number (Father), Number(Left Son)
			// Numbers can only be in the leaves of the tree!

			throw (new Exception("Syntax error"));
		}

		// insert the number to the right of the operand
		Tree tmpSon = new Tree(anOperand);
		tmpSon.setFather(anExistingTree);
		anExistingTree.setRightSon(tmpSon);

		return anExistingTree;
	}

	private Tree InsertOperatorIntoTree(Tree anExistingTree, Operator anOperator) {

		Tree tmpHelpTree = null;

		// is the root in the existing tree an operand?
		if (anExistingTree.getRoot() instanceof Operand) {

			// if it is, build a new tree with Operator (father), Operand (Left Son)
			tmpHelpTree = new Tree(anOperator, null, anExistingTree, null);
			anExistingTree.setFather(tmpHelpTree);
		}
		// is the root in the existing tree an operator?
		else if (anExistingTree.getRoot() instanceof Operator) {

			// ok, it is.... now we have to compare the priority of the
			// different operators
			Operator tmpExistingOperator = (Operator) anExistingTree.getRoot();

			if (tmpExistingOperator.getOperatorType().getPriority() <= anOperator.getOperatorType().getPriority()) {

				// if the existing operator has a lower priority , a new tree
				// is built and the existing tree is appended to the left
				// but first we have to navigate to the next operator with a
				// different priority
//				int tmpInsertPriority = anOperator.getOperatorType().getPriority();
//				int tmpFatherPriority = anOperator.getOperatorType().getPriority();
//
//				while (anExistingTree.getFather() != null
//						|| tmpFatherPriority >= tmpInsertPriority) {
//
//					anExistingTree = anExistingTree.getFather();
//					if (anExistingTree.getFather() != null) {
//						tmpFatherPriority = ((Operator) anExistingTree
//								.getFather().getRoot()).getOperatorType()
//								.getPriority();
//					}
//				}

				tmpHelpTree = new Tree(anOperator, null, anExistingTree, null);
				anExistingTree.setFather(tmpHelpTree);
			} else {
				// ok, now we have more to do... we need to insert the new
				// Operator
				// as the right son of the existing tree. The current right son
				// will become the left so of the new sub-tree
				tmpHelpTree = new Tree(anOperator, anExistingTree,
						anExistingTree.getRightSon(), null);
				anExistingTree.setRightSon(tmpHelpTree);
			}
		}
		return tmpHelpTree;
	}

	/**
	 * Builds a binary formula tree :-)
	 * 
	 * @param aFunction
	 * @throws Exception 
	 */
	public Tree BuildTree(String aFunction) throws Exception {

		// prepare formula, mathobj and tmptree
		Formula tmpFormula = new Formula(aFunction);
		MathObj tmpNextElement = null;
		Tree tmpTree = null;

		// get the first element and fill the tree, because an empty tree is not
		// lucky!
		// this avoids further complex cases :-)
		tmpNextElement = tmpFormula.getNextElement();
		if (tmpNextElement.getMathType() == MathType.END_OF_TERM) {
			tmpTree = new Tree(new NumberObj(0)); // no formula
			return tmpTree;
		}
		tmpTree = new Tree(tmpNextElement);

		// iterate over all elements of the formula
		tmpNextElement = tmpFormula.getNextElement();
		while (tmpNextElement.getMathType() != MathType.END_OF_TERM) {

			// is the next element an Operand??
			if (tmpNextElement instanceof Operand) {
				tmpTree = this.InsertOperandIntoTree(tmpTree,(Operand) tmpNextElement);
			} 
			else if (tmpNextElement instanceof Operator) {
				tmpTree = this.InsertOperatorIntoTree(tmpTree,(Operator) tmpNextElement);
			}
		}

		return tmpTree;
	}
}
