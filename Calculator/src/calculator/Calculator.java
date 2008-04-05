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

    // Variables for textinput and output -- Use Statics instead?
    ConsoleInput tmpInput = new ConsoleInput();
    ConsoleOutput tmpOutput = new ConsoleOutput();

    // defines that the calculator is running
    Boolean runCalculator = true;
    String tmpInputString = new String("");

    tmpOutput.showTitle();

    while (runCalculator) {

      tmpOutput.showMenu();

      // read user's choice of the mainmenu
      try {
        tmpInputString = tmpInput.getConsoleInput();
      } catch (IOException e) {
        tmpOutput.printError(e.getMessage());
      }

      // if terminout
      if (tmpInputString.equals("t")) {

        tmpInputString = "";
        String tmpEnterVariablesValue = new String("");
        ArrayList<String[]> tmpVariables = new ArrayList<String[]>();

        // while the user has not chosen to quit the calculator
        while (!tmpInputString.toLowerCase().equals("n")) {

          System.out.println("Bitte geben Sie einen Term ein:");

          tmpEnterVariablesValue = "";
          // the user types in the term
          try {
            tmpInputString = tmpInput.getConsoleInput();
          } catch (IOException e) {
            tmpOutput.printError(e.getMessage());
          }

          // while the user has not chosen to stop enter variables
          while (!tmpEnterVariablesValue.toLowerCase().equals("n") && !tmpEnterVariablesValue.toLowerCase().equals("error")) {

            // Variable Block Start
            if (ConverterUtil.hasVariables(tmpInputString)) {

              tmpVariables = ConverterUtil.getVariables(tmpInputString);
              tmpOutput.promptVariableInput();

              // ==== Variable input start ====
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
              // ==== Variable input end ====
            }
            // Variable Block End

            // calculate term
            try {
              FormulaTree tmpFormulaTreeBuilder = new FormulaTree();

              Tree tmpTree = tmpFormulaTreeBuilder.BuildTree(ConverterUtil
                  .termToStandardString(tmpInputString));

              System.out.println(FormulaTree.EvaluateTree(tmpTree));

              tmpTree.paintMe();
            } catch (Exception e) {
              System.out.println(e.getMessage());
              tmpEnterVariablesValue = "error";
            }
          }

          // if no error occurs, the user is asked if he wants to set another
          // variable
          if (!tmpEnterVariablesValue.equals("error")) {

            System.out.println("Wollen Sie andere Werte fuer die Variablen im Term eingeben? (j / n)\n");

            try {
              tmpEnterVariablesValue = tmpInput.getConsoleInput();
            } catch (IOException e) {
              tmpOutput.printError(e.getMessage());
            }
          }

          System.out.println("Wollen Sie einen weiteren Term eingeben? (j / n)\n");

          try {
            tmpInputString = tmpInput.getConsoleInput();
          } catch (IOException e) {
            tmpOutput.printError(e.getMessage());
          }

        }
        // if Helpmenu
      } else if (tmpInputString.equals("h")) {

        tmpOutput.showHelp();

        // if exit
      } else if (tmpInputString.toLowerCase().equals("e") || tmpInputString.toLowerCase().equals("exit")) {

        runCalculator = false;

        // if unknown command
      } else {

        tmpOutput.unknownCommand();
      }
    }
    System.out.println("\nSie verlassen nun den Taschenrechner.\n\n");
  }
}
