package calculator.userinterface.swing;

import javax.swing.JProgressBar;

/**
 * the progress bar
 */
public class ProgressBar extends Thread {

  // defines the speed of "loading"
  private static int DELAY = 10;

  private JProgressBar progressBar;

  /**
   * The constructor
   * 
   * @param aParentFrame
   */
  public ProgressBar(FrameCalculator aParentFrame) {
    progressBar = aParentFrame.getProgressBar();
  }

  /**
   * starts the "loading"
   */
  public void run() {

    // is called by start() !!!

    // reset the progressBar
    progressBar.setValue(0);
    progressBar.setString("0%");
    progressBar.setStringPainted(true);

    // get min and max Values
    int minimum = progressBar.getMinimum();
    int maximum = progressBar.getMaximum();

    if (FrameCalculator.isPlaySound()) {
      Flam tmpFlam = new Flam();
      tmpFlam.start();
    }

    // increase value by 1 in the defined speed
    for (int i = minimum; i < maximum; i++) {
      try {
        int value = progressBar.getValue();
        progressBar.setValue(value + 1);
        progressBar.setString(value + "%");

        Thread.sleep(DELAY);
      } catch (InterruptedException e) {
      }
    }
    progressBar.setString("100%");

    // when finished, show the result
    FrameCalculator.calculateFormula((FrameCalculator) progressBar.getParent().getParent().getParent()
        .getParent().getParent());
  }
}
