package calculator.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import calculator.userinterface.CalculatorVariableDialog;
import calculator.userinterface.FrameCalculator;
import calculator.userinterface.ProgressBarThread;

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

  /**
   * 
   * @param aDialogsParentFrame 
   * @param aDialog 
   * @param aButton
   */
  public static void setVariableCalculateListener(final FrameCalculator aDialogsParentFrame, final CalculatorVariableDialog aDialog, JButton aButton) {
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        boolean tmpAllVariablesAreFloats = true;
        
                // check if each variable is a float value and write the variables in
                // the list
                for (int i = 0; i < aDialog.inputFieldsOfVariablesArray.length; i++) {
                  if (MathUtil.isDouble(ConverterUtil.unifyCommas(aDialog.inputFieldsOfVariablesArray[i].getText()))) {
                    aDialogsParentFrame.getListOfVariables().get(i)[1] = ConverterUtil.unifyCommas(aDialog.inputFieldsOfVariablesArray[i].getText());
                  } else {
                    tmpAllVariablesAreFloats = false;
                    i = aDialog.inputFieldsOfVariablesArray.length;
                  }
                }
        
                // if everything is OK, the window is closed and the formula is
                // calculated
                if (tmpAllVariablesAreFloats) {
                  aDialog.dispose();
        
                  // use the progressBar?
                  if (aDialogsParentFrame.getProgressBarStatus()) {
                    Thread tmpProgressBarThread = new ProgressBarThread(aDialogsParentFrame.getProgressBar());
                    tmpProgressBarThread.start();
                  }
                  FrameCalculator.calculateFormula(aDialogsParentFrame);
                } else {
                  JOptionPane.showMessageDialog(new JFrame(), "The entered value(s) must be number(s).",
                      "An error occured!", JOptionPane.WARNING_MESSAGE);
                }
        
        aDialog.setVisible(false);
        aDialog.dispose();
      }
    });
  }
}
