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
   * @param anOutput
   * @return
   */
  private String changeFunctionsIntoSigns(String anOutput) {
    // TODO @Tobias
    return null;
  }

  /**
   * @param aOutput
   * @return
   */
  private String handleCommas(String aOutput) {
    // TODO @Tim
    return null;
  }

  /**
   * @param anInputString
   * @return
   */
  private boolean checkValidTerm(String anInputString) {
    // TODO @Tim&Tobias Exceptions except Boolean return value
    // TODO @Tim&Tobias put checkBrackets behind checkMinusSigns and check order
    // of checks generally
    // TODO @Tim&Tobias check if numbers follow behind commas
    return checkIfOnlyValidBlanks(anInputString) && checkOperators(anInputString) && checkBrackets(anInputString) && checkMinusSigns(anInputString)
        && doFurtherConsistencyChecks(anInputString);
  }

  /**
   * @param anInputString
   * @return true for all other checks (what do we have to check???)
   */
  private boolean doFurtherConsistencyChecks(String anInputString) {
    // TODO @all who want to
    return true;
  }

  /**
   * @param anInputString
   * @return true if all negative numbers are in brackets, assure that there are
   *         no blanks in the inputString!!
   * @author Tobias
   */
  private boolean checkMinusSigns(String anInputString) {
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
   * @param aAnInputString
   * @return
   */
  private boolean checkBrackets(String anInputString) {
    // TODO @Tim
    return false;
  }

  /**
   * @param anInputString
   * @return true if there are no operators directly beside each other like
   *         "*-", "+/" or "* /" ...
   * @author Tobias
   */
  private boolean checkOperators(String anInputString) {
    Pattern tmpPattern = Pattern.compile("[\\+\\-\\*/] *[\\+\\-\\*/]");
    Matcher tmpMatcher = tmpPattern.matcher(anInputString);
    if (tmpMatcher.find()) {
      return false;
    }
    return true;
  }

  /**
   * @param aAnInputString
   * @return
   */
  private boolean checkIfOnlyValidBlanks(String anInputString) {
    // TODO @Tim

    return false;
  }

  private int getNextBlankPosition(String anInputString) {
    return 0;
  }

  /**
   * A method to clean the whitespaces in anInputString. replaces "2 + 3" with
   * "2+3"
   *
   * @param anInputString
   * @return
   */
  public String removeBlanks(String anInputString) {

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
      if (isVariable(anInputString.charAt(i)) && (i + 1 < anInputString.length() && isVariable(anInputString.charAt(i + 1)))) {
        tmpOutput += "*";
      } else if (isNumeric(anInputString.charAt(i)) && (i + 1 < anInputString.length() && isVariable(anInputString.charAt(i + 1)))) {
        tmpOutput += "*";
      }
    }

    return tmpOutput;
  }

  private boolean isNumeric(char aCharacter) {

    String tmpNumbers = new String("0123456789");

    for (int i = 0; i < tmpNumbers.length(); i++) {
      if (tmpNumbers.charAt(i) == aCharacter) {
        return true;
      }
    }
    return false;
  }

  private boolean isVariable(char aCharacter) {

    String tmpUpperCase = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    String tmpLowerCase = new String("abcdefghijklmnopqrstuvwxyz");

    for (int i = 0; i < tmpUpperCase.length(); i++) {
      if (tmpUpperCase.charAt(i) == aCharacter || tmpLowerCase.charAt(i) == aCharacter) {
        return true;
      }
    }
    return false;
  }
}
