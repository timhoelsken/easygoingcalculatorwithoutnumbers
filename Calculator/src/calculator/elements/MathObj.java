package calculator.elements;

/**
 * 
 */
public class MathObj {
  private MathType type;

  /**
   * @return the type
   */
  public MathType getMathType() {
    return type;
  }

  /**
   * constructor
   * 
   * @param aType
   */
  public MathObj(MathType aType) {
    type = aType;
  }

}