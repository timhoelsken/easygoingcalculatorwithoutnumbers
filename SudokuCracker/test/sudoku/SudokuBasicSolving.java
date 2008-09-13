package sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.SetException;
import sudoku.exceptions.SolveException;

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
   * If there is only one free field in the row, it is easy to solve
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveLastFieldOfRowWith1() throws SetException, SolveException {
    sudoku.set(2, 2, 1);
    sudoku.set(3, 3, 1);
    sudoku.set(4, 4, 1);
    sudoku.set(5, 5, 1);
    sudoku.set(6, 6, 1);
    sudoku.set(7, 7, 1);
    sudoku.set(8, 8, 1);
    sudoku.set(9, 9, 1);
    sudoku.addLastMissingNumberInRow(1, 1);
    assertEquals(1, sudoku.get(1, 1));
  }

  /**
   * If there is only one free field in the row, it is easy to solve
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveLastFieldOfRowWith9() throws SetException, SolveException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 2, 1);
    sudoku.set(3, 3, 1);
    sudoku.set(4, 4, 1);
    sudoku.set(5, 5, 1);
    sudoku.set(6, 6, 1);
    sudoku.set(7, 7, 1);
    sudoku.set(8, 8, 1);
    sudoku.addLastMissingNumberInRow(9, 1);
    assertEquals(9, sudoku.get(9, 1));
  }

  /**
   * If there is only one free field in the row, it is easy to solve. If it is
   * not, the call of the solve method fails
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveLastFieldOfRowNotPossible() throws SetException, SolveException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 2, 1);
    sudoku.set(3, 3, 1);
    sudoku.addLastMissingNumberInRow(4, 1);
  }

  /**
   * If there is only one free field in the column, it is easy to solve
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveLastFieldOfColumnWith1() throws SetException, SolveException {
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.set(4, 1, 4);
    sudoku.set(5, 1, 5);
    sudoku.set(6, 1, 6);
    sudoku.set(7, 1, 7);
    sudoku.set(8, 1, 8);
    sudoku.set(9, 1, 9);
    sudoku.addLastMissingNumberInColumn(1, 1);
    assertEquals(1, sudoku.get(1, 1));
  }

  /**
   * If there is only one free field in the column, it is easy to solve
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveLastFieldOfColumnWith9() throws SetException, SolveException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.set(4, 1, 4);
    sudoku.set(5, 1, 5);
    sudoku.set(6, 1, 6);
    sudoku.set(7, 1, 7);
    sudoku.set(8, 1, 8);
    sudoku.addLastMissingNumberInColumn(1, 9);
    assertEquals(9, sudoku.get(1, 9));
  }

  /**
   * If there is only one free field in the column, it is easy to solve. If it
   * is not, the call of the solve method fails
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveLastFieldOfColumnNotPossible() throws SetException, SolveException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.addLastMissingNumberInColumn(1, 4);
  }

  /**
   * If there is only one free field in the square, it is easy to solve
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveLastFieldOfSquareWith1() throws SetException, SolveException {
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.set(4, 2, 1);
    sudoku.set(5, 2, 2);
    sudoku.set(6, 2, 3);
    sudoku.set(7, 3, 1);
    sudoku.set(8, 3, 2);
    sudoku.set(9, 3, 3);
    sudoku.addLastMissingNumberInSquare(1, 1);
    assertEquals(1, sudoku.get(1, 1));
  }

  /**
   * If there is only one free field in the square, it is easy to solve
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test
  public void solveLastFieldOfSquareWith9() throws SetException, SolveException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.set(4, 2, 1);
    sudoku.set(5, 2, 2);
    sudoku.set(6, 2, 3);
    sudoku.set(7, 3, 1);
    sudoku.set(8, 3, 2);
    sudoku.addLastMissingNumberInSquare(3, 3);
    assertEquals(9, sudoku.get(3, 3));
  }

  /**
   * If there is only one free field in the square, it is easy to solve. If it
   * is not, the call of the solve method fails
   *
   * @throws SetException
   * @throws SolveException
   */
  @Test(expected = SolveException.class)
  public void solveLastFieldOfSquareNotPossible() throws SetException, SolveException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.addLastMissingNumberInSquare(2, 1);
  }
}
