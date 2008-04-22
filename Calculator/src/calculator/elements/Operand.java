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
}
