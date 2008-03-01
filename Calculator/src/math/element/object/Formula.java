package math.element.object;

/*
 * This is mathematical formula
 */
public class Formula {
	
	private String pFormula;
	private int Index;
	
	public Formula(String aFormula)	{
		pFormula = aFormula;
	}
	
	public void setStartElement()
	{
		Index = 0;
	}
	
	public MathObj getNextElement()
	{
		return null;
	}

}
