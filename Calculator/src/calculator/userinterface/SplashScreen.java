/**
 *
 */
package calculator.userinterface;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.JWindow;

/**
 * @author Tobias
 *
 */
public class SplashScreen extends JWindow implements Runnable {

  private static final long serialVersionUID = -7714038087742768149L;

  /**
   * runs the splashscreen
   */
  public void run() {
    setSize(600, 300);
    setLocationRelativeTo(null);
    setVisible(true);

    try {
      Thread.sleep(5000);
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
    ClassLoader tmpClassLoader = this.getClass().getClassLoader();
    URL tmpUrl = tmpClassLoader.getResource("SplashScreen.jpg");
    System.out.println(tmpUrl.toString());
    Image tmpSplashImage = getToolkit().getImage(tmpUrl);
    g.drawImage(tmpSplashImage, 0, 0, this);
  }
}
