package calculator.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.elements.MathObj;
import calculator.elements.NumberObj;
import calculator.elements.Operator;
import calculator.elements.OperatorType;
import calculator.elements.Variable;
import calculator.exceptions.IllegalInputStreamException;

/**
 */
public class MathUtil {

	/**
	 * Priority for addition operator
	 */
	public static final int PRIO_ADDITION = 1;
	/**
	 * Priority for multiplication operator
	 */
	public static final int PRIO_MULTIPLICATION = 3;
	/**
	 * Priority for functions
	 */
	public static final int PRIO_FUNCTIONS = 5;
	/**
	 * Priority for brackets
	 */
	public static final int PRIO_BRACKETS = 7;
	/**
	 * Priority for operands
	 */
	public static final int PRIO_OPERANDS = 9;
	/**
	 * Priority for power ^
	 */
	public static final int PRIO_POW = 4;

	private static char COMMA = '.';

	/**
	 * @param aChar
	 * @return true if char is a number (0 - 9)
	 */
	public static boolean isNumber(char aChar) {
		return (aChar >= '0' && aChar <= '9');
	}

	/**
	 * Checks if a string is numeric
	 * 
	 * @param aString
	 * @return true if the given string is numeric only
	 */
	public static boolean isDouble(String aString) {

		return aString.matches("\\-?([1-9][0-9]*|0)(\\.[0-9]+)?");
	}

	/**
	 * 
	 * @param aChar
	 * @return
	 */
	public static boolean isFunction(char aChar) {
		String tmpString = Character.toString(aChar);

		return tmpString.matches("[%~#&]");
	}

	/**
	 * @param aCharacter
	 * @return boolean true if character is a variable
	 */
	public static boolean isVariable(char aCharacter) {
		Pattern tmpPattern = Pattern.compile("[a-z]");
		Matcher tmpMatcher = tmpPattern.matcher(Character.toString(aCharacter).toLowerCase());
		if (tmpMatcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param aCharacter
	 * @return true if char is a number or variable
	 */
	public static boolean isNumberOrVariable(char aCharacter) {
		return (isNumber(aCharacter) || isVariable(aCharacter));
	}

	/**
	 * @param aChar
	 * @return true if char is a comma
	 */
	public static boolean IsComma(char aChar) {
		return (aChar == COMMA);
	}

	/**
	 * 
	 * @param aChar
	 * @return true if char is a minus
	 */
	public static boolean IsMinus(char aChar) {
		return (aChar == '-');
	}

	/**
	 * @param aChar
	 * @return true if char is operator
	 */
	public static boolean IsOperator(char aChar) {
		switch (aChar) {
		case '+':
		case '-':
		case '*':
		case '/':
		case '%':
		case '#':
		case '^':
		case '~':
		case '&':
			return true;
		default:
			return false;
		}
	}

	/**
	 * 
	 * @param aChar
	 * @return true if char is left bracket
	 */
	public static boolean IsLeftBracket(char aChar) {
		return (aChar == '(');
	}

	/**
	 * 
	 * @param aChar
	 * @return true if char is a right bracket
	 */
	public static boolean IsRightBracket(char aChar) {
		return (aChar == ')');
	}

	/**
	 * method creates a float value based mathobj out of a given string
	 * 
	 * @param aNumberContainingString
	 * @return MathObj
	 */
	public static MathObj buildNumberMathObject(String aNumberContainingString) {
		Double tmpFl; // Double tmpFl = new Double("0.0");
		tmpFl = Double.valueOf("0.0").doubleValue();
		tmpFl = Double.valueOf(aNumberContainingString).doubleValue();

		NumberObj tmpNumberObj = new NumberObj(tmpFl);
		return tmpNumberObj;
	}

	/**
	 * method creates a operator mathobj out of a given string
	 * 
	 * @param aOperatorContainingString
	 * @return MathObj
	 */
	public static MathObj buildOperatorMathObject(String aOperatorContainingString) {
		OperatorType tmpOpType;
		if (aOperatorContainingString.equals("-")) {
			tmpOpType = OperatorType.SUBTRACTION;
		} else if (aOperatorContainingString.equals("/")) {
			tmpOpType = OperatorType.DIVISION;
		} else if (aOperatorContainingString.equals("*")) {
			tmpOpType = OperatorType.MULTIPLICATION;
		} else if (aOperatorContainingString.equals("%")) {
			tmpOpType = OperatorType.SIN;
		} else if (aOperatorContainingString.equals("~")) {
			tmpOpType = OperatorType.COS;
		} else if (aOperatorContainingString.equals("#")) {
			tmpOpType = OperatorType.TAN;
		} else if (aOperatorContainingString.equals("&")) {
			tmpOpType = OperatorType.SQRT;
		} else if (aOperatorContainingString.equals("^")) {
			tmpOpType = OperatorType.POW;
		} else {
			tmpOpType = OperatorType.ADDITION;
		}
		Operator tmpOp = new Operator(tmpOpType);
		return tmpOp;
	}

	/**
	 * method build a new variable object
	 * 
	 * @param cVariable
	 * @return MathObj
	 */
	public static MathObj buildVariableMathObject(char cVariable) {
		return new Variable(cVariable);
	}

	/**
	 * method creates a mathobj list out of the form string
	 * 
	 * @return ArrayList of mathobj
	 * @param aFormula
	 *            sting which contains a formula containing string
	 */
	public static ArrayList<Object> FormulaToArrayList(String aFormula) {
		ArrayList<Object> MathList = new ArrayList<Object>();

		int iLenOfString;
		iLenOfString = aFormula.length();

		int iStartPosition = 0;
		int iEndPosition = 0;
		int iCounterLeftBracket;

		while (iStartPosition < iLenOfString) {
			if ((MathUtil.isNumber(aFormula.charAt(iStartPosition)))) {
				iEndPosition = iStartPosition + 1;
				while ((iEndPosition < iLenOfString)
						&& ((MathUtil.isNumber(aFormula.charAt(iEndPosition))) || (MathUtil.IsComma(aFormula
								.charAt(iEndPosition))))) {
					iEndPosition++;
				}
				MathList.add(buildNumberMathObject(aFormula.substring(iStartPosition, iEndPosition)));
			} else if (MathUtil.IsLeftBracket(aFormula.charAt(iStartPosition))
					&& (MathUtil.IsMinus(aFormula.charAt(iStartPosition + 1)))
					&& (MathUtil.isNumber(aFormula.charAt(iStartPosition + 2)))) {
				iStartPosition++;
				iEndPosition = iStartPosition + 1;
				while ((MathUtil.IsRightBracket(aFormula.charAt(iEndPosition))) == false) {
					iEndPosition++;
				}
				MathList.add(buildNumberMathObject(aFormula.substring(iStartPosition, iEndPosition)));
				iEndPosition++;
			} else if (MathUtil.IsLeftBracket(aFormula.charAt(iStartPosition))) {
				iStartPosition++;
				iCounterLeftBracket = 1;
				iEndPosition = iStartPosition;
				outer: while (true) {
					if (MathUtil.IsLeftBracket(aFormula.charAt(iEndPosition))) {
						iCounterLeftBracket++;
					}
					if (MathUtil.IsRightBracket(aFormula.charAt(iEndPosition))) {
						iCounterLeftBracket--;
						if (iCounterLeftBracket == 0) {
							break outer;
						}
					}
					iEndPosition++;
				}
				MathList.add(MathUtil.FormulaToArrayList(aFormula.substring(iStartPosition, iEndPosition)));
				iEndPosition++;
			} else if (MathUtil.IsOperator(aFormula.charAt(iStartPosition))) {
				iEndPosition = iStartPosition + 1;
				MathList.add(buildOperatorMathObject(aFormula.substring(iStartPosition, iEndPosition)));
			} else if (MathUtil.isVariable(aFormula.charAt(iStartPosition))) {
				iEndPosition = iStartPosition + 1;
				MathList.add(buildVariableMathObject(aFormula.charAt(iStartPosition)));
			} else {
				System.out.println(aFormula.charAt(iStartPosition));
				throw new IllegalInputStreamException("Input String contains non valid characters!");
			}
			iStartPosition = iEndPosition;
		}
		return MathList;
	}

}
