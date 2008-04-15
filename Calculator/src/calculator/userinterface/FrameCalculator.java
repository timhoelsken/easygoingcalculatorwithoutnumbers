package calculator.userinterface;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;
import calculator.utils.MathUtil;

/**
 * This openes the calculator in a frame.
 */
public class FrameCalculator extends JFrame {

  private static final long serialVersionUID = -4971700820441624660L;

  // button to calculate formula
  private JButton buttonCalculateTerm = new JButton("calculate");

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
  private static ArrayList<String[]> listOfVariables = new ArrayList<String[]>();
  private static Hashtable<String, Double> dictionaryOfEnteredVariables = new Hashtable<String, Double>();

  // an array of textfields for dynamic variableinput
  private JTextField[] inputFieldOfVariablesArray = new JTextField[0];

  // the formula entered by the user
  private String convertedFormula = new String("");
  /**
   *
   */
  public static String calculatedFormula = new String("");

  // Variable Dialog
  JDialog dialogEnterVariables = new JDialog();
  //TODO @Tim Dialog nicht benutzerfreundlich. Schmeiﬂt bei Zweiteingabe alle Ersteingaben weg. Doof. :)

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

    menuItemHelp.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        openHelpDialog();
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

            openVariableDialog();

            // otherwise the formula is calculated directly
          } else {

            // use the progressBar?
            if (loadProgressBar) {
              Thread tmpProgressBarThread = new ProgressBarThread(progressBar);
              tmpProgressBarThread.start();
            }

            // reset the list to avoid errors
            listOfVariables = new ArrayList<String[]>();

            calculateFormula(convertedFormula);
          }

        } catch (Exception e) {

          JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
              JOptionPane.WARNING_MESSAGE);
        }

      }

    });
  }

  /**
   * Opens the additional frame to enter the variables
   */
  private void openVariableDialog() {

    // define modal dialog
    dialogEnterVariables = new JDialog(FrameCalculator.this, "Variable(s)", Dialog.ModalityType.DOCUMENT_MODAL);
    dialogEnterVariables.setLocation(330, 330);
    dialogEnterVariables.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    dialogEnterVariables.getContentPane().setLayout(new BorderLayout(10, 10));

    // define Title
    JLabel tmpTitle = new JLabel("      Enter value(s) of variable(s):      ");

    // define button for proceeding variable input
    JButton tmpButton = new JButton("Enter");

    // define Panels for the button and dynamic variable input
    JPanel tmpButtonPanel = new JPanel(new GridLayout(listOfVariables.size(), 1));
    JPanel tmpPanelOfVariableLabels = new JPanel(new GridLayout(listOfVariables.size(), 1));
    JPanel tmpPanelOfVariableTextFields = new JPanel(new GridLayout(listOfVariables.size(), 1));

    // an arraylist in the size of variable number
    JLabel[] tmpLabelsOfVariablesArray = new JLabel[listOfVariables.size()];

    // the private attribute of the main frame is redimensioned to the size of
    // variable number
    inputFieldOfVariablesArray = new JTextField[listOfVariables.size()];

    // each variable gets its Label and textField, which is centered
    // horizontally and then put on the spezific panel
    for (int i = 0; i < listOfVariables.size(); i++) {

      // define label and textField
      tmpLabelsOfVariablesArray[i] = new JLabel(listOfVariables.get(i)[0] + " = ");
      inputFieldOfVariablesArray[i] = new JTextField(3);

      // Er machts nicht richtig! :(
      inputFieldOfVariablesArray[i].setSize(12, 30);

      // allign elements
      tmpLabelsOfVariablesArray[i].setHorizontalAlignment(JLabel.CENTER);
      inputFieldOfVariablesArray[i].setHorizontalAlignment(JTextField.CENTER);

      // put elements on panel
      tmpPanelOfVariableLabels.add(tmpLabelsOfVariablesArray[i]);
      tmpPanelOfVariableTextFields.add(inputFieldOfVariablesArray[i]);
      tmpButtonPanel.add(tmpButton);
    }

    // add Variable listener to buttom
    addVariableButtonListener(tmpButton);

    // align elements
    tmpTitle.setHorizontalAlignment(JLabel.CENTER);
    tmpButton.setVerticalAlignment(JButton.NORTH);

    // place label, textField, button on frame
    dialogEnterVariables.getContentPane().add(BorderLayout.NORTH, tmpTitle);
    dialogEnterVariables.getContentPane().add(BorderLayout.WEST, tmpPanelOfVariableLabels);
    dialogEnterVariables.getContentPane().add(BorderLayout.CENTER, tmpPanelOfVariableTextFields);
    dialogEnterVariables.getContentPane().add(BorderLayout.EAST, tmpButtonPanel);

    // generate frame correctly
    dialogEnterVariables.pack();

    // select text in textField to enter a formula directly
    dialogEnterVariables.setVisible(true);

    // set the "Enter"-button as defaultButton to activate enter-functionality
    dialogEnterVariables.getRootPane().setDefaultButton(tmpButton);

    // disable resizing the dialog
    dialogEnterVariables.setResizable(false);
  }

  /**
   * Actionlistener for the variable input button
   *
   * @param aButton
   */
  public void addVariableButtonListener(JButton aButton) {

    aButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent ae) {

        boolean tmpAllVariablesAreFloats = true;

        // check if each variable is a float value and write the variables in
        // the list
        for (int i = 0; i < inputFieldOfVariablesArray.length; i++) {
          if (MathUtil.isDouble(ConverterUtil.unifyCommas(inputFieldOfVariablesArray[i].getText()))) {
            listOfVariables.get(i)[1] = ConverterUtil.unifyCommas(inputFieldOfVariablesArray[i].getText());
          } else {
            tmpAllVariablesAreFloats = false;
            i = inputFieldOfVariablesArray.length;
          }
        }

        // if everything is OK, the window is closed and the formula is
        // calculated
        if (tmpAllVariablesAreFloats) {
          dialogEnterVariables.dispose();

          // use the progressBar?
          if (loadProgressBar) {
            Thread tmpProgressBarThread = new ProgressBarThread(progressBar);
            tmpProgressBarThread.start();
          }
          calculateFormula(convertedFormula);
        } else {
          JOptionPane.showMessageDialog(new JFrame(), "The entered value(s) must be number(s).",
              "An error occured!", JOptionPane.WARNING_MESSAGE);
        }
      }
    });
  }

  private static void openHelpDialog() {
    // TODO Display Help-Dialog

  }

  /**
   * calculates the formula
   *
   * @param aFormula
   */
  public static void calculateFormula(String aFormula) {

    // puts the ArrayList into the dictionary
    dictionaryOfEnteredVariables = ConverterUtil.putArrayListIntoHashtable(listOfVariables);

    // calculate!
    try {
      Tree tmpTree = FormulaTreeUtil.BuildTree(aFormula);
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
}
