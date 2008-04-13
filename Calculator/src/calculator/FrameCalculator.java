package calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JOptionPane;

import calculator.elements.Tree;
import calculator.utils.ConverterUtil;
import calculator.utils.MathUtil;

/**
 * This openes the calculator in a frame.
 * 
 * @author Tim
 * 
 */
public class FrameCalculator extends JFrame {

  private static final long serialVersionUID = -4971700820441624660L;

  // button
  private JButton buttonCalculateTerm = new JButton("calculate");

  // labels
  private JLabel labelCalculatorTitle = new JLabel("PSE III Calculator");
  private JLabel labelEnterFormula = new JLabel("Formula:");
  private JLabel labelResult = new JLabel("RESULT:");

  // input / output fields
  private static JTextField textTermInput = new JTextField(16);
  private static JTextField textFormulaOutput = new JTextField();

  // sub panel
  private JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

  // list and dictionary for variables
  private static ArrayList<String[]> listOfVariables = new ArrayList<String[]>();
  private static Hashtable<String, Double> dictionaryOfEnteredVariables = new Hashtable<String, Double>();

  // an array of textfields for dynamic variableinput
  private JTextField[] inputFieldOfVariablesArray = new JTextField[0];

  // the formula entered by the user
  private String enteredFormula = new String("");

  // Variable Frame
  private JFrame frameEnterVariables = new JFrame();

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
    labelEnterFormula.setVerticalAlignment((JLabel.CENTER));
    buttonCalculateTerm.setVerticalAlignment(JButton.CENTER);
    labelResult.setHorizontalAlignment(JLabel.LEFT);
    textFormulaOutput.setHorizontalAlignment(JTextField.CENTER);

    // add listener to calculateButton
    addCalculatorButtonListener(buttonCalculateTerm);

    // disable result textField
    textFormulaOutput.setEditable(false);

    // place label and textField on panel
    bottomPanel.add(labelResult);
    bottomPanel.add(textFormulaOutput);

    // place labels, textField, button and panel on frame
    getContentPane().add(BorderLayout.NORTH, labelCalculatorTitle);
    getContentPane().add(BorderLayout.WEST, labelEnterFormula);
    getContentPane().add(BorderLayout.CENTER, textTermInput);
    getContentPane().add(BorderLayout.EAST, buttonCalculateTerm);
    getContentPane().add(BorderLayout.SOUTH, bottomPanel);

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

        if (!frameEnterVariables.isVisible()) {
          // convert the user's input to standard string
          try {
            enteredFormula = ConverterUtil.termToStandardString(textTermInput.getText());

            // if the formula has Variables, a new frame is opened
            if (ConverterUtil.hasVariables(enteredFormula)) {

              listOfVariables = ConverterUtil.getVariables(enteredFormula);

              openVariableFrame();

              // otherwise the formula is calculated directly
            } else {

              // reset the list to avoid errors
              listOfVariables = new ArrayList<String[]>();

              calculateFormula(enteredFormula);
            }

          } catch (Exception e) {

            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
                JOptionPane.WARNING_MESSAGE);
          }

        }
      }
    });
  }

  /**
   * Opens the additional frame to enter the variables
   */
  private void openVariableFrame() {

    // define frame
    frameEnterVariables = new JFrame("Variable(s)");
    frameEnterVariables.setLocation(330, 330);
    frameEnterVariables.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameEnterVariables.getContentPane().setLayout(new BorderLayout(10, 10));

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
    frameEnterVariables.getContentPane().add(BorderLayout.NORTH, tmpTitle);
    frameEnterVariables.getContentPane().add(BorderLayout.WEST, tmpPanelOfVariableLabels);
    frameEnterVariables.getContentPane().add(BorderLayout.CENTER, tmpPanelOfVariableTextFields);
    frameEnterVariables.getContentPane().add(BorderLayout.EAST, tmpButtonPanel);

    // generate frame correctly
    frameEnterVariables.pack();

    // select text in textField to enter a formula directly
    frameEnterVariables.setVisible(true);

    // set the "Enter"-button as defaultButton to activate enter-functionality
    frameEnterVariables.getRootPane().setDefaultButton(tmpButton);
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
          frameEnterVariables.dispose();
          if (!enteredFormula.equals(textTermInput.getText())) {
            textTermInput.setText(enteredFormula);
          }
          calculateFormula(enteredFormula);
        } else {
          JOptionPane.showMessageDialog(new JFrame(), "The entered value(s) must be a number.",
              "An error occured!", JOptionPane.WARNING_MESSAGE);
        }
      }
    });
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
      Tree tmpTree = FormulaTree.BuildTree(aFormula);
      textFormulaOutput.setText("" + FormulaTree.EvaluateTree(tmpTree, dictionaryOfEnteredVariables));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
          JOptionPane.WARNING_MESSAGE);
    }
  }

}
