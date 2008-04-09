package calculator.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Tim, Tobias
 * 
 */
public class ConverterUtil {

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
    checkIfEmptyBrackets(aFormula);
    aFormula = removeBlanks(aFormula);
    aFormula = unifyCommas(aFormula);
    aFormula = changeFunctionsIntoSigns(aFormula);
    aFormula = insertMultiplicationOperators(aFormula);
    checkDecimalNumbers(aFormula);
    aFormula = checkNegativeNumbers(aFormula);
    checkOperators(aFormula);
    checkBrackets(aFormula);

    return aFormula;
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException
   *             if illegal signs in the formula
   */
  public static void checkIfValidSignsOnly(String aFormula) throws IllegalArgumentException {
    Pattern tmpPattern = Pattern.compile("[\\(\\)\\,\\.\\+\\-\\*/\\^\\w ]*");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);
    if (!tmpMatcher.matches()) {
      throw new IllegalArgumentException("The formula contains invalid signs.");
    }
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException
   *            if empty brackets in the formula
   */
  public static void checkIfEmptyBrackets(String aFormula) throws IllegalArgumentException {
    Pattern tmpPattern = Pattern.compile("\\(\\)");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);
    if (tmpMatcher.find()) {
      throw new IllegalArgumentException("The formula contains empty brackets.");
    }

  }

  /**
   * Checks if there are only valid blanks in the string
   * 
   * @param aFormula
   * @throws IllegalArgumentException
   */
  /*
   * public static void checkIfValidBlanksOnly(String aFormula) throws
   * IllegalArgumentException { int tmpPosition = 0; while
   * (getNextBlankPosition(aFormula, tmpPosition) != -1) { tmpPosition =
   * getNextBlankPosition(aFormula, tmpPosition); if (tmpPosition != 0 ||
   * tmpPosition != aFormula.length()) {
   * 
   * if ((tmpPosition - 1 > -1 &&
   * MathUtil.isNumberOrVariable(aFormula.charAt(tmpPosition - 1))) &&
   * (tmpPosition + 1 <= aFormula.length() &&
   * MathUtil.isNumberOrVariable(aFormula .charAt(tmpPosition - 1)))) { throw
   * new IllegalArgumentException("The formula contains invalid blanks."); } } } }
   */

  /**
   * @param aString
   * @return aString without blanks
   */
  public static String removeBlanks(String aString) {
    // + is part of a regular expression. it has nothin to do with the char '+'
    return aString.replaceAll(" +", "");
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
      if (MathUtil.isVariable(aFormula.charAt(i))
          && (i + 1 < aFormula.length() && (MathUtil.isNumberOrVariable(aFormula.charAt(i + 1))
              || aFormula.charAt(i + 1) == '(' || MathUtil.isFunction(aFormula.charAt(i + 1))))) {
        tmpOutput += "*";
      } else if (MathUtil.isNumber(aFormula.charAt(i))
          && (i + 1 < aFormula.length() && (MathUtil.isVariable(aFormula.charAt(i + 1))
              || aFormula.charAt(i + 1) == '(' || MathUtil.isFunction(aFormula.charAt(i + 1))))) {
        tmpOutput += "*";
      }
    }
    return tmpOutput;
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException
   *             if illegal commas in the formula, for example '3.45.34' or
   *             '32.'
   */
  public static void checkDecimalNumbers(String aFormula) throws IllegalArgumentException {

    if (aFormula.contains(".")) {

      int tmpCommaPosition = 0;
      String tmpCopyOfFormula = aFormula;

      tmpCommaPosition = tmpCopyOfFormula.indexOf(".");

      if (tmpCommaPosition != 0) {

        while (tmpCommaPosition != -1) {
          if (!MathUtil.isNumber(tmpCopyOfFormula.charAt(tmpCommaPosition - 1))) {
            throw new IllegalArgumentException("The formula contains invalid commas.");
          }
          int i = tmpCommaPosition + 1;
          while (i < tmpCopyOfFormula.length() - 1 && MathUtil.isNumber(tmpCopyOfFormula.charAt(i))) {
            i++;
          }
          if (tmpCopyOfFormula.charAt(i) == '.') {
            throw new IllegalArgumentException("The formula contains invalid commas.");
          }
          tmpCopyOfFormula = tmpCopyOfFormula.substring(tmpCommaPosition + 1, tmpCopyOfFormula.length());
          tmpCommaPosition = tmpCopyOfFormula.indexOf(".");
        }
      } else {
        throw new IllegalArgumentException("The formula contains invalid commas.");
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

    aFormula = aFormula.replace("sin(", "%(");
    aFormula = aFormula.replace("cos(", "~(");
    aFormula = aFormula.replace("tan(", "#(");
    aFormula = aFormula.replace("sqrt(", "&(");

    return aFormula;
  }

  /**
   * @param aFormula
   * @throws IllegalArgumentException
   *             if there are operators directly beside each other like "*-",
   *             "+/", "+)" or "* /" ...
   * @author Tobias
   */
  public static void checkOperators(String aFormula) throws IllegalArgumentException {
    // cases "- ( ..."
    if (aFormula.length() > 1 && aFormula.charAt(1) == '(') {
      Pattern tmpPattern = Pattern.compile("[\\+\\-\\*/\\^]");
      if (tmpPattern.matcher(Character.toString(aFormula.charAt(0))).find()) {
        throw new IllegalArgumentException(
            "Do not let a bracket follow an alone standing arithmetic operator.");
      }
    }
    // cases "... +|-|*|/|^"
    Pattern tmpSingleOperatorPattern = Pattern.compile("[\\+\\-\\*/\\^]");
    String tmpLastSignInFormular = Character.toString(aFormula.charAt(aFormula.length() - 1));
    Matcher tmpSingleOperatorMatcher = tmpSingleOperatorPattern.matcher(tmpLastSignInFormular);
    if (tmpSingleOperatorMatcher.find()) {
      throw new IllegalArgumentException("The formula ends with an operator.");
    }
    // cases "+-", "*/" ...
    Pattern tmpPattern = Pattern.compile("[\\+\\-\\*/\\^] *[\\+\\-\\*/\\^\\)]");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);
    if (tmpMatcher.find()) {
      throw new IllegalArgumentException("The order of operators in the formula is not correct.");
    }
    // cases "(+", "(*" ...
    tmpPattern = Pattern.compile("\\([\\+\\*/\\^]");
    tmpMatcher = tmpPattern.matcher(aFormula);
    if (tmpMatcher.find()) {
      throw new IllegalArgumentException("Do not let an alone standing arithmetic operator follow a bracket.");
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
   * @author Tobias
   */
  public static String setBracketsAroundNegatives(String aFormula) {

    if (aFormula.length() > 0) {
      // check negative number at the beginning
      if (aFormula.charAt(0) == '-') {
        aFormula = setBracketsAroundNegative(aFormula, 0);
      }
    }

    // check negative numbers at the beginning of brackets
    int i = 0;
    int newBracketsCounter = 0;
    Pattern tmpPattern = Pattern.compile("[%~#&]\\(\\-[\\w\\.]+\\)");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);
    while (tmpMatcher.find(i)) {
      int tmpStart = tmpMatcher.start() + 1;
      aFormula = setBracketsAroundNegative(aFormula, ++tmpStart + newBracketsCounter);
      newBracketsCounter = newBracketsCounter + 2;
      i = tmpStart;
    }

    i = 0;
    newBracketsCounter = 0;

    tmpPattern = Pattern.compile("\\(\\-[\\w\\.]+[^\\)\\w]");
    tmpMatcher = tmpPattern.matcher(aFormula);
    while (tmpMatcher.find(i)) {
      int tmpStart = tmpMatcher.start();
      aFormula = setBracketsAroundNegative(aFormula, ++tmpStart + newBracketsCounter);
      newBracketsCounter = newBracketsCounter + 2;
      i = tmpStart;
    }

    return aFormula;
  }

  /**
   * @param aFormula
   * @param startIndex
   *            index of the minus sign '-'
   * @return the formula with brackets around the number beginning at the
   *         startIndex
   */
  private static String setBracketsAroundNegative(String aFormula, int aStartIndex) {
    int i = aStartIndex + 1;
    Matcher tmpMatcher;
    do {
      Pattern tmpPattern = Pattern.compile("[\\w\\.]");
      if (i == aFormula.length()) {
        tmpMatcher = null;
      } else {
        tmpMatcher = tmpPattern.matcher(Character.toString(aFormula.charAt(i++)));
      }
    } while (tmpMatcher != null && tmpMatcher.find());

    aFormula = aFormula.substring(0, aStartIndex) + "(" + aFormula.substring(aStartIndex, --i) + ")"
        + aFormula.substring(i);
    return aFormula;
  }

  /**
   * if there is a negative number at the beginning of the formula it has to be
   * in brackets to make this method work!
   * 
   * @param aFormula
   * @return the bracked formula
   * @throws IllegalArgumentException
   *             if not all negative numbers are in brackets
   */
  public static String checkNegativeNumbers(String aFormula) throws IllegalArgumentException {

    aFormula = setBracketsAroundNegatives(aFormula);

    for (int i = 1; i < aFormula.length(); i++) {
      if (aFormula.charAt(i) == '-') {
        Pattern tmpPattern = Pattern.compile("[\\(\\)\\w]");
        Matcher tmpMatcher = tmpPattern.matcher(Character.toString(aFormula.charAt(i - 1)));
        if (!tmpMatcher.matches()) {
          throw new IllegalArgumentException("Some negative operands are not in brackets.");
        }
      }
    }

    return aFormula;
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
        throw new IllegalArgumentException("Wrong bracket order in the formula.");
      }
    }
    if (tmpSum != 0) {
      throw new IllegalArgumentException("Missing closing brackets in the formula.");
    }
  }

  /**
   * gets the position of the next blank in a string
   * 
   * @param aFormula
   * @param aStartPosition
   * @return The position of the next blank in the given String, returns -1 if
   *         no blank is found
   */
  public static int getNextBlankPosition(String aFormula, int aStartPosition) {
    int tmpPosition = -1;

    if (aStartPosition != 0) {
      aStartPosition++;
    }

    for (int i = aStartPosition; i < aFormula.length(); i++) {
      if (aFormula.charAt(i) == ' ') {
        tmpPosition = i;
        i = aFormula.length();
      }
    }

    return tmpPosition;
  }

  /**
   * paints a string in the center of some spaces, needed to paint the tree in
   * the console
   * 
   * @param someSpaces
   * @param aString
   * @return a string centered in the given spaces
   */
  public static String centerStringInSpaces(String someSpaces, String aString) {
    String tmpReturnString = new String();
    int tmpSpaceLength = someSpaces.length();
    int tmpStringLength = aString.length();
    if (tmpSpaceLength <= tmpStringLength) {
      return aString;
    }
    int tmpStart = tmpSpaceLength / 2 - tmpStringLength / 2;
    tmpReturnString = someSpaces.substring(0, tmpStart);
    tmpReturnString += aString;
    for (int i = tmpReturnString.length() + 1; i < tmpSpaceLength; i++) {
      tmpReturnString += " ";
    }
    return tmpReturnString;
  }

  /**
   * Checks if a formula has variables
   * 
   * @param aFormula
   * @return true if a formula has variables
   */
  public static boolean hasVariables(String aFormula) {

    Pattern tmpPattern = Pattern.compile("[A-Za-z]");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);

    return tmpMatcher.find();
  }

  /**
   * Finds all variables in the given formula string
   * 
   * @param aFormula
   * @return a String[] ArrayList of variables. [0] is the variable, [1] is the
   *         variable's value
   */
  public static ArrayList<String[]> getVariables(String aFormula) {

    ArrayList<String[]> tmpVariableList = new ArrayList<String[]>();

    Pattern tmpPattern = Pattern.compile("[A-Za-z]");
    Matcher tmpMatcher = tmpPattern.matcher(aFormula);

    while (tmpMatcher.find()) {
      // Bei einem Hashtable kann kein null wert gesetzt werden, daher ist
      // jede Variable am Anfang = 0
      String[] tmpStringArray = new String[2];
      tmpStringArray[0] = tmpMatcher.group();
      tmpStringArray[1] = null;
      tmpVariableList.add(tmpStringArray);
    }

    return tmpVariableList;
  }

  /**
   * Puts a String[] ArrayList into a Hashtable<String, Double>
   * 
   * @param aListOfVariables
   * @return a Hashtable<String, Double>, where key is the variable and value
   *         is the variables value
   */
  public static Hashtable<String, Double> putArrayListIntoHashtable(ArrayList<String[]> aListOfVariables) {
    Hashtable<String, Double> tmpVariableDictionary = new Hashtable<String, Double>();

    for (int i = 0; i < aListOfVariables.size(); i++) {
      tmpVariableDictionary.put(aListOfVariables.get(i)[0], Double.parseDouble(aListOfVariables.get(i)[1]));
    }

    return tmpVariableDictionary;
  }
}
