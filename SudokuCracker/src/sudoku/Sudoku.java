/**
 *
 */
package sudoku;

import sudoku.exceptions.SetException;

/**
 * The Sudoku Class :P
 * 
 * @author Tobias
 */
public class Sudoku {

  int[] value;

  /**
   * Standard constructor
   */
  public Sudoku() {
    value = new int[2];
  }

  /**
   * Sets aValue on the field (x|y)
   * 
   * @param aValue
   * @param x
   * @param y
   * @throws SetException
   */
  public void set(int aValue, int x, int y) throws SetException {
    if (0 == value[y - 1]) {
      value[y - 1] = aValue;
    } else {
      throw new SetException("Setting " + aValue + " on (" + x + "|" + y + ") not allowed.");
    }
  }

  /**
   * Returns value of field (x|y)
   * 
   * @param x
   * @param y
   * @return
   */
  public int get(int x, int y) {
    return value[y - 1];
  }
}