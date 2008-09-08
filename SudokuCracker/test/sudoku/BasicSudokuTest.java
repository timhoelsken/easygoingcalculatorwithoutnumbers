package sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @authors Timbo & Tobe
 *
 */
public class BasicSudokuTest {

  Sudoku aSudoku;

  /**
   * Create a sudoku that is used in the tests
   */
  @Before
  public void createSudoku() {
    aSudoku = new Sudoku();
  }

  /**
   * A test to check if the set value of the top left field is readable
   */
  @Test
  public void getFirstFieldsValue() {
    aSudoku.setContent(1, 1, 1);
    assertEquals(1, aSudoku.getField(1, 1));
  }

  /**
   * A test to check if the set values of the top left field and the next right
   * field are readable
   */
  @Test
  public void testGetField() {
    aSudoku.setContent(1, 1, 1);
    aSudoku.setContent(2, 1, 2);
    assertEquals(1, aSudoku.getField(1, 1));
    assertEquals(2, aSudoku.getField(1, 2));
  }

  /**
   * Tries to get a field to wide on the y-side of life
   */
  @Test(expected=IndexOutOfBoundsException.class)
  public void getAnOutOfBoundField() {
    aSudoku.getField(9, 10);
  }

  /**
   * Tries to get a field to wide on the x-side of life
   */
  @Test(expected=IndexOutOfBoundsException.class)
  public void getAnotherOutOfBoundField() {
    aSudoku.getField(10, 9);
  }
}