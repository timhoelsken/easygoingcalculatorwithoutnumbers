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
        FrameCalculator tmpFrameCalculator = new FrameCalculator();
        tmpFrameCalculator.setVisible(true);
      }
    } else {
      System.out.println("Please start the calculator with a parameter.");
    }
  }
}
