/**
 *
 */
package sudoku.exceptions;

/**
 * @author Tobias
 *
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
   * @param aString
   */
  public SolveException(String aString) {
    super(aString);
  }

  /**
   * serial
   */
  private static final long serialVersionUID = -6327980139450042394L;

}
