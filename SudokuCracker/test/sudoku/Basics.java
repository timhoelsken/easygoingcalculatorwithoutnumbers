package sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.SetException;

/**
 * Basic functional tests of the Sudoku
 *
 * @author Tobias
 */
public class Basics {

  private Sudoku sudoku;

  /**
   * Produces an empty Sudoku instance for all tests.
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
   * All corner fields of the Sudoku should be remembered.
   *
   * @throws SetException
   */
  @Test
  public void setCornerFields() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(9, 1, 9);
    sudoku.set(1, 9, 1);
    sudoku.set(9, 9, 9);
    assertEquals(1, sudoku.get(1, 1));
    assertEquals(9, sudoku.get(1, 9));
    assertEquals(1, sudoku.get(9, 1));
    assertEquals(9, sudoku.get(9, 9));
  }

  /**
   * A value set beyond the Sudoku is not allowed.
   *
   * @throws SetException
   */
  @Test (expected=IndexOutOfBoundsException.class)
  public void setNegativeBoundaryFields1() throws SetException {
    sudoku.set(1, 0, 1);
  }

  /**
   * A value set beyond the Sudoku is not allowed.
   *
   * @throws SetException
   */
  @Test (expected=IndexOutOfBoundsException.class)
  public void setNegativeBoundaryFields2() throws SetException {
    sudoku.set(1, 1, 0);
  }

  /**
   * A value set beyond the Sudoku is not allowed.
   *
   * @throws SetException
   */
  @Test (expected=IndexOutOfBoundsException.class)
  public void setNegativeBoundaryFields3() throws SetException {
    sudoku.set(1, 9, 10);
  }

  /**
   * A value set beyond the Sudoku is not allowed.
   *
   * @throws SetException
   */
  @Test (expected=IndexOutOfBoundsException.class)
  public void setNegativeBoundaryFields4() throws SetException {
    sudoku.set(1, 10, 9);
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

  /**
   * A value has to be at least 1
   *
   * @throws SetException
   */
  @Test(expected = SetException.class)
  public void avoidWrongValuesLowerBound() throws SetException {
    sudoku.set(0, 1, 1);
  }

  /**
   * A value has to be maximally 9 (dimension of the Sudoku)
   *
   * @throws SetException
   */
  @Test(expected = SetException.class)
  public void avoidWrongValuesUpperBound() throws SetException {
    sudoku.set(10, 1, 1);
  }
}
