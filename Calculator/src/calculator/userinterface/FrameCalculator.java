package calculator.userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import calculator.elements.Tree;
import calculator.utils.ActionListenerUtil;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;

/**
 * This openes the calculator in a frame.
 */
public class FrameCalculator extends JFrame {

  private static final long serialVersionUID = -4971700820441624660L;

  // button to calculate formula
  private JButton buttonCalculateTerm = new JButton("calculate");

  private CalculatorHelpDialog dialogHelpText = new CalculatorHelpDialog(this);
  private CalculatorInfoDialog dialogInfoText = new CalculatorInfoDialog(this);

  // labels
  private JLabel labelCalculatorTitle = new JLabel("PSE III Calculator");
  private JLabel labelEnterFormula = new JLabel("Formula:");
  private JLabel labelResult = new JLabel("RESULT:");

  // input / output fields
  private static JTextField textTermInput = new JTextField(16);

  /**
   * 
   */
  public static JTextField textFormulaOutput = new JTextField();

  // sub panels
  private JPanel panelBottom = new JPanel(new GridLayout(1, 2));
  private JPanel panelCenter = new JPanel(new GridLayout(2, 1));

  // list and dictionary for variables
  /**
   * 
   */
  private ArrayList<String[]> listOfVariables = new ArrayList<String[]>();
  private static Hashtable<String, Double> dictionaryOfEnteredVariables = new Hashtable<String, Double>();

  // an array of textfields for dynamic variableinput

  // the formula entered by the user
  private String convertedFormula = new String("");
  /**
   * 
   */
  public static String calculatedFormula = new String("");

  // Variable Dialog
  // JDialog dialogEnterVariables = new JDialog();
  private CalculatorVariableDialog dialogEnterVariables = new CalculatorVariableDialog(this);
  // TODO @Tim Dialog nicht benutzerfreundlich. Schmeißt bei Zweiteingabe alle
  // Ersteingaben weg. Doof. :)

  // TODO @Tim noch was Benutzerunfreundliches: Man sieht beim Ergebnis nicht,
  // welche Variablen man eingegeben hat.

  // == Menu Components ==
  private JMenuBar menuBarcalculator = new JMenuBar();

  private JMenu menuFile = new JMenu("File");
  private JMenu menuOptions = new JMenu("Options");
  private JMenu menuHelp = new JMenu("Help");

  private JMenuItem menuItemExit = new JMenuItem("Exit");

  private JMenuItem menuItemProgressBar = new JMenuItem("Enable ProgressBar");

  private JMenuItem menuItemHelp = new JMenuItem("Help");
  private JMenuItem menuItemInfo = new JMenuItem("Info");

  // == Menu Components ==

  // define if a progressBar is loading
  private static boolean loadProgressBar = false;

  // the progressbar :)
  // TODO @Tim die läuft auch wenn beim Rechnen (z.B. Division durch 0) ein
  // Fehler auftritt (gleichzeitig). Ich würde sagen, entweder vorher oder gar
  // nicht. Lieber vorher. Da ist die Spannung größer :P
  private JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);

  /**
   * the constructor
   */
  public FrameCalculator() {

    super("PSE III Calculator");

    // define frame
    setLocation(330, 330);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));

    // align elements
    labelCalculatorTitle.setHorizontalAlignment(JLabel.CENTER);
    labelEnterFormula.setVerticalAlignment((JLabel.TOP));
    buttonCalculateTerm.setVerticalAlignment(JButton.CENTER);
    labelResult.setHorizontalAlignment(JLabel.LEFT);
    textFormulaOutput.setHorizontalAlignment(JTextField.CENTER);

    // add listener to calculateButton
    addCalculatorButtonListener(buttonCalculateTerm);

    ActionListenerUtil.setMenuItemOpenDialogListener(menuItemHelp, dialogHelpText);
    ActionListenerUtil.setMenuItemOpenDialogListener(menuItemInfo, dialogInfoText);

    // disable result textField
    textFormulaOutput.setEditable(false);

    // place label and textField on panel
    panelBottom.add(labelResult);
    panelBottom.add(textFormulaOutput);

    panelCenter.add(textTermInput);
    progressBar.setVisible(false);
    panelCenter.add(progressBar);

    // place labels, textField, button and panel on frame
    getContentPane().add(BorderLayout.NORTH, labelCalculatorTitle);
    getContentPane().add(BorderLayout.WEST, labelEnterFormula);
    getContentPane().add(BorderLayout.CENTER, panelCenter);
    getContentPane().add(BorderLayout.EAST, buttonCalculateTerm);
    getContentPane().add(BorderLayout.SOUTH, panelBottom);

    // define menu shortcuts
    menuFile.setMnemonic('F');
    menuItemExit.setMnemonic('E');

    menuOptions.setMnemonic('O');
    menuItemProgressBar.setMnemonic('P');

    menuHelp.setMnemonic('H');
    menuItemHelp.setMnemonic('H');
    menuItemInfo.setMnemonic('I');

    // == ActionListener of the menu ==
    menuItemExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        dispose();
        System.exit(0);
      }
    });

    menuItemProgressBar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if (loadProgressBar) {
          loadProgressBar = false;
          progressBar.setVisible(false);
          menuItemProgressBar.setText("Enable ProgressBar");
        } else {
          loadProgressBar = true;
          progressBar.setValue(0);
          progressBar.setStringPainted(false);
          progressBar.setVisible(true);
          menuItemProgressBar.setText("Disable ProgressBar");
        }
        repaint();
      }
    });

    // == ActionListener of the menu ==

    // build menu
    menuFile.add(menuItemExit);

    menuOptions.add(menuItemProgressBar);

    menuHelp.add(menuItemHelp);
    menuHelp.add(menuItemInfo);

    menuBarcalculator.add(menuFile);
    menuBarcalculator.add(menuOptions);
    menuBarcalculator.add(menuHelp);

    // place menu on the frame
    setJMenuBar(menuBarcalculator);

    // generate frame correctly
    pack();

    // select text in textField to enter a formula directly
    textTermInput.selectAll();

    // set the "Calculate"-button as defaultButton to activate
    // enter-functionality
    getRootPane().setDefaultButton(buttonCalculateTerm);

    // disable resizing the frame
    setResizable(false);
  }

  /**
   * Actionlistener for the calculator button
   * 
   * @param aButton
   */
  private void addCalculatorButtonListener(JButton aButton) {

    aButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent ae) {

        // convert the user's input to standard string
        try {
          convertedFormula = ConverterUtil.termToStandardString(textTermInput.getText());

          // if the formula has Variables, a new frame is opened
          if (ConverterUtil.hasVariables(convertedFormula)) {

            listOfVariables = ConverterUtil.getVariables(convertedFormula);

            // openVariableDialog();
            dialogEnterVariables.load(listOfVariables);

            // otherwise the formula is calculated directly
          } else {

            // use the progressBar?
            if (loadProgressBar) {
              Thread tmpProgressBarThread = new ProgressBarThread(progressBar);
              tmpProgressBarThread.start();
            }

            // reset the list to avoid errors
            listOfVariables = new ArrayList<String[]>();

            calculateFormula(FrameCalculator.this);
          }

        } catch (Exception e) {

          JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
              JOptionPane.WARNING_MESSAGE);
        }

      }

    });
  }

  /**
   * calculates the formula
   * @param aFrameCalculator 
   * 
   * @param aFormula
   */
  public static void calculateFormula(FrameCalculator aFrameCalculator) {

    // puts the ArrayList into the dictionary
    dictionaryOfEnteredVariables = ConverterUtil.putArrayListIntoHashtable(aFrameCalculator.getListOfVariables());

    // calculate!
    try {
      Tree tmpTree = FormulaTreeUtil.BuildTree(aFrameCalculator.getConvertedFormula());
      calculatedFormula = "" + FormulaTreeUtil.EvaluateTree(tmpTree, dictionaryOfEnteredVariables);
      if (!loadProgressBar) {
        textFormulaOutput.setText(calculatedFormula);
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  /**
   * 
   */
  public static void showCalculation() {
    textFormulaOutput.setText(calculatedFormula);
  }

  /**
   * @return the progressBar
   */
  public boolean getProgressBarStatus() {
    return loadProgressBar;
  }

  /**
   * @return the progressBar
   */
  public JProgressBar getProgressBar() {
    return progressBar;
  }

  /**
   * @return the progressBar
   */
  public String getConvertedFormula() {
    return convertedFormula;
  }

  /**
   * @return the listOfVariables
   */
  public ArrayList<String[]> getListOfVariables() {
    return listOfVariables;
  }

  /**
   * @param aListOfVariables the listOfVariables to set
   */
  public void setListOfVariables(ArrayList<String[]> aListOfVariables) {
    listOfVariables = aListOfVariables;
  }
}
