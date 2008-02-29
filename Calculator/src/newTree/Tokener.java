package newTree;

import math.elements.object.MathUtil;

/**
 *
 */
public final class Tokener {

	/**
	 * parse the input function
	 *
	 * @param aFunction
	 */
	public void ParseFunction(String aFunction) {
		String tmpFunction = aFunction;
//		Tree tmpRoot;

		Integer i = 0;
		while (i < tmpFunction.length()) {
			char tmpChar = tmpFunction.charAt(i); // get first char

			if (MathUtil.IsComma(tmpChar) || MathUtil.IsNumber(tmpChar)) {
				// Root = new Tree(MathUtil.getNextMathObj(i_Funct))
			}
		}

	}
}
