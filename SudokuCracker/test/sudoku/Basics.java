/**
 *
 */
package sudoku;

import org.junit.Test;

import sudoku.exceptions.SetException;

/**
 * @author Tobias
 *
 */
public class Basics {

  /**
   * A once set field should not be set again
   */
  @Test (expected=SetException.class)
  public void avoidDoubleSetting() {
    Sudoku tmpSudoku = new Sudoku();
    tmpSudoku.set(1, 1, 1);
    tmpSudoku.set(2, 1, 1);
  }
}
