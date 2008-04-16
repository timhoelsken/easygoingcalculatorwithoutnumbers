package calculator.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import calculator.utils.ActionListenerUtil;

/**
 */
public class CalculatorHelpDialog extends JDialog {

  private JLabel labelTitle = new JLabel("Help:");
  private JTextArea textAreaContent = new JTextArea();
  private JButton buttonClose = new JButton("Close");
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   *
   * @param aParentFrame
   */
  public CalculatorHelpDialog(JFrame aParentFrame) {

    // define dialog window
    super(aParentFrame, "Help", Dialog.ModalityType.DOCUMENT_MODAL);
    setLocation(330, 330);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));

    String tmpHelpTextContent = new String("");
    tmpHelpTextContent = "How to use the calculator:\n" + "Enter a formula in the input field.\n"
        + "You can put in the following as part of the formula:\n\n"
        + "- real numbers (negative numbers have to be in brackets)\n" + "- operators \"+ - * \\ ^\"\n"
        + "- functions \"sin() cos() tan() sqrt()\"\n" + "- variables\n\n"
        + "If your formula contains variables, you are prompted to enter their value.\n";

    // define TextArea
    textAreaContent.setText(tmpHelpTextContent);
    textAreaContent.setEnabled(false);
    textAreaContent.setDisabledTextColor(Color.BLACK);
    textAreaContent.setBackground(aParentFrame.getBackground());

    // add ActionListener to Close-Button
    ActionListenerUtil.setDialogCloseListener(this, buttonClose);

    // align elements
    labelTitle.setHorizontalAlignment(JLabel.LEFT);
    buttonClose.setHorizontalAlignment(JButton.CENTER);

    // place label, textField, button on frame
    getContentPane().add(BorderLayout.NORTH, labelTitle);
    getContentPane().add(BorderLayout.CENTER, textAreaContent);
    getContentPane().add(BorderLayout.SOUTH, buttonClose);

    // generate frame correctly
    pack();

    // set the "Enter"-button as defaultButton to activate enter-functionality
    getRootPane().setDefaultButton(buttonClose);

    // disable resizing the dialog
    setResizable(false);
  }
}
