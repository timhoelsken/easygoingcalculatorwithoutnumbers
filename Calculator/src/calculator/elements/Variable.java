package calculator.elements;

/**
 * 
 */
public class Variable extends Operand {

  private char value;

  /**
   * @return the value
   */
  public float getValue() {
    return value;
  }

  /**
   * constructor
   * 
   * @param aValue
   */
  public Variable(char aValue) {
    value = aValue;
  }
}