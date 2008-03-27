package user.util.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tim
 *
 */
public class Converter {
  // TODO @Tim&Tobias make methods static
  /**
   * Method that first calls removeBlanks, then cleanVariables
   *
   * @param anInputString
   * @return a standard Term
   * @throws Exception
   */
  public String termToStandardString(String anInputString) throws Exception {

    String tmpOutput = new String("");

    if (!checkValidTerm(anInputString)) {
      // TODO schöner machen
      throw new Exception("Invalid InputString.");
    }
    tmpOutput = removeBlanks(anInputString);
    tmpOutput = insertMultiplicationOperators(tmpOutput);
    tmpOutput = handleCommas(tmpOutput);
    tmpOutput = changeFunctionsIntoSigns(tmpOutput);

    return tmpOutput;
  }

  /**
   * Replaces sin, cos, tan, sqrt functions with abbreviation signs
   *
   * @param anInputString
   * @return a string containing abbreviation sign, defined in
   *         Standard-String.txt
   */
  private String changeFunctionsIntoSigns(String anInputString) {

    String tmpOutput = new String("");
    int tmpFunctionFound = 0;

    for (int i = 0; i < anInputString.length(); i++) {
      if (anInputString.charAt(i) == 's') {
        if (anInputString.charAt(i + 1) == 'i' && anInputString.charAt(i + 2) == 'n'
            && anInputString.charAt(i + 3) == '(') {
          tmpOutput += "%";
          tmpFunctionFound = 3;
        } else if (anInputString.charAt(i + 1) == 'q' && anInputString.charAt(i + 2) == 'r'
            && anInputString.charAt(i + 3) == 't' && anInputString.charAt(i + 4) == '(') {
          tmpOutput += "&";
          tmpFunctionFound = 4;
        } else {
          tmpOutput += anInputString.charAt(i);
          tmpFunctionFound = 0;
        }
      } else if (anInputString.charAt(i) == 'c') {
        if (anInputString.charAt(i + 1) == 'o' && anInputString.charAt(i + 2) == 's'
            && anInputString.charAt(i + 3) == '(') {
          tmpOutput += "~";
          tmpFunctionFound = 3;
        } else {
          tmpOutput += anInputString.charAt(i);
          tmpFunctionFound = 0;
        }
      } else if (anInputString.charAt(i) == 't') {
        if (anInputString.charAt(i + 1) == 'a' && anInputString.charAt(i + 2) == 'n'
            && anInputString.charAt(i + 3) == '(') {
          tmpOutput += "#";
          tmpFunctionFound = 3;
        } else {
          tmpOutput += anInputString.charAt(i);
          tmpFunctionFound = 0;
        }
      } else if (anInputString.charAt(i) == 'w') {
        if (anInputString.charAt(i + 1) == 'u' && anInputString.charAt(i + 2) == 'r'
            && anInputString.charAt(i + 3) == 'z' && anInputString.charAt(i + 4) == 'e'
            && anInputString.charAt(i + 5) == 'l' && anInputString.charAt(i + 6) == '(') {
          tmpOutput += "&";
          tmpFunctionFound = 6;
        } else {
          tmpOutput += anInputString.charAt(i);
          tmpFunctionFound = 0;
        }
      } else {
        tmpOutput += anInputString.charAt(i);
        tmpFunctionFound = 0;
      }
      i += tmpFunctionFound;
      tmpFunctionFound = 0;
    }

    return tmpOutput;
  }

  /**
   * Replaces all commas (,) of a string with full-stops (.)
   *
   * @param anInputString
   * @return a string containing .
   */
  private String handleCommas(String anInputString) {
    return anInputString.replace(',', '.');
  }

  /**
   * @param anInputString
   * @return
   */
  private static boolean checkValidTerm(String anInputString) {
    // TODO @Tim&Tobias Exceptions instead of Boolean return value
    // TODO @Tim&Tobias put checkBrackets behind checkMinusSigns and check order
    // of checks generally
    // TODO @Tim&Tobias check if numbers follow behind commas
    return checkIfOnlyValidBlanks(anInputString) && containsValidSignsOnly(anInputString)
        && checkOperators(anInputString) && checkBrackets(anInputString) && checkMinusSigns(anInputString)
        && doFurtherConsistencyChecks(anInputString);
  }

  /**
   * @param anInputString
   * @return true for all other checks (what do we have to check???)
   */
  private static boolean doFurtherConsistencyChecks(String anInputString) {
    // TODO @all who want to
    return true;
  }

  /**
   * @param anInputString
   * @return true if all negative numbers are in brackets, assure that there are
   *         no blanks in the inputString!!
   * @author Tobias
   */
  private static boolean checkMinusSigns(String anInputString) {
    for (int i = 1; i < anInputString.length(); i++) {
      if (anInputString.charAt(i) == '-') {
        Pattern tmpPattern = Pattern.compile("[\\(0-9].*");
        Matcher tmpMatcher = tmpPattern.matcher(anInputString.substring(i - 1));
        if (!tmpMatcher.matches()) {
          return false;
        }
      }
    }
    return true;
  }

  // TODO finalize and call this method :P
  private void setBracketsAroundNegativeNumbers(String anInputString) {
    if (anInputString.charAt(0) == '-') {
      // TODO regard brackets?! :-!
      int i = 1;
      Matcher tmpMatcher;
      do {
        Pattern tmpPattern = Pattern.compile("[0-9\\.]");
        if (i == anInputString.length()) {
          tmpMatcher = null;
        } else {
          tmpMatcher = tmpPattern.matcher("" + anInputString.charAt(i++));
        }
      } while (tmpMatcher != null && tmpMatcher.find());

      anInputString = "(" + anInputString.substring(0, --i) + ")" + anInputString.substring(i);
    }
  }

  /**
   * checks if there is the same amount of ( and ) brackets, and if no ) are in
   * lead of ( , that means not more than there should be
   *
   * @param aAnInputString
   * @return true if the brackets in the term are correct
   */
  private static boolean checkBrackets(String anInputString) {

    int tmpLeftBracketCounter = 0;
    int tmpRightBracketCounter = 0;
    int tmpOpenCloseCounter = 0;

    for (int i = 0; i < anInputString.length(); i++) {
      if (anInputString.charAt(i) == '(') {
        tmpLeftBracketCounter++;
        tmpOpenCloseCounter++;
      } else if (anInputString.charAt(i) == ')') {
        tmpRightBracketCounter++;
        tmpOpenCloseCounter--;
      }

      if (tmpOpenCloseCounter <0){
        return false;
      }
    }

    // TODO @Tim sobald tmpOpenCloseCounter<0 (auch innerhalb des Terms) ist die
    // Formel kaputt. Eine Prüfung reicht nicht.
    //
    // RAPHIs VORSCHLAG:
    // for (int i = 0; i < anInputString.length(); i++){
    // if (anInputString.charAt(i) == '(') summe=summe+1
    // if (anInputString.charAt(i) == ')') summe=summe-1
    // if (summe < 0) return false;
    // }
    // return (summe == 0);

    // ??? Ich steh grad auf dem Schlauch... was mach ich denn bitte? Genau DAS!
    // :D
    if (tmpRightBracketCounter != tmpLeftBracketCounter) {
      return false;
    }

    return true;
  }

  /**
   * @param anInputString
   * @return true if there are no operators directly beside each other like
   *         "*-", "+/" or "* /" ...
   * @author Tobias
   */
  private static boolean checkOperators(String anInputString) {
    Pattern tmpPattern = Pattern.compile("[\\+\\-\\*/^] *[\\+\\-\\*/^]");
    Matcher tmpMatcher = tmpPattern.matcher(anInputString);
    if (tmpMatcher.find()) {
      return false;
    }
    return true;
  }

  /**
   * Checks if there are only valid blanks in the string
   *
   * @param aAnInputString
   * @return true if the string contains only valid blanks
   */
  private static boolean checkIfOnlyValidBlanks(String anInputString) {
    int tmpPosition = 0;
    while (getNextBlankPosition(anInputString, tmpPosition) != -1) {
      tmpPosition = getNextBlankPosition(anInputString, tmpPosition);
      if (tmpPosition != 0 || tmpPosition != anInputString.length()) {

        if ((tmpPosition - 1 > 0 && isNumericOrVariable(anInputString.charAt(tmpPosition - 1)))
            && (tmpPosition + 1 <= anInputString.length() && isNumericOrVariable(anInputString
                .charAt(tmpPosition - 1)))) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * gets the position of the next blank in a string
   *
   * @param anInputString
   * @param aStartPosition
   * @return The position of the next blank in the given String, returns -1 if
   *         no blank is found
   */
  private static int getNextBlankPosition(String anInputString, int aStartPosition) {
    int tmpPosition = -1;

    for (int i = 0; i < anInputString.length(); i++) {
      if (anInputString.charAt(i) == ' ') {
        tmpPosition = i;
        i = anInputString.length();
      }
    }

    return tmpPosition;
  }

  /**
   * A method to clean the whitespaces in anInputString. replaces "2 + 3" with
   * "2+3"
   *
   * @param anInputString
   * @return
   */
  public String removeBlanks(String anInputString) {
    // TODO @Tim da gibt's ne Methode String.replace()
    // TODO @whoever ich kann keinen empty Char als Replace benutzen
    String tmpOutput = new String("");

    for (int i = 0; i < anInputString.length(); i++) {
      if (anInputString.charAt(i) != ' ') {
        tmpOutput += anInputString.charAt(i);
      }
    }

    return tmpOutput;
  }

  /**
   * A method to clean the variables in anInputString. Variables "ab" will be
   * replaced with "a*b" "2a" will be replaced with "2*a"
   *
   * @param anInputString
   * @return a String that contains no "ab" or "2a" variables
   */
  public String insertMultiplicationOperators(String anInputString) {

    String tmpOutput = new String("");

    for (int i = 0; i < anInputString.length(); i++) {
      tmpOutput += anInputString.charAt(i);
      if (isVariable(anInputString.charAt(i))
          && (i + 1 < anInputString.length() && isVariable(anInputString.charAt(i + 1)))) {
        tmpOutput += "*";
      } else if (isNumeric(anInputString.charAt(i))
          && (i + 1 < anInputString.length() && isVariable(anInputString.charAt(i + 1)))) {
        tmpOutput += "*";
      }
    }

    return tmpOutput;
  }

  private static boolean isNumericOrVariable(char aCharacter) {
    return (isNumeric(aCharacter) || isVariable(aCharacter)) ? true : false;
  }

  private static boolean isNumeric(char aCharacter) {

    String tmpNumbers = new String("0123456789");
    // TODO @Tim Regex Pattern.compile("[0-9]")
    // TODO @whoever ich liefer mir nen Char, das will RegEx nicht

    for (int i = 0; i < tmpNumbers.length(); i++) {
      if (tmpNumbers.charAt(i) == aCharacter) {
        return true;
      }
    }
    return false;
  }

  private static boolean isVariable(char aCharacter) {

    String tmpValidLetters = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    for (int i = 0; i < tmpValidLetters.length(); i++) {
      if (tmpValidLetters.charAt(i) == aCharacter || tmpValidLetters.toLowerCase().charAt(i) == aCharacter) {
        return true;
      }
    }
    return false;
  }

  private static boolean containsValidSignsOnly(String anInputString) {

    String tmpValidSigns = new String("(),.²³+*/-");

    for (int i = 0; i < anInputString.length(); i++) {
      for (int j = 0; j < tmpValidSigns.length(); j++) {
        if (tmpValidSigns.charAt(j) != anInputString.charAt(i)) {
          if (!isNumericOrVariable(anInputString.charAt(i))) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
