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
public class SolveEasySudokus {

  Sudoku sudoku;

  /**
   * Create a sudoku that is used in the tests
   */
  @Before
  public void createSudoku() {
    sudoku = new Sudoku();
  }

  /**
   * A "very easy" sudoku
   *
   * @throws SetException
   *
   * @throws InternalException
   */
  @Test
  public void solveVeryEasySudoku() throws SetException {
    sudoku.set(3, 1, 1);
    sudoku.set(1, 2, 1);
    sudoku.set(6, 7, 1);
    sudoku.set(5, 9, 1);
    sudoku.set(6, 2, 2);
    sudoku.set(5, 3, 2);
    sudoku.set(2, 4, 2);
    sudoku.set(9, 8, 2);
    sudoku.set(1, 9, 2);
    sudoku.set(9, 1, 3);
    sudoku.set(1, 4, 3);
    sudoku.set(5, 5, 3);
    sudoku.set(2, 8, 3);
    sudoku.set(8, 4, 4);
    sudoku.set(5, 6, 4);
    sudoku.set(7, 7, 4);
    sudoku.set(4, 8, 4);
    sudoku.set(1, 3, 5);
    sudoku.set(9, 5, 5);
    sudoku.set(5, 7, 5);
    sudoku.set(4, 2, 6);
    sudoku.set(8, 3, 6);
    sudoku.set(7, 4, 6);
    sudoku.set(2, 6, 6);
    sudoku.set(3, 2, 7);
    sudoku.set(7, 5, 7);
    sudoku.set(1, 6, 7);
    sudoku.set(6, 9, 7);
    sudoku.set(1, 1, 8);
    sudoku.set(2, 2, 8);
    sudoku.set(3, 6, 8);
    sudoku.set(9, 7, 8);
    sudoku.set(8, 8, 8);
    sudoku.set(7, 1, 9);
    sudoku.set(9, 3, 9);
    sudoku.set(3, 8, 9);
    sudoku.set(4, 9, 9);
    TestUtil.paint(sudoku);

    int[] expectedArray = { 3, 1, 2, 4, 8, 9, 6, 7, 5, 4, 6, 5, 2, 3, 7, 8, 9, 1, 9, 8, 7, 1, 5, 6, 4, 2, 3,
        6, 9, 3, 8, 1, 5, 7, 4, 2, 2, 7, 1, 3, 9, 4, 5, 6, 8, 5, 4, 8, 7, 6, 2, 3, 1, 9, 8, 3, 4, 9, 7, 1, 2,
        5, 6, 1, 2, 6, 5, 4, 3, 9, 8, 7, 7, 5, 9, 6, 2, 8, 1, 3, 4 };
    assertArrayEquals(expectedArray, sudoku.solve());
  }
}
