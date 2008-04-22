package calculator.userinterface.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import calculator.utils.ActionListenerUtil;

/**
 * the about dialog
 * 
 * @author Tim
 * 
 */
public class FrameCalculatorAboutDialog extends JDialog {

  private JLabel labelTitle = new JLabel("About:");
  private JTextArea textAreaContent = new JTextArea();
  private JButton buttonClose = new JButton("Close");

  private static final long serialVersionUID = 1L;

  /**
   * 
   * @param aParentFrame
   */
  public FrameCalculatorAboutDialog(JFrame aParentFrame) {

    // define dialog window
    super(aParentFrame, "Info", Dialog.ModalityType.DOCUMENT_MODAL);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));
  }

  /**
   * loads the about dialog
   */
  public void load() {

    // set text content
    String tmpHelpTextContent = new String("");
    tmpHelpTextContent = "This formula calculator is brought to you by PSE III group 3:\n\n"
        + "- Tim Hölsken\n" + "- André Wuthenow\n" + "- Tobias Wörenkämper\n" + "- Raphael Zimmer";

    // define TextArea
    textAreaContent.setText(tmpHelpTextContent);
    textAreaContent.setEnabled(false);
    textAreaContent.setDisabledTextColor(Color.BLACK);
    textAreaContent.setBackground(getParent().getBackground());

    // add ActionListener to Close-Button
    ActionListenerUtil.putDialogCloseListener(this, buttonClose);

    // align elements
    labelTitle.setHorizontalAlignment(JLabel.LEFT);
    buttonClose.setHorizontalAlignment(JButton.CENTER);

    // place label, textField, button on dialog
    getContentPane().add(BorderLayout.NORTH, labelTitle);
    getContentPane().add(BorderLayout.CENTER, textAreaContent);
    getContentPane().add(BorderLayout.SOUTH, buttonClose);

    // generate frame correctly
    pack();

    // set location of the dialog
    Point tmpPoint = getParent().getLocationOnScreen();
    int tmpX = tmpPoint.x + getParent().getWidth() / 2 - getWidth() / 2;
    int tmpY = tmpPoint.y;
    setLocation(tmpX, tmpY);

    // set the "Enter"-button as defaultButton to activate enter-functionality
    getRootPane().setDefaultButton(buttonClose);

    // disable resizing the dialog and display it
    setResizable(false);
    setVisible(true);
  }
}
