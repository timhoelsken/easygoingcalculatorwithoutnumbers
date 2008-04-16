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
public class FrameCalculatorTreeDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  private JLabel labelTitle = new JLabel("Tree:");
  private JTextArea textAreaContent = new JTextArea();
  private JButton buttonClose = new JButton("Close");

  /**
   * The Constructor
   * 
   * @param aParentFrame
   */
  public FrameCalculatorTreeDialog(FrameCalculator aParentFrame) {

    // define dialog window
    super(aParentFrame, "Tree", Dialog.ModalityType.DOCUMENT_MODAL);
    setLocation(330, 330);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));

    // add ActionListener to Close-Button
    ActionListenerUtil.putDialogCloseListener(this, buttonClose);
  }

  /**
   * Paints the tree in the dialog
   * 
   * @param aParentFrame
   */
  public void paintTree(FrameCalculator aParentFrame) {

    String tmpHelpTextContent = new String("");
    tmpHelpTextContent = aParentFrame.getCalculatorTree().paintMeAsString();

    // define TextArea
    textAreaContent.setText(tmpHelpTextContent);
    textAreaContent.setEnabled(false);
    textAreaContent.setDisabledTextColor(Color.BLACK);
    textAreaContent.setBackground(aParentFrame.getBackground());

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

    setVisible(true);
  }
}
