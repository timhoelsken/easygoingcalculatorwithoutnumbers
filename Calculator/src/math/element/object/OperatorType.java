package math.element.object;

/**
 *
 */
public enum OperatorType {
	/**
	 * addition
	 */
	ADDITION(1),
	/**
	 * subtraction
	 */
	SUBTRACTION(1),
	/**
	 * multiplication
	 */
	MULTIPLICATION(2),
	/**
	 * division
	 */
	DIVISION(2);

	private int operatorType;

	private OperatorType(int anOperatorType) {
		operatorType = anOperatorType;
	}

	/**
	 * @return the operatortype as int
	 */
	public int getPriority() {
		return operatorType;
	}
}
