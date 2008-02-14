package pse3;

public final class Tokener {

  public void ParseFunction(String i_Funct)
	{
		String sFunct = i_Funct;
		Tree Root;

		Integer i = 0;
		while (i < sFunct.length())
		{
			char c = sFunct.charAt(i); // get first char

			if (MathUtil.IsComma(c) || MathUtil.IsNumber(c))
			{
//				Root = new Tree(MathUtil.getNextMathObj(i_Funct))
			}
		}

	}
}
