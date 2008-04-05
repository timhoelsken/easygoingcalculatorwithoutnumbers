package calculator.elements;

import calculator.utils.MathUtil;

/**
 * Defines an operand
 * 
 * @author Andr�
 *
 */
public class Operand extends MathObj {
  /**
   * constructor
   * 
   * @param aValue
   */
  public Operand() {
	  super.setPriority(MathUtil.PriorityOfOperands);
  }
}
