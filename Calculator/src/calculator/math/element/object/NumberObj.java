package calculator.math.element.object;

/**
 *
 */
public class NumberObj extends Operand {
  private float value;

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
  public NumberObj(float aValue) {
    value = aValue;
  }

  /**
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return Float.toString(value);
  }
}
