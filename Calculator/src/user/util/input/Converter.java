package user.util.input;

/**
 *
 * @author Tim
 *
 */
public class Converter {

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

    return tmpOutput;
  }

  /**
   * @param anInputString
   * @return
   */
  private boolean checkValidTerm(String anInputString) {
    return checkIfOnlyValidBlanks(anInputString) && checkMultiplicationNotation(anInputString) && checkBrackets(anInputString)
        && checkMinusSigns(anInputString) && doFurtherConsistencyChecks(anInputString);
  }

  /**
   * @param aAnInputString
   * @return true for all other checks (what do we have to check???)
   */
  private boolean doFurtherConsistencyChecks(String anInputString) {
    // TODO @all who want to
    return true;
  }

  /**
   * @param aAnInputString
   * @return
   */
  private boolean checkMinusSigns(String anInputString) {
    // TODO @Tobias
    return false;
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
   * @param aAnInputString
   * @return
   */
  private boolean checkMultiplicationNotation(String anInputString) {
    // TODO @Tobias
    return false;
  }

  /**
   * @param aAnInputString
   * @return
   */
  private boolean checkIfOnlyValidBlanks(String anInputString) {
    // TODO @Tim
    
    return false;
  }
  
  private int getNextBlankPosition(String anInputString){
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
