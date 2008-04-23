package calculator.elements;

/**
 * @author Raphael
 */
public enum OperatorType {
  /**
   * addition
   */
  ADDITION,
  /**
   * subtraction
   */
  SUBTRACTION,
  /**
   * multiplication
   */
  MULTIPLICATION,
  /**
   * division
   */
  DIVISION,
  /**
   * sinus
   */
  SIN,
  /**
   * cosinus
   */
  COS,
  /**
   * tangens
   */
  TAN,
  /**
   * square root
   */
  SQRT,
  /**
   * potenz
   */
  POW;

  /**
   * @uml.property name="operand"
   * @uml.associationEnd inverse="operatorType:calculator.elements.Operand"
   */
  private Operand operand;

  /**
   * Getter of the property <tt>operand</tt>
   * 
   * @return Returns the operand.
   * @uml.property name="operand"
   */
  public Operand getOperand() {
    return operand;
  }

  /**
   * Setter of the property <tt>operand</tt>
   * 
   * @param operand
   *            The operand to set.
   * @uml.property name="operand"
   */
  public void setOperand(Operand operand) {
    this.operand = operand;
  }
}
