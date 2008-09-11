package sudoku;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Easy complete Sudoku tests
 * 
 * @author Tim
 */
public class SolveEasySudokus {

  Sudoku aSudoku;

  /**
   * Create a sudoku that is used in the tests
   */
  @Before
  public void createSudoku() {
    aSudoku = new Sudoku();
  }

  /**
   * Aa "very easy" sudoku
   * 
   * @throws InternalException
   */
  @Ignore("System not ready yet")
  @Test
  public void solveVeryEasySudoku() {
    // aSudoku.set(3, 1, 1);
    // aSudoku.set(1, 2, 1);
    // aSudoku.set(6, 7, 1);
    // aSudoku.set(5, 9, 1);
    // aSudoku.set(6, 2, 2);
    // aSudoku.set(5, 3, 2);
    // aSudoku.set(2, 4, 2);
    // aSudoku.set(9, 8, 2);
    // aSudoku.set(1, 9, 2);
    // aSudoku.set(9, 1, 3);
    // aSudoku.set(1, 4, 3);
    // aSudoku.set(5, 5, 3);
    // aSudoku.set(2, 8, 3);
    // aSudoku.set(8, 4, 4);
    // aSudoku.set(5, 6, 4);
    // aSudoku.set(7, 7, 4);
    // aSudoku.set(4, 8, 4);
    // aSudoku.set(1, 3, 5);
    // aSudoku.set(9, 5, 5);
    // aSudoku.set(5, 7, 5);
    // aSudoku.set(4, 2, 6);
    // aSudoku.set(8, 3, 6);
    // aSudoku.set(7, 4, 6);
    // aSudoku.set(2, 6, 6);
    // aSudoku.set(3, 2, 7);
    // aSudoku.set(7, 5, 7);
    // aSudoku.set(1, 6, 7);
    // aSudoku.set(6, 9, 7);
    // aSudoku.set(1, 1, 8);
    // aSudoku.set(2, 2, 8);
    // aSudoku.set(3, 6, 8);
    // aSudoku.set(9, 7, 8);
    // aSudoku.set(8, 8, 8);
    // aSudoku.set(7, 1, 9);
    // aSudoku.set(9, 3, 9);
    // aSudoku.set(3, 8, 9);
    // aSudoku.set(4, 9, 9);
    //
    // int[] expectedArray = { 3, 1, 2, 4, 8, 9, 6, 7, 5, 4, 6, 5, 2, 3, 7, 8,
    // 9, 1, 9, 8, 7, 1, 5, 6, 4, 2, 3,
    // 6, 9, 3, 8, 1, 5, 7, 4, 2, 2, 7, 1, 3, 9, 4, 5, 6, 8, 5, 4, 8, 7, 6, 2,
    // 3, 1, 9, 8, 3, 4, 9, 7, 1, 2,
    // 5, 6, 1, 2, 6, 5, 4, 3, 9, 8, 7, 7, 5, 9, 6, 2, 8, 1, 3, 4 };
  }
}
