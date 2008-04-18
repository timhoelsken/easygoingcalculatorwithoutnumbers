/**
 *
 */
package calculator.userinterface;

import java.awt.Graphics;
import java.awt.Image;

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
  public void run()
  {
      setSize(600, 300);
      setLocationRelativeTo(null);
      setVisible(true);

      try
      {
          Thread.sleep(5000);
      }
      catch (InterruptedException e)
      {
          dispose();
      }
      dispose();
  }

  /**
   * shows us the splashscreen
   * @param g
   */
  public void paint(Graphics g)
  {
      Image splashImage = getToolkit().getImage("misc/images/SplashScreen.jpg");
      g.drawImage(splashImage, 0, 0, this);
  }
}
