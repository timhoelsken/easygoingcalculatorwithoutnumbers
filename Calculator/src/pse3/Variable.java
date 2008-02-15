package pse3;

/**
 *
 */
public class Variable extends MathObj {

	private char value;

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
	public Variable(char aValue) {
		super(MathType.VARIABLE);
		value = aValue;
	}
}
