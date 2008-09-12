package sudoku;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Tobias
 * 
 */
public class SudokuBasicSolving {

  private Sudoku sudoku;

  /**
   * Produces an empty Sudoku instance for all tests.
   */
  @Before
  public void initialiseSudoku() {
    sudoku = new Sudoku();
  }

  /**
   * Tests something regarding the solving
   */
  @Test
  @Ignore("test not yet implemented")
  public void newTest() {
    sudoku.toString();
  }

}
