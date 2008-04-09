package calculator.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.elements.MathObj;
import calculator.elements.NumberObj;
import calculator.elements.Operator;
import calculator.elements.OperatorType;
import calculator.exceptions.IllegalInputStreamException;

/**
 * @author Tobias
 *
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
  public static boolean isFloat(String aString) {

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
    return (isNumber(aCharacter) || isVariable(aCharacter)) ? true : false;
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
      case '?':
      case '~':
      case '&':
        // TODO @info for andre: ich hab hier noch die sqrt & hinzugefügt, ist
        // das ok?
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
   *
   * @param aChar
   * @return true if char is a bracket
   */
  public static boolean IsBracket(char aChar) {
    return (IsRightBracket(aChar) ? true : IsLeftBracket(aChar));
  }

  /**
   * method creates a float value based mathobj out of a given string
   *
   * @author André
   * @param aNumberContainingString
   * @return MathObj
   */
  public static MathObj buildNumberMathObject(String aNumberContainingString) {
    Double tmpFl;
    tmpFl = Double.valueOf("0.0").doubleValue();
    try {
      tmpFl = Double.valueOf(aNumberContainingString).doubleValue();
    } catch (NumberFormatException e) {
    }

    NumberObj tmpNumberObj = new NumberObj(tmpFl);
    return tmpNumberObj;
  }

  /**
   * method creates a operator mathobj out of a given string
   *
   * @author André
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
    } else {
      tmpOpType = OperatorType.ADDITION;
    }
    Operator tmpOp = new Operator(tmpOpType);
    return tmpOp;
  }

  /**
   * method creates a mathobj list out of the form string
   *
   * @author André
   * @return ArrayList of mathobj
   * @param aFormula
   *            sting which contains a formula containing string
   * @todo Andre: Add support for negative numbers and brackets
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
        try {
          MathList.add(buildNumberMathObject(aFormula.substring(iStartPosition, iEndPosition)));
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else if (MathUtil.IsLeftBracket(aFormula.charAt(iStartPosition))
          && (MathUtil.IsMinus(aFormula.charAt(iStartPosition + 1)))) {
        iStartPosition++;
        iEndPosition = iStartPosition + 1;
        while ((MathUtil.IsRightBracket(aFormula.charAt(iEndPosition))) == false) {
          iEndPosition++;
        }
        try {
          MathList.add(buildNumberMathObject(aFormula.substring(iStartPosition, iEndPosition)));
        } catch (Exception e) {
          e.printStackTrace();
        }
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
        try {
          MathList.add(MathUtil.FormulaToArrayList(aFormula.substring(iStartPosition, iEndPosition)));
        } catch (Exception e) {
          e.printStackTrace();
        }
        iEndPosition++;
      } else if (MathUtil.IsOperator(aFormula.charAt(iStartPosition))) {
        iEndPosition = iStartPosition + 1;
        try {
          MathList.add(buildOperatorMathObject(aFormula.substring(iStartPosition, iEndPosition)));
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        System.out.println(aFormula.charAt(iStartPosition));
        throw new IllegalInputStreamException("Input String contains non valid characters!");
      }
      iStartPosition = iEndPosition;
    }
    return MathList;
  }

}
