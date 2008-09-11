package sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.SetException;

/**
 * Basic functional tests of the Sudoku
 * @author Tobias
 */
public class Basics {

  private Sudoku sudoku;

  /**
   * Produces an empty Sudoku instance for all tests
   */
  @Before
  public void initialiseSudoku() {
    sudoku = new Sudoku();
  }

  /**
   * A once set value should be remembered.
   *
   * @throws SetException
   */
  @Test
  public void rememberOnceSetValues() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 2);
    assertEquals(1, sudoku.get(1, 1));
    assertEquals(2, sudoku.get(1, 2));
  }

  /**
   * All fields of the Sudoku should be remembered
   *
   * @throws SetException
   */
  @Test
  public void setFullSudoku() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.set(4, 1, 4);
    sudoku.set(5, 1, 5);
    sudoku.set(6, 1, 6);
    sudoku.set(7, 1, 7);
    sudoku.set(8, 1, 8);
    sudoku.set(9, 1, 9);
    sudoku.set(1, 2, 1);
    sudoku.set(2, 2, 2);
    sudoku.set(3, 2, 3);
    sudoku.set(4, 2, 4);
    sudoku.set(5, 2, 5);
    sudoku.set(6, 2, 6);
    sudoku.set(7, 2, 7);
    sudoku.set(8, 2, 8);
    sudoku.set(9, 2, 9);
    sudoku.set(1, 3, 1);
    sudoku.set(2, 3, 2);
    sudoku.set(3, 3, 3);
    sudoku.set(4, 3, 4);
    sudoku.set(5, 3, 5);
    sudoku.set(6, 3, 6);
    sudoku.set(7, 3, 7);
    sudoku.set(8, 3, 8);
    sudoku.set(9, 3, 9);
    sudoku.set(1, 4, 1);
    sudoku.set(2, 4, 2);
    sudoku.set(3, 4, 3);
    sudoku.set(4, 4, 4);
    sudoku.set(5, 4, 5);
    sudoku.set(6, 4, 6);
    sudoku.set(7, 4, 7);
    sudoku.set(8, 4, 8);
    sudoku.set(9, 4, 9);
    sudoku.set(1, 5, 1);
    sudoku.set(2, 5, 2);
    sudoku.set(3, 5, 3);
    sudoku.set(4, 5, 4);
    sudoku.set(5, 5, 5);
    sudoku.set(6, 5, 6);
    sudoku.set(7, 5, 7);
    sudoku.set(8, 5, 8);
    sudoku.set(9, 5, 9);
    sudoku.set(1, 6, 1);
    sudoku.set(2, 6, 2);
    sudoku.set(3, 6, 3);
    sudoku.set(4, 6, 4);
    sudoku.set(5, 6, 5);
    sudoku.set(6, 6, 6);
    sudoku.set(7, 6, 7);
    sudoku.set(8, 6, 8);
    sudoku.set(9, 6, 9);
    sudoku.set(1, 7, 1);
    sudoku.set(2, 7, 2);
    sudoku.set(3, 7, 3);
    sudoku.set(4, 7, 4);
    sudoku.set(5, 7, 5);
    sudoku.set(6, 7, 6);
    sudoku.set(7, 7, 7);
    sudoku.set(8, 7, 8);
    sudoku.set(9, 7, 9);
    sudoku.set(1, 8, 1);
    sudoku.set(2, 8, 2);
    sudoku.set(3, 8, 3);
    sudoku.set(4, 8, 4);
    sudoku.set(5, 8, 5);
    sudoku.set(6, 8, 6);
    sudoku.set(7, 8, 7);
    sudoku.set(8, 8, 8);
    sudoku.set(9, 8, 9);
    sudoku.set(1, 9, 1);
    sudoku.set(2, 9, 2);
    sudoku.set(3, 9, 3);
    sudoku.set(4, 9, 4);
    sudoku.set(5, 9, 5);
    sudoku.set(6, 9, 6);
    sudoku.set(7, 9, 7);
    sudoku.set(8, 9, 8);
    sudoku.set(9, 9, 9);
    assertEquals(1, sudoku.get(1, 1));
    assertEquals(2, sudoku.get(1, 2));
    assertEquals(3, sudoku.get(1, 3));
    assertEquals(4, sudoku.get(1, 4));
    assertEquals(5, sudoku.get(1, 5));
    assertEquals(6, sudoku.get(1, 6));
    assertEquals(7, sudoku.get(1, 7));
    assertEquals(8, sudoku.get(1, 8));
    assertEquals(9, sudoku.get(1, 9));
    assertEquals(1, sudoku.get(2, 1));
    assertEquals(2, sudoku.get(2, 2));
    assertEquals(3, sudoku.get(2, 3));
    assertEquals(4, sudoku.get(2, 4));
    assertEquals(5, sudoku.get(2, 5));
    assertEquals(6, sudoku.get(2, 6));
    assertEquals(7, sudoku.get(2, 7));
    assertEquals(8, sudoku.get(2, 8));
    assertEquals(9, sudoku.get(2, 9));
    assertEquals(1, sudoku.get(3, 1));
    assertEquals(2, sudoku.get(3, 2));
    assertEquals(3, sudoku.get(3, 3));
    assertEquals(4, sudoku.get(3, 4));
    assertEquals(5, sudoku.get(3, 5));
    assertEquals(6, sudoku.get(3, 6));
    assertEquals(7, sudoku.get(3, 7));
    assertEquals(8, sudoku.get(3, 8));
    assertEquals(9, sudoku.get(3, 9));
    assertEquals(1, sudoku.get(4, 1));
    assertEquals(2, sudoku.get(4, 2));
    assertEquals(3, sudoku.get(4, 3));
    assertEquals(4, sudoku.get(4, 4));
    assertEquals(5, sudoku.get(4, 5));
    assertEquals(6, sudoku.get(4, 6));
    assertEquals(7, sudoku.get(4, 7));
    assertEquals(8, sudoku.get(4, 8));
    assertEquals(9, sudoku.get(4, 9));
    assertEquals(1, sudoku.get(5, 1));
    assertEquals(2, sudoku.get(5, 2));
    assertEquals(3, sudoku.get(5, 3));
    assertEquals(4, sudoku.get(5, 4));
    assertEquals(5, sudoku.get(5, 5));
    assertEquals(6, sudoku.get(5, 6));
    assertEquals(7, sudoku.get(5, 7));
    assertEquals(8, sudoku.get(5, 8));
    assertEquals(9, sudoku.get(5, 9));
    assertEquals(1, sudoku.get(6, 1));
    assertEquals(2, sudoku.get(6, 2));
    assertEquals(3, sudoku.get(6, 3));
    assertEquals(4, sudoku.get(6, 4));
    assertEquals(5, sudoku.get(6, 5));
    assertEquals(6, sudoku.get(6, 6));
    assertEquals(7, sudoku.get(6, 7));
    assertEquals(8, sudoku.get(6, 8));
    assertEquals(9, sudoku.get(6, 9));
    assertEquals(1, sudoku.get(7, 1));
    assertEquals(2, sudoku.get(7, 2));
    assertEquals(3, sudoku.get(7, 3));
    assertEquals(4, sudoku.get(7, 4));
    assertEquals(5, sudoku.get(7, 5));
    assertEquals(6, sudoku.get(7, 6));
    assertEquals(7, sudoku.get(7, 7));
    assertEquals(8, sudoku.get(7, 8));
    assertEquals(9, sudoku.get(7, 9));
    assertEquals(1, sudoku.get(8, 1));
    assertEquals(2, sudoku.get(8, 2));
    assertEquals(3, sudoku.get(8, 3));
    assertEquals(4, sudoku.get(8, 4));
    assertEquals(5, sudoku.get(8, 5));
    assertEquals(6, sudoku.get(8, 6));
    assertEquals(7, sudoku.get(8, 7));
    assertEquals(8, sudoku.get(8, 8));
    assertEquals(9, sudoku.get(8, 9));
    assertEquals(1, sudoku.get(9, 1));
    assertEquals(2, sudoku.get(9, 2));
    assertEquals(3, sudoku.get(9, 3));
    assertEquals(4, sudoku.get(9, 4));
    assertEquals(5, sudoku.get(9, 5));
    assertEquals(6, sudoku.get(9, 6));
    assertEquals(7, sudoku.get(9, 7));
    assertEquals(8, sudoku.get(9, 8));
    assertEquals(9, sudoku.get(9, 9));
  }

  /**
   * A once set field should not be set again
   *
   * @throws SetException
   */
  @Test(expected = SetException.class)
  public void avoidDoubleSetting() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 1);
  }
}
