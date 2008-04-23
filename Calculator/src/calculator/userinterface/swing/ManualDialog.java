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
 * the manual dialog
 */
public class ManualDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel labelTitle = new JLabel("Help:");
	private JTextArea textAreaContent = new JTextArea();
	private JButton buttonClose = new JButton("Close");

	/**
	 * The constructor
	 * 
	 * @param aParentFrame
	 */
	public ManualDialog(JFrame aParentFrame) {

		// define dialog window
		super(aParentFrame, "Help", Dialog.ModalityType.DOCUMENT_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(10, 10));
	}

	/**
	 * loads the manual dialog
	 */
	public void load() {

		// set text content
		String tmpHelpTextContent = new String("");
		tmpHelpTextContent = "How to use the calculator:\n" + "Enter a formula in the input field.\n"
				+ "You can put in the following as part of the formula:\n\n"
				+ "- real numbers (negative numbers have to be in brackets)\n"
				+ "- operators \"+ - * \\ ^\"\n" + "- functions \"sin() cos() tan() sqrt()\"\n"
				+ "- variables\n\n"
				+ "If your formula contains variables, you are prompted to enter their value.\n";

		// define TextArea
		textAreaContent.setText(tmpHelpTextContent);
		textAreaContent.setEnabled(false);
		textAreaContent.setDisabledTextColor(Color.BLACK);
		textAreaContent.setBackground(getParent().getBackground());

		// add ActionListener to Close-Button
		ActionListenerUtil.putButtonDialogCloseListener(this, buttonClose);

		// align elements
		labelTitle.setHorizontalAlignment(JLabel.LEFT);
		buttonClose.setHorizontalAlignment(JButton.CENTER);

		// place label, textField, button on frame
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

		// set the "Enter"-button as defaultButton to activate
		// enter-functionality
		getRootPane().setDefaultButton(buttonClose);

		// disable resizing the dialog and display it
		setResizable(false);
		setVisible(true);
	}
}
