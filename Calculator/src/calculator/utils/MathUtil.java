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
  public static boolean isComma(char aChar) {
    return (aChar == COMMA);
  }

  /**
   * 
   * @param aChar
   * @return true if char is a minus
   */
  public static boolean isMinus(char aChar) {
    return (aChar == '-');
  }

  /**
   * @param aChar
   * @return true if char is operator
   */
  public static boolean isOperator(char aChar) {
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
  public static boolean isLeftBracket(char aChar) {
    return (aChar == '(');
  }

  /**
   * 
   * @param aChar
   * @return true if char is a right bracket
   */
  public static boolean isRightBracket(char aChar) {
    return (aChar == ')');
  }

  /**
   * method creates a float value based mathobj out of a given string
   * 
   * @param aNumberContainingString
   * @return MathObj
   */
  public static MathObj buildNumberMathObject(String aNumberContainingString) {
    Double tmpDouble = new Double(aNumberContainingString);

    NumberObj tmpNumberObj = new NumberObj(tmpDouble);
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
  public static ArrayList<Object> formulaToArrayList(String aFormula) {
    ArrayList<Object> MathList = new ArrayList<Object>();

    int tmpLenOfString = aFormula.length();

    int tmpStartPosition = 0;
    int tmpEndPosition = 0;

    while (tmpStartPosition < tmpLenOfString) {
      // extract numbers
      if ((MathUtil.isNumber(aFormula.charAt(tmpStartPosition)))) {
        tmpEndPosition = tmpStartPosition + 1;
        while ((tmpEndPosition < tmpLenOfString)
            && ((MathUtil.isNumber(aFormula.charAt(tmpEndPosition))) || (MathUtil.isComma(aFormula
                .charAt(tmpEndPosition))))) {
          tmpEndPosition++;
        }
        MathList.add(buildNumberMathObject(aFormula.substring(tmpStartPosition, tmpEndPosition)));
        // extract negative numbers
      } else if (MathUtil.isLeftBracket(aFormula.charAt(tmpStartPosition))
          && (MathUtil.isMinus(aFormula.charAt(tmpStartPosition + 1)))
          && (MathUtil.isNumber(aFormula.charAt(tmpStartPosition + 2)))) {
        tmpStartPosition++;
        tmpEndPosition = tmpStartPosition + 1;
        while ((MathUtil.isRightBracket(aFormula.charAt(tmpEndPosition))) == false) {
          tmpEndPosition++;
        }
        MathList.add(buildNumberMathObject(aFormula.substring(tmpStartPosition, tmpEndPosition)));
        tmpEndPosition++;
        // extract inner formula (brackets)
      } else if (MathUtil.isLeftBracket(aFormula.charAt(tmpStartPosition))) {
        tmpStartPosition++;
        int tmpCounterLeftBracket = 1;
        tmpEndPosition = tmpStartPosition;
        outer: while (true) {
          if (MathUtil.isLeftBracket(aFormula.charAt(tmpEndPosition))) {
            tmpCounterLeftBracket++;
          }
          if (MathUtil.isRightBracket(aFormula.charAt(tmpEndPosition))) {
            tmpCounterLeftBracket--;
            if (tmpCounterLeftBracket == 0) {
              break outer;
            }
          }
          tmpEndPosition++;
        }
        MathList.add(MathUtil.formulaToArrayList(aFormula.substring(tmpStartPosition, tmpEndPosition)));
        tmpEndPosition++;
        // extract operators (only one sign)
      } else if (MathUtil.isOperator(aFormula.charAt(tmpStartPosition))) {
        tmpEndPosition = tmpStartPosition + 1;
        MathList.add(buildOperatorMathObject(aFormula.substring(tmpStartPosition, tmpEndPosition)));
        // extract variables (only one sign)
      } else if (MathUtil.isVariable(aFormula.charAt(tmpStartPosition))) {
        tmpEndPosition = tmpStartPosition + 1;
        MathList.add(buildVariableMathObject(aFormula.charAt(tmpStartPosition)));
        // should never appear
      } else {
        throw new IllegalInputStreamException("Input String contains non valid characters!");
      }
      tmpStartPosition = tmpEndPosition;
    }
    return MathList;
  }

}
