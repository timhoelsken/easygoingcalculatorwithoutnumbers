package calculator;

/**
 * 
 * @author Tim
 *
 */
public class Calculator {

  /**
   * Starts the calculator
   * @param args
   */
  public static void main(String[] args) {

    if (args.length != 0) {
      if ("console".equals(args[0].toLowerCase())) {
        ConsoleCalculator.start();
      } else if ("frame".equals(args[0].toLowerCase())) {
    	//TODO @Tim: im window mode ergibt sin(23) 23 als ergebnis. im konsolenmodus das ergebnis korrekt
        FrameCalculator tmpFrameCalculator = new FrameCalculator();
        tmpFrameCalculator.setVisible(true);
      }
    } else {
      System.out.println("Please start the calculator with a parameter.");
    }
  }
}
