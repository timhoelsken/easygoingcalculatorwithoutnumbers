package calculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

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
public class ConsoleCalculator {

  /**
   * The Calculator
   *
   * @param args
   */
  public static void start() {

    // defines that the calculator is running
    Boolean runCalculator = true;
    String tmpInputString = new String("");

    ConsoleOutput.showTitle();

    while (runCalculator) {

      ConsoleOutput.showMenu();

      // read user's choice of the mainmenu
      try {
        tmpInputString = ConsoleInput.getConsoleInput();
      } catch (IOException e) {
        ConsoleOutput.printError(e.getMessage());
        e.printStackTrace();
      }

      // if termin out
      if (tmpInputString.equals("t")) {

        tmpInputString = "";
        boolean tmpTermHasVariables = false;
        String tmpEnterVariablesValue = new String("");
        ArrayList<String[]> tmpVariablesList = new ArrayList<String[]>();
        Hashtable<String, Double> tmpVariableDictionary = new Hashtable<String, Double>();

        // while the user has not chosen to quit the calculator
        while (!tmpInputString.toLowerCase().equals("n")) {

          System.out.println("Bitte geben Sie einen Term ein:");

          tmpEnterVariablesValue = "";
          // the user types in the term
          try {
            tmpInputString = ConsoleInput.getConsoleInput();
          } catch (IOException e) {
            ConsoleOutput.printError(e.getMessage());
            e.printStackTrace();
          }

          try {
            tmpInputString = ConverterUtil.termToStandardString(tmpInputString);
          } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            tmpEnterVariablesValue = "error";
          }

          tmpTermHasVariables = ConverterUtil.hasVariables(tmpInputString);

          // while the user has not chosen to stop enter variables
          do {

            // Variable Block Start
            if (tmpTermHasVariables) {

              tmpVariablesList = ConverterUtil.getVariables(tmpInputString);
              String tmpInputVariableValue = new String("");

              ConsoleOutput.promptVariableInput();
              // ==== Variable input start ====
              for (int i = 0; i < tmpVariablesList.size(); i++) {
                do {
                  tmpInputVariableValue = "";
                  System.out.print(tmpVariablesList.get(i)[0] + " = ");
                  try {
                    tmpInputVariableValue = ConsoleInput.getConsoleInput();
                  } catch (IOException e) {
                    ConsoleOutput.printError(e.getMessage());
                    e.printStackTrace();
                  }

                  tmpInputVariableValue = ConverterUtil.unifyCommas(tmpInputVariableValue);
                  if (!MathUtil.isDouble(tmpInputVariableValue)) {
                    System.out
                        .println("\nDer eingegebene Wert muss eine Zahl sein. Bitte wiederholen Sie ihre Eingabe.\n");
                  }
                } while (!MathUtil.isDouble(tmpInputVariableValue));

                tmpVariablesList.get(i)[1] = tmpInputVariableValue;
              }

              tmpVariableDictionary = ConverterUtil.putArrayListIntoHashtable(tmpVariablesList);

              // ==== Variable input end ====

            }
            else{
              tmpEnterVariablesValue = "noVariables";
            }
            // Variable Block End

            // calculate term
            try {

              Tree tmpTree = FormulaTree.BuildTree(tmpInputString);

              System.out.println(FormulaTree.EvaluateTree(tmpTree, tmpVariableDictionary));
              tmpEnterVariablesValue = "calculated";
              tmpTree.paintMe();

            } catch (Exception e) {
              System.out.println(e.getMessage());
              e.printStackTrace();
              tmpEnterVariablesValue = "error";
            }
            // if no error occurs, the user is asked if he wants to set another
            // variable
            if (!tmpEnterVariablesValue.equals("error") && tmpTermHasVariables) {

              System.out.println("Wollen Sie andere Werte fuer die Variablen im Term eingeben? (j / n)\n");

              try {
                tmpEnterVariablesValue = ConsoleInput.getConsoleInput();
              } catch (IOException e) {
                ConsoleOutput.printError(e.getMessage());
                e.printStackTrace();
              }
            }
          } while (!"n".equals(tmpEnterVariablesValue) && !"error".equals(tmpEnterVariablesValue) && !"calculated".equals(tmpEnterVariablesValue));


          System.out.println("Wollen Sie einen weiteren Term eingeben? (j / n)\n");

          try {
            tmpInputString = ConsoleInput.getConsoleInput();
          } catch (IOException e) {
            ConsoleOutput.printError(e.getMessage());
            e.printStackTrace();
          }

        }
        // if Helpmenu
      } else if (tmpInputString.equals("h")) {

        ConsoleOutput.showHelp();

        // if exit
      } else if (tmpInputString.toLowerCase().equals("e") || tmpInputString.toLowerCase().equals("exit")) {

        runCalculator = false;

        // if unknown command
      } else {

        ConsoleOutput.unknownCommand();
      }
    }
    System.out.println("\nSie verlassen nun den Taschenrechner.\n\n");
  }
}
