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

	/**
	 * Calculates the result of a formula-tree
	 * @param aTree the tree from which the result should be calculated
	 * @return the result of the tree is returned as float value
	 * @throws Exception It is not possible to calculate the tree. An internal error occurred.
	 */
	public static float EvaluateTree(Tree aTree) throws Exception {
		//no tree? no calculation!
		if (aTree == null)	return 0;

		float tmpResult = 0;

		//is it a tree where there is only a number at the root?
		if (aTree.getRoot() instanceof NumberObj) {
			tmpResult = ((NumberObj) aTree.getRoot()).getValue();
		} 
		else if (aTree.getRoot() instanceof Operator){ 
			
			Operator tmpOperator = (Operator) aTree.getRoot();
			
			if (tmpOperator.getOperatorType() == OperatorType.ADDITION) {				
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())	+ FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.DIVISION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())	/ FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.MULTIPLICATION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())	* FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.SUBTRACTION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon()) - FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			}
		}
		else throw (new Exception("Not possible to calculate the formula-tree."));

		return tmpResult;
	}
	


	/**
	 * Inserts an operand into an existing tree
	 * @param anExistingTree 
	 * @param anOperand
	 * @return the tree is returned with the focus on the father-operator of the inserted operand
	 * @throws Exception
	 */
	private static Tree InsertOperandIntoTree(Tree anExistingTree, Operand anOperand) throws Exception {

		// insert the number to the right of the operand
		Tree tmpSon = new Tree(anOperand);
		tmpSon.setFather(anExistingTree);
		
		if (anExistingTree!=null)
		{
			anExistingTree.setRightSon(tmpSon);
			return anExistingTree;
		}
		else
		{
			return tmpSon;
		}		
	}

	/**
	 * Inserts an operator into an existing tree
	 * @param anExistingTree
	 * @param anOperator
	 * @return the tree is returned with the focus on the new operator
	 */
	private static Tree InsertOperatorIntoTree(Tree anExistingTree, Operator anOperator) {

		Tree tmpHelpTree = null;

		assert(anExistingTree!=null);	
		
		// is the root in the existing tree an operand?		
		if (anExistingTree.getRoot() instanceof Operand) {

			// if it is, build a new tree with Operator (father), Operand (Left Son)
			tmpHelpTree = new Tree(anOperator,anExistingTree.getFather(), anExistingTree, null);
			anExistingTree.setFather(tmpHelpTree);
		}
		// is the root in the existing tree an operator?
		else if (anExistingTree.getRoot() instanceof Operator) {

			// ok, it is.... now we have to compare the priority of the
			// different operators
			Operator tmpExistingOperator = (Operator) anExistingTree.getRoot();

			if (tmpExistingOperator.getPriority() >= anOperator.getPriority()) {

				// if the existing operator has a lower priority , a new tree
				// is built and the existing tree is appended to the left			

				tmpHelpTree = new Tree(anOperator,anExistingTree.getFather(), anExistingTree, null);
				anExistingTree.setFather(tmpHelpTree);
			} else {
				// ok, now we have more to do... we need to insert the new
				// Operator
				// as the right son of the existing tree. The current right son
				// will become the left son of the new sub-tree
				tmpHelpTree = new Tree(anOperator, anExistingTree,anExistingTree.getRightSon(), null);
				anExistingTree.setRightSon(tmpHelpTree);
			}
		}
		return tmpHelpTree;
	}

	/**t
	 * Builds a binary formula tree :-)
	 * 
	 * @param aFunction
	 * @throws Exception 
	 */
	public static Tree BuildTree(String aFunction) throws Exception {

		// prepare formula, mathobj and tmptree
		Formula tmpFormula = new Formula(aFunction);
		MathObj tmpNextElement = null;
		Tree tmpTree = null;		

		// iterate over all elements of the formula
		tmpFormula.setStartElement();
		tmpNextElement = tmpFormula.getNextElement();
		while (tmpNextElement.getMathType() != MathType.END_OF_TERM) {

			// is the next element an Operand??
			if (tmpNextElement instanceof Operand) {
				tmpTree = InsertOperandIntoTree(tmpTree,(Operand) tmpNextElement);
			} 
			else if (tmpNextElement instanceof Operator) {
				tmpTree = InsertOperatorIntoTree(tmpTree,(Operator) tmpNextElement);
			}
			
			tmpNextElement = tmpFormula.getNextElement();
		}
		
		//go to the oldest father
		while (tmpTree!=null && tmpTree.getFather()!=null)
		{
			tmpTree = tmpTree.getFather();
		}
		//5*3*2+6+5*2-30*2+8*2/3*4-14
		return tmpTree;
	}
}
