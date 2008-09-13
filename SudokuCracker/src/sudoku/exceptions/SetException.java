package sudoku.exceptions;

/**
 * Exception for wrong set requests
 *
 * @author Tobias
 */
public class SetException extends Throwable {

  /**
   * Message constructor
   *
   * @param aMessage
   */
  public SetException(String aMessage) {
    super(aMessage);
  }

  /**
   * serial
   */
  private static final long serialVersionUID = 6463296705712801218L;

}
