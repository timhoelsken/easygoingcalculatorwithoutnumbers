package pse3;



public class MathUtil {
	public static char Comma = ',';
	
	public static boolean IsNumber(char c)
	{
		return (c >= '0' && c <= '9');
	}
	
	public static boolean IsComma(char c)
	{
		return ( c == Comma );
	}
	
	public static boolean IsOperator(char c)
	{		
		switch(c)
		{
			case OperatorType.Addition.getOpAsChar():			
			case OperatorType.Subtraction.getOpAsChar():		
			case OperatorType.Division.getOpAsChar():
			case OperatorType.Multiplication.getOpAsChar():
				return true;
				break;
			default:
				return false;
				break;
		}
	}
	
	public static MathObj getNextMathObj(String i_Funct, Integer charPos)
	{
		if (charPos>=i_Funct.length()) return null;
		
		char c = i_Funct.charAt(charPos);
		
		if (IsNumber(c))
		{
			return getNextNumber(i_Funct,charPos);
		}
		else (IsOperator(c))
		{
			
		}
		return null;
	}
	
	private static Number getNextNumber(String i_Funct, Integer charPos)
	{
		if (charPos>=i_Funct.length()) return null;
		
		String res = ""; int CommaCount=0;
		while (charPos<i_Funct.length())
		{
			char c = i_Funct.charAt(charPos);
			if (IsNumber(c))
			{
				res+=c;
			}
			else if (IsComma(c))
			{			
				CommaCount++;
				if (CommaCount==1) res+=c;
				else break;
			}
			else break;
			charPos++;
		}

		return new Number(Float.parseFloat(res));
	}
}
