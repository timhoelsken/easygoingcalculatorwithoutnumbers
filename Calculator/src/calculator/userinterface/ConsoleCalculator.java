package calculator.userinterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import calculator.elements.Tree;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;
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

      // if the user wants to enter a formula
      if (tmpInputString.equals("f")) {

        tmpInputString = "";

        boolean tmpFormulaHasVariables = false;
        boolean tmpErrorOccuredInTerm = false;
        boolean tmpErrorOccuredInVariableInput = false;

        String tmpEnterVariablesValue = new String("");

        ArrayList<String[]> tmpVariablesList = new ArrayList<String[]>();
        Hashtable<String, Double> tmpVariableDictionary = new Hashtable<String, Double>();
        
        Tree tmpTree = null;

        // while the user has not chosen to quit entering formulas
        while (!tmpInputString.toLowerCase().equals("n")) {

          // reset of control variables and tree
          tmpErrorOccuredInTerm = false;
          tmpEnterVariablesValue = "";
          tmpTree = null;

          ConsoleOutput.promptFormulaInput();

          // the user types in the term
          try {
            tmpInputString = ConsoleInput.getConsoleInput();
          } catch (IOException e) {
            ConsoleOutput.printError(e.getMessage());
          }

          try {
            tmpInputString = ConverterUtil.termToStandardString(tmpInputString);
          } catch (Exception e) {
            ConsoleOutput.printError(e.getMessage());
            tmpErrorOccuredInTerm = true;
            tmpTree = null;
          }

          if (!tmpErrorOccuredInTerm) {

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

                    try {
                      tmpInputVariableValue = ConsoleInput.getConsoleInput();
                    } catch (IOException e) {
                      ConsoleOutput.printError(e.getMessage());
                    }

                    // convert , to .
                    tmpInputVariableValue = ConverterUtil.unifyCommas(tmpInputVariableValue);

                    if (!MathUtil.isDouble(tmpInputVariableValue)) {
                      ConsoleOutput.invalidDouble();
                    }
                  } while (!MathUtil.isDouble(tmpInputVariableValue));

                  // assign variable with variableValue
                  tmpVariablesList.get(i)[1] = tmpInputVariableValue;
                }

                tmpVariableDictionary = ConverterUtil.putArrayListIntoHashtable(tmpVariablesList);
                // ==== Variable input end ====
              }
              // Variable Block End

              // calculate formula
              try {

                if (tmpTree == null){
                  tmpTree = FormulaTreeUtil.BuildTree(tmpInputString);                  
                }

                System.out.println(FormulaTreeUtil.EvaluateTree(tmpTree, tmpVariableDictionary));

                // to avoid endless loop the control is set to "n" (Do-While-Loop)
                if (!ConverterUtil.hasVariables(tmpInputString)) {
                  tmpEnterVariablesValue = "n";
                }

                tmpTree.paintMe();

              } catch (Exception e) {
                System.out.println(e.getMessage());
                tmpTree = null;
                // if (e.getMessage().equals("")){
                tmpErrorOccuredInVariableInput = true;
                //
                //else{
                tmpErrorOccuredInTerm = true;
              //}
              }
              
              //TODO @all Wir sollten unsere Exceptions nochmal überdenken. Ich schlage folgende Exception Kategorien vor: "Syntatktischer Fehler" (z.B. 2+()**sin()), Rechenfehler " 2/0 ", Programmfehler (aufteilung nach ebenen: gui,mathutil,formelbaum). Braucht jemand noch mehr Kategorien für Fehler?
              //TODO fehlerbehandlung in der gui -> nach exceptions neuordnung
              
              // if no error occured and the formula has variables, the user is asked if he wants to set another variable
              if (!tmpErrorOccuredInVariableInput && tmpFormulaHasVariables) {

                ConsoleOutput.askAnotherVariableInput();

                try {
                  tmpEnterVariablesValue = ConsoleInput.getConsoleInput();
                } catch (IOException e) {
                  ConsoleOutput.printError(e.getMessage());
                  //e.printStackTrace();
                }
              }
              // Enter variables loop
            } while (!"n".equals(tmpEnterVariablesValue) && !tmpErrorOccuredInTerm);
          }

          ConsoleOutput.askAnotherFormulaInput();

          try {
            tmpInputString = ConsoleInput.getConsoleInput();
          } catch (IOException e) {
            ConsoleOutput.printError(e.getMessage());
            //e.printStackTrace();
          }

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
