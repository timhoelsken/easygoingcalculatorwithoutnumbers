package sudoku;

import static org.junit.Assert.assertArrayEquals;
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
    aSudoku.setValue(1, 1, 1);
    assertEquals(1, aSudoku.getField(1, 1));
  }

  /**
   * A test to check if the set values of the top left field and the next right
   * field are readable
   */
  @Test
  public void getTwoValuesOfTwoFields() {
    aSudoku.setValue(1, 1, 1);
    aSudoku.setValue(2, 1, 2);
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

  /**
   * Wants all missing numbers of square 1
   */
  @Test
  public void getMissingNumbersOfUpperLeftSquare() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(1));
  }

  /**
   * Wants all missing numbers of square 5
   */
  @Test
  public void getMissingNumbersOfMiddleSquare() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 5, 5);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(5));
  }

  /**
   * Wants all missing numbers of square 9
   */
  @Test
  public void getMissingNumbersOfLowerRightSquare() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 9, 9);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(9));
  }

  /**
   * Wants all missing numbers of square 1 (none in this case)
   */
  @Test
  public void noNumberMissingInUpperLeftSquare() {
    aSudoku.setValue(1, 1, 1);
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 1, 2);
    aSudoku.setValue(5, 2, 2);
    aSudoku.setValue(6, 3, 2);
    aSudoku.setValue(7, 1, 3);
    aSudoku.setValue(8, 2, 3);
    aSudoku.setValue(9, 3, 3);
    assertArrayEquals(new int[]{}, aSudoku.getMissingNumbersInSquare(1));
  }

  /**
   * Wants all missing numbers of row 1
   */
  @Test
  public void getMissingNumbersOfFirstRow() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(1));
  }

  /**
   * Wants all missing numbers of row 5
   */
  @Test
  public void getMissingNumbersOfMiddleRow() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 1, 5);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(5));
  }

  /**
   * Wants all missing numbers of row 9
   */
  @Test
  public void getMissingNumbersOfLastRow() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 1, 9);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(9));
  }

  /**
   * Wants all missing numbers of row 1 (none in this case)
   */
  @Test
  public void noNumberMissingInFirstRow() {
    aSudoku.setValue(1, 1, 1);
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 4, 1);
    aSudoku.setValue(5, 5, 1);
    aSudoku.setValue(6, 6, 1);
    aSudoku.setValue(7, 7, 1);
    aSudoku.setValue(8, 8, 1);
    aSudoku.setValue(9, 9, 1);
    assertArrayEquals(new int[]{}, aSudoku.getMissingNumbersInRow(1));
  }

  /**
   * Wants all missing numbers of column 1
   */
  @Test
  public void getMissingNumbersOfFirstColumn() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(1));
  }

  /**
   * Wants all missing numbers of column 5
   */
  @Test
  public void getMissingNumbersOfMiddleColumn() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 5, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(5));
  }

  /**
   * Wants all missing numbers of column 9
   */
  @Test
  public void getMissingNumbersOfLastColumn() {
    int[] expectedArray = {2, 3, 4, 5, 6, 7, 8, 9};
    aSudoku.setValue(1, 9, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(9));
  }
}