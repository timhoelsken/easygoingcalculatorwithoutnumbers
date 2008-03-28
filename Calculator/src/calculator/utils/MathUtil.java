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
  private static char COMMA = '.';

  /**
   * @param aChar
   * @return true if char is a number (0 - 9)
   */
  public static boolean isNumber(char aChar) {
    return (aChar >= '0' && aChar <= '9');
  }

  /**
   * 
   * @param aCharacter
   * @return true if character is a variable
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
   * @param aFunction
   * @param aCharPos
   * @return the next mathobject of the function string
   */
  public static MathObj getNextMathObj(String aFunction, Integer aCharPos) {
    if (aCharPos >= aFunction.length())
      return null;

    char tmpChar = aFunction.charAt(aCharPos);

    if (isNumber(tmpChar)) {
      return getNextNumber(aFunction, aCharPos);
    }
    // else (IsOperator(c))
    // {
    //
    // }
    return null;
  }

  /**
   * @param aFunction
   * @param aCharPos
   * @return the next number object of the function string
   */
  public static NumberObj getNextNumber(String aFunction, Integer aCharPos) {
    if (aCharPos >= aFunction.length()) {
      return null;
    }

    String tmpResult = new String("");
    int tmpCommaCount = 0;
    while (aCharPos < aFunction.length()) {
      char tmpChar = aFunction.charAt(aCharPos);
      if (isNumber(tmpChar)) {
        tmpResult += tmpChar;
      } else if (IsComma(tmpChar)) {
        tmpCommaCount++;
        if (tmpCommaCount == 1)
          tmpResult += ".";
        else
          break;
      } else
        break;
      aCharPos++;
    }

    return new NumberObj(Float.parseFloat(tmpResult));
  }

  /**
   * method creates a float value based mathobj out of a given string
   * 
   * @author André
   * @param aNumberContainingString
   * @return MathObj
   */
  public static MathObj buildNumberMathObject(String aNumberContainingString) {
    Float tmpFl;
    tmpFl = Float.valueOf("0.0").floatValue();
    try {
      tmpFl = Float.valueOf(aNumberContainingString).floatValue();
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
   *          sting which contains a formula containing string
   * @todo Andre: Add support for negative numbers and brackets
   */
  public static ArrayList<Object> FormulaToArrayList(String aFormula) {
    ArrayList<Object> MathList = new ArrayList<Object>();

    int iLenOfString;
    iLenOfString = aFormula.length();

    int iStartPosition = 0;
    int iEndPosition = 0;

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
          System.out.println(e.getMessage());
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
          System.out.println(e.getMessage());
        }
        iEndPosition++;
      } else if (MathUtil.IsLeftBracket(aFormula.charAt(iStartPosition))) {
        iStartPosition++;
        iEndPosition = iStartPosition + 1;
        while ((MathUtil.IsRightBracket(aFormula.charAt(iEndPosition))) == false) {
          iEndPosition++;
        }
        try {
          MathList.add(MathUtil.FormulaToArrayList(aFormula.substring(iStartPosition, iEndPosition)));
        } catch (Exception e) {
          System.out.print(e.getMessage());
        }
        iEndPosition++;
      } else if (MathUtil.IsOperator(aFormula.charAt(iStartPosition))) {
        iEndPosition = iStartPosition + 1;
        while ((iEndPosition < iLenOfString) && (MathUtil.IsOperator(aFormula.charAt(iEndPosition)))) {
          iEndPosition++;
        }
        try {
          MathList.add(buildOperatorMathObject(aFormula.substring(iStartPosition, iEndPosition)));
        } catch (Exception e) {
          System.out.print(e.getMessage());
        }
      } else {
        throw new IllegalInputStreamException("Input String contains non valid characters!");
      }
      iStartPosition = iEndPosition;
    }
    return MathList;
  }

}
