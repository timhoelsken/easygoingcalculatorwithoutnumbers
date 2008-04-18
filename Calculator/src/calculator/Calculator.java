package calculator;

import calculator.userinterface.ConsoleCalculator;
import calculator.userinterface.FrameCalculator;
import calculator.userinterface.SplashScreen;


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

    if (args.length == 0) {
      SplashScreen tmpSplashScreen = new SplashScreen();
      tmpSplashScreen.run();
      FrameCalculator tmpFrameCalculator = new FrameCalculator();
      tmpFrameCalculator.setVisible(true);
    } else if (args.length == 1 && args[0].equals("console")){
      ConsoleCalculator.start();
    } else {
      throw new IllegalArgumentException("Please start the Calculator with the parameter \"console\" or without any.");
    }
  }
}
