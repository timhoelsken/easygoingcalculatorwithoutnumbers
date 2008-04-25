package calculator.userinterface.swing;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculator.utils.ActionListenerUtil;
import calculator.utils.ConverterUtil;
import calculator.utils.MathUtil;

/**
 * the variable dialog
 */
public class VariableDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  // textField
  private JTextField[] inputFieldsOfVariablesArray = new JTextField[0];

  // labels
  private JLabel[] labelsOfVariablesArray = new JLabel[0];
  private JLabel labelTitle = new JLabel();

  // buttons
  private JButton buttonEnter = new JButton("Enter");
  private JButton buttonCancel = new JButton("Cancel");

  // sub panels
  private JPanel panelButton = new JPanel();
  private JPanel panelLabelsForVariable = new JPanel();
  private JPanel panelTextFieldsForVariables = new JPanel();

  /**
   * The constructor
   *
   * @param aParentFrame
   */
  public VariableDialog(FrameCalculator aParentFrame) {

    // define modal dialog
    super(aParentFrame, "Variable Input", Dialog.ModalityType.DOCUMENT_MODAL);
    getContentPane().setLayout(new BorderLayout(10, 10));
    ActionListenerUtil.putFrameDialogCloseListener(this);

    buttonEnter.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        boolean tmpAllVariablesAreFloats = true;

        // check if each variable is a float value and write the
        // variables in the list
        for (int i = 0; i < VariableDialog.this.getInputFieldsOfVariablesArray().length; i++) {

          if (MathUtil.isDouble(ConverterUtil
              .unifyCommas(VariableDialog.this.getInputFieldsOfVariablesArray()[i].getText()))) {
            FrameCalculator.listOfVariables.get(i)[1] = ConverterUtil.unifyCommas(VariableDialog.this
                .getInputFieldsOfVariablesArray()[i].getText());
          } else {
            tmpAllVariablesAreFloats = false;
            i = VariableDialog.this.getInputFieldsOfVariablesArray().length;
          }
        }

        // if everything is OK, the window is closed and the formula is
        // calculated
        if (tmpAllVariablesAreFloats) {
          VariableDialog.this.dispose();

          // use the progressBar?
          if (FrameCalculator.isLoadProgressBar()) {
            Thread tmpProgressBarThread = new ProgressBar(VariableDialog.this.getParentFrame());
            tmpProgressBarThread.start();
          } else {
            FrameCalculator.calculateFormula(VariableDialog.this.getParentFrame());

          }
        } else {
          String tmpValue = "value";
          if (getInputFieldsOfVariablesArray().length > 1) {
            tmpValue += "s";
          }
          JOptionPane.showMessageDialog(new JFrame(), "The entered " + tmpValue + " must be numeric.",
              "An error occured!", JOptionPane.WARNING_MESSAGE);
        }
      }
    });

    ActionListenerUtil.putButtonDialogCloseListener(this, buttonCancel);
  }

  /**
   * dynamically place the variable inputs on the dialog
   *
   * @param aListOfVariables
   */
  public void load() {

    // set location of the dialog
    Point tmpPoint = this.getParent().getLocationOnScreen();
    int tmpX = tmpPoint.x + getParent().getWidth();
    int tmpY = tmpPoint.y;
    setLocation(tmpX, tmpY);

    // define Panels for the button and dynamic variable input
    if (FrameCalculator.listOfVariables.size() > 1) {
      panelButton = new JPanel(new GridLayout(FrameCalculator.listOfVariables.size(), 1));
      panelLabelsForVariable = new JPanel(new GridLayout(FrameCalculator.listOfVariables.size(), 1));
      panelTextFieldsForVariables = new JPanel(new GridLayout(FrameCalculator.listOfVariables.size(), 1));
    } else {
      panelButton = new JPanel(new GridLayout(2, 1));
      panelLabelsForVariable = new JPanel(new GridLayout(2, 1));
      panelTextFieldsForVariables = new JPanel(new GridLayout(2, 1));
    }

    // an arraylist in the size of variable number
    labelsOfVariablesArray = new JLabel[FrameCalculator.listOfVariables.size()];

    // the attribute of variableDialog is redimensioned to the size of
    // variable
    // number
    inputFieldsOfVariablesArray = new JTextField[FrameCalculator.listOfVariables.size()];

    // each variable gets its Label and textField, which is centered
    // horizontally and then put on the spezific panel
    for (int i = 0; i < FrameCalculator.listOfVariables.size(); i++) {

      // define label and textField
      labelsOfVariablesArray[i] = new JLabel(FrameCalculator.listOfVariables.get(i)[0] + " = ");
      inputFieldsOfVariablesArray[i] = new JTextField(3);

      // the variable already has a value, it is set in the textField
      if (FrameCalculator.listOfVariables.get(i)[1] != null) {
        inputFieldsOfVariablesArray[i].setText(FrameCalculator.listOfVariables.get(i)[1]);
      } else {
        inputFieldsOfVariablesArray[i].setText("");
      }

      // align elements
      labelsOfVariablesArray[i].setHorizontalAlignment(JLabel.CENTER);
      inputFieldsOfVariablesArray[i].setHorizontalAlignment(JTextField.CENTER);

      // put elements on panel
      panelLabelsForVariable.add(labelsOfVariablesArray[i]);
      panelTextFieldsForVariables.add(inputFieldsOfVariablesArray[i]);
      panelButton.add(buttonEnter);
      panelButton.add(buttonCancel);
    }

    // define title
    String tmpValue = "value";
    String tmpVariable = "variable";
    if (inputFieldsOfVariablesArray.length > 1) {
      tmpValue += "s";
      tmpVariable += "s";
    }
    labelTitle.setText("      Enter " + tmpValue + " of " + tmpVariable + ":      ");

    // align elements
    labelTitle.setHorizontalAlignment(JLabel.CENTER);
    buttonEnter.setVerticalAlignment(JButton.NORTH);

    // set the "Enter"-button as defaultButton to activate
    // enter-functionality
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

    // disable resizing the dialog and display it
    setResizable(false);
    setVisible(true);
  }

  /**
   *
   * @return the FrameCalculator
   */
  public FrameCalculator getParentFrame() {
    return ((FrameCalculator) this.getParent());
  }

  /**
   * @return the inputFieldsOfVariablesArray
   */
  public JTextField[] getInputFieldsOfVariablesArray() {
    return inputFieldsOfVariablesArray;
  }

}
