package calculator.userinterface;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculator.utils.ActionListenerUtil;

/**
 * 
 * @author Tim
 * 
 */
public class FrameCalculatorVariableDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  private JTextField[] inputFieldsOfVariablesArray = new JTextField[0];

  private JLabel[] labelsOfVariablesArray = new JLabel[0];
  private JLabel labelTitle = new JLabel("      Enter value(s) of variable(s):      ");

  private JButton buttonEnter = new JButton("Enter");
  private JButton buttonCancel = new JButton("Cancel");

  private JPanel panelButton = new JPanel();
  private JPanel panelLabelsForVariable = new JPanel();
  private JPanel panelTextFieldsForVariables = new JPanel();

  /**
   * The constructor
   * 
   * @param aParentFrame
   */
  public FrameCalculatorVariableDialog(FrameCalculator aParentFrame) {

    // define modal dialog
    super(aParentFrame, "Variable(s)", Dialog.ModalityType.DOCUMENT_MODAL);
    setLocation(330, 330);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));

    ActionListenerUtil.setVariableCalculateListener(aParentFrame, this, buttonEnter);
    ActionListenerUtil.putCancelListener(this, buttonCancel);
  }

  /**
   * dynamically place the variable inputs on the dialog
   * 
   * @param aListOfVariables
   */
  public void load(ArrayList<String[]> aListOfVariables) {

    // define Panels for the button and dynamic variable input
    panelButton = new JPanel(new GridLayout(aListOfVariables.size(), 1));
    panelLabelsForVariable = new JPanel(new GridLayout(aListOfVariables.size(), 1));
    panelTextFieldsForVariables = new JPanel(new GridLayout(aListOfVariables.size(), 1));

    // an arraylist in the size of variable number
    labelsOfVariablesArray = new JLabel[aListOfVariables.size()];

    // the private attribute of the FrameCalculator is redimensioned to the size
    // of
    // variable number
    inputFieldsOfVariablesArray = new JTextField[aListOfVariables.size()];

    // each variable gets its Label and textField, which is centered
    // horizontally and then put on the spezific panel
    for (int i = 0; i < aListOfVariables.size(); i++) {

      // define label and textField
      labelsOfVariablesArray[i] = new JLabel(aListOfVariables.get(i)[0] + " = ");
      inputFieldsOfVariablesArray[i] = new JTextField(3);

      if (aListOfVariables.get(i)[1] != null) {
        inputFieldsOfVariablesArray[i].setText(aListOfVariables.get(i)[1]);
      } else {
        inputFieldsOfVariablesArray[i].setText("");
      }

      // allign elements
      labelsOfVariablesArray[i].setHorizontalAlignment(JLabel.CENTER);
      inputFieldsOfVariablesArray[i].setHorizontalAlignment(JTextField.CENTER);

      // put elements on panel
      panelLabelsForVariable.add(labelsOfVariablesArray[i]);
      panelTextFieldsForVariables.add(inputFieldsOfVariablesArray[i]);
      panelButton.add(buttonEnter);
      panelButton.add(buttonCancel);
    }

    // align elements
    labelTitle.setHorizontalAlignment(JLabel.CENTER);
    buttonEnter.setVerticalAlignment(JButton.NORTH);

    // set the "Enter"-button as defaultButton to activate enter-functionality
    getRootPane().setDefaultButton(buttonEnter);

    // reset the dialog to avoid errors
    getContentPane().removeAll();

    // place label, textField, button on frame
    getContentPane().add(BorderLayout.NORTH, labelTitle);
    getContentPane().add(BorderLayout.WEST, panelLabelsForVariable);
    getContentPane().add(BorderLayout.CENTER, panelTextFieldsForVariables);
    getContentPane().add(BorderLayout.EAST, panelButton);

    // generate frame correctly
    pack();

    // disable resizing the dialog
    setResizable(false);

    setVisible(true);
  }

  /**
   * @return the inputFieldsOfVariablesArray
   */
  public JTextField[] getInputFieldsOfVariablesArray() {
    return inputFieldsOfVariablesArray;
  }

}
