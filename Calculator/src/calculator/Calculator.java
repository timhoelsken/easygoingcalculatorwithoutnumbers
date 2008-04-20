package calculator;

import calculator.userinterface.ConsoleCalculator;
import calculator.userinterface.FrameCalculator;
import calculator.userinterface.SplashScreen;

/**
 * The main calculator-class
 * 
 */
public class Calculator {

  /**
   * Starts the calculator
   * 
   * @param args
   */
  public static void main(String[] args) {

    // by default, the calculator starts in frame mode
    if (args.length == 0) {
      // show splashScreen
      SplashScreen tmpSplashScreen = new SplashScreen();
      tmpSplashScreen.run();

      // start calculator
      FrameCalculator tmpFrameCalculator = new FrameCalculator();
      tmpFrameCalculator.setVisible(true);

      // if parameter "console" is set
    } else if (args.length == 1 && args[0].equals("console")) {
      ConsoleCalculator.start();

    } else {
      throw new IllegalArgumentException(
          "Please start the Calculator with the parameter \"console\" or without any.");
    }
  }
}
