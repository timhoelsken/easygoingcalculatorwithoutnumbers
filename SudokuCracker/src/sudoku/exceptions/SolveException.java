/**
 *
 */
package sudoku.exceptions;

/**
 * Exception if a solving method cannot get a result
 * 
 * @author Tobias
 */
public class SolveException extends Throwable {

  /**
   * Standard constructor
   */
  public SolveException() {
    super();
  }

  /**
   * Message constructor
   * 
   * @param aMessage
   */
  public SolveException(String aMessage) {
    super(aMessage);
  }

  /**
   * serial
   */
  private static final long serialVersionUID = -6327980139450042394L;

}
