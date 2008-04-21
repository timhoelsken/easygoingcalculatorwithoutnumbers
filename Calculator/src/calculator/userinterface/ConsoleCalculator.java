package calculator.userinterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import calculator.elements.Tree;
import calculator.exceptions.CalculatingException;
import calculator.exceptions.FormulaConversionException;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;
import calculator.utils.MathUtil;

/**
 * the console calculator
 */
public class ConsoleCalculator {

  /**
   * starts the console calculator
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

      // if the user wants to enter a formula
      if (tmpInputString.equals("f")) {

        tmpInputString = "";

        boolean tmpFormulaHasVariables = false;
        boolean tmpErrorOccuredInFormula = false;

        String tmpEnterVariablesValue = new String("");

        ArrayList<String[]> tmpVariablesList = new ArrayList<String[]>();
        Hashtable<String, Double> tmpVariableDictionary = new Hashtable<String, Double>();

        Tree tmpTree = null;

        // while the user has not chosen to quit entering formulas
        while (!tmpInputString.toLowerCase().equals("n")) {

          // reset of control variables and tree
          tmpErrorOccuredInFormula = false;
          tmpEnterVariablesValue = "";
          tmpTree = null;

          ConsoleOutput.promptFormulaInput();

          // the user types in the formula
          try {
            tmpInputString = ConsoleInput.getConsoleInput();
          } catch (IOException e) {
            ConsoleOutput.printError(e.getMessage());
          }

          // convert input into the standard string
          try {
            tmpInputString = ConverterUtil.formulaToStandardString(tmpInputString);
          } catch (FormulaConversionException e) {
            ConsoleOutput.printError(e.getMessage());
            tmpErrorOccuredInFormula = true;
            tmpTree = null;
          }

          if (!tmpErrorOccuredInFormula) {

            tmpFormulaHasVariables = ConverterUtil.hasVariables(tmpInputString);

            // while the user has not chosen to stop enter variables
            do {

              // Variable Block Start
              if (tmpFormulaHasVariables) {

                tmpVariablesList = ConverterUtil.getVariables(tmpInputString);
                String tmpInputVariableValue = new String("");

                ConsoleOutput.promptVariableInput();

                // ==== Variable input start ====
                for (int i = 0; i < tmpVariablesList.size(); i++) {
                  do {
                    tmpInputVariableValue = "";
                    System.out.print(tmpVariablesList.get(i)[0] + " = ");

                    // read users input
                    try {
                      tmpInputVariableValue = ConsoleInput.getConsoleInput();
                    } catch (IOException e) {
                      ConsoleOutput.printError(e.getMessage());
                    }

                    // convert , to .
                    tmpInputVariableValue = ConverterUtil.unifyCommas(tmpInputVariableValue);

                    // while the entered value is not a double value, show
                    // message and repeat the loop
                    if (!MathUtil.isDouble(tmpInputVariableValue)) {
                      ConsoleOutput.invalidDouble();
                    }
                  } while (!MathUtil.isDouble(tmpInputVariableValue));

                  // assign variable with variableValue
                  tmpVariablesList.get(i)[1] = tmpInputVariableValue;
                }

                // put list into a dictionary
                tmpVariableDictionary = ConverterUtil.putArrayListIntoHashtable(tmpVariablesList);
                // ==== Variable input end ====
              }
              // Variable Block End

              // calculate formula
              try {

                // if the tree already exist, use it!
                if (tmpTree == null) {
                  tmpTree = FormulaTreeUtil.BuildTree(tmpInputString);
                }

                // calculate!
                System.out.println(FormulaTreeUtil.EvaluateTree(tmpTree, tmpVariableDictionary));

                // to avoid endless loop the control is set to "n"
                // (Do-While-Loop)
                if (!ConverterUtil.hasVariables(tmpInputString)) {
                  tmpEnterVariablesValue = "n";
                }

                tmpTree.paintMe();

              } catch (CalculatingException e) {
                System.out.println(e.getMessage());
                tmpTree = null;
                tmpErrorOccuredInFormula = true;
              }

              // if no error occured and the formula has variables, the user is
              // asked if he wants to enter another variable
              if (!tmpErrorOccuredInFormula && tmpFormulaHasVariables) {

                ConsoleOutput.askAnotherVariableInput();

                try {
                  tmpEnterVariablesValue = ConsoleInput.getConsoleInput();
                } catch (IOException e) {
                  ConsoleOutput.printError(e.getMessage());
                }
              }
              // Enter variables loop
            } while (!"n".equals(tmpEnterVariablesValue) && !tmpErrorOccuredInFormula);
          }

          ConsoleOutput.askAnotherFormulaInput();

          try {
            tmpInputString = ConsoleInput.getConsoleInput();
          } catch (IOException e) {
            ConsoleOutput.printError(e.getMessage());
          }

          // end of entering formulas loop
        }

        // if helpmenu
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
    ConsoleOutput.exitCalculator();
  }
}
