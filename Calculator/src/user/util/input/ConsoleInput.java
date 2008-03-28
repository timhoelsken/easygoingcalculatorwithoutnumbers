package user.util.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Tim
 * 
 */
public class ConsoleInput {

  private String inputString;

  /**
   * Constructor
   */
  public ConsoleInput(){
    
  }
  
  /**
   * 
   * @return The input of the user, tested and corrected with private methods on
   *         usability
   * @throws IOException
   */
  public String getConsoleInput() throws IOException {
    InputStreamReader tmpStremReader = new InputStreamReader(System.in);
    BufferedReader tmpStdIn = new BufferedReader(tmpStremReader);
    inputString = tmpStdIn.readLine();

    return inputString;
  }
}
