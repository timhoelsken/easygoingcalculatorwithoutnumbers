package sudoku;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import sudoku.exceptions.InternalException;
import sudoku.exceptions.NotPossibleException;


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
   * @throws InternalException
   */
  @Test
  public void getFirstFieldsValue() throws InternalException {
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
   * @throws InternalException
   */
  @Test
  public void getTwoValuesOfTwoFields() throws InternalException {
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
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in the setted row yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingRow() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 2, 4);
    // evoke exception
    aSudoku.setValue(1, 8, 4);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in the setted column yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingColumn() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 5, 4);
    // evoke exception
    aSudoku.setValue(1, 5, 6);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare1() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 1, 1);
    // evoke exception
    aSudoku.setValue(1, 3, 3);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare2() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 4, 1);
    // evoke exception
    aSudoku.setValue(1, 6, 3);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare3() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 7, 1);
    // evoke exception
    aSudoku.setValue(1, 9, 3);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare4() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 1, 4);
    // evoke exception
    aSudoku.setValue(1, 3, 6);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare5() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 4, 4);
    // evoke exception
    aSudoku.setValue(1, 6, 6);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare6() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 7, 4);
    // evoke exception
    aSudoku.setValue(1, 9, 6);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare7() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 1, 7);
    // evoke exception
    aSudoku.setValue(1, 3, 9);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare8() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 4, 7);
    // evoke exception
    aSudoku.setValue(1, 6, 9);
  }

  /**
   * Wants to get an internal exception because the value that should be set is
   * not right. It is in square 5 yet.
   * @throws InternalException
   */
  @Test(expected = InternalException.class)
  public void avoidWrongValueSettingRegardingSquare9() throws InternalException {
    // prepare sudoku
    aSudoku.setValue(1, 7, 7);
    // evoke exception
    aSudoku.setValue(1, 9, 9);
  }

  /**
   * Wants all missing numbers of square 1
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfUpperLeftSquare() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(1));
  }

  /**
   * Wants all missing numbers of square 3
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfUpperRightSquareWithOtherNumbersInSquareTwoExisting() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 9, 3);
    aSudoku.setValue(2, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(3));
  }

  /**
   * Wants all missing numbers of square 5
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfCenterSquareWithOtherNumbersAround() throws InternalException {
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
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfMiddleSquare() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 5, 5);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(5));
  }

  /**
   * Wants all missing numbers of square 9
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfLowerRightSquare() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 9, 9);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInSquare(9));
  }

  /**
   * Wants all missing numbers of square 1 (none in this case)
   * @throws InternalException
   */
  @Test
  public void noNumberMissingInUpperLeftSquare() throws InternalException {
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
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfFirstRow() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(1));
  }

  /**
   * Wants all missing numbers of row 5
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfMiddleRow() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 5);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(5));
  }

  /**
   * Wants all missing numbers of row 9
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfLastRow() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 9);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInRow(9));
  }

  /**
   * Wants all missing numbers of row 1 (none in this case)
   * @throws InternalException
   */
  @Test
  public void noNumberMissingInFirstRow() throws InternalException {
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
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfFirstColumn() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 1, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(1));
  }

  /**
   * Wants all missing numbers of column 5
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfMiddleColumn() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 5, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(5));
  }

  /**
   * Wants all missing numbers of column 9
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfLastColumn() throws InternalException {
    int[] expectedArray = { 2, 3, 4, 5, 6, 7, 8, 9 };
    aSudoku.setValue(1, 9, 1);
    assertArrayEquals(expectedArray, aSudoku.getMissingNumbersInColumn(9));
  }

  /**
   * Wants all missing numbers of column 1 (none in this case)
   * @throws InternalException
   */
  @Test
  public void noNumberMissingInFirstColumn() throws InternalException {
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
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfFirstRowAndMiddleUpperSquare() throws InternalException {
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
   * @throws InternalException
   */
  @Test
  public void getMissingNumbersOfFirstColumnAndMiddleLeftSquare() throws InternalException {
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
   * @throws InternalException
   */
   @Test
  public void getMissingNumberOfField() throws NotPossibleException, InternalException {
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
    aSudoku.setValue(4, 5, 3);
    aSudoku.setValue(3, 5, 4);
    aSudoku.setValue(7, 5, 6);
    aSudoku.setValue(6, 5, 7);
    aSudoku.setValue(8, 5, 8);
    aSudoku.setValue(9, 5, 9);
    assertEquals(5, aSudoku.getMissingNumberOfField(5, 5));
    assertEquals(5, aSudoku.getValue(5, 5));
  }

  /**
   * Determs the row of the missing number in a square. Two other numbers must
   * be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void getRowWhereNumberMustBeInSquare2() throws NotPossibleException, InternalException {
    aSudoku.setValue(3, 1, 1);
    aSudoku.setValue(3, 9, 3);

    assertEquals(2, aSudoku.getRowOfMissingNumber(2, 3));
  }

  /**
   * Determs the row of the missing number in a square. Two other numbers must
   * be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void getRowWhereNumberMustBeInSquare4() throws NotPossibleException, InternalException {
    aSudoku.setValue(3, 5, 5);
    aSudoku.setValue(3, 9, 4);

    assertEquals(6, aSudoku.getRowOfMissingNumber(4, 3));
  }

  /**
   * Determs the row of the missing number in a square. Two other numbers must
   * be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void getRowWhereNumberMustBeInSquare9() throws NotPossibleException, InternalException {
    aSudoku.setValue(3, 5, 8);
    aSudoku.setValue(3, 2, 9);

    assertEquals(7, aSudoku.getRowOfMissingNumber(9, 3));
  }

  /**
   * Determs the row of the missing number in a square. Two other numbers must
   * be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test(expected = NotPossibleException.class)
  public void getExceptionWhileGetRowWhereNumberMustBe() throws NotPossibleException, InternalException {
    aSudoku.setValue(3, 9, 3);

    assertEquals(2, aSudoku.getRowOfMissingNumber(2, 3));
  }

  /**
   * Determs the column of the missing number in a square. Two other numbers
   * must be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void getColumnWhereNumberMustBeInSquare5() throws NotPossibleException, InternalException {
    aSudoku.setValue(4, 5, 1);
    aSudoku.setValue(4, 6, 9);

    assertEquals(4, aSudoku.getColumnOfMissingNumber(5, 4));
  }

  /**
   * Determs the column of the missing number in a square. Two other numbers
   * must be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void getColumnWhereNumberMustBeInSquare3() throws NotPossibleException, InternalException {
    aSudoku.setValue(4, 8, 5);
    aSudoku.setValue(4, 9, 7);

    assertEquals(7, aSudoku.getColumnOfMissingNumber(3, 4));
  }

  /**
   * Determs the column of the missing number in a square. Two other numbers
   * must be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void getColumnWhereNumberMustBeInSquare7() throws NotPossibleException, InternalException {
    aSudoku.setValue(4, 1, 3);
    aSudoku.setValue(4, 3, 6);

    assertEquals(2, aSudoku.getColumnOfMissingNumber(7, 4));
  }

  /**
   * Determs the column of the missing number in a square. Two other numbers
   * must be set in the neighbour squares
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test(expected = NotPossibleException.class)
  public void getExceptionWhileGetColumnWhereNumberMustBe() throws NotPossibleException, InternalException {
    aSudoku.setValue(4, 5, 1);

    aSudoku.getColumnOfMissingNumber(5, 4);
  }

  /**
   * Determs the coordinates of a number in a square
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  @Ignore
  public void findNumberInSquare() throws NotPossibleException, InternalException {
//    int[] expectedArray = { 2, 2 };
//    aSudoku.setValue(1, 2, 2);
//    int[] tmpResult = aSudoku.locateNumberInSquare(1, 1);
//    assertEquals(expectedArray[0], tmpResult[0]);
//    assertEquals(expectedArray[1], tmpResult[1]);
  }

  /**
   * Determs the coordinates of a number in a square (number does not exist in
   * square)
   *
   * @throws NotPossibleException
   */
  @Test(expected = NotPossibleException.class)
  @Ignore
  public void getExceptionFindingNumberInSquare() throws NotPossibleException {
//    aSudoku.locateNumberInSquare(1, 1);
  }

  /**
   * Wants the number of the specified field (not easy possible)
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test(expected = NotPossibleException.class)
  public void getExceptionWhileWantingMissingNumberOfField() throws NotPossibleException, InternalException {
    aSudoku.getMissingNumberOfField(5, 5);
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowNone() throws NotPossibleException, InternalException {
        
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowOne() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowTwo() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }

  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowThree() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 4, 1);
    
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowFour() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 4, 1);
    aSudoku.setValue(5, 5, 1);
    
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowFife() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 4, 1);
    aSudoku.setValue(5, 5, 1);
    aSudoku.setValue(6, 6, 1);
    
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowSix() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 4, 1);
    aSudoku.setValue(5, 5, 1);
    aSudoku.setValue(6, 6, 1);
    aSudoku.setValue(7, 7, 1);
    
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowSeven() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 4, 1);
    aSudoku.setValue(5, 5, 1);
    aSudoku.setValue(6, 6, 1);
    aSudoku.setValue(7, 7, 1);
    aSudoku.setValue(8, 8, 1);
    
    assertFalse(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the rows values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInRowEight() throws NotPossibleException, InternalException {
    aSudoku.setValue(2, 2, 1);
    aSudoku.setValue(3, 3, 1);
    aSudoku.setValue(4, 4, 1);
    aSudoku.setValue(5, 5, 1);
    aSudoku.setValue(6, 6, 1);
    aSudoku.setValue(7, 7, 1);
    aSudoku.setValue(8, 8, 1);
    aSudoku.setValue(9, 9, 1);
    
    assertTrue(aSudoku.setValueForFieldInRow(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnNone() throws NotPossibleException, InternalException {
        
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnOne() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(2, 1, 2);
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnTwo() throws NotPossibleException, InternalException {
    
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnThree() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    aSudoku.setValue(4, 1, 4);
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnFour() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    aSudoku.setValue(4, 1, 4);
    aSudoku.setValue(5, 1, 5);
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnFife() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    aSudoku.setValue(4, 1, 4);
    aSudoku.setValue(5, 1, 5);
    aSudoku.setValue(6, 1, 6);
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnSix() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    aSudoku.setValue(4, 1, 4);
    aSudoku.setValue(5, 1, 5);
    aSudoku.setValue(6, 1, 6);
    aSudoku.setValue(7, 1, 7);
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnSeven() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    aSudoku.setValue(4, 1, 4);
    aSudoku.setValue(5, 1, 5);
    aSudoku.setValue(6, 1, 6);
    aSudoku.setValue(7, 1, 7);
    aSudoku.setValue(8, 1, 8);
    assertFalse(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the column values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInColumnEight() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(2, 1, 2);
    aSudoku.setValue(3, 1, 3);
    aSudoku.setValue(4, 1, 4);
    aSudoku.setValue(5, 1, 5);
    aSudoku.setValue(6, 1, 6);
    aSudoku.setValue(7, 1, 7);
    aSudoku.setValue(8, 1, 8);
    aSudoku.setValue(9, 1, 9);
    assertTrue(aSudoku.setValueForFieldInColumn(1, 1));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareNone() throws NotPossibleException, InternalException {
        
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareOne() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareTwo() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    aSudoku.setValue(2, 5, 4);
    
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareThree() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    aSudoku.setValue(2, 5, 4);
    aSudoku.setValue(3, 6, 4);
    
    
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareFour() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    aSudoku.setValue(2, 5, 4);
    aSudoku.setValue(3, 6, 4);
    aSudoku.setValue(4, 4, 5);
    
    
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareFife() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    aSudoku.setValue(2, 5, 4);
    aSudoku.setValue(3, 6, 4);
    aSudoku.setValue(4, 4, 5);
    aSudoku.setValue(6, 6, 5);
    
    
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareSix() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    aSudoku.setValue(2, 5, 4);
    aSudoku.setValue(3, 6, 4);
    aSudoku.setValue(4, 4, 5);
    aSudoku.setValue(6, 6, 5);
    aSudoku.setValue(7, 4, 6);
    
    
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareSeven() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    aSudoku.setValue(2, 5, 4);
    aSudoku.setValue(3, 6, 4);
    aSudoku.setValue(4, 4, 5);
    aSudoku.setValue(6, 6, 5);
    aSudoku.setValue(7, 4, 6);
    aSudoku.setValue(8, 5, 6);
    
    
    assertFalse(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the square values
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberInSquareEight() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(1, 4, 4);
    aSudoku.setValue(2, 5, 4);
    aSudoku.setValue(3, 6, 4);
    aSudoku.setValue(4, 4, 5);
    aSudoku.setValue(6, 6, 5);
    aSudoku.setValue(7, 4, 6);
    aSudoku.setValue(8, 5, 6);
    aSudoku.setValue(9, 6, 6);
    
    
    assertTrue(aSudoku.setValueForFieldInSquare(5, 5));
  }
  
  /**
   * Wants to set a number depending on the row combination
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithSingleRowCombination() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 1, 4);
    aSudoku.setValue(5, 9, 6);
    aSudoku.setValue(3, 4, 5);
    aSudoku.setValue(4, 6, 5);
        
    assertTrue(aSudoku.setValueForSingleFieldInRowCombination(5, 5));
  }
  
  /**
   * Wants to set a number depending on the row combination
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithSingleRowCombinationNotPossible() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 1, 4);
    aSudoku.setValue(5, 9, 6);
    aSudoku.setValue(3, 4, 5);
        
    assertFalse(aSudoku.setValueForSingleFieldInRowCombination(5, 5));
  }
  
  /**
   * Wants to set a number depending on the row combination
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithSingleRowCombinationAlsoNotPossible() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 1, 4);
    aSudoku.setValue(5, 9, 6);
    aSudoku.setValue(4, 6, 5);
        
    assertFalse(aSudoku.setValueForSingleFieldInRowCombination(5, 5));
  }
  
  /**
   * Wants to set a number depending on the row combination
   * (does not fill 5,5 but 4,5!)
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithDoubleRowCombination() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 1, 4);
    aSudoku.setValue(5, 9, 6);
    aSudoku.setValue(4, 6, 5);
    aSudoku.setValue(5, 5, 3);
        
    assertTrue(aSudoku.setValueForDoubleFieldInRowCombination(5, 5));
  }
  
  /**
   * Wants to set a number depending on the row combination
   * 
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithDoubleRowCombinationAnotherOne() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 1, 4);
    aSudoku.setValue(5, 9, 6);
    aSudoku.setValue(4, 6, 5);
    aSudoku.setValue(5, 4, 3);
        
    assertTrue(aSudoku.setValueForDoubleFieldInRowCombination(5, 5));
  }
  
  /**
   * Wants to set a number depending on the row combination
   * 
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithDoubleRowCombinationNotPossible() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 1, 4);
    aSudoku.setValue(5, 9, 6);
    aSudoku.setValue(4, 6, 5);
            
    assertFalse(aSudoku.setValueForDoubleFieldInRowCombination(5, 5));
  }
  
  /**
   * Wants to set a number depending on the column combination
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithSingleColumnCombination() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 4, 1);
    aSudoku.setValue(5, 6, 9);
    aSudoku.setValue(3, 5, 4);
    aSudoku.setValue(4, 5, 6);
        
    assertTrue(aSudoku.setValueForSingleFieldInColumnCombination(5, 5));
  }
  
  /**
   * Wants to set a number depending on the column combination
   *
   * @throws NotPossibleException
   * @throws InternalException
   */
  @Test
  public void setNumberWithSingleColumnCombinationNotPossible() throws NotPossibleException, InternalException {
        
    aSudoku.setValue(5, 4, 1);
    aSudoku.setValue(5, 6, 9);
    aSudoku.setValue(3, 5, 4);
    
        
    assertFalse(aSudoku.setValueForSingleFieldInColumnCombination(5, 5));
  }
}