package calculator;

import java.io.IOException;
import java.util.Enumeration;
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
        Hashtable<String, Double> tmpVariables = new Hashtable<String, Double>();

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
              do {
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
                tmpVariables.put(tmpCurrentKey, Double.parseDouble(tmpInputVariableValue));
              } while (tmpKeys.hasMoreElements());

              // ==== Variable input end ====
              // TODO Update von Raphi for Tim: die Variablen werden durch das entfernen und Neuschreiben
              // in der Reihenfolge verschoben... für den User ist eine Eingabe
              // in der richtigen Reihenfolge sicherlich besser oder was denkt
              // ihr?
              // comment by Raphael: in beiden Fällen kann der User seine Werte eingeben für alle Variabeln, ja?
              // dann nehmen wir das, was wenigr aufwand ist :-) Benutzerfreundlichkeit war keine Anforderung.
            }
            // Variable Block End

            // calculate term
            try {

              Tree tmpTree = FormulaTree.BuildTree(tmpInputString);

              System.out.println(FormulaTree.EvaluateTree(tmpTree,tmpVariables));

              tmpTree.paintMe();

              // TODO Update von Raphi - >Tim: endlosschleife, ich schreib hier ma was hin, damit
              // der mich nic nervt :-)
              // Antwort Tim: was für eine Endlosschleife?
              // (Variablenwerteingabe?? ==> soll doch so sein ^^)
              // Antwort Raphi: Die Zeile unterhalb tmpEnterVariablesValue = "n";
              //wenn ich die rausnehme, hab ch ne endlosschleife im menü
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
}
