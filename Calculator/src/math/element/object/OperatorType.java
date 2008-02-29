package math.element.object;

/**
 *
 */
public enum OperatorType {
	/**
	 * addition
	 */
	ADDITION('+'),
	/**
	 * subtraction
	 */
	SUBTRACTION('-'),
	/**
	 * multiplication
	 */
	MULTIPLICATION('*'),
	/**
	 * division
	 */
	DIVISION('/');

	private char operatorType;

	private OperatorType(char anOperatorType) {
		operatorType = anOperatorType;
	}

	/**
	 * @return the operatortype as char
	 */
	public char getOperatorTypeAsChar() {
		return operatorType;
	}
}
