package sudoku;

import java.util.ArrayList;

import exceptions.NotPossibleException;

/**
 *
 * @authors Timbo & Tobe
 *
 */
public class Sudoku {

  private int[][] aSudoku;

  /**
   * Standard constructor
   */
  public Sudoku() {
    aSudoku = new int[9][9];
  }

  private ArrayList<Integer> getAllNumbers() {
    ArrayList<Integer> tmpNumbers = new ArrayList<Integer>();
    tmpNumbers.add(1);
    tmpNumbers.add(2);
    tmpNumbers.add(3);
    tmpNumbers.add(4);
    tmpNumbers.add(5);
    tmpNumbers.add(6);
    tmpNumbers.add(7);
    tmpNumbers.add(8);
    tmpNumbers.add(9);
    return tmpNumbers;
  }

  private int[] makeIntegerArrayListAnArray(ArrayList<Integer> aList) {
    int[] tmpArray = new int[aList.size()];
    for (int i = 0; i < aList.size(); i++) {
      tmpArray[i] = aList.get(i);
    }
    return tmpArray;
  }

  /**
   * Sets the given value on the field with the coordinates x and y
   *
   * @param anX
   * @param aY
   * @param aValue
   */
  public void setValue(int aValue, int anX, int aY) {
    aSudoku[aY - 1][anX - 1] = aValue;
  }

  /**
   * @param anX
   * @param aY
   * @return Returns the value of the field with the coordinates x and y
   */
  public int getValue(int anX, int aY) {
    return aSudoku[aY - 1][anX - 1];
  }

  /**
   * @param aSquareNo
   *            (from 1 - upper left - to 9 - lower right)
   * @return Returns an array with all missing numbers
   */
  public int[] getMissingNumbersInSquare(int aSquareNo) {
    ArrayList<Integer> tmpNumbers = getAllNumbers();

    int tmpX = 0;
    switch (aSquareNo) {
      case 1:
      case 4:
      case 7:
        tmpX = 3;
        break;
      case 2:
      case 5:
      case 8:
        tmpX = 6;
        break;
      case 3:
      case 6:
      case 9:
        tmpX = 9;
    }
    int tmpY = 0;
    switch (aSquareNo) {
      case 1:
      case 2:
      case 3:
        tmpY = 3;
        break;
      case 4:
      case 5:
      case 6:
        tmpY = 6;
        break;
      case 7:
      case 8:
      case 9:
        tmpY = 9;
    }

    for (int y = 1; y <= tmpY; y++) {
      for (int x = 1; x <= tmpX; x++) {
        tmpNumbers.remove((Object) getValue(x, y));
      }
    }

    return makeIntegerArrayListAnArray(tmpNumbers);
  }

  /**
   * @param aRowNo
   *            (from 1 - upper - to 9 - lower)
   * @return An array with all missing numbers
   */
  public int[] getMissingNumbersInRow(int aRowNo) {
    ArrayList<Integer> tmpNumbers = getAllNumbers();

    for (int x = 1; x <= 9; x++) {
      tmpNumbers.remove((Object) getValue(x, aRowNo));
    }

    return makeIntegerArrayListAnArray(tmpNumbers);
  }

  /**
   * @param aColumnNo
   *            (from 1 - left - to 9 - right)
   * @return An array with all missing numbers
   */
  public int[] getMissingNumbersInColumn(int aColumnNo) {
    ArrayList<Integer> tmpNumbers = getAllNumbers();

    for (int y = 1; y <= 9; y++) {
      tmpNumbers.remove((Object) getValue(aColumnNo, y));
    }

    return makeIntegerArrayListAnArray(tmpNumbers);
  }

  /**
   * @param aRowNo
   * @param aSquareNo
   * @return Returns all missing numbers in both, row and square
   */
  public int[] getMissingNumbersInRowAndSquare(int aRowNo, int aSquareNo) {
    int[] tmpRowNumbers = getMissingNumbersInRow(aRowNo);
    int[] tmpSquareNumbers = getMissingNumbersInSquare(aSquareNo);

    ArrayList<Integer> tmpNumbers = new ArrayList<Integer>();
    for (int i = 0; i < tmpRowNumbers.length; i++) {
      for (int j = 0; j < tmpSquareNumbers.length; j++) {
        if (tmpRowNumbers[i] == tmpSquareNumbers[j]) {
          tmpNumbers.add(tmpRowNumbers[i]);
        }
      }
    }

    return makeIntegerArrayListAnArray(tmpNumbers);
  }

  /**
   * @param aColumnNo
   * @param aSquareNo
   * @return
   */
  public int[] getMissingNumbersInColumnAndSquare(int aColumnNo, int aSquareNo) {
    int[] tmpColumnNumbers = getMissingNumbersInColumn(aColumnNo);
    int[] tmpSquareNumbers = getMissingNumbersInSquare(aSquareNo);

    ArrayList<Integer> tmpNumbers = new ArrayList<Integer>();
    for (int i = 0; i < tmpColumnNumbers.length; i++) {
      for (int j = 0; j < tmpSquareNumbers.length; j++) {
        if (tmpColumnNumbers[i] == tmpSquareNumbers[j]) {
          tmpNumbers.add(tmpColumnNumbers[i]);
        }
      }
    }

    return makeIntegerArrayListAnArray(tmpNumbers);
  }

  /**
   * @param anX
   * @param aY
   * @return Returns the missing number if possible
   * @throws NotPossibleException if calculation not possible yet
   */
  public int getMissingNumberOfField(int anX, int aY) throws NotPossibleException {
    int[] tmpColumnNumbers = getMissingNumbersInColumn(anX);
    int[] tmpRowNumbers = getMissingNumbersInRow(aY);

    if (tmpColumnNumbers.length == tmpRowNumbers.length && tmpColumnNumbers.length == 1) {
      int tmpMissingNumber = tmpColumnNumbers[0] = tmpRowNumbers[0];
      setValue(tmpMissingNumber, anX, aY);
      return tmpMissingNumber;
    }
    throw new NotPossibleException("Calculation of missing number for that field is not possible yet.");
  }
}