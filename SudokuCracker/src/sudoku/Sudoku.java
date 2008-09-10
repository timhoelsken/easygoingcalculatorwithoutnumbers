package sudoku;

import java.util.ArrayList;

import sudoku.exceptions.InternalException;
import sudoku.exceptions.NotPossibleException;

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
    // This sudoku builds a 9x9 game. Dynamic size building is not supported!
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
   * Sets the given value on the field with the coordinates x and y and checks
   * the correctness of the set action before
   *
   * @param anX
   * @param aY
   * @param aValue
   * @throws InternalException
   *             if set action is not allowed
   */
  public void setValue(int aValue, int anX, int aY) throws InternalException {
    int[] tmpRowNumbers = getMissingNumbersInRow(aY);
    boolean tmpChecked = false;
    for (int i = 0; i < tmpRowNumbers.length; i++) {
      if (tmpRowNumbers[i] == aValue) {
        tmpChecked = true;
        i = tmpRowNumbers.length;
      }
    }
    if (!tmpChecked) {
      throw new InternalException("Value set not allowed.");
    }
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

  private int[] getSquareCoordinates(int aSquareNo) {
    int tmpLowerRightX = 0;
    int tmpUpperLeftX = 0;
    int tmpLowerRightY = 0;
    int tmpUpperLeftY = 0;
    switch (aSquareNo) {
      case 1:
        tmpLowerRightX = 3;
        tmpUpperLeftX = 1;
        tmpLowerRightY = 3;
        tmpUpperLeftY = 1;
        break;
      case 2:
        tmpLowerRightX = 6;
        tmpUpperLeftX = 4;
        tmpLowerRightY = 3;
        tmpUpperLeftY = 1;
        break;
      case 3:
        tmpLowerRightX = 9;
        tmpUpperLeftX = 7;
        tmpLowerRightY = 3;
        tmpUpperLeftY = 1;
        break;
      case 4:
        tmpLowerRightX = 3;
        tmpUpperLeftX = 1;
        tmpLowerRightY = 6;
        tmpUpperLeftY = 4;
        break;
      case 5:
        tmpLowerRightX = 6;
        tmpUpperLeftX = 4;
        tmpLowerRightY = 6;
        tmpUpperLeftY = 4;
        break;
      case 6:
        tmpLowerRightX = 9;
        tmpUpperLeftX = 7;
        tmpLowerRightY = 6;
        tmpUpperLeftY = 4;
        break;
      case 7:
        tmpLowerRightX = 3;
        tmpUpperLeftX = 1;
        tmpLowerRightY = 9;
        tmpUpperLeftY = 7;
        break;
      case 8:
        tmpLowerRightX = 6;
        tmpUpperLeftX = 4;
        tmpLowerRightY = 9;
        tmpUpperLeftY = 7;
        break;
      case 9:
        tmpLowerRightX = 9;
        tmpUpperLeftX = 7;
        tmpLowerRightY = 9;
        tmpUpperLeftY = 7;
        break;
    }
    int[] tmpCoordinates = { tmpUpperLeftX, tmpUpperLeftY, tmpLowerRightX, tmpLowerRightY };
    return tmpCoordinates;
  }

  /**
   * Determs all missing numbers in the given square
   *
   * @param aSquareNo
   *            (from 1 - upper left - to 9 - lower right)
   * @return Returns an array with all missing numbers
   */
  public int[] getMissingNumbersInSquare(int aSquareNo) {
    ArrayList<Integer> tmpNumbers = getAllNumbers();

    int[] tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo);

    for (int y = tmpCoordinatesOfSquare[1]; y <= tmpCoordinatesOfSquare[3]; y++) {
      for (int x = tmpCoordinatesOfSquare[0]; x <= tmpCoordinatesOfSquare[2]; x++) {
        tmpNumbers.remove((Object) getValue(x, y));
      }
    }

    return makeIntegerArrayListAnArray(tmpNumbers);
  }

  /**
   * Determs all missing numbers in the given row
   *
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
   * Determs all missing numbers in the given column
   *
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
   * Determs all missing numbers in the given row and square
   *
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
   * Determs all missing numbers in the given column and square
   *
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
   * @throws NotPossibleException
   *             if calculation not possible yet
   * @throws InternalException
   */
  public int getMissingNumberOfField(int anX, int aY) throws NotPossibleException, InternalException {
    int[] tmpColumnNumbers = getMissingNumbersInColumn(anX);
    int[] tmpRowNumbers = getMissingNumbersInRow(aY);

    if (tmpColumnNumbers.length == tmpRowNumbers.length && tmpColumnNumbers.length == 1) {
      int tmpMissingNumber = tmpColumnNumbers[0] = tmpRowNumbers[0];
      setValue(tmpMissingNumber, anX, aY);
      return tmpMissingNumber;
    }
    throw new NotPossibleException("Calculation of missing number for that field is not possible yet.");
  }

  /**
   * Determs the row for the missing number in the given square
   *
   * @param aSquareNo
   * @param aNumber
   * @return int - the y coordinate of the row
   * @throws NotPossibleException
   */
  public int getRowOfMissingNumber(int aSquareNo, int aNumber) throws NotPossibleException {
    // rows of the searched square
    ArrayList<Integer> tmpSquareRows = new ArrayList<Integer>();
    // rows in the other two squares
    ArrayList<Integer> tmpOtherRows = new ArrayList<Integer>();

    int tmpSquareNeighbourOne = 0, tmpSquareNeighbourTwo = 0;
    // the other two squares are both on the right, both on the left or one left
    // and one right
    switch (aSquareNo) {
      case 1:
      case 4:
      case 7:
        tmpSquareNeighbourOne = 1;
        tmpSquareNeighbourTwo = 2;
        break;
      case 2:
      case 5:
      case 8:
        tmpSquareNeighbourOne = 1;
        tmpSquareNeighbourTwo = -1;
        break;
      case 3:
      case 6:
      case 9:
        tmpSquareNeighbourOne = -1;
        tmpSquareNeighbourTwo = -2;
        break;
    }

    // saving the rows of the searched square
    int[] tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo);
    tmpSquareRows.add(tmpCoordinatesOfSquare[1]);
    tmpSquareRows.add(tmpCoordinatesOfSquare[1] + 1);
    tmpSquareRows.add(tmpCoordinatesOfSquare[3]);

    // look for the row in the first neighbour containing the number
    tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo + tmpSquareNeighbourOne);
    for (int y = tmpCoordinatesOfSquare[1]; y <= tmpCoordinatesOfSquare[3]; y++) {
      for (int x = tmpCoordinatesOfSquare[0]; x <= tmpCoordinatesOfSquare[2]; x++) {
        if (aNumber == getValue(x, y)) {
          tmpOtherRows.add(y);
        }
      }
    }

    // look for the row in the second neighbour containing the number
    tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo + tmpSquareNeighbourTwo);
    for (int y = tmpCoordinatesOfSquare[1]; y <= tmpCoordinatesOfSquare[3]; y++) {
      for (int x = tmpCoordinatesOfSquare[0]; x <= tmpCoordinatesOfSquare[2]; x++) {
        if (aNumber == getValue(x, y)) {
          tmpOtherRows.add(y);
        }
      }
    }

    // if two numbers were found everythings alright, else it is not possible to
    // determ => exception
    if (tmpOtherRows.size() > 1) {
      // remove rows from the searched square
      tmpSquareRows.remove(tmpOtherRows.get(0));
      tmpSquareRows.remove(tmpOtherRows.get(1));
    } else {
      throw new NotPossibleException("Not possible to determ row for missing number");
    }

    // resize arraylist to the one left number
    tmpSquareRows.trimToSize();

    return tmpSquareRows.get(0);
  }

  /**
   * Determs the column for the missing number in the given square
   *
   * @param aSquareNo
   * @param aNumber
   * @return int - the x coordinate of the column
   * @throws NotPossibleException
   */
  public int getColumnOfMissingNumber(int aSquareNo, int aNumber) throws NotPossibleException {
    // rows of the searched square
    ArrayList<Integer> tmpSquareColumns = new ArrayList<Integer>();
    // rows in the other two squares
    ArrayList<Integer> tmpOtherColumns = new ArrayList<Integer>();
    int tmpSquareNeighbourOne = 0, tmpSquareNeighbourTwo = 0;
    // the other two squares are both on top, both beneath or one on top
    // and one beneath
    switch (aSquareNo) {
      case 1:
      case 2:
      case 3:
        tmpSquareNeighbourOne = 3;
        tmpSquareNeighbourTwo = 6;
        break;
      case 4:
      case 5:
      case 6:
        tmpSquareNeighbourOne = 3;
        tmpSquareNeighbourTwo = -3;
        break;
      case 7:
      case 8:
      case 9:
        tmpSquareNeighbourOne = -3;
        tmpSquareNeighbourTwo = -6;
        break;
    }

    // saving the columns of the searched square
    int[] tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo);
    tmpSquareColumns.add(tmpCoordinatesOfSquare[0]);
    tmpSquareColumns.add(tmpCoordinatesOfSquare[0] + 1);
    tmpSquareColumns.add(tmpCoordinatesOfSquare[2]);

    // look for the column in the first neighbour containing the number
    tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo + tmpSquareNeighbourOne);
    for (int y = tmpCoordinatesOfSquare[1]; y <= tmpCoordinatesOfSquare[3]; y++) {
      for (int x = tmpCoordinatesOfSquare[0]; x <= tmpCoordinatesOfSquare[2]; x++) {
        if (aNumber == getValue(x, y)) {
          tmpOtherColumns.add(x);
        }
      }
    }

    // look for the column in the second neighbour containing the number
    tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo + tmpSquareNeighbourTwo);
    for (int y = tmpCoordinatesOfSquare[1]; y <= tmpCoordinatesOfSquare[3]; y++) {
      for (int x = tmpCoordinatesOfSquare[0]; x <= tmpCoordinatesOfSquare[2]; x++) {
        if (aNumber == getValue(x, y)) {
          tmpOtherColumns.add(x);
        }
      }
    }

    // if two numbers were found everythings alright, else it is not possible to
    // determ => exception
    if (tmpOtherColumns.size() > 1) {
      // remove rows from the searched square
      tmpSquareColumns.remove(tmpOtherColumns.get(0));
      tmpSquareColumns.remove(tmpOtherColumns.get(1));
    } else {
      throw new NotPossibleException("Not possible to determ row for missing number");
    }
    // resize arraylist to the one left number
    tmpSquareColumns.trimToSize();

    return tmpSquareColumns.get(0);
  }

  /**
   * Locates a number inside a square if it is already set
   *
   * @param aSquareNo
   * @param aNumber
   * @return int[] - the x and y coordinates of the number
   * @throws NotPossibleException
   */
  public int[] locateNumberInSquare(int aSquareNo, int aNumber) throws NotPossibleException {
    int[] tmpCoordinatesOfNumber = { 0, 0 };

    int[] tmpCoordinatesOfSquare = getSquareCoordinates(aSquareNo);

    for (int y = tmpCoordinatesOfSquare[1]; y <= tmpCoordinatesOfSquare[3]; y++) {
      for (int x = tmpCoordinatesOfSquare[0]; x <= tmpCoordinatesOfSquare[2]; x++) {
        if (aNumber == getValue(x, y)) {
          tmpCoordinatesOfNumber[0] = x;
          tmpCoordinatesOfNumber[1] = y;
        }
      }
    }

    if (tmpCoordinatesOfNumber[0] == 0) {
      throw new NotPossibleException("Number cannot be found in square!");
    }
    return tmpCoordinatesOfNumber;
  }
}