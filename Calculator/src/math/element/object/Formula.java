package math.element.object;
import java.util.LinkedList;

/*
 * This is mathematical formula
 */
public class Formula {
	
	private String pFormula;
	private int Index;
    private LinkedList<MathObj> MathList;
	
    /**
     * constructor, requires a formula string
     * calls a function to create a linked list out of the formula string
     * 
     * @author André
     * @param aFormula
     * 
     */
	public Formula(String aFormula)	{
		pFormula = aFormula;
        MathList = new LinkedList<MathObj>();
        toLinkedList();
        setStartElement();
	}
	
    /**
     * set start element to 0
     * @author André
     */
    public void setStartElement()
    {
        Index = 0;
    }
    
    /**
     * return a MathObject of the passed number
     * 
     * @author André
     * @param number of the required element
     * @return MathObj
     */
    public MathObj getElement(int i)
    {
        return MathList.get(i);
    }
	
    /**
     * returns the next in the list existing MathObj 
     * 
     * @author André
     * @return MathObj
     */
	public MathObj getNextElement()
	{
		Index++;
        return MathList.get(Index--);
	}
    
    /**
     * method creates a float value based mathobj out of a given string
     * 
     * @author André
     * @param aNumberContainingString
     * @return MathObj
     */
    private MathObj buildNumberMathObject(String aNumberContainingString)
    {
      Float tmpFl;
      tmpFl = Float.valueOf("0.0").floatValue();
      try 
      {
          tmpFl = Float.valueOf(aNumberContainingString).floatValue();
      }
      catch (NumberFormatException e) {}
      
      NumberObj tmpNumberObj = new NumberObj(tmpFl);
      return tmpNumberObj;
    }
    
    /**
     * method creates a operator mathobj out of a given string
     * 
     * @author André
     * @param aOperatorContainingString
     * @return MathObj
     */
    private MathObj buildOperatorMathObject(String aOperatorContainingString)
    {
      OperatorType tmpOpType;
      if (aOperatorContainingString.equals("-"))
      {
        tmpOpType = OperatorType.SUBTRACTION;
      }
      else if (aOperatorContainingString.equals("/"))
      {
        tmpOpType = OperatorType.DIVISION;
      }
      else if (aOperatorContainingString.equals("*"))
      {
        tmpOpType = OperatorType.MULTIPLICATION;
      }
      else
      {
        tmpOpType = OperatorType.ADDITION;
      }
      Operator tmpOp = new Operator(tmpOpType);
      return tmpOp;
    }
    
    /**
     * method creates a mathobj list out ouf the form string
     * @author André
     */
    private void toLinkedList()
    {
        int iLenOfString;
        iLenOfString = pFormula.length();
        
        int iStartPosition = 0;
        int iEndPosition = 0;

        while (iStartPosition < iLenOfString)
        {        
          if ((MathUtil.IsNumber(pFormula.charAt(iStartPosition))) || (MathUtil.IsComma(pFormula.charAt(iStartPosition))))
          {
              iEndPosition = iStartPosition++;
              while ((MathUtil.IsNumber(pFormula.charAt(iEndPosition))) || (MathUtil.IsComma(pFormula.charAt(iEndPosition))))
              {
                  iEndPosition++;
              }
              iEndPosition--;
              try
              {
                  MathList.add(buildNumberMathObject(pFormula.substring(iStartPosition, iEndPosition)));
              }
              catch (Exception e)
              {}
          }
          if (MathUtil.IsOperator(pFormula.charAt(iEndPosition)))
          {
              iEndPosition = iStartPosition++;
              while (MathUtil.IsOperator(pFormula.charAt(iEndPosition)))
              {
                  iEndPosition++;
              }
              iEndPosition--;
              try
              {
                MathList.add(buildOperatorMathObject(pFormula.substring(iStartPosition, iEndPosition)));
              }
              catch (Exception e)
              {}
          }
          else
          {
              throw new ExceptionWrongInputStream("Input String contains non valid characters!");
          }
        }
    }

}
