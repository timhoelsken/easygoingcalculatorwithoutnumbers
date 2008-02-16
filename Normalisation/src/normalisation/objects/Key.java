package normalisation.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Tobias
 *
 */
public class Key {

  private ArrayList<String> keyAttributes;

  /**
   * default constructor
   */
  public Key() {
    keyAttributes = new ArrayList<String>();
  }

  /**
   * default constructor
   *
   * @param someKeyAttributes
   */
  public Key(ArrayList<String> someKeyAttributes) {
    keyAttributes = someKeyAttributes;
  }

  /**
   * adds a dependentOn
   *
   * @param aKeyAttribute
   */
  public void addKeyAttribute(String aKeyAttribute) {
    keyAttributes.add(aKeyAttribute);
  }

  /**
   * removes a dependentOn
   *
   * @param aKeyAttribute
   */
  public void removeKeyAttribute(String aKeyAttribute) {
    keyAttributes.remove(aKeyAttribute);
  }

  /**
   * @param aKey
   * @return true if 'this' contains or equals the parameter key
   */
  public boolean containsOrEquals(Key aKey) {
    if (keyAttributes.size() >= aKey.keyAttributes.size()) {
      return doContainsAndEqualCheck(aKey);
    }
    return false;
  }

  /**
   * @param aKey
   * @return true if 'this' contains the parameter key, false if keys are equal
   *         or 'this' does not contain it
   */
  public boolean contains(Key aKey) {
    if (keyAttributes.size() > aKey.keyAttributes.size()) {
      return doContainsAndEqualCheck(aKey);
    }
    return false;
  }

  /**
   * @param aKey
   * @return true if keys are equal
   */
  public boolean equals(Key aKey) {
    if (keyAttributes.size() == aKey.keyAttributes.size()) {
      return doContainsAndEqualCheck(aKey);
    }
    return false;
  }

  private boolean doContainsAndEqualCheck(Key aKey) {
    for (Iterator<String> i = aKey.keyAttributes.iterator(); i.hasNext();) {
      String tmpKeyAttribute = i.next();
      if (!keyAttributes.contains(tmpKeyAttribute)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return a deep copy of the calling object
   */
  public Key copy() {
    Key tmpReturnKey = new Key();
    for (Iterator<String> i = keyAttributes.iterator(); i.hasNext();) {
      String tmpKeyAttribute = i.next();
      tmpReturnKey.addKeyAttribute(tmpKeyAttribute);
    }
    return tmpReturnKey;
  }

  /**
   * @return the keyAttributes
   */
  public ArrayList<String> getKeyAttributes() {
    return keyAttributes;
  }

  /**
   * prints the key on console
   */
  public void out() {
    String tmpKeyOut = new String();
    for (Iterator<String> i = keyAttributes.iterator(); i.hasNext();) {
      String tmpKeyAttribute = i.next();
      tmpKeyOut += tmpKeyAttribute;
      if (i.hasNext()) {
        tmpKeyOut += ", ";
      }
    }
    System.out.println("(" + tmpKeyOut + ")");
  }

  /**
   * @param aKeyAttribute
   * @return true if the given attribute is attribute of this key
   */
  public boolean contains(String aKeyAttribute) {
    for (String tmpKeyAttribute : keyAttributes) {
      if (tmpKeyAttribute.equals(aKeyAttribute)) {
        return true;
      }
    }
    return false;
  }
}
