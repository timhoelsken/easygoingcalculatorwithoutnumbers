package calculator.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import calculator.userinterface.FrameCalculator;
import calculator.userinterface.FrameCalculatorTreeDialog;
import calculator.userinterface.FrameCalculatorVariableDialog;
import calculator.userinterface.ProgressBarThread;

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

  /**
   * Opens the dialog containing the drawn tree
   *
   * @param aMenuItem
   * @param aDialog
   * @param aParentFrame
   */
  public static void setMenuItemOpenTreeDialogListener(JMenuItem aMenuItem,
      final FrameCalculatorTreeDialog aDialog, final FrameCalculator aParentFrame) {
    aMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        aDialog.paintTree(aParentFrame);
        aDialog.setVisible(true);
      }
    });
  }

  /**
   * Listener to open the variable input dialog
   *
   * @param aDialogsParentFrame
   * @param aDialog
   * @param aButton
   */
  public static void setVariableCalculateListener(final FrameCalculator aDialogsParentFrame,
      final FrameCalculatorVariableDialog aDialog, JButton aButton) {
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        boolean tmpAllVariablesAreFloats = true;

        // check if each variable is a float value and write the variables in
        // the list
        for (int i = 0; i < aDialog.getInputFieldsOfVariablesArray().length; i++) {

          if (MathUtil.isDouble(ConverterUtil.unifyCommas(aDialog.getInputFieldsOfVariablesArray()[i].getText()))) {
            aDialogsParentFrame.getListOfVariables().get(i)[1] = ConverterUtil
                .unifyCommas(aDialog.getInputFieldsOfVariablesArray()[i].getText());
          } else {
            tmpAllVariablesAreFloats = false;
            i = aDialog.getInputFieldsOfVariablesArray().length;
          }
        }

        // if everything is OK, the window is closed and the formula is
        // calculated
        if (tmpAllVariablesAreFloats) {
          aDialog.dispose();

          // use the progressBar?
          if (aDialogsParentFrame.getProgressBarStatus()) {
            Thread tmpProgressBarThread = new ProgressBarThread(aDialogsParentFrame);
            tmpProgressBarThread.start();
          }

          FrameCalculator.calculateFormula(aDialogsParentFrame);
        } else {
          JOptionPane.showMessageDialog(new JFrame(), "The entered value(s) must be number(s).",
              "An error occured!", JOptionPane.WARNING_MESSAGE);
        }
      }
    });
  }

  /**
   * Listener to start the calculation
   *
   * @param aParentFrame
   * @param aButton
   */
  public static void putCalculateFormulaListener(final FrameCalculator aParentFrame, JButton aButton) {
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {


        // use the progressBar?
        if (FrameCalculator.isLoadProgressBar()) {
          Thread tmpProgressBarThread = new ProgressBarThread(aParentFrame);
          tmpProgressBarThread.start();
        }
        else{
          FrameCalculator.convertAndCalculate(aParentFrame);
        }
      }
    });
  }

  /**
   * Listener to close the FrameCalculator
   *
   * @param aParentFrame
   * @param aMenuItem
   */
  public static void putFrameCalculatorCloseListener(final FrameCalculator aParentFrame, JMenuItem aMenuItem) {
    aMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        aParentFrame.setVisible(false);
        aParentFrame.dispose();
        System.exit(0);
      }
    });
  }

  /**
   * Listener to activate / deactivate the progressbar
   *
   * @param aParentFrame
   * @param aMenuItem
   */
  public static void putProgressBarActivateListener(final FrameCalculator aParentFrame,
      final JMenuItem aMenuItem) {
    aMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        if (FrameCalculator.isLoadProgressBar()) {
          FrameCalculator.setLoadProgressBar(false);
          aParentFrame.getProgressBar().setVisible(false);
          aMenuItem.setText("Enable ProgressBar");
        } else {
          FrameCalculator.setLoadProgressBar(true);
          aParentFrame.getProgressBar().setValue(0);
          aParentFrame.getProgressBar().setStringPainted(false);
          aParentFrame.getProgressBar().setVisible(true);
          aMenuItem.setText("Disable ProgressBar");
        }
        aParentFrame.repaint();
      }
    });
  }

  /**
   * Listener to show / hide the painted tree
   *
   * @param aParentFrame
   * @param aMenuItem
   */
  public static void putShowTreeActivateListener(final FrameCalculator aParentFrame, final JMenuItem aMenuItem) {
    aMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        if (FrameCalculator.isDisplayTree()) {
          FrameCalculator.setDisplayTree(false);
          aMenuItem.setText("Show Tree");
        } else {
          FrameCalculator.setDisplayTree(true);
          aMenuItem.setText("Hide Tree");
        }
      }
    });
  }

  /**
   * @param aParentFrame
   * @param aButton
   *
   */
  public static void putCancelListener(final FrameCalculatorVariableDialog aParentFrame, JButton aButton){
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        aParentFrame.setVisible(false);
        aParentFrame.dispose();
      }
    });
  }
}
