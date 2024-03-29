package sudoku;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sudoku.exceptions.SetException;

/**
 * Basic functional tests of the Sudoku
 * 
 * @author Tobias
 */
public class SudokuBasics {

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
    sudoku.set(2, 1, 9);
    sudoku.set(3, 9, 1);
    sudoku.set(4, 9, 9);
    assertEquals(1, sudoku.get(1, 1));
    assertEquals(2, sudoku.get(1, 9));
    assertEquals(3, sudoku.get(9, 1));
    assertEquals(4, sudoku.get(9, 9));
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
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForRow(1));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   * 
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowThree() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForRow(3));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   * 
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowFour() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(6);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForRow(4));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   * 
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowSix() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(6);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForRow(6));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   * 
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowSeven() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(8);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForRow(7));
  }

  /**
   * For each row there are three possible squares a field of that row could
   * belong to
   * 
   * @throws SetException
   */
  @Test
  public void determineSquarePossiblitiesForRowNine() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(8);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForRow(9));
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow1() {
    sudoku.getPossibleSquareNumbersForRow(0);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow2() {
    sudoku.getPossibleSquareNumbersForRow(-1);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow3() {
    sudoku.getPossibleSquareNumbersForRow(10);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidRow4() {
    sudoku.getPossibleSquareNumbersForRow(11);
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnOne() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(7);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForColumn(1));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnThree() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(7);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForColumn(3));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnFour() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(8);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForColumn(4));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnSix() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(8);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForColumn(6));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnSeven() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForColumn(7));
  }

  /**
   * For each column there are three possible squares a field of that column
   * could belong to
   */
  @Test
  public void determineSquarePossiblitiesForColumnNine() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getPossibleSquareNumbersForColumn(9));
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn1() {
    sudoku.getPossibleSquareNumbersForColumn(0);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn2() {
    sudoku.getPossibleSquareNumbersForColumn(-1);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn3() {
    sudoku.getPossibleSquareNumbersForColumn(10);
  }

  /**
   * A row beyond the sudoku is not known
   */
  @Test(expected = IllegalArgumentException.class)
  public void determineSquarePossiblitiesWithInvalidColumn4() {
    sudoku.getPossibleSquareNumbersForColumn(11);
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareOne() {
    assertEquals(Square.getSquare(1), sudoku.getSquare(1, 1));
    assertEquals(Square.getSquare(1), sudoku.getSquare(3, 1));
    assertEquals(Square.getSquare(1), sudoku.getSquare(1, 3));
    assertEquals(Square.getSquare(1), sudoku.getSquare(3, 3));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareTwo() {
    assertEquals(Square.getSquare(2), sudoku.getSquare(4, 1));
    assertEquals(Square.getSquare(2), sudoku.getSquare(6, 1));
    assertEquals(Square.getSquare(2), sudoku.getSquare(4, 3));
    assertEquals(Square.getSquare(2), sudoku.getSquare(6, 3));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareThree() {
    assertEquals(Square.getSquare(3), sudoku.getSquare(7, 1));
    assertEquals(Square.getSquare(3), sudoku.getSquare(9, 1));
    assertEquals(Square.getSquare(3), sudoku.getSquare(7, 3));
    assertEquals(Square.getSquare(3), sudoku.getSquare(9, 3));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareFour() {
    assertEquals(Square.getSquare(4), sudoku.getSquare(1, 4));
    assertEquals(Square.getSquare(4), sudoku.getSquare(3, 4));
    assertEquals(Square.getSquare(4), sudoku.getSquare(1, 6));
    assertEquals(Square.getSquare(4), sudoku.getSquare(3, 6));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareFive() {
    assertEquals(Square.getSquare(5), sudoku.getSquare(4, 4));
    assertEquals(Square.getSquare(5), sudoku.getSquare(6, 4));
    assertEquals(Square.getSquare(5), sudoku.getSquare(4, 6));
    assertEquals(Square.getSquare(5), sudoku.getSquare(6, 6));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareSix() {
    assertEquals(Square.getSquare(6), sudoku.getSquare(7, 4));
    assertEquals(Square.getSquare(6), sudoku.getSquare(9, 4));
    assertEquals(Square.getSquare(6), sudoku.getSquare(7, 6));
    assertEquals(Square.getSquare(6), sudoku.getSquare(9, 6));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareSeven() {
    assertEquals(Square.getSquare(7), sudoku.getSquare(1, 7));
    assertEquals(Square.getSquare(7), sudoku.getSquare(3, 7));
    assertEquals(Square.getSquare(7), sudoku.getSquare(1, 9));
    assertEquals(Square.getSquare(7), sudoku.getSquare(3, 9));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareEight() {
    assertEquals(Square.getSquare(8), sudoku.getSquare(4, 7));
    assertEquals(Square.getSquare(8), sudoku.getSquare(6, 7));
    assertEquals(Square.getSquare(8), sudoku.getSquare(4, 9));
    assertEquals(Square.getSquare(8), sudoku.getSquare(6, 9));
  }

  /**
   * Every field exactly belongs to one square
   */
  @Test
  public void determineSquareWithFieldSquareNine() {
    assertEquals(Square.getSquare(9), sudoku.getSquare(7, 7));
    assertEquals(Square.getSquare(9), sudoku.getSquare(9, 7));
    assertEquals(Square.getSquare(9), sudoku.getSquare(7, 9));
    assertEquals(Square.getSquare(9), sudoku.getSquare(9, 9));
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
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(8);
    tmpExpectedSet.add(9);
    
    assertEquals(tmpExpectedSet, sudoku.getRowNumbers(1));
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
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getRowNumbers(1));
  }

  /**
   * All numbers of a row should be investigable
   */
  @Test
  public void showNumbersOfEmptyRow() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    assertEquals(tmpExpectedSet, sudoku.getRowNumbers(1));
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
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(8);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getColumnNumbers(1));
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
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(9);
    assertEquals(tmpExpectedSet, sudoku.getColumnNumbers(1));
  }

  /**
   * All numbers of a column should be investigable
   */
  @Test
  public void showNumbersOfEmptyColumn() {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    assertEquals(tmpExpectedSet, sudoku.getColumnNumbers(1));
  }

  /**
   * All numbers of a square should be investigable
   * 
   * @throws SetException
   */
  @Test
  public void showNumbersOfFullSquareFive() throws SetException {
    sudoku.set(1, 4, 4);
    sudoku.set(2, 5, 4);
    sudoku.set(3, 6, 4);
    sudoku.set(4, 4, 5);
    sudoku.set(5, 5, 5);
    sudoku.set(6, 6, 5);
    sudoku.set(7, 4, 6);
    sudoku.set(8, 5, 6);
    sudoku.set(9, 6, 6);
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(2);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(5);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(8);
    tmpExpectedSet.add(9);
    Square tmpSquare = Square.getSquare(5);
    assertEquals(tmpExpectedSet, sudoku.getSquareNumbers(tmpSquare));
  }

  /**
   * All numbers of a square should be investigable
   * 
   * @throws SetException
   */
  @Test
  public void showNumbersOfHalfFilledSquareFive() throws SetException {
    sudoku.set(1, 4, 4);
    sudoku.set(3, 6, 4);
    sudoku.set(4, 4, 5);
    sudoku.set(6, 6, 5);
    sudoku.set(7, 4, 6);
    sudoku.set(9, 6, 6);
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(1);
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(4);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(7);
    tmpExpectedSet.add(9);
    Square tmpSquare = Square.getSquare(5);
    assertEquals(tmpExpectedSet, sudoku.getSquareNumbers(tmpSquare));
  }

  /**
   * All numbers of a square should be investigable
   * 
   * @throws SetException
   */
  @Test
  public void showNumbersOfEmptySquareFive() throws SetException {
    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    Square tmpSquare = Square.getSquare(5);
    assertEquals(tmpExpectedSet, sudoku.getSquareNumbers(tmpSquare));
  }

  /**
   * All numbers of a square should be investigable
   * 
   * @throws SetException
   */
  @Test
  public void showNumbersOfEmptySquareFiveInFullSudoku() throws SetException {
    sudoku.set(1, 1, 1);
    sudoku.set(2, 2, 1);
    sudoku.set(3, 3, 1);
    sudoku.set(4, 4, 1);
    sudoku.set(5, 5, 1);
    sudoku.set(6, 6, 1);
    sudoku.set(7, 7, 1);
    sudoku.set(8, 8, 1);
    sudoku.set(9, 9, 1);

    sudoku.set(4, 1, 2);
    sudoku.set(5, 2, 2);
    sudoku.set(6, 3, 2);
    sudoku.set(7, 4, 2);
    sudoku.set(8, 5, 2);
    sudoku.set(9, 6, 2);
    sudoku.set(1, 7, 2);
    sudoku.set(2, 8, 2);
    sudoku.set(3, 9, 2);

    sudoku.set(7, 1, 3);
    sudoku.set(8, 2, 3);
    sudoku.set(9, 3, 3);
    sudoku.set(1, 4, 3);
    sudoku.set(2, 5, 3);
    sudoku.set(3, 6, 3);
    sudoku.set(4, 7, 3);
    sudoku.set(5, 8, 3);
    sudoku.set(6, 9, 3);

    sudoku.set(2, 1, 4);
    sudoku.set(3, 2, 4);
    sudoku.set(4, 3, 4);
    sudoku.set(8, 7, 4);
    sudoku.set(9, 8, 4);
    sudoku.set(1, 9, 4);

    sudoku.set(5, 1, 5);
    sudoku.set(6, 2, 5);
    sudoku.set(7, 3, 5);
    sudoku.set(2, 7, 5);
    sudoku.set(3, 8, 5);
    sudoku.set(4, 9, 5);

    sudoku.set(8, 1, 6);
    sudoku.set(9, 2, 6);
    sudoku.set(1, 3, 6);
    sudoku.set(5, 7, 6);
    sudoku.set(6, 8, 6);
    sudoku.set(7, 9, 6);

    sudoku.set(3, 1, 7);
    sudoku.set(4, 2, 7);
    sudoku.set(5, 3, 7);
    sudoku.set(6, 4, 7);
    sudoku.set(7, 5, 7);
    sudoku.set(8, 6, 7);
    sudoku.set(9, 7, 7);
    sudoku.set(1, 8, 7);
    sudoku.set(2, 9, 7);

    sudoku.set(6, 1, 8);
    sudoku.set(7, 2, 8);
    sudoku.set(8, 3, 8);
    sudoku.set(9, 4, 8);
    sudoku.set(1, 5, 8);
    sudoku.set(2, 6, 8);
    sudoku.set(3, 7, 8);
    sudoku.set(4, 8, 8);
    sudoku.set(5, 9, 8);

    sudoku.set(9, 1, 9);
    sudoku.set(1, 2, 9);
    sudoku.set(2, 3, 9);
    sudoku.set(3, 4, 9);
    sudoku.set(4, 5, 9);
    sudoku.set(5, 6, 9);
    sudoku.set(6, 7, 9);
    sudoku.set(7, 8, 9);
    sudoku.set(8, 9, 9);

    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    Square tmpSquare = Square.getSquare(5);
    assertEquals(tmpExpectedSet, sudoku.getSquareNumbers(tmpSquare));
  }

  /**
   * All numbers of a square should be investigable
   * 
   * @throws SetException
   */
  @Test
  public void showNumbersOfSemiFilledSquareFiveInSemiFilledSudoku() throws SetException {
    // square five
    sudoku.set(3, 4, 4);
    sudoku.set(6, 5, 5);
    sudoku.set(9, 6, 6);

    // other values
    sudoku.set(1, 2, 2);
    sudoku.set(2, 5, 2);
    sudoku.set(4, 8, 2);
    sudoku.set(5, 2, 5);
    sudoku.set(7, 8, 5);
    sudoku.set(8, 2, 8);
    sudoku.set(9, 5, 8);
    sudoku.set(1, 8, 8);

    Set<Integer> tmpExpectedSet = new HashSet<Integer>();
    tmpExpectedSet.add(3);
    tmpExpectedSet.add(6);
    tmpExpectedSet.add(9);
    Square tmpSquare = Square.getSquare(5);
    assertEquals(tmpExpectedSet, sudoku.getSquareNumbers(tmpSquare));
  }

  /**
   * Do not allow two times the same value in one column
   * 
   * @throws SetException
   */
  @Test(expected = SetException.class)
  public void avoidWrongCalculatedValueSetsRegardingColumn() throws SetException {
    sudoku.set(1, 5, 1);
    sudoku.set(1, 5, 9);
  }

  /**
   * Do not allow two times the same value in one row
   * 
   * @throws SetException
   */
  @Test(expected = SetException.class)
  public void avoidWrongCalculatedValueSetsRegardingRow() throws SetException {
    sudoku.set(1, 1, 5);
    sudoku.set(1, 9, 5);
  }

  /**
   * Do not allow two times the same value in one square
   * 
   * @throws SetException
   */
  @Test(expected = SetException.class)
  public void avoidWrongCalculatedValueSetsRegardingSquare() throws SetException {
    sudoku.set(1, 4, 4);
    sudoku.set(1, 6, 6);
  }
}
