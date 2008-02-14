package pse3;

public class Number extends MathObj
{
	private float p_value;
	
	public float getValue(){return p_value;}
	
	public Number(float i_Value) { super(MathType.Number); p_value=i_Value; }
}
