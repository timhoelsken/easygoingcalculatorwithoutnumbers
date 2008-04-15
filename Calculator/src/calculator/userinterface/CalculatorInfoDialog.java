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
 * 
 * @author Tim
 * 
 */
public class CalculatorInfoDialog extends JDialog {

  private JLabel labelTitle = new JLabel("Info:");
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
  public CalculatorInfoDialog(JFrame aParentFrame) {

    // define dialog window
    super(aParentFrame, "Info", Dialog.ModalityType.DOCUMENT_MODAL);
    setLocation(330, 330);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));

    String tmpHelpTextContent = new String("");
    tmpHelpTextContent = "This formula calculator is brought to you by PSE III group 3:\n\n"+
    "- André Wuthenow\n" +
    "- Raphael Zimmer\n" +
    "- Tim Hölsken\n" +
    "- Tobias Wörenkämper";

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
