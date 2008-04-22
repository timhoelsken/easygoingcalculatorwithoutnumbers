/**
 *
 */
package calculator.userinterface.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.JWindow;

/**
 * the splashScreen
 */
public class SplashScreen extends JWindow implements Runnable {

  private static final long serialVersionUID = -7714038087742768149L;

  /**
   * runs the splashscreen
   */
  public void run() {

    // define screen and set location
    setSize(600, 300);
    setLocationRelativeTo(null);
    setVisible(true);

    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      dispose();
    }
    dispose();
  }

  /**
   * shows us the splashscreen
   *
   * @param g
   */
  public void paint(Graphics g) {

    // load image
    ClassLoader tmpClassLoader = this.getClass().getClassLoader();
    URL tmpUrl = tmpClassLoader.getResource("SplashScreen.jpg");
    Image tmpSplashImage = getToolkit().getImage(tmpUrl);

    g.drawImage(tmpSplashImage, 0, 0, this);
  }
}
