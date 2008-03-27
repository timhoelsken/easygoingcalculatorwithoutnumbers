package user.util.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tim, Tobias
 *
 */
public class ConverterUtil {

  /**
   * main for tests
   *
   * @param args
   */
  public static void main(String[] args) {
    String tmpString = "-3432+23";
    System.out.println(termToStandardString(tmpString));
  }

  /**
   * Method makes the parameter formula a standard term (see
   * misc/documents/Standard-String.txt)
   *
   * @param aFormula
   * @return the standard term
   * @throws IllegalArgumentException
   *             if any checks fail
   */
  public static String termToStandardString(String aFormula) throws IllegalArgumentException {

    checkIfValidSignsOnly(aFormula);
    checkIfValidBlanksOnly(aFormula);
    checkIfCommaFollowedByNumber(aFormula);
    aFormula = unifyCommas(aFormula);
    aFormula = removeBlanks(aFormula);
    aFormula = insertMultiplicationOperators(aFormula);
    checkOperators(aFormula);
    aFormula = setBracketsAroundNegativeNumbers(aFormula);
    checkNegativeNumbers(aFormula);
    checkBrackets(aFormula);
    aFormula = changeFunctionsIntoSigns(aFormula);
    return aFormula;
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException if illegal signs in the formula
   */
  public static void checkIfValidSignsOnly(String aFormula) throws IllegalArgumentException {
    Pattern tmpPattern = Pattern.compile("[\\()\\,\\.��\\+\\-\\*/^[0-9] ]*");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);
    if (!tmpMatcher.matches()) {
      throw new IllegalArgumentException("The formula contains invalid signs.");
    }
  }

  /**
   * Checks if there are only valid blanks in the string
   *
   * @param aFormula
   * @throws IllegalArgumentException
   */
  public static void checkIfValidBlanksOnly(String aFormula) throws IllegalArgumentException {
    int tmpPosition = 0;
    while (getNextBlankPosition(aFormula, tmpPosition) != -1) {
      tmpPosition = getNextBlankPosition(aFormula, tmpPosition);
      if (tmpPosition != 0 || tmpPosition != aFormula.length()) {

        if ((tmpPosition - 1 > 0 && isNumericOrVariable(aFormula.charAt(tmpPosition - 1)))
            && (tmpPosition + 1 <= aFormula.length() && isNumericOrVariable(aFormula.charAt(tmpPosition - 1)))) {
          throw new IllegalArgumentException("The formula contains invalid blanks.");
        }
      }
    }
  }

  /**
   * Replaces all commas (,) of a string with full-stops (.)
   *
   * @param aFormula
   * @return a string containing .
   */
  public static String unifyCommas(String aFormula) {
    return aFormula.replace(',', '.');
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException if illegal commas in the formula
   */
  public static void checkIfCommaFollowedByNumber(String aFormula) throws IllegalArgumentException {
    // TODO @Tim :) implement this
  }

  /**
   * @param aString
   * @return aString without blanks
   */
  public static String removeBlanks(String aString) {
    return aString.replaceAll(" +", "");
  }

  /**
   * A method to clean the variables in aFormula. Variables "ab" will be
   * replaced with "a*b" "2a" will be replaced with "2*a"
   *
   * @param aFormula
   * @return a String that contains no "ab" or "2a" variables
   */
  public static String insertMultiplicationOperators(String aFormula) {

    String tmpOutput = new String("");

    for (int i = 0; i < aFormula.length(); i++) {
      tmpOutput += aFormula.charAt(i);
      if (isVariable(aFormula.charAt(i)) && (i + 1 < aFormula.length() && isVariable(aFormula.charAt(i + 1)))) {
        tmpOutput += "*";
      } else if (isNumeric(aFormula.charAt(i))
          && (i + 1 < aFormula.length() && isVariable(aFormula.charAt(i + 1)))) {
        tmpOutput += "*";
      }
    }
    return tmpOutput;
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException
   *             if there are operators directly beside each other like "*-",
   *             "+/" or "* /" ...
   * @author Tobias
   */
  public static void checkOperators(String aFormula) throws IllegalArgumentException {
    Pattern tmpPattern = Pattern.compile("[\\+\\-\\*/^] *[\\+\\-\\*/^]");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);
    if (tmpMatcher.find()) {
      throw new IllegalArgumentException("The order of operators in the formula is not correct.");
    }
  }

  /**
   * sets brackets around negative numbers at the beginning of the formular or
   * at the beginning of brackets
   *
   * makes -3*2*(-5*6) look like (-3)*2*((-5)*6)
   *
   * @param aFormula
   * @return the bracked formula
   */
  // TODO finalize and call this method :P
  public static String setBracketsAroundNegativeNumbers(String aFormula) {
    if (aFormula.charAt(0) == '-') {
      // TODO regard brackets?!!!!! :-!
      // bis jetzt behandelt die Methode nur die Minuszahl am Anfang der Formel
      int i = 1;
      Matcher tmpMatcher;
      do {
        Pattern tmpPattern = Pattern.compile("[0-9\\.]");
        if (i == aFormula.length()) {
          tmpMatcher = null;
        } else {
          tmpMatcher = tmpPattern.matcher(Character.toString(aFormula.charAt(i++)));
        }
      } while (tmpMatcher != null && tmpMatcher.find());

      aFormula = "(" + aFormula.substring(0, --i) + ")" + aFormula.substring(i);
    }

    return aFormula;
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException
   *             if not all negative numbers are in brackets
   */
  public static void checkNegativeNumbers(String aFormula) throws IllegalArgumentException {
    for (int i = 1; i < aFormula.length(); i++) {
      if (aFormula.charAt(i) == '-') {
        Pattern tmpPattern = Pattern.compile("[\\(0-9].*");
        Matcher tmpMatcher = tmpPattern.matcher(aFormula.substring(i - 1));
        if (!tmpMatcher.matches()) {
          throw new IllegalArgumentException("Some negative numbers are not in brackets.");
        }
      }
    }
  }

  /**
   * checks if there is the same amount of ( and ) brackets, and if no ) are in
   * lead of ( , that means not more than there should be
   *
   * @param aFormula
   * @throws IllegalArgumentException
   *             if the brackets in the term are not correct
   */
  public static void checkBrackets(String aFormula) throws IllegalArgumentException {

    int tmpSum = 0;
    for (int i = 0; i < aFormula.length(); i++) {
      if (aFormula.charAt(i) == '(')
        ++tmpSum;
      if (aFormula.charAt(i) == ')')
        --tmpSum;
      if (tmpSum < 0) {
        throw new IllegalArgumentException("Wrong brackets in the formula.");
      }
    }
  }

  /**
   * Replaces sin, cos, tan, sqrt functions with abbreviation signs
   *
   * @param aFormula
   * @return a string containing abbreviation sign, defined in
   *         Standard-String.txt
   */
  public static String changeFunctionsIntoSigns(String aFormula) {

    String tmpOutput = new String("");
    int tmpFunctionFound = 0;

    for (int i = 0; i < aFormula.length(); i++) {
      if (aFormula.charAt(i) == 's') {
        if (aFormula.charAt(i + 1) == 'i' && aFormula.charAt(i + 2) == 'n' && aFormula.charAt(i + 3) == '(') {
          tmpOutput += "%";
          tmpFunctionFound = 3;
        } else if (aFormula.charAt(i + 1) == 'q' && aFormula.charAt(i + 2) == 'r'
            && aFormula.charAt(i + 3) == 't' && aFormula.charAt(i + 4) == '(') {
          tmpOutput += "&";
          tmpFunctionFound = 4;
        } else {
          tmpOutput += aFormula.charAt(i);
          tmpFunctionFound = 0;
        }
      } else if (aFormula.charAt(i) == 'c') {
        if (aFormula.charAt(i + 1) == 'o' && aFormula.charAt(i + 2) == 's' && aFormula.charAt(i + 3) == '(') {
          tmpOutput += "~";
          tmpFunctionFound = 3;
        } else {
          tmpOutput += aFormula.charAt(i);
          tmpFunctionFound = 0;
        }
      } else if (aFormula.charAt(i) == 't') {
        if (aFormula.charAt(i + 1) == 'a' && aFormula.charAt(i + 2) == 'n' && aFormula.charAt(i + 3) == '(') {
          tmpOutput += "#";
          tmpFunctionFound = 3;
        } else {
          tmpOutput += aFormula.charAt(i);
          tmpFunctionFound = 0;
        }
      } else if (aFormula.charAt(i) == 'w') {
        if (aFormula.charAt(i + 1) == 'u' && aFormula.charAt(i + 2) == 'r' && aFormula.charAt(i + 3) == 'z'
            && aFormula.charAt(i + 4) == 'e' && aFormula.charAt(i + 5) == 'l' && aFormula.charAt(i + 6) == '(') {
          tmpOutput += "&";
          tmpFunctionFound = 6;
        } else {
          tmpOutput += aFormula.charAt(i);
          tmpFunctionFound = 0;
        }
      } else {
        tmpOutput += aFormula.charAt(i);
        tmpFunctionFound = 0;
      }
      i += tmpFunctionFound;
      tmpFunctionFound = 0;
    }

    return tmpOutput;
  }

  /**
   * gets the position of the next blank in a string
   *
   * @param aFormula
   * @param aStartPosition
   * @return The position of the next blank in the given String, returns -1 if
   *         no blank is found
   */
  private static int getNextBlankPosition(String aFormula, int aStartPosition) {
    int tmpPosition = -1;

    for (int i = 0; i < aFormula.length(); i++) {
      if (aFormula.charAt(i) == ' ') {
        tmpPosition = i;
        i = aFormula.length();
      }
    }

    return tmpPosition;
  }

  // TODO @Tim isNumericOrVariable + Untermethoden in MathUtil ausgliedern?!
  // (gibt's da auch schon teilweise)
  private static boolean isNumericOrVariable(char aCharacter) {
    return (isNumeric(aCharacter) || isVariable(aCharacter)) ? true : false;
  }

  private static boolean isNumeric(char aCharacter) {
    Pattern tmpPattern = Pattern.compile("[0-9]");
    Matcher tmpMatcher = tmpPattern.matcher(Character.toString(aCharacter));
    if (tmpMatcher.find()) {
      return true;
    }
    return false;
  }

  private static boolean isVariable(char aCharacter) {
    Pattern tmpPattern = Pattern.compile("[a-z]");
    Matcher tmpMatcher = tmpPattern.matcher(Character.toString(aCharacter).toLowerCase());
    if (tmpMatcher.find()) {
      return true;
    }
    return false;
  }
}