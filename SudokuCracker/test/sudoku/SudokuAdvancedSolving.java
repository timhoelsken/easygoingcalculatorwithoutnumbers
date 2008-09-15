package sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.SetException;
import sudoku.exceptions.SolveException;

/**
 * Easy solving algorithms tests
 *
 * @author Tobias
 */
public class SudokuAdvancedSolving {

  private Sudoku sudoku;

  /**
   * Produces an empty Sudoku instance for all tests.
   */
  @Before
  public void initialiseSudoku() {
    sudoku = new Sudoku();
  }

  /**
   * If there is only one free field in the row, it is easy to solve
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveSingleFieldOfColumn() throws SetException, SolveException {
    sudoku.set(7, 1, 1);
    sudoku.set(7, 3, 9);
    sudoku.set(5, 2, 4);
    sudoku.set(6, 2, 6);

    // TBD by Tim => grade AFK
    sudoku.addMissingNumberColumnCombination(2, 5);
    assertEquals(1, sudoku.get(1, 1));
  }

}
