package calculator.userinterface.swing;

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

    try {
      flamAudio = new FileInputStream(tmpUrl.getFile());
    } catch (Exception e) {
      System.err.println("Error loading Sound!");
      e.printStackTrace();
    }
  }
}