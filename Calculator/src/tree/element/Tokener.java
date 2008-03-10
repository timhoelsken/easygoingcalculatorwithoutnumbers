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

		// iterate over all elements of the formula
		NextElement = tmpFormula.getNextElement();		
		while (NextElement.getMathType() != MathType.END_OF_TERM) {

			

		}
	}

}
