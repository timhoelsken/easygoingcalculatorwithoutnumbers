package sudoku;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
  @Test(expected = IndexOutOfBoundsException.class)
  public void setNegativeBoundaryFields1() throws SetException {
    sudoku.set(1, 0, 1);
  }

  /**
   * A value set beyond the Sudoku is not allowed.
   *
   * @throws SetException
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void setNegativeBoundaryFields2() throws SetException {
    sudoku.set(1, 1, 0);
  }

  /**
   * A value set beyond the Sudoku is not allowed.
   *
   * @throws SetException
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void setNegativeBoundaryFields3() throws SetException {
    sudoku.set(1, 9, 10);
  }

  /**
   * A value set beyond the Sudoku is not allowed.
   *
   * @throws SetException
   */
  @Test(expected = IndexOutOfBoundsException.class)
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

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   *
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowOne() {
    assertArrayEquals(new int[] { 1, 2, 3 }, sudoku.getPossibleSquaresForRow(1));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   *
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowThree() {
    assertArrayEquals(new int[] { 1, 2, 3 }, sudoku.getPossibleSquaresForRow(3));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   *
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowFour() {
    assertArrayEquals(new int[] { 4, 5, 6 }, sudoku.getPossibleSquaresForRow(4));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   *
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowSix() {
    assertArrayEquals(new int[] { 4, 5, 6 }, sudoku.getPossibleSquaresForRow(6));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   *
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowSeven() {
    assertArrayEquals(new int[] { 7, 8, 9 }, sudoku.getPossibleSquaresForRow(7));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   *
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowNine() {
    assertArrayEquals(new int[] { 7, 8, 9 }, sudoku.getPossibleSquaresForRow(9));
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow1() {
    sudoku.getPossibleSquaresForRow(0);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow2() {
    sudoku.getPossibleSquaresForRow(-1);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow3() {
    sudoku.getPossibleSquaresForRow(10);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow4() {
    sudoku.getPossibleSquaresForRow(11);
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnOne() {
    assertArrayEquals(new int[] { 1, 4, 7 }, sudoku.getPossibleSquaresForColumn(1));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnThree() {
    assertArrayEquals(new int[] { 1, 4, 7 }, sudoku.getPossibleSquaresForColumn(3));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnFour() {
    assertArrayEquals(new int[] { 2, 5, 8 }, sudoku.getPossibleSquaresForColumn(4));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnSix() {
    assertArrayEquals(new int[] { 2, 5, 8 }, sudoku.getPossibleSquaresForColumn(6));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnSeven() {
    assertArrayEquals(new int[] { 3, 6, 9 }, sudoku.getPossibleSquaresForColumn(7));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnNine() {
    assertArrayEquals(new int[] { 3, 6, 9 }, sudoku.getPossibleSquaresForColumn(9));
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn1() {
    sudoku.getPossibleSquaresForColumn(0);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn2() {
    sudoku.getPossibleSquaresForColumn(-1);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn3() {
    sudoku.getPossibleSquaresForColumn(10);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn4() {
    sudoku.getPossibleSquaresForColumn(11);
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareOne() {
    assertEquals(1, sudoku.getSquareNumber(1, 1));
    assertEquals(1, sudoku.getSquareNumber(3, 1));
    assertEquals(1, sudoku.getSquareNumber(1, 3));
    assertEquals(1, sudoku.getSquareNumber(3, 3));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareTwo() {
    assertEquals(2, sudoku.getSquareNumber(4, 1));
    assertEquals(2, sudoku.getSquareNumber(6, 1));
    assertEquals(2, sudoku.getSquareNumber(4, 3));
    assertEquals(2, sudoku.getSquareNumber(6, 3));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareThree() {
    assertEquals(3, sudoku.getSquareNumber(7, 1));
    assertEquals(3, sudoku.getSquareNumber(9, 1));
    assertEquals(3, sudoku.getSquareNumber(7, 3));
    assertEquals(3, sudoku.getSquareNumber(9, 3));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareFour() {
    assertEquals(4, sudoku.getSquareNumber(1, 4));
    assertEquals(4, sudoku.getSquareNumber(3, 4));
    assertEquals(4, sudoku.getSquareNumber(1, 6));
    assertEquals(4, sudoku.getSquareNumber(3, 6));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareFive() {
    assertEquals(5, sudoku.getSquareNumber(4, 4));
    assertEquals(5, sudoku.getSquareNumber(6, 4));
    assertEquals(5, sudoku.getSquareNumber(4, 6));
    assertEquals(5, sudoku.getSquareNumber(6, 6));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareSix() {
    assertEquals(6, sudoku.getSquareNumber(7, 4));
    assertEquals(6, sudoku.getSquareNumber(9, 4));
    assertEquals(6, sudoku.getSquareNumber(7, 6));
    assertEquals(6, sudoku.getSquareNumber(9, 6));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareSeven() {
    assertEquals(7, sudoku.getSquareNumber(1, 7));
    assertEquals(7, sudoku.getSquareNumber(3, 7));
    assertEquals(7, sudoku.getSquareNumber(1, 9));
    assertEquals(7, sudoku.getSquareNumber(3, 9));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareEight() {
    assertEquals(8, sudoku.getSquareNumber(4, 7));
    assertEquals(8, sudoku.getSquareNumber(6, 7));
    assertEquals(8, sudoku.getSquareNumber(4, 9));
    assertEquals(8, sudoku.getSquareNumber(6, 9));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareNine() {
    assertEquals(9, sudoku.getSquareNumber(7, 7));
    assertEquals(9, sudoku.getSquareNumber(9, 7));
    assertEquals(9, sudoku.getSquareNumber(7, 9));
    assertEquals(9, sudoku.getSquareNumber(9, 9));
  }

  /**
   * All numbers of a row should be investigable
   *
   * @throws SetException
   */
  @Test
  public void showNumbersOfFullRow() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 2, 1);
    sudoku.set(3, 3, 1);
    sudoku.set(4, 4, 1);
    sudoku.set(5, 5, 1);
    sudoku.set(6, 6, 1);
    sudoku.set(7, 7, 1);
    sudoku.set(8, 8, 1);
    sudoku.set(9, 9, 1);
    ArrayList<Integer> tmpExpectedList = new ArrayList<Integer>();
    tmpExpectedList.add(1);
    tmpExpectedList.add(2);
    tmpExpectedList.add(3);
    tmpExpectedList.add(4);
    tmpExpectedList.add(5);
    tmpExpectedList.add(6);
    tmpExpectedList.add(7);
    tmpExpectedList.add(8);
    tmpExpectedList.add(9);
    assertEquals(tmpExpectedList, sudoku.getRowNumbers(1));
  }

  /**
   * All numbers of a row should be investigable
   *
   * @throws SetException
   */
  @Test
  public void showNumbersOfHalfFilledRow() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(3, 3, 1);
    sudoku.set(4, 4, 1);
    sudoku.set(6, 6, 1);
    sudoku.set(7, 7, 1);
    sudoku.set(9, 9, 1);
    ArrayList<Integer> tmpExpectedList = new ArrayList<Integer>();
    tmpExpectedList.add(1);
    tmpExpectedList.add(3);
    tmpExpectedList.add(4);
    tmpExpectedList.add(6);
    tmpExpectedList.add(7);
    tmpExpectedList.add(9);
    assertEquals(tmpExpectedList, sudoku.getRowNumbers(1));
  }

  /**
   * All numbers of a row should be investigable
   *
   * @throws SetException
   */
  @Test
  public void showNumbersOfEmptyRow() throws SetException {
    ArrayList<Integer> tmpExpectedList = new ArrayList<Integer>();
    assertEquals(tmpExpectedList, sudoku.getRowNumbers(1));
  }

  /**
   * All numbers of a column should be investigable
   *
   * @throws SetException
   */
  @Test
  public void showNumbersOfFullColumn() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 1, 2);
    sudoku.set(3, 1, 3);
    sudoku.set(4, 1, 4);
    sudoku.set(5, 1, 5);
    sudoku.set(6, 1, 6);
    sudoku.set(7, 1, 7);
    sudoku.set(8, 1, 8);
    sudoku.set(9, 1, 9);
    ArrayList<Integer> tmpExpectedList = new ArrayList<Integer>();
    tmpExpectedList.add(1);
    tmpExpectedList.add(2);
    tmpExpectedList.add(3);
    tmpExpectedList.add(4);
    tmpExpectedList.add(5);
    tmpExpectedList.add(6);
    tmpExpectedList.add(7);
    tmpExpectedList.add(8);
    tmpExpectedList.add(9);
    assertEquals(tmpExpectedList, sudoku.getColumnNumbers(1));
  }

  /**
   * All numbers of a column should be investigable
   *
   * @throws SetException
   */
  @Test
  public void showNumbersOfHalfFilledColumn() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(3, 1, 3);
    sudoku.set(4, 1, 4);
    sudoku.set(6, 1, 6);
    sudoku.set(7, 1, 7);
    sudoku.set(9, 1, 9);
    ArrayList<Integer> tmpExpectedList = new ArrayList<Integer>();
    tmpExpectedList.add(1);
    tmpExpectedList.add(3);
    tmpExpectedList.add(4);
    tmpExpectedList.add(6);
    tmpExpectedList.add(7);
    tmpExpectedList.add(9);
    assertEquals(tmpExpectedList, sudoku.getColumnNumbers(1));
  }

  /**
   * All numbers of a column should be investigable
   *
   * @throws SetException
   */
  @Test
  public void showNumbersOfEmptyColumn() throws SetException {
    ArrayList<Integer> tmpExpectedList = new ArrayList<Integer>();
    assertEquals(tmpExpectedList, sudoku.getColumnNumbers(1));
  }
}
