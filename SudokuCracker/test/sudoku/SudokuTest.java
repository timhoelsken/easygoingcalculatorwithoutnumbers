package sudoku;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import exceptions.NotPossibleException;

/**
 *
 * @authors Timbo & Tobe
 *
 */
public class SudokuTest {

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
    assertEquals(1, aSudoku.getValue(1, 1));
  }

  /**
   * A test to check if the standard value of a field is 0
   */
  @Test
  public void getFirstFieldsNonValue() {
    assertEquals(0, aSudoku.getValue(1, 1));
  }

  /**
   * A test to check if the set values of the top left field and the next right
   * field are readable
   */
  @Test
  public void getTwoValuesOfTwoFields() {
    aSudoku.setValue(1, 1, 1);
    aSudoku.setValue(2, 1, 2);
    assertEquals(1, aSudoku.getValue(1, 1));
    assertEquals(2, aSudoku.getValue(1, 2));
  }

  /**
   * Tries to get a field to wide on the y-side of life
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void getAnOutOfBoundField() {
    aSudoku.getValue(9, 10);
  }

  /**
   * Tries to get a field to wide on the x-side of life
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void getAnotherOutOfBoundField() {
    aSudoku.getValue(10, 9);
  }

  /**
   * Wants all missing numbers of square 1
   */
  @Test
  public void getMissingNumbersOfUpperLeftSquare() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(1));
  }

  /**
   * Wants all missing numbers of square 3
   */
  @Test
  public void getMissingNumbersOfUpperRightSquareWithOtherNumbersInSquareTwoExisting() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 9, 3);
    aSudoku.setValue(2, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(3));
  }

  /**
   * Wants all missing numbers of square 5
   */
  @Test
  public void getMissingNumbersOfCenterSquareWithOtherNumbersAround() {
    int[] expectedArray = { 1, 2, 3, 4, 6, 7, 8, 9 };
    aSudoku.setValue(5, 5, 5);
    aSudoku.setValue(1, 2, 2);
    aSudoku.setValue(2, 5, 2);
    aSudoku.setValue(3, 8, 2);
    aSudoku.setValue(4, 2, 5);
    aSudoku.setValue(6, 8, 5);
    aSudoku.setValue(7, 2, 8);
    aSudoku.setValue(8, 5, 8);
    aSudoku.setValue(9, 8, 8);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(5));
  }

  /**
   * Wants all missing numbers of square 5
   */
  @Test
  public void getMissingNumbersOfMiddleSquare() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 5, 5);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(5));
  }

  /**
   * Wants all missing numbers of square 9
   */
  @Test
  public void getMissingNumbersOfLowerRightSquare() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
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
    assertArrayEquals(new int[] {}, aSudoku.getMissingNumbersInSquare(1));
  }

  /**
   * Wants all missing numbers of row 1
   */
  @Test
  public void getMissingNumbersOfFirstRow() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(1));
  }

  /**
   * Wants all missing numbers of row 5
   */
  @Test
  public void getMissingNumbersOfMiddleRow() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 5);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(5));
  }

  /**
   * Wants all missing numbers of row 9
   */
  @Test
  public void getMissingNumbersOfLastRow() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
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
    assertArrayEquals(new int[] {}, aSudoku.getMissingNumbersInRow(1));
  }

  /**
   * Wants all missing numbers of column 1
   */
  @Test
  public void getMissingNumbersOfFirstColumn() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(1));
  }

  /**
   * Wants all missing numbers of column 5
   */
  @Test
  public void getMissingNumbersOfMiddleColumn() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 5, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(5));
  }

  /**
   * Wants all missing numbers of column 9
   */
  @Test
  public void getMissingNumbersOfLastColumn() {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 9, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(9));
  }

  /**
   * Wants all missing numbers of column 1 (none in this case)
   */
  @Test
  public void noNumberMissingInFirstColumn() {
    aSudoku.setValue(1, 1, 1);
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    aSudoku.setValue(4, 1, 4);
    aSudoku.setValue(5, 1, 5);
    aSudoku.setValue(6, 1, 6);
    aSudoku.setValue(7, 1, 7);
    aSudoku.setValue(8, 1, 8);
    aSudoku.setValue(9, 1, 9);
    assertArrayEquals(new int[] {}, aSudoku.getMissingNumbersInColumn(1));
  }

  /**
   * Wants all numbers that are missing in row 1 and square 2
   */
  @Test
  public void getMissingNumbersOfFirstRowAndMiddleUpperSquare() {
    int[] expectedArray = { 3, 5, 7 };
    // values in row only
    aSudoku.setValue(1, 2, 1);
    aSudoku.setValue(2, 8, 1);
    // values in square only
    aSudoku.setValue(4, 4, 2);
    aSudoku.setValue(6, 5, 3);
    // values in both
    aSudoku.setValue(8, 4, 1);
    aSudoku.setValue(9, 6, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRowAndSquare(1, 2));
  }

  /**
   * Wants all numbers that are missing in column 1 and square 4
   */
  @Test
  public void getMissingNumbersOfFirstColumnAndMiddleLeftSquare() {
    int[] expectedArray = { 3, 5, 7 };
    // values in row only
    aSudoku.setValue(1, 1, 2);
    aSudoku.setValue(2, 1, 8);
    // values in square only
    aSudoku.setValue(4, 2, 4);
    aSudoku.setValue(6, 3, 5);
    // values in both
    aSudoku.setValue(8, 1, 4);
    aSudoku.setValue(9, 1, 6);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumnAndSquare(1, 4));
  }

  /**
   * Wants to calculate the number of the specified field (easy possible) and
   * that this number is the field's value after calculation
   *
   * @throws NotPossibleException
   */
  @Test
  public void getMissingNumberOfField() throws NotPossibleException {
    // middle row
    aSudoku.setValue(1, 1, 5);
    aSudoku.setValue(2, 2, 5);
    aSudoku.setValue(3, 3, 5);
    aSudoku.setValue(4, 4, 5);
    aSudoku.setValue(6, 6, 5);
    aSudoku.setValue(7, 7, 5);
    aSudoku.setValue(8, 8, 5);
    aSudoku.setValue(9, 9, 5);
    // middle column
    aSudoku.setValue(1, 5, 1);
    aSudoku.setValue(2, 5, 2);
    aSudoku.setValue(3, 5, 3);
    aSudoku.setValue(4, 5, 4);
    aSudoku.setValue(6, 5, 6);
    aSudoku.setValue(7, 5, 7);
    aSudoku.setValue(8, 5, 8);
    aSudoku.setValue(9, 5, 9);
    assertEquals(5, aSudoku.getMissingNumberOfField(5, 5));
    assertEquals(5, aSudoku.getValue(5, 5));
  }

  /**
   * Determs the row of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test
  public void getRowWhereNumberMustBeInSquare2() throws NotPossibleException {
    aSudoku.setValue(3, 1, 1);
    aSudoku.setValue(3, 9, 3);

    assertEquals(2, aSudoku.getRowOfMissingNumber(2, 3));
  }
  
  /**
   * Determs the row of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test
  public void getRowWhereNumberMustBeInSquare4() throws NotPossibleException {
    aSudoku.setValue(3, 5, 5);
    aSudoku.setValue(3, 9, 4);

    assertEquals(6, aSudoku.getRowOfMissingNumber(4, 3));
  }
  
  /**
   * Determs the row of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test
  public void getRowWhereNumberMustBeInSquare9() throws NotPossibleException {
    aSudoku.setValue(3, 5, 8);
    aSudoku.setValue(3, 2, 9);

    assertEquals(7, aSudoku.getRowOfMissingNumber(9, 3));
  }

  /**
   * Determs the row of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test(expected = NotPossibleException.class)
  public void getExceptionWhileGetRowWhereNumberMustBe() throws NotPossibleException {
    aSudoku.setValue(3, 9, 3);

    assertEquals(2, aSudoku.getRowOfMissingNumber(2, 3));
  }

  /**
   * Determs the column of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test
  public void getColumnWhereNumberMustBeInSquare5() throws NotPossibleException {
    aSudoku.setValue(4, 5, 1);
    aSudoku.setValue(4, 6, 9);

    assertEquals(4, aSudoku.getColumnOfMissingNumber(5, 4));
  }
  
  /**
   * Determs the column of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test
  public void getColumnWhereNumberMustBeInSquare3() throws NotPossibleException {
    aSudoku.setValue(4, 8, 5);
    aSudoku.setValue(4, 9, 7);

    assertEquals(7, aSudoku.getColumnOfMissingNumber(3, 4));
  }
  
  /**
   * Determs the column of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test
  public void getColumnWhereNumberMustBeInSquare7() throws NotPossibleException {
    aSudoku.setValue(4, 1, 3);
    aSudoku.setValue(4, 3, 6);

    assertEquals(2, aSudoku.getColumnOfMissingNumber(7, 4));
  }

  /**
   * Determs the column of the missing number in a square.
   * Two other numbers must be set in the neighbour squares
   * 
   * @throws NotPossibleException 
   */
  @Test(expected = NotPossibleException.class)
  public void getExceptionWhileGetColumnWhereNumberMustBe() throws NotPossibleException {
    aSudoku.setValue(4, 5, 1);

    aSudoku.getColumnOfMissingNumber(5, 4);
  }

  /**
   * Determs the coordinates of a number in a square
   * 
   * @throws NotPossibleException 
   */
  @Test
  public void findNumberInSquare() throws NotPossibleException {
    int[] expectedArray = { 2, 2 };
    aSudoku.setValue(1, 2, 2);
    int[] tmpResult = aSudoku.locateNumberInSquare(1, 1);
    assertEquals(expectedArray[0], tmpResult[0]);
    assertEquals(expectedArray[1], tmpResult[1]);
  }

  /**
   * Determs the coordinates of a number in a square (number does not exist in square)
   * 
   * @throws NotPossibleException 
   */
  @Test(expected = NotPossibleException.class)
  public void getExceptionFindingNumberInSquare() throws NotPossibleException {
    aSudoku.locateNumberInSquare(1, 1);
  }

  /**
   * Wants the number of the specified field (not easy possible)
   *
   * @throws NotPossibleException
   */
  @Test(expected = NotPossibleException.class)
  public void getExceptionWhileWantingMissingNumberOfField() throws NotPossibleException {
    aSudoku.getMissingNumberOfField(5, 5);
  }

}