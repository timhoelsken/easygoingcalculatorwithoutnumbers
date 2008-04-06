package calculator;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;

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
        Dictionary<String, Float> tmpVariables = null;

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

          try {
            tmpInputString = ConverterUtil.termToStandardString(tmpInputString);
          } catch (Exception e) {
            System.out.println(e.getMessage());
            tmpEnterVariablesValue = "error";
          }

          // while the user has not chosen to stop enter variables
          while (!tmpEnterVariablesValue.toLowerCase().equals("n")
              && !tmpEnterVariablesValue.toLowerCase().equals("error")) {

            // Variable Block Start
            if (ConverterUtil.hasVariables(tmpInputString)) {

              tmpVariables = ConverterUtil.getVariables(tmpInputString);
              Enumeration<String> tmpKeys = tmpVariables.keys();

              String tmpInputVariableValue = new String("");
              String tmpCurrentKey = new String("");

              tmpOutput.promptVariableInput();
              // ==== Variable input start ====
              while (tmpKeys.hasMoreElements()) {
                do {
                  tmpCurrentKey = tmpKeys.nextElement();
                  tmpInputVariableValue = "";
                  System.out.print(tmpCurrentKey + " = ");
                  try {
                    tmpInputVariableValue = tmpInput.getConsoleInput();
                  } catch (IOException e) {
                    tmpOutput.printError(e.getMessage());
                  }
                  if (!MathUtil.isFloat(tmpInputVariableValue)) {
                    System.out
                        .println("\nDer eingegebene Wert muss eine Zahl sein. Bitte wiederholen Sie ihre Eingabe.\n");
                  }
                } while (!MathUtil.isFloat(tmpInputVariableValue));

                tmpVariables.remove(tmpCurrentKey);
                tmpVariables.put(tmpCurrentKey, Float.parseFloat(tmpInputVariableValue));
              }
              // ==== Variable input end ====
              // TODO ich hoffe das der Code soweit funktioniert, konnte nicht
              // testen wegen Instanziierungsproblem
            }
            // Variable Block End

            // calculate term
            try {

              Tree tmpTree = FormulaTree.BuildTree(ConverterUtil.termToStandardString(tmpInputString));

              System.out.println(FormulaTree.EvaluateTree(tmpTree));

              tmpTree.paintMe();

              // TODO TIM: endlosschleife, ich schreib hier ma was hin, damit
              // der mich nic nervt :-)
              // Antwort Tim: was für eine Endlosschleife?
              // (Variablenwerteingabe?? ==> soll doch so sein ^^)
              tmpEnterVariablesValue = "n";
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
  // TODO Tim: wenn ich sin(35) eintrage, fragt mich das Hauptprogramm anch 100
  // Variablen (ok es sind nur 3: s i n)
  // Antwort Tim: ich habe die Convertierung des Strings vorgezogen, damit
  // wirklich nur Variablen abgefragt werden.

  // TODO @all, wenn einer sin4 eintraegt, was macht die Syntax Prüfung damit?
  // Antwort Tim: ==> s*i*n*4 (s. Test)

  // TODO @all -a+45 wird das (-a) da geklammert?
  // Antwort Tim: ==> (-a)+45 (s. Test)
}
