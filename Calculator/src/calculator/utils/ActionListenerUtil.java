package calculator.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;

/**
 * @author Tim
 */
public class ActionListenerUtil {
  
  /**
   * Listener for closing a dialog with a button
   *
   * @param aDialog
   * @param aButton
   */
  public static void putDialogCloseListener(final JDialog aDialog, JButton aButton) {
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        aDialog.setVisible(false);
        aDialog.dispose();
      }
    });
  }

  /**
   * Listener for opening a dialog via a menuItem
   *
   * @param aMenuItem
   * @param aDialog
   */
  public static void putMenuItemOpenDialogListener(JMenuItem aMenuItem, final JDialog aDialog) {
    aMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        aDialog.setVisible(true);
      }
    });
  }
}
