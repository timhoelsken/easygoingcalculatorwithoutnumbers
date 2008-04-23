package calculator.userinterface.swing;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * 
 * @author Tim
 * 
 */
public class Flam extends Thread {

  private InputStream flamAudio;

  /**
   * 
   */
  public Flam() {
    loadFlam();
  }

  /**
   * 
   */
  public void run() {
    try {
      AudioStream tmpAudioStream = new AudioStream(flamAudio);
      AudioPlayer.player.start(tmpAudioStream);
    } catch (Exception e) {
      System.err.println("Error loading Sound!");
      e.printStackTrace();
    }
  }

  /**
   * 
   */
  public void loadFlam() {
    ClassLoader tmpClassLoader = this.getClass().getClassLoader();
    URL tmpUrl = tmpClassLoader.getResource("flam.wav");

    /*
     * 
     * tmpUrl.getURI() & tmpUrl.toString()
     * liefern mir den tatsächlichen Pfad (c:\program etc)
     * 
     * tmpUrl.getPath()
     * liefert mir den Pfad ins bin. Aber da wird trotzdem eine
     * FileNotFound Exception geworfen :(
     */
    try {
      
      //File tmpFile = new File(tmpUrl.getPath());
      File tmpFile = new File("misc/sounds/flam.wav");
      flamAudio = new FileInputStream(tmpFile);
    } catch (Exception e) {
      System.err.println("Error loading Sound!");
      e.printStackTrace();
    }
  }
}
