package calculator.userinterface.console;

/**
 * defines the user dialogs
 */
public final class ConsoleOutput {

  /**
   * Prints the mainmenu on the screen
   * 
   */
  public static void showMenu() {

    System.out.println("\nMainmenu");
    System.out.println(horizontalLine());
    System.out.println("Please choose a menuitem:\n");
    System.out.println("f \t enter formula");
    System.out.println("h \t help");
    System.out.println("e \t exit");
  }

  /**
   * Prompts the user to type a formula.
   * 
   */
  public static void promptFormulaInput() {
    System.out.println("\nPlease enter a formula:\n");
  }

  /**
   * Prints the headline of the calculator
   * 
   */
  public static void showTitle() {
    System.out.println(horizontalLine());
    System.out.println("Welcome to the PSE III calculator of group 3");
    System.out.println(horizontalLine());
  }

  /**
   * Prints a standard error Message, containing the given errorMessage.
   * 
   * @param anErrorMessage
   */
  public static void printError(String anErrorMessage) {
    System.out.println("\nFollowing error occurd while calculating the formula:");
    System.out.println(anErrorMessage + "\n");

  }

  /**
   * Prints a line on the screen.
   * 
   * @return A horizontal line with "-"
   */
  private static String horizontalLine() {
    return ("---------------------------------------------------");
  }

  /**
   * Prints a help dialog on the screen.
   * 
   */
  public static void showHelp() {

    System.out.println("\nHelp menu");
    System.out.println(horizontalLine());
    System.out.println("\nHow to use the calculator:\n");
    System.out.println("By entering f, you get to the formula input.\n"
        + "There you can input the following as part of the formula:\n\n"
        + "- real numbers (negative numbers have to be in brackets)\n" + "- operators \"+ - * \\ ^\"\n"
        + "- functions \"sin() cos() tan() sqrt()\"\n" + "- variables\n\n"
        + "If your formula contains variables, you are prompted to enter their value.\n");

  }

  /**
   * A given result will be printed on the console, with a static prefix
   * sentance.
   * 
   * @param anOutput
   */
  public static void showResultOnScreen(String anOutput) {
    System.out.println("The entered formula has the following result:\n");
    System.out.println(anOutput);
  }

  /**
   * Prints an errorMessage that the command could not be read.
   * 
   */
  public static void unknownCommand() {
    System.out.println("\nThe entered command is unknown.\n\n");
  }

  /**
   * Prints an errorMessage that the value must be a number.
   * 
   */
  public static void invalidDouble() {
    System.out.println("\nThe entered value must be a number.\n\n");
  }

  /**
   * Prints a message that the calculater is closed.
   * 
   */
  public static void exitCalculator() {
    System.out.println("\nThe calculator is shut down.\n\n");
  }

  /**
   * Prompts the user to type the values of variables
   */
  public static void promptVariableInput() {
    System.out.println("\nYour formula contains at least one variable. Please enter the value(s):\n");
  }

  /**
   * Prints a question to enter new values for variable(s)
   * 
   */
  public static void askAnotherVariableInput() {
    System.out.println("\nDo you like to enter other values for the variable(s)? (y / n)\n");
  }

  /**
   * Prints a question to enter new formula
   * 
   */
  public static void askAnotherFormulaInput() {
    System.out.println("\nDo you like to enter another formula? (y / n)\n");
  }
}
