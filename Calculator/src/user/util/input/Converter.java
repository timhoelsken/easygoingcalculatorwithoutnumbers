package user.util.input;

/**
 * 
 * @author Tim
 *
 */
public class Converter {

  /**
   * 
   * @param anInputString 
   * @return
   */
  public String removeBlanks(String anInputString){
    
    String tmpOutput = new String("");
    
    for(int i=0; i<anInputString.length(); i++){
      if (anInputString.charAt(i)!=' '){
        tmpOutput+=anInputString.charAt(i);
      }
    }
    return tmpOutput;
  }
}
