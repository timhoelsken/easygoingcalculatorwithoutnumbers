package calculator;

import java.io.IOException;

import user.util.input.ConsoleInput;
import user.util.input.Converter;
import user.util.output.ConsoleOutput;

/**
 * 
 * @author Tim
 * 
 */
public class Main {

  /**
   * The Calculator
   * 
   * @param args
   */
  public static void main(String[] args) {
    ConsoleInput tmpInput = new ConsoleInput();
    ConsoleOutput tmpOutput = new ConsoleOutput();
    Boolean runCalculator = true;
    Converter tmpConverter = new Converter();
    String tmpInputString = new String("");
    tmpOutput.showTitle();

    while (runCalculator) {

      tmpOutput.showMenu();

      try {
        tmpInputString = tmpInput.getConsoleInput();
      } catch (IOException e) {
        tmpOutput.printError(e.getMessage());
      }

      if (tmpInputString.equals("t")) {

        tmpInputString = "";

        while (!tmpInputString.toLowerCase().equals("n")) {

          System.out.println("Bitte geben Sie einen Term ein:");
          try {
            tmpInputString = tmpInput.getConsoleInput();
          } catch (IOException e) {
            tmpOutput.printError(e.getMessage());
          }

          //TODO hier sollte dann sinnvoll was mit dem Term angestellt werden 
          System.out.println("Sie haben eingegeben:\n" + tmpInputString);
          System.out.println("das bereinigte Ergebnis ist:\n");
          System.out.println(tmpConverter.termToStandardString(tmpInputString));

          System.out.println("Wollen Sie einen weiteren Term eingeben? (j / n)\n");
          try {
            tmpInputString = tmpInput.getConsoleInput();
          } catch (IOException e) {
            tmpOutput.printError(e.getMessage());
          }

        }
      } else if (tmpInputString.equals("h")) {

        tmpOutput.showHelp();

      } else if (tmpInputString.toLowerCase().equals("e") || tmpInputString.toLowerCase().equals("exit")) {

        runCalculator = false;

      } else {

        tmpOutput.unknownCommand();
      }
    }
    System.out.println("\nSie verlassen nun den Taschenrechner.\n\n");
  }
}
