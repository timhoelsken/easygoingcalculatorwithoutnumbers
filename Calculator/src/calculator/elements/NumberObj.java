package calculator.elements;

/**
 */
public class NumberObj extends Operand {
  private Double value;

  /**
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * constructor
   * 
   * @param aValue
   */
  public NumberObj(Double aValue) {
    value = aValue;
  }

  /**
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return Double.toString(value);
  }
}
