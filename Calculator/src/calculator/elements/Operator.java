package calculator.elements;

import calculator.utils.MathUtil;

/**
 * 
 */
public class Operator extends MathObj {

	private OperatorType operatorType;

	/**
	 * @param anOperatorType
	 */
	public Operator(OperatorType anOperatorType) {

		operatorType = anOperatorType;

		if (anOperatorType.ordinal() == OperatorType.ADDITION.ordinal()
				|| anOperatorType.ordinal() == OperatorType.SUBTRACTION.ordinal()) {
			super.setPriority(MathUtil.PRIO_ADDITION);
		} else if (anOperatorType.ordinal() == OperatorType.MULTIPLICATION.ordinal()
				|| anOperatorType.ordinal() == OperatorType.DIVISION.ordinal()) {
			super.setPriority(MathUtil.PRIO_MULTIPLICATION);
		} else if (anOperatorType.ordinal() == OperatorType.SIN.ordinal()
				|| anOperatorType.ordinal() == OperatorType.COS.ordinal()
				|| anOperatorType.ordinal() == OperatorType.SQRT.ordinal()
				|| anOperatorType.ordinal() == OperatorType.TAN.ordinal()) {
			super.setPriority(MathUtil.PRIO_FUNCTIONS);
		} else if (anOperatorType.ordinal() == OperatorType.POW.ordinal()) {
			super.setPriority(MathUtil.PRIO_POW);
		}

	}

	/**
	 * @return the operatorType
	 */
	public OperatorType getOperatorType() {
		return operatorType;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		switch (operatorType) {
		case ADDITION:
			return "+";
		case DIVISION:
			return "/";
		case MULTIPLICATION:
			return "*";
		case SUBTRACTION:
			return "-";
		case TAN:
			return "tan";
		case SIN:
			return "sin";
		case COS:
			return "cos";
		case SQRT:
			return "sqrt";
		case POW:
			return "pow";
		default:
			return "+";
		}

	}
}
