package calculator.elements;

/**
 * 
 */
public class Variable extends Operand {

	private char value;

	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	/**
	 * constructor
	 * 
	 * @param aValue
	 */
	public Variable(char aValue) {
		value = aValue;
	}

	/**
	 * gives the value of the variable
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return Character.toString(value);
	}
}
