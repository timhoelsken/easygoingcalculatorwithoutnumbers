package calculator;

import java.io.IOException;
import java.util.ArrayList;

import calculator.elements.Tree;
import calculator.userinterface.ConsoleInput;
import calculator.userinterface.ConsoleOutput;
import calculator.utils.ConverterUtil;
import calculator.utils.MathUtil;

/**
 * 
 * @author Tim
 * 
 */
public class Calculator {

  /**
   * The Calculator
   * 
   * @param args
   */
  public static void main(String[] args) {
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
        String tmpOtherVariables = new String("");
        ArrayList<String[]> tmpVariables = new ArrayList<String[]>();

        while (!tmpInputString.toLowerCase().equals("n")) {

          System.out.println("Bitte geben Sie einen Term ein:");

          try {
            tmpInputString = tmpInput.getConsoleInput();
          } catch (IOException e) {
            tmpOutput.printError(e.getMessage());
          }

          System.out.println("Sie haben eingegeben:\n" + tmpInputString);
          System.out.println("das bereinigte Ergebnis ist:");

          try {
            tmpInputString = ConverterUtil.termToStandardString(tmpInputString);
            System.out.println(tmpInputString);

            while (!tmpOtherVariables.toLowerCase().equals("n")) {

              if (ConverterUtil.hasVariables(tmpInputString)) {

                tmpVariables = ConverterUtil.getVariables(tmpInputString);
                tmpOutput.promptVariableInput();

                for (int i = 0; i < tmpVariables.size(); i++) {
                  do {
                    System.out.print(tmpVariables.get(i)[0] + " = ");

                    try {
                      tmpVariables.get(i)[1] = tmpInput.getConsoleInput();
                    } catch (IOException e) {
                      tmpOutput.printError(e.getMessage());
                    }
                    if (!MathUtil.isFloat(tmpVariables.get(i)[1])) {
                      System.out
                          .println("\nDer eingegebene Wert muss eine Zahl sein. Bitte wiederholen Sie ihre Eingabe.\n");
                    }
                  } while (!MathUtil.isFloat(tmpVariables.get(i)[1]));
                }
              }

              try {
                FormulaTree tmpFormulaTreeBuilder = new FormulaTree();

                Tree tmpTree = tmpFormulaTreeBuilder.BuildTree(ConverterUtil
                    .termToStandardString(tmpInputString));

                System.out.println(FormulaTree.EvaluateTree(tmpTree));

                tmpTree.paintMe();
              } catch (Exception e) {
                System.out.println(e.getMessage());
                tmpOtherVariables = "n";
              }
            }

            System.out.println("Wollen Sie andere Werte fuer die Variablen im Term eingeben? (j / n)\n");

            try {
              tmpOtherVariables = tmpInput.getConsoleInput();
            } catch (IOException e) {
              tmpOutput.printError(e.getMessage());
            }

          } catch (IllegalArgumentException e) {
            tmpOutput.printError(e.getMessage());
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
