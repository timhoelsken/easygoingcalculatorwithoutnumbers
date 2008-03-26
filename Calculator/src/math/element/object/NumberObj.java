package math.element.object;


/**
 *
 */
public class NumberObj extends Operand {
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
		value = aValue;
	}
    
    public String toString()
    {
      return Float.toString(value);
    }
}
