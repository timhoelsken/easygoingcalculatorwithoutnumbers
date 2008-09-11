package sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.InternalException;

/**
 * 
 * @author Timbo & Tobe
 * 
 */
public class SudokuIntegrationTest {

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
  @Test
  public void solveVeryEasySudoku() throws InternalException {
    aSudoku.setValue(3, 1, 1);
    aSudoku.setValue(1, 2, 1);
    aSudoku.setValue(6, 7, 1);
    aSudoku.setValue(5, 9, 1);
    aSudoku.setValue(6, 2, 2);
    aSudoku.setValue(5, 3, 2);
    aSudoku.setValue(2, 4, 2);
    aSudoku.setValue(9, 8, 2);
    aSudoku.setValue(1, 9, 2);
    aSudoku.setValue(9, 1, 3);
    aSudoku.setValue(1, 4, 3);
    aSudoku.setValue(5, 5, 3);
    aSudoku.setValue(2, 8, 3);
    aSudoku.setValue(8, 4, 4);
    aSudoku.setValue(5, 6, 4);
    aSudoku.setValue(7, 7, 4);
    aSudoku.setValue(4, 8, 4);
    aSudoku.setValue(1, 3, 5);
    aSudoku.setValue(9, 5, 5);
    aSudoku.setValue(5, 7, 5);
    aSudoku.setValue(4, 2, 6);
    aSudoku.setValue(8, 3, 6);
    aSudoku.setValue(7, 4, 6);
    aSudoku.setValue(2, 6, 6);
    aSudoku.setValue(3, 2, 7);
    aSudoku.setValue(7, 5, 7);
    aSudoku.setValue(1, 6, 7);
    aSudoku.setValue(6, 9, 7);
    aSudoku.setValue(1, 1, 8);
    aSudoku.setValue(2, 2, 8);
    aSudoku.setValue(3, 6, 8);
    aSudoku.setValue(9, 7, 8);
    aSudoku.setValue(8, 8, 8);
    aSudoku.setValue(7, 1, 9);
    aSudoku.setValue(9, 3, 9);
    aSudoku.setValue(3, 8, 9);
    aSudoku.setValue(4, 9, 9);

    int[] expectedArray = { 3, 1, 2, 4, 8, 9, 6, 7, 5, 4, 6, 5, 2, 3, 7, 8, 9, 1, 9, 8, 7, 1, 5, 6, 4, 2, 3,
        6, 9, 3, 8, 1, 5, 7, 4, 2, 2, 7, 1, 3, 9, 4, 5, 6, 8, 5, 4, 8, 7, 6, 2, 3, 1, 9, 8, 3, 4, 9, 7, 1, 2,
        5, 6, 1, 2, 6, 5, 4, 3, 9, 8, 7, 7, 5, 9, 6, 2, 8, 1, 3, 4 };
    
    assertArrayEquals(expectedArray, aSudoku.solve());
  }
}
