package calculator.userinterface;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Listener to close variable dialog and enable calculate button again!
 * @author Tim
 * 
 */
public class FrameCalculatorCloseVariableDialog implements WindowListener {

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
   */
  @Override
  public void windowActivated(WindowEvent aE) {
    // 

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
   */
  @Override
  public void windowClosed(WindowEvent aE) {
    // 

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
   */
  @Override
  public void windowClosing(WindowEvent aE) {
    FrameCalculator.enableButtonCalculateFormula();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
   */
  @Override
  public void windowDeactivated(WindowEvent aE) {
    // 

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
   */
  @Override
  public void windowDeiconified(WindowEvent aE) {
    // 

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
   */
  @Override
  public void windowIconified(WindowEvent aE) {
    // 

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
   */
  @Override
  public void windowOpened(WindowEvent aE) {
    // 

  }
}
