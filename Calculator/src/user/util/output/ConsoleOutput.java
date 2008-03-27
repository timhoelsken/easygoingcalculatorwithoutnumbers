package user.util.output;

/**
 * 
 * @author Tim
 * 
 */
public class ConsoleOutput {

  /**
   * Prints the mainmenu on the screen
   * 
   */
  public void showMenu() {

    System.out.println("\nHauptmenu");
    System.out.println(horizontalLine());
    System.out.println("Bitte waehlen Sie eine der folgenden Aktionen aus:\n");
    System.out.println("t \t Term eingeben");
    System.out.println("h \t Hilfe");
    System.out.println("e \t exit");
  }

  /**
   * Prompts the user to type a term.
   * 
   */
  public void showInputPrompt() {
    System.out.println("\nBitte geben Sie einen Term ein:\n");
  }

  /**
   * Prints the headline of the calculator
   * 
   */
  public void showTitle() {
    System.out.println(horizontalLine());
    System.out.println("Willkommen beim PSE III Taschenrechner der Gruppe 3");
    System.out.println(horizontalLine());
  }

  /**
   * Prints a standard error Message, containing the given errorMessage.
   * 
   * @param anErrorMessage
   */
  public void printError(String anErrorMessage) {
    System.out.println("Bei der Verarbeitung ist folgender Fehler aufgetreten:\n\n");
    System.out.println(anErrorMessage + "\n\n");

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
  public void showHelp() {

    System.out.println("\nHilfe Menu");
    System.out.println(horizontalLine());
    System.out.println("\nHier wird Ihnen geholfen, sobald ein sinnvoller Text entstanden ist.\n");

  }

  /**
   * A given result will be printed on the console, with a static prefix
   * sentance.
   * 
   * @param anOutput
   */
  public static void showResultOnScreen(String anOutput) {
    System.out.println("Der eingegebene Term wurde mit folgendem Ergebnis berechnet:\n");
    System.out.println(anOutput);
  }

  /**
   * Prints an errorMessage that the command could not be read.
   * 
   */
  public void unknownCommand() {
    System.out.println("\nIhr eingegebener Befehl konnte leider nicht verarbeitet werden.\n\n");
  }
}
