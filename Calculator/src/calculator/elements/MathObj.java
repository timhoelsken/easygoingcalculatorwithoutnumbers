package calculator.elements;

/**
 * @author Raphael
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
   * @return the priority
   * @uml.property name="priority"
   */
  public int getPriority() {
    return priority;
  }

  /**
   * sets the priority
   * 
   * @param aPriority
   * @uml.property name="priority"
   */
  public void setPriority(int aPriority) {
    priority = aPriority;
  }

}
