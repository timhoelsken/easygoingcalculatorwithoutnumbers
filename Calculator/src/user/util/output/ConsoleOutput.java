package user.util.output;

/**
 * 
 * @author Tim
 *
 */
public class ConsoleOutput {

  /**
   * A given result will be printed on the console, with a
   * static prefix sentance.
   * 
   * @param anOutput
   */
  public static void showResultOnScreen(String anOutput){
    System.out.println("Der eingegebene Term wurde mit folgendem Ergebnis berechnet:\n");
    System.out.println(anOutput);
  }
}
