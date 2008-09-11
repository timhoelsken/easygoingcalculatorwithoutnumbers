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

  private boolean checkIfValueIsInArray(int aValue, int[] anArray) {
    for (int i = 0; i < anArray.length; i++) {
      if (anArray[i] == aValue) {
        return true;
      }
    }
    return false;
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
    if (!checkIfValueIsInArray(aValue, tmpRowNumbers)) {
      throw new InternalException("Value set not allowed. Value already in row.");
    }
    int[] tmpColumnNumbers = getMissingNumbersInColumn(anX);
    if (!checkIfValueIsInArray(aValue, tmpColumnNumbers)) {
      throw new InternalException("Value set not allowed. Value already in column.");
    }

    int tmpSquareNo = getSquareNumber(anX, aY);
    int[] tmpSquareNumbers = getMissingNumbersInSquare(tmpSquareNo);
    if (!checkIfValueIsInArray(aValue, tmpSquareNumbers)) {
      throw new InternalException("Value set not allowed. Value already in square.");
    }

    aSudoku[aY - 1][anX - 1] = aValue;
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   */
  private int getSquareNumber(int anX, int aY) {
    int tmpSquareNo = 0;
    switch (anX) {
      case 1:
      case 2:
      case 3:
        switch (aY) {
          case 1:
          case 2:
          case 3:
            tmpSquareNo = 1;
            break;
          case 4:
          case 5:
          case 6:
            tmpSquareNo = 4;
            break;
          case 7:
          case 8:
          case 9:
            tmpSquareNo = 7;
        }
        break;
      case 4:
      case 5:
      case 6:
        switch (aY) {
          case 1:
          case 2:
          case 3:
            tmpSquareNo = 2;
            break;
          case 4:
          case 5:
          case 6:
            tmpSquareNo = 5;
            break;
          case 7:
          case 8:
          case 9:
            tmpSquareNo = 8;
        }
        break;
      case 7:
      case 8:
      case 9:
        switch (aY) {
          case 1:
          case 2:
          case 3:
            tmpSquareNo = 3;
            break;
          case 4:
          case 5:
          case 6:
            tmpSquareNo = 6;
            break;
          case 7:
          case 8:
          case 9:
            tmpSquareNo = 9;
        }
    }

    return tmpSquareNo;
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
    tmpOtherRows.add(locateNumberInSquare(aSquareNo + tmpSquareNeighbourOne, aNumber)[1]);

    // look for the row in the second neighbour containing the number
    tmpOtherRows.add(locateNumberInSquare(aSquareNo + tmpSquareNeighbourTwo, aNumber)[1]);

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
    tmpOtherColumns.add(locateNumberInSquare(aSquareNo + tmpSquareNeighbourOne, aNumber)[0]);

    // look for the column in the second neighbour containing the number
    tmpOtherColumns.add(locateNumberInSquare(aSquareNo + tmpSquareNeighbourTwo, aNumber)[0]);

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
  private int[] locateNumberInSquare(int aSquareNo, int aNumber) throws NotPossibleException {
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

  /**
   * 
   * @return
   */
  public int[] solve() {

    boolean hasNumberFound = false;

    for (int i = 0; i < aSudoku.length; i++) {
      for (int j = 0; j < aSudoku[i].length; j++) {

        try {
          // +++ Look if there is only one number in the row missing+++
          hasNumberFound = setValueForFieldInRow(j + 1, i + 1);

          // +++ Look if there is only one number in the row missing+++
          hasNumberFound = setValueForFieldInColumn(j + 1, i + 1);

          // +++ Look if there is only one number in the square missing+++
          hasNumberFound = setValueForFieldInSquare(j + 1, i + 1);

          // +++ Look if there is a row entry combination for a number and it is
          // definite
          hasNumberFound = setValueForSingleFieldInRowCombination(j + 1, i + 1);

          // +++ +++ Look if there is a row entry combination for a number and
          // there are two possibilities
          hasNumberFound = setValueForDoubleFieldInRowCombination(j + 1, i + 1);

          // +++ Look if there is a row entry combination for a number and it is
          // definite
          hasNumberFound = setValueForSingleFieldInColumnCombination(j + 1, i + 1);

          // +++ +++ Look if there is a row entry combination for a number and
          // there are two possibilities
          hasNumberFound = setValueForDoubleFieldInColumnCombination(j + 1, i + 1);

        } catch (InternalException e) {

        }

        // repeat solving the sudoku until no value could be set!
        if (hasNumberFound && (i == 8 && j == 8)) {
          hasNumberFound = false;
          i = 1;
          j = 1;
        }
      } // j loop
    } // i loop

    return getAllFields();
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   */
  public boolean setValueForDoubleFieldInColumnCombination(int anX, int aY) {
    int[] tmpMissingNumbers;
    if (getValue(anX, aY) == 0) {
      int tmpSearchedSquare = getSquareNumber(anX, aY);
      tmpMissingNumbers = getMissingNumbersInSquare(tmpSearchedSquare);

      // check each missing number
      for (int k = 0; k < tmpMissingNumbers.length; k++) {
        try {
          getColumnOfMissingNumber(tmpSearchedSquare, tmpMissingNumbers[k]);
          // the Y coordinates of the square
          int[] tmpCoordinatesOfSquare = getSquareCoordinates(tmpSearchedSquare);

          int tmpYOfRowOne, tmpYOfRowTwo;

          if (aY - tmpCoordinatesOfSquare[1] == 0) {
            tmpYOfRowOne = aY + 1;
            tmpYOfRowTwo = aY + 2;
          } else if (anX - tmpCoordinatesOfSquare[0] == 1) {
            tmpYOfRowOne = aY - 1;
            tmpYOfRowTwo = aY + 1;
          } else {
            tmpYOfRowOne = aY - 1;
            tmpYOfRowTwo = aY - 2;
          }

          if (getValue(anX, tmpYOfRowOne) == 0 && getValue(anX, tmpYOfRowTwo) == 0) {
            return false;
          } else if (getValue(anX, tmpYOfRowOne) == 0) {
            if (isNumberInRow(tmpMissingNumbers[k], tmpYOfRowOne)) {
              try {
                setValue(tmpMissingNumbers[k], anX, aY);
                return true;
              } catch (InternalException e) {
              }
            } else if (isNumberInRow(tmpMissingNumbers[k], aY)) {
              try {
                setValue(tmpMissingNumbers[k], anX, tmpYOfRowOne);
                return true;
              } catch (InternalException e) {
              }
            }
          } else if (getValue(anX, tmpYOfRowTwo) == 0) {
            if (isNumberInRow(tmpMissingNumbers[k], tmpYOfRowTwo)) {
              try {
                setValue(tmpMissingNumbers[k], anX, aY);
                return true;
              } catch (InternalException e) {
              }
            } else if (isNumberInRow(tmpMissingNumbers[k], aY)) {
              try {
                setValue(tmpMissingNumbers[k], anX, tmpYOfRowTwo);
                return true;
              } catch (InternalException e) {
              }
            }
          }
        } catch (NotPossibleException e) {
          // do nothing
        }
      }
    }
    return false;
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   */
  public boolean setValueForSingleFieldInColumnCombination(int anX, int aY) {
    int[] tmpMissingNumbers;
    if (getValue(anX, aY) == 0) {
      int tmpSearchedSquare = getSquareNumber(anX, aY);
      tmpMissingNumbers = getMissingNumbersInSquare(tmpSearchedSquare);

      // check each missing number
      for (int k = 0; k < tmpMissingNumbers.length; k++) {

        try {
          getColumnOfMissingNumber(tmpSearchedSquare, tmpMissingNumbers[k]);
        } catch (NotPossibleException e) {
        }

        // get the Y coordinates of the square
        int[] tmpCoordinatesOfSquare = getSquareCoordinates(tmpSearchedSquare);

        // IDs to identify the Row-Situation
        int tmpEmptyFieldId = 3; // 0, 1, 2 used for single empty fields
        // from left to right in the square, 3 is empty

        // first field, empty? ==> ID=0
        if (getValue(anX, tmpCoordinatesOfSquare[1]) == 0) {
          tmpEmptyFieldId = 0;
        }

        if (getValue(anX, tmpCoordinatesOfSquare[1] + 1) == 0) {
          // first field is not empty? ==> ID=1
          if (tmpEmptyFieldId == 3) {
            tmpEmptyFieldId = 1;
          }
          // first field is already empty? ==> ID=3, two empty fields
          else {
            tmpEmptyFieldId = 3;
          }
        }
        if (getValue(anX, tmpCoordinatesOfSquare[2]) == 0) {

          // none of the fields before is empty? ==> ID=2
          if (tmpEmptyFieldId == 3) {
            tmpEmptyFieldId = 2;
          }
          // one of the fields before is empty
          else {
            tmpEmptyFieldId = 3;
          }
        }

        // if theres only one empty field, the missing number can be set
        // there! Wohey! :)
        if (tmpEmptyFieldId != 3) {
          try {

            if (tmpEmptyFieldId == 0 && tmpCoordinatesOfSquare[1] == aY) {
              setValue(tmpMissingNumbers[k], anX, aY);
              return true;
            } else if (tmpEmptyFieldId == 1 && tmpCoordinatesOfSquare[1] + 1 == aY) {
              setValue(tmpMissingNumbers[k], anX, aY);
              return true;
            } else {
              setValue(tmpMissingNumbers[k], anX, aY);
              return true;
            }
          } catch (InternalException ie) {

          }
        }
      }
    }
    return false;
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   */
  public boolean setValueForDoubleFieldInRowCombination(int anX, int aY) {
    int[] tmpMissingNumbers;
    if (getValue(anX, aY) == 0) {
      int tmpSearchedSquare = getSquareNumber(anX, aY);
      tmpMissingNumbers = getMissingNumbersInSquare(tmpSearchedSquare);

      // check each missing number
      for (int k = 0; k < tmpMissingNumbers.length; k++) {
        try {
          getRowOfMissingNumber(tmpSearchedSquare, tmpMissingNumbers[k]);
          // the X coordinates of the square
          int[] tmpCoordinatesOfSquare = getSquareCoordinates(tmpSearchedSquare);

          int tmpXOfColumnOne, tmpXOfColumnTwo;

          if (anX - tmpCoordinatesOfSquare[0] == 0) {
            tmpXOfColumnOne = anX + 1;
            tmpXOfColumnTwo = anX + 2;
          } else if (anX - tmpCoordinatesOfSquare[0] == 1) {
            tmpXOfColumnOne = anX - 1;
            tmpXOfColumnTwo = anX + 1;
          } else {
            tmpXOfColumnOne = anX - 1;
            tmpXOfColumnTwo = anX - 2;
          }

          if (getValue(tmpXOfColumnOne, aY) == 0 && getValue(tmpXOfColumnTwo, aY) == 0) {
            return false;
          } else if (getValue(tmpXOfColumnOne, aY) == 0) {
            if (isNumberInColumn(tmpMissingNumbers[k], tmpXOfColumnOne)) {
              try {
                setValue(tmpMissingNumbers[k], anX, aY);
                return true;
              } catch (InternalException e) {
              }
            } else if (isNumberInColumn(tmpMissingNumbers[k], anX)) {
              try {
                setValue(tmpMissingNumbers[k], tmpXOfColumnOne, aY);
                return true;
              } catch (InternalException e) {
              }
            }
          } else if (getValue(tmpXOfColumnTwo, aY) == 0) {
            if (isNumberInColumn(tmpMissingNumbers[k], tmpXOfColumnTwo)) {
              try {
                setValue(tmpMissingNumbers[k], anX, aY);
                return true;
              } catch (InternalException e) {
              }
            } else if (isNumberInColumn(tmpMissingNumbers[k], anX)) {
              try {
                setValue(tmpMissingNumbers[k], tmpXOfColumnTwo, aY);
                return true;
              } catch (InternalException e) {
              }
            }
          }
        } catch (NotPossibleException e) {
          // do nothing
        }
      }
    }
    return false;
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   */
  public boolean setValueForSingleFieldInRowCombination(int anX, int aY) {
    int[] tmpMissingNumbers;
    if (getValue(anX, aY) == 0) {
      int tmpSearchedSquare = getSquareNumber(anX, aY);
      tmpMissingNumbers = getMissingNumbersInSquare(tmpSearchedSquare);

      // check each missing number
      for (int k = 0; k < tmpMissingNumbers.length; k++) {

        try {
          getRowOfMissingNumber(tmpSearchedSquare, tmpMissingNumbers[k]);
        } catch (NotPossibleException e) {
        }

        // get the X coordinates of the square
        int[] tmpCoordinatesOfSquare = getSquareCoordinates(tmpSearchedSquare);

        // IDs to identify the Row-Situation
        int tmpEmptyFieldId = 3; // 0, 1, 2 used for single empty fields
        // from left to right in the square, 3 is empty

        // first field, empty? ==> ID=0
        if (getValue(tmpCoordinatesOfSquare[0], aY) == 0) {
          tmpEmptyFieldId = 0;
        }

        if (getValue(tmpCoordinatesOfSquare[0] + 1, aY) == 0) {
          // first field is not empty? ==> ID=1
          if (tmpEmptyFieldId == 3) {
            tmpEmptyFieldId = 1;
          }
          // first field is already empty? ==> ID=3, two empty fields
          else {
            tmpEmptyFieldId = 3;
          }
        }
        if (getValue(tmpCoordinatesOfSquare[2], aY) == 0) {

          // none of the fields before is empty? ==> ID=2
          if (tmpEmptyFieldId == 3) {
            tmpEmptyFieldId = 2;
          }
          // one of the fields before is empty
          else {
            tmpEmptyFieldId = 3;
          }
        }

        // if theres only one empty field, the missing number can be set
        // there! Wohey! :)
        if (tmpEmptyFieldId != 3) {
          try {

            if (tmpEmptyFieldId == 0 && tmpCoordinatesOfSquare[0] == anX) {
              setValue(tmpMissingNumbers[k], anX, aY);
              return true;
            } else if (tmpEmptyFieldId == 1 && tmpCoordinatesOfSquare[0] + 1 == anX) {
              setValue(tmpMissingNumbers[k], anX, aY);
              return true;
            } else {
              setValue(tmpMissingNumbers[k], anX, aY);
              return true;
            }
          } catch (InternalException ie) {

          }
        }
      }
    }
    return false;
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   * @throws InternalException
   */
  public boolean setValueForFieldInSquare(int anX, int aY) throws InternalException {
    int[] tmpMissingNumbers;
    if (getValue(anX, aY) == 0) {

      int tmpSquareNo = getSquareNumber(anX, aY);
      tmpMissingNumbers = getMissingNumbersInSquare(tmpSquareNo);

      if (tmpMissingNumbers.length == 1) {
        setValue(tmpMissingNumbers[0], anX, aY);
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   * @throws InternalException
   */
  public boolean setValueForFieldInColumn(int anX, int aY) throws InternalException {
    int[] tmpMissingNumbers;
    if (getValue(anX, aY) == 0) {

      tmpMissingNumbers = getMissingNumbersInColumn(anX);
      if (tmpMissingNumbers.length == 1) {
        setValue(tmpMissingNumbers[0], anX, aY);
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @param anX
   * @param aY
   * @return
   * @throws InternalException
   */
  public boolean setValueForFieldInRow(int anX, int aY) throws InternalException {
    int[] tmpMissingNumbers;
    if (getValue(anX, aY) == 0) {

      tmpMissingNumbers = getMissingNumbersInRow(aY);
      if (tmpMissingNumbers.length == 1) {
        setValue(tmpMissingNumbers[0], anX, aY);
        return true;
      }
    }
    return false;
  }

  private int[] getAllFields() {
    ArrayList<Integer> tmpAllFields = new ArrayList<Integer>();

    for (int i = 0; i < aSudoku.length; i++) {
      for (int j = 0; j < aSudoku[i].length; j++) {
        tmpAllFields.add(getValue(j + 1, i + 1));
        System.out.print(getValue(j + 1, i + 1));
        if ((j+1)%3==0)
        System.out.print(" ");
      }
      System.out.print("\n");
      if ((i+1)%3==0)
      System.out.print("\n");
    }
    return makeIntegerArrayListAnArray(tmpAllFields);
  }

  private boolean isNumberInRow(int aNumber, int aY) {

    int[] tmpMissingNumbers = getMissingNumbersInRow(aY);

    for (int i = 0; i < tmpMissingNumbers.length; i++) {
      if (tmpMissingNumbers[i] == aNumber)
        return false;
    }

    return true;
  }

  private boolean isNumberInColumn(int aNumber, int anX) {

    int[] tmpMissingNumbers = getMissingNumbersInColumn(anX);

    for (int i = 0; i < tmpMissingNumbers.length; i++) {
      if (tmpMissingNumbers[i] == aNumber)
        return false;
    }

    return true;
  }

}
