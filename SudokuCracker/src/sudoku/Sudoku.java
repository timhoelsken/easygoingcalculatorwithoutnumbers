/**
 *
 */
package sudoku;

import java.util.ArrayList;

import sudoku.exceptions.InternalException;
import sudoku.exceptions.SetException;

/**
 * The Sudoku Class :P
 *
 * @author Tobias
 */
public class Sudoku {

  /**
   * The dimension of the Sudoku (x*x)
   */
  public static final int DIMENSION = 9;

  int[][] value;

  /**
   * Standard constructor
   */
  public Sudoku() {
    value = new int[DIMENSION][DIMENSION];
  }

  /**
   * Sets aValue on the field (x|y)
   *
   * @param aValue
   * @param x
   * @param y
   * @throws SetException
   */
  public void set(int aValue, int x, int y) throws SetException {
    if (value[y - 1][x - 1] == 0 && 0 < aValue && aValue <= DIMENSION) {
      value[y - 1][x - 1] = aValue;
    } else {
      throw new SetException("Setting " + aValue + " on (" + x + "|" + y + ") not allowed.");
    }
  }

  /**
   * @param x
   * @param y
   * @return Returns value of field (x|y)
   */
  public int get(int x, int y) {
    return value[y - 1][x - 1];
  }

  /**
   * @param y
   * @return Returns the possible square numbers for the row with index y
   */
  public int[] getPossibleSquareNumbersForRow(int y) {
    if (y < 1 || y > DIMENSION) {
      throw new IllegalArgumentException("Row index " + y + " does not exist.");
    }
    if (y > 6) {
      return new int[] { 7, 8, 9 };
    } else if (y > 3) {
      return new int[] { 4, 5, 6 };
    }
    return new int[] { 1, 2, 3 };
  }

  /**
   * @param x
   * @return Returns the possible square numbers for the column with index x
   */
  public int[] getPossibleSquareNumbersForColumn(int x) {
    if (x < 1 || x > DIMENSION) {
      throw new IllegalArgumentException("Column index " + x + " does not exist.");
    }
    if (x > 6) {
      return new int[] { 3, 6, 9 };
    } else if (x > 3) {
      return new int[] { 2, 5, 8 };
    }
    return new int[] { 1, 4, 7 };
  }

  /**
   * @param x
   * @param y
   * @return Returns the square number belonging to the field (x|y)
   * @throws InternalException
   */
  public Square getSquare(int x, int y) throws InternalException {
    int[] tmpPossColumnSquares = getPossibleSquareNumbersForColumn(x);
    int[] tmpPossRowSquares = getPossibleSquareNumbersForRow(y);
    for (int i = 0; i < tmpPossColumnSquares.length; i++) {
      for (int j = 0; j < tmpPossRowSquares.length; j++) {
        if (tmpPossColumnSquares[i] == tmpPossRowSquares[j]) {
          int tmpSearchedSquare = tmpPossColumnSquares[i];
          return Square.getSquare(tmpSearchedSquare);
        }
      }
    }
    // should not happen
    throw new InternalException();
  }

  /**
   * @param y
   * @return All numbers in row y
   */
  public ArrayList<Integer> getRowNumbers(int y) {
    ArrayList<Integer> tmpRowNumbers = new ArrayList<Integer>();
    for (int i = 1; i <= DIMENSION; i++) {
      int tmpValue = get(i, y);
      if (tmpValue != 0) {
        tmpRowNumbers.add(tmpValue);
      }
    }
    return tmpRowNumbers;
  }

  /**
   * @param x
   * @return All numbers in column x
   */
  public ArrayList<Integer> getColumnNumbers(int x) {
    ArrayList<Integer> tmpColumnNumbers = new ArrayList<Integer>();
    for (int i = 1; i <= DIMENSION; i++) {
      int tmpValue = get(x, i);
      if (tmpValue != 0) {
        tmpColumnNumbers.add(tmpValue);
      }
    }
    return tmpColumnNumbers;
  }

  /**
   * @param aSquareNo
   * @return All numbers in square aSquareNo
   */
  public ArrayList<Integer> getSquareNumbers(int aSquareNo) {
    Square tmpSquare = Square.getSquare(aSquareNo);
    ArrayList<Integer> tmpSquareNumbers = new ArrayList<Integer>();
    for (int i = tmpSquare.getXUpLeft(); i <= tmpSquare.getXDownRight(); i++) {
      for (int j = tmpSquare.getYUpLeft(); j <= tmpSquare.getYDownRight(); j++) {
        int tmpValue = get(j, i);
        if (tmpValue != 0) {
          tmpSquareNumbers.add(tmpValue);
        }
      }
    }
    return tmpSquareNumbers;
  }
}