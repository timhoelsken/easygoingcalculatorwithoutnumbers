package calculator.userinterface.swing;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * the flam sound class
 */
public class Flam extends Thread {

  private AudioClip flamAudio;

  /**
   * the standard constructor
   */
  public Flam() {
    loadFlam();
  }

  /**
   * runs the audio player
   */
  @Override
  public void run() {
    flamAudio.play();
    try {
      Thread.sleep(1210);
    } catch (InterruptedException e) {
    }
    flamAudio.stop();
  }

  /**
   * loads the sound file
   */
  public void loadFlam() {
    ClassLoader tmpClassLoader = this.getClass().getClassLoader();
    URL tmpUrl = tmpClassLoader.getResource("flam.wav");

    try {
      flamAudio = Applet.newAudioClip(tmpUrl);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
