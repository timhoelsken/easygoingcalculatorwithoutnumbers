package calculator;

import calculator.userinterface.ConsoleCalculator;
import calculator.userinterface.FrameCalculator;


/**
 *
 *
 */
public class Calculator {

  /**
   * Starts the calculator
   *
   * @param args
   */
  public static void main(String[] args) {

    if (args.length != 0 && args[0].equals("frame")) {
      FrameCalculator tmpFrameCalculator = new FrameCalculator();
      tmpFrameCalculator.setVisible(true);
    } else {
      ConsoleCalculator.start();
    }
  }
}
