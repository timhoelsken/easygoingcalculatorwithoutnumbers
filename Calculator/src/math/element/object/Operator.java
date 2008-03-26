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
		
		if (anOperatorType.ordinal() == OperatorType.ADDITION.ordinal() || anOperatorType.ordinal() == OperatorType.SUBTRACTION.ordinal())
		{
			Priority = 1;
		}
		else if (anOperatorType.ordinal() == OperatorType.MULTIPLICATION.ordinal() || anOperatorType.ordinal() == OperatorType.DIVISION.ordinal())
		{
			Priority = 5;
		} else Priority = 0;
		
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
	
	public String toString()
    {
	    switch (operatorType){
          case DIVISION:
              return "/";
          case MULTIPLICATION:
              return "*";
          case SUBTRACTION:
              return "-";
          default:
              return "+";
        }
      
    }
}
