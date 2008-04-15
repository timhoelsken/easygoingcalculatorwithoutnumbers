package calculator.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;

/**
 */
public class ActionListenerUtil {

  /**
   * @param aDialog
   * @param aButton
   * 
   */
  public static void setDialogCloseListener(final JDialog aDialog, JButton aButton) {
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        aDialog.setVisible(false);
        aDialog.dispose();
      }
    });
  }

  /**
   * 
   * @param aMenuItem
   * @param aDialog
   */
  public static void setMenuItemOpenDialogListener(JMenuItem aMenuItem, final JDialog aDialog) {
    aMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        aDialog.setVisible(true);
      }
    });
  }

}
