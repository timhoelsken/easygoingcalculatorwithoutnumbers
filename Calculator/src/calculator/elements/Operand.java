package calculator.elements;

import calculator.utils.MathUtil;

/**
 * Defines an operand
 */
public class Operand extends MathObj {
	/**
	 * constructor
	 * 
	 * @param aValue
	 */
	public Operand() {
		super.setPriority(MathUtil.PRIO_OPERANDS);
	}

  /**
   * @uml.property  name="operatorType"
   * @uml.associationEnd  inverse="operand:calculator.elements.OperatorType"
   */
  private OperatorType operatorType;

  /**
   * Getter of the property <tt>operatorType</tt>
   * @return  Returns the operatorType.
   * @uml.property  name="operatorType"
   */
  public OperatorType getOperatorType() {
    return operatorType;
  }

  /**
   * Setter of the property <tt>operatorType</tt>
   * @param operatorType  The operatorType to set.
   * @uml.property  name="operatorType"
   */
  public void setOperatorType(OperatorType operatorType) {
    this.operatorType = operatorType;
  }
}
