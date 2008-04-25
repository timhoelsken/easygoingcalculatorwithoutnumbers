package calculator.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;

import calculator.userinterface.swing.AboutDialog;
import calculator.userinterface.swing.FrameCalculator;
import calculator.userinterface.swing.ManualDialog;

/**
 * an util class for adding listeners to the FrameCalculator
 */
public final class ActionListenerUtil {

  /**
   * Listener for closing a dialog with a button
   * 
   * @param aDialog
   * @param aButton
   */
  public static void putButtonDialogCloseListener(final JDialog aDialog, JButton aButton) {
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        FrameCalculator.enableButtonCalculateFormula();
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

        if (aDialog instanceof AboutDialog) {
          AboutDialog tmpAboutDialog = (AboutDialog) aDialog;
          tmpAboutDialog.load();
        } else if (aDialog instanceof ManualDialog) {
          ManualDialog tmpManualDialog = (ManualDialog) aDialog;
          tmpManualDialog.load();
        } else {
          aDialog.setVisible(true);
        }
      }
    });
  }

  /**
   * Listener for enableing the calculateButton when closing a Dialog
   * 
   * @param aDialog
   */
  public static void putFrameDialogCloseListener(JDialog aDialog) {

    aDialog.addWindowListener(new WindowListener() {

      /**
       * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
       */
      public void windowActivated(WindowEvent aE) {
        //

      }

      /**
       * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
       */
      public void windowClosed(WindowEvent aE) {
        //

      }

      /**
       * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
       */
      public void windowClosing(WindowEvent aE) {
        FrameCalculator.enableButtonCalculateFormula();
      }

      /**
       * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
       */
      public void windowDeactivated(WindowEvent aE) {
        //

      }

      /**
       * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
       */
      public void windowDeiconified(WindowEvent aE) {
        //

      }

      /**
       * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
       */
      public void windowIconified(WindowEvent aE) {
        //

      }

      /**
       * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
       */
      public void windowOpened(WindowEvent aE) {
        //

      }
    });
  }
}
