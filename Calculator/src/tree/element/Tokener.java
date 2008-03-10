package tree.element;

import java.rmi.UnexpectedException;

import org.omg.CORBA.SystemException;

import math.element.object.Formula;
import math.element.object.MathObj;
import math.element.object.MathType;
import math.element.object.NumberObj;
import math.element.object.Operator;
import math.element.object.Operand;

/**
 * 
 */
public final class Tokener {

	private Tree InsertOperandIntoTree(Tree anExistingTree, Operand anOperand) {

		// check whether there is an operator in the root of the tree
		if (anExistingTree.getRoot().getMathType() == MathType.OPERAND) {

			// this is an error situation, the tree would look like the
			// following: Number (Father), Number(Left Son)
			// Numbers can only be in the leaves of the tree!

			//TODO hat jemand Ahnung von Exception Handling? Wieso muss ich die Lokal abfangen??
			//throw (new Exception("Syntax error"));
		}

		// insert the number to the right of the operand
		anExistingTree.setRightSon(new Tree(anOperand));
		
		return anExistingTree;
	}

	private Tree InsertOperatorIntoTree(Tree anExistingTree, Operator anOperator) {

		Tree tmpHelpTree = null;

		// is the root in the existing tree an operand?
		if (anExistingTree.getRoot().getMathType() == MathType.OPERAND) {

			// if it is, build a new tree with Operator (father), Operand (Left
			// Son)
			tmpHelpTree = new Tree(anOperator, null, anExistingTree, null);
			anExistingTree.setFather(tmpHelpTree);
		}
		// is the root in the existing tree an operator?
		else if (anExistingTree.getRoot().getMathType() == MathType.OPERATOR) {

			// ok, it is.... now we have to compare the priority of the
			// different operators
			Operator tmpExistingOperator = (Operator) anExistingTree.getRoot();

			if (tmpExistingOperator.getOperatorType().getPriority() >= anOperator
					.getOperatorType().getPriority()) {
				// if the existing operator has a higher priority , a new tree
				// is buildt
				// and the existing tree is appended to the left
				tmpHelpTree = new Tree(anOperator, null, anExistingTree, null);
				anExistingTree.setFather(tmpHelpTree);
			} else {
				// ok, now we have more to do... we need to insert the new
				// Operator
				// as the right son of the existing tree. The current right son
				// will become the left so of the new sub-tree
				// TODO Tim, Gibt es plugins für eclipse, so dass man
				// Zeichnungen in den Quelltext einfügen kann als Algorithmus
				// erklärung?
				// Kennst du da was?
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
	 */
	public Tree BuildTree(String aFunction) {

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
			// TODO An unsere java experten: ist es moeglich eine direkte
			// Typ-Prüfung zu machen? z.B. (tmpNextElement is Operand)
			if (tmpNextElement.getMathType() == MathType.OPERAND) {
				tmpTree = this.InsertOperandIntoTree(tmpTree,
						(Operand) tmpNextElement);
			} else if (tmpNextElement.getMathType() == MathType.OPERATOR) {
				tmpTree = this.InsertOperatorIntoTree(tmpTree,
						(Operator) tmpNextElement);
			}
		}
		
		return tmpTree;
	}

}
