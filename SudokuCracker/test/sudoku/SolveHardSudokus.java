package sudoku;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.InternalException;
import sudoku.exceptions.SetException;

/**
 * Easy complete Sudoku tests
 * 
 * @author Tim
 */
public class SolveHardSudokus {

  Sudoku sudoku;

  /**
   * Create a sudoku that is used in the tests
   */
  @Before
  public void createSudoku() {
    sudoku = new Sudoku();
  }

  /**
   * A "just hard" sudoku
   * 
   * @throws SetException
   * 
   * @throws InternalException
   */
  @Test
  public void solveJustHardSudoku() throws SetException {
    sudoku.set(7, 1, 1);
    sudoku.set(2, 6, 1);
    sudoku.set(8, 7, 1);
    sudoku.set(4, 8, 1);
    sudoku.set(9, 2, 2);
    sudoku.set(1, 5, 2);
    sudoku.set(7, 7, 2);
    sudoku.set(6, 8, 2);
    sudoku.set(6, 1, 3);
    sudoku.set(7, 4, 3);
    sudoku.set(9, 9, 3);
    sudoku.set(4, 5, 4);
    sudoku.set(1, 8, 4);
    sudoku.set(8, 9, 4);
    sudoku.set(1, 3, 5);
    sudoku.set(3, 7, 5);
    sudoku.set(2, 1, 6);
    sudoku.set(5, 2, 6);
    sudoku.set(3, 5, 6);
    sudoku.set(5, 1, 7);
    sudoku.set(6, 6, 7);
    sudoku.set(7, 9, 7);
    sudoku.set(3, 2, 8);
    sudoku.set(4, 3, 8);
    sudoku.set(2, 5, 8);
    sudoku.set(8, 8, 8);
    sudoku.set(7, 2, 9);
    sudoku.set(6, 3, 9);
    sudoku.set(3, 4, 9);
    sudoku.set(1, 9, 9);

    int[] expectedArray = { 7, 1, 5, 9, 6, 2, 8, 4, 3, 8, 9, 2, 4, 1, 3, 7, 6, 5, 6, 4, 3, 7, 5, 8, 1, 2, 9,
        3, 6, 7, 2, 4, 9, 5, 1, 8, 4, 8, 1, 6, 7, 5, 3, 9, 2, 2, 5, 9, 8, 3, 1, 6, 7, 4, 5, 2, 8, 1, 9, 6, 4,
        3, 7, 1, 3, 4, 5, 2, 7, 9, 8, 6, 9, 7, 6, 3, 8, 4, 2, 5, 1 };
    assertArrayEquals(expectedArray, sudoku.solve());
  }

  /**
   * A "just hard" sudoku
   * 
   * @throws SetException
   * 
   * @throws InternalException
   */
  @Test
  public void solveVeryHardSudoku() throws SetException {
    sudoku.set(3, 4, 1);
    sudoku.set(9, 5, 1);
    sudoku.set(2, 9, 1);
    sudoku.set(6, 1, 2);
    sudoku.set(9, 3, 2);
    sudoku.set(2, 5, 2);
    sudoku.set(7, 7, 2);
    sudoku.set(6, 6, 3);
    sudoku.set(9, 9, 3);
    sudoku.set(8, 2, 4);
    sudoku.set(6, 3, 4);
    sudoku.set(7, 4, 4);
    sudoku.set(3, 5, 4);
    sudoku.set(1, 7, 4);
    sudoku.set(3, 3, 5);
    sudoku.set(4, 7, 5);
    sudoku.set(5, 3, 6);
    sudoku.set(4, 5, 6);
    sudoku.set(8, 6, 6);
    sudoku.set(6, 7, 6);
    sudoku.set(3, 8, 6);
    sudoku.set(5, 1, 7);
    sudoku.set(2, 4, 7);
    sudoku.set(4, 3, 8);
    sudoku.set(7, 5, 8);
    sudoku.set(9, 7, 8);
    sudoku.set(1, 9, 8);
    sudoku.set(3, 1, 9);
    sudoku.set(5, 5, 9);
    sudoku.set(1, 6, 9);
    

    int[] expectedArray = { 1, 4, 8, 3, 9, 7, 5, 6, 2, 6, 5, 9, 8, 2, 4, 7, 1, 3, 7, 3, 2, 5, 1, 6, 8, 4, 9,
        4, 8, 6, 7, 3, 2, 1, 9, 5, 9, 7, 3, 1, 6, 5, 4, 2, 8, 2, 1, 5, 9, 4, 8, 6, 3, 7, 5, 6, 1, 2, 8, 9, 3,
        7, 4, 8, 2, 4, 6, 7, 3, 9, 5, 1, 3, 9, 7, 4, 5, 1, 2, 8, 6 };
    assertArrayEquals(expectedArray, sudoku.solve());
  }
}
