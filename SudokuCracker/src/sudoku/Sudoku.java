package sudoku;

import java.util.ArrayList;

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
  public int getField(int anX, int aY) {
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
        tmpNumbers.remove((Object) getField(x, y));
      }
    }

    int[] tmpNumberArray = new int[tmpNumbers.size()];
    for (int i = 0; i < tmpNumbers.size(); i++) {
      tmpNumberArray[i] = tmpNumbers.get(i);
    }
    return tmpNumberArray;
  }

  /**
   * @param aRowNo
   *            (from 1 - upper - to 9 - lower)
   * @return An array with all missing numbers
   */
  public int[] getMissingNumbersInRow(int aRowNo) {
    ArrayList<Integer> tmpNumbers = getAllNumbers();

    for (int x = 1; x <= 9; x++) {
      tmpNumbers.remove((Object) getField(x, aRowNo));
    }

    int[] tmpNumberArray = new int[tmpNumbers.size()];
    for (int i = 0; i < tmpNumbers.size(); i++) {
      tmpNumberArray[i] = tmpNumbers.get(i);
    }
    return tmpNumberArray;
  }

  /**
   * @param aColumnNo (from 1 - left - to 9 - right)
   * @return An array with all missing numbers
   */
  public int[] getMissingNumbersInColumn(int aColumnNo) {
    ArrayList<Integer> tmpNumbers = getAllNumbers();

    for (int y = 1; y <= 9; y++) {
      tmpNumbers.remove((Object) getField(aColumnNo, y));
    }

    int[] tmpNumberArray = new int[tmpNumbers.size()];
    for (int i = 0; i < tmpNumbers.size(); i++) {
      tmpNumberArray[i] = tmpNumbers.get(i);
    }
    return tmpNumberArray;
  }

}