package pse3;

/**
 *
 */
public class NumberObj extends MathObj {
	private float aValue;

	/**
	 * @return the value
	 */
	public float getValue() {
		return aValue;
	}

	/**
	 * constructor
	 *
	 * @param tmpValue
	 */
	public NumberObj(float tmpValue) {
		super(MathType.NUMBER);
		aValue = tmpValue;
	}
}
