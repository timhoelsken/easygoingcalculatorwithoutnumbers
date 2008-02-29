package math.element.object;


/**
 *
 */
public class Operator extends MathObj {
	private OperatorType operatorType;

	/**
	 * @param anOperatorType
	 */
	public Operator(OperatorType anOperatorType) {
		super(MathType.OPERATOR);
		operatorType = anOperatorType;
	}

	/**
	 * @return the operatorType
	 */
	public OperatorType getOperatorType() {
		return operatorType;
	}
}
