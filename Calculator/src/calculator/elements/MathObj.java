package calculator.elements;

/**
 * 
 */
public class MathObj {

  private int priority;

  /**
   * constructor
   * 
   * @param aType
   */
  public MathObj() {
    priority = 0;
  }

  /**
   * 
   * @return the priority
   */
  public int getPriority() {
    return priority;
  }

  /**
   * sets the priority
   * 
   * @param aPriority
   * 
   */
  public void setPriority(int aPriority) {
    priority = aPriority;
  }

}
