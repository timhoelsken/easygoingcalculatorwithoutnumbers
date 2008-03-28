package calculator;

import java.io.IOException;

import calculator.userinterface.ConsoleInput;
import calculator.userinterface.ConsoleOutput;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTree;
import calculator.utils.Tree;


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
   * @throws Exception
   */
  // TODO handle Exception
  public static void main(String[] args) throws Exception {
    ConsoleInput tmpInput = new ConsoleInput();
    ConsoleOutput tmpOutput = new ConsoleOutput();
    Boolean runCalculator = true;
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

          // TODO hier sollte dann sinnvoll was mit dem Term angestellt werden
          System.out.println("Sie haben eingegeben:\n" + tmpInputString);
          System.out.println("das bereinigte Ergebnis ist:");
          System.out.println(ConverterUtil.termToStandardString(tmpInputString));

          try {
            FormulaTree tmpFormulaTreeBuilder = new FormulaTree();

            Tree tmpTree = tmpFormulaTreeBuilder.BuildTree(ConverterUtil.termToStandardString(tmpInputString));

            System.out.println(FormulaTree.EvaluateTree(tmpTree));

            tmpTree.paintMe();
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }

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
