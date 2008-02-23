package pse3;

/**
 * @author Tobias
 *
 */
public class MathUtil {
  private static char COMMA = ',';

  /**
   * @param aChar
   * @return true if char is a number (0 - 9)
   */
  public static boolean IsNumber(char aChar) {
    return (aChar >= '0' && aChar <= '9');
  }

  /**
   * @param aChar
   * @return true if char is a comma
   */
  public static boolean IsComma(char aChar) {
    return (aChar == COMMA);
  }

  /**
   * @param aChar
   * @return true if char is operator
   */
  public static boolean IsOperator(char aChar) {
    // switch (c) {
    // case OperatorType.ADDITION.getOpAsChar():
    // case OperatorType.SUBTRACTION.getOpAsChar():
    // case OperatorType.DIVISION.getOpAsChar():
    // case OperatorType.MULTIPLICATION.getOpAsChar():
    // return true;
    // break;
    // default:
    return false;
    // break;
    // }
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

    if (IsNumber(tmpChar)) {
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
      if (IsNumber(tmpChar)) {
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
}
