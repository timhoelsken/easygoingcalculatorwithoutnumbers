package normalisation.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Tobias
 *
 */
public class Key extends AbstractListObject {

  /**
   * default constructor
   */
  public Key() {
    attributes = new ArrayList<String>();
  }

  /**
   * constructor
   *
   * @param someKeyAttributes
   */
  public Key(ArrayList<String> someKeyAttributes) {
    attributes = someKeyAttributes;
  }

  /**
   * prints the key on console
   */
  public void out() {
    String tmpKeyOut = new String();
    for (Iterator<String> i = attributes.iterator(); i.hasNext();) {
      String tmpKeyAttribute = i.next();
      tmpKeyOut += tmpKeyAttribute;
      if (i.hasNext()) {
        tmpKeyOut += ", ";
      }
    }
    System.out.println("(" + tmpKeyOut + ")");
  }
}
