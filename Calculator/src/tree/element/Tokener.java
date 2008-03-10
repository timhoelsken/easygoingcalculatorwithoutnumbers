package tree.element;

import math.element.object.Formula;
import math.element.object.MathObj;
import math.element.object.MathType;
import math.element.object.NumberObj;
import math.element.object.Operator;

/**
 * 
 */
public final class Tokener {

	/**
	 * parse the input function
	 * 
	 * @param aFunction
	 */
	public void BuildNiceTree(String aFunction) {

		Formula tmpFormula = new Formula(aFunction); // mathematical formula
		MathObj NextElement = null; // An element of the formula
		Tree tmpTree = null; // Tree

		NextElement = tmpFormula.getNextElement();
		// iterate over all elements of the formula
		while (NextElement.getMathType() != MathType.END_OF_TERM) {

			// for an first approach it is assumed that a formula
			// have only binary operators

			// get first operand
			NextElement = tmpFormula.getNextElement();
			if (NextElement.getMathType() != MathType.OPERAND) {
				throw (new Exception("Syntax Error"));
			}
			Operand tmpOperandOne = NextElement;

			// get operator
			NextElement = tmpFormula.getNextElement();
			if (NextElement.getMathType() != MathType.OPERATOR) {
				throw (new Exception("Syntax Error"));
			}
			Operand tmpOperator = NextElement;

			// get first operand
			NextElement = tmpFormula.getNextElement();
			if (NextElement.getMathType() != MathType.OPERAND) {
				throw (new Exception("Syntax Error"));
			}
			Operand tmpOperandTwo = NextElement;

		}
	}

}
