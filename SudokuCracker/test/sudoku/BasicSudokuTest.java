package sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
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
   * A test to check if the sudoku has a dimension of nine fields vertically and
   * horizontally (default)
   */
  @Test
  public void checkDimension() {
    assertEquals(9, aSudoku.getDimension());
  }

  /**
   * A test to check if the top left field is setable
   */
  @Test
  public void setFirstField() {
    assertTrue(aSudoku.setContent(1, 1, 1));
  }

  /**
   * A test to check if the set value of the top left field is readable
   */
  @Test
  public void getFirstField() {
    aSudoku.setContent(1, 1, 1);
    assertEquals(1, aSudoku.getField(1, 1));
  }

  /**
   * A test to check if the set values of the top left field and the next right
   * field are readable
   */
  @Ignore ("Lower level first")
  @Test
  public void testGetField() {
    aSudoku.setContent(1, 1, 1);
    aSudoku.setContent(2, 1, 2);
    assertEquals(1, aSudoku.getField(1, 1));
    assertEquals(2, aSudoku.getField(1, 2));
  }
}