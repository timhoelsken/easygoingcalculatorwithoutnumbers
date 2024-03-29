package calculator.userinterface.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 */
public final class ConsoleInput {

  private static String inputString;

  /**
   * Reads the console input of the user
   * 
   * @return The input of the user, tested and corrected with private methods on
   *         usability
   * @throws IOException
   */
  public static String getConsoleInput() throws IOException {
    InputStreamReader tmpStremReader = new InputStreamReader(System.in);
    BufferedReader tmpStdIn = new BufferedReader(tmpStremReader);
    inputString = tmpStdIn.readLine();

    return inputString;
  }
}
