package math.element.object;


/**
 *
 */
public class Operator extends MathObj {
	private OperatorType operatorType;

	private int Priority;	
	
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

	public int getPriority() {
		return Priority;
	}

	public void setPriority(int priority) {
		Priority = priority;
	}
	
	
}
