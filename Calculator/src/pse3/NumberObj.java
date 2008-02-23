package pse3;

/**
 *
 */
public class NumberObj extends MathObj {
	private float value;

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
	public NumberObj(float aValue) {
		super(MathType.NUMBER);
		value = aValue;
	}
}
