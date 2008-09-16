/**
 *
 */
package sudoku;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import sudoku.exceptions.InternalException;
import sudoku.exceptions.SetException;
import sudoku.exceptions.SolveException;

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

  private Set<Integer> NUMBERS;

  private int[][] values;

  /**
   * Standard constructor
   */
  public Sudoku() {
    values = new int[DIMENSION][DIMENSION];
    NUMBERS = new HashSet<Integer>();

    for (int i = 1; i < DIMENSION + 1; i++) {
      NUMBERS.add(i);
    }
  }

  /**
   * The solve method :)
   * 
   * @return Returns the solved Sudoku
   * 
   * @throws SetException
   */
  public int[] solve() throws SetException {
    boolean doAgain = true;
    while (doAgain) {
      doAgain = false;
      for (int y = 1; y <= DIMENSION; y++) {
        for (int x = 1; x <= DIMENSION; x++) {
          int tmpValue = get(x, y);
          if (tmpValue == 0) {
            try {
              addLastMissingNumberInColumn(x, y);
              doAgain = true;
            } catch (SolveException e1) {
              try {
                addLastMissingNumberInRow(x, y);
                doAgain = true;
              } catch (SolveException e2) {
                try {
                  addLastMissingNumberInSquare(x, y);
                  doAgain = true;
                } catch (SolveException e3) {
                  try {
                    addLastMissingNumberInRowAndColumn(x, y);
                    doAgain = true;
                  } catch (SolveException e4) {
                    try {
                      addLastMissingNumberInRowAndSquare(x, y);
                      doAgain = true;
                    } catch (SolveException e5) {
                      try {
                        addLastMissingNumberInColumnAndSquare(x, y);
                        doAgain = true;
                      } catch (SolveException e6) {
                        try {
                          addMissingNumberWithColumnCombination(x, y);
                          doAgain = true;
                        } catch (SolveException e7) {
                          try {
                            addMissingNumberWithRowCombination(x, y);
                            doAgain = true;
                          } catch (SolveException e8) {
                            try {
                              addMissingNumberInSquareWithRowColumnCross(x, y);
                              doAgain = true;
                            } catch (SolveException e9) {
                              /**
                               * STARTS A LOOP... WHY EVER!!! TODO: Test? which
                               * one? =>solveRowWithMultipleMissingFieldsOne in
                               * AdvancedSolving represents the sudoku so far
                               * without this method!
                               */
                              // try {
                              // addMissingRowNumberInMultipleMissingFields(x,
                              // y);
                              // doAgain = true;
                              // } catch (SolveException e10) {
                              // }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    int[] tmpSolvedSudoku = new int[81];
    int tmpPosition = 0;
    for (int y = 1; y <= DIMENSION; y++) {
      for (int x = 1; x <= DIMENSION; x++) {
        tmpSolvedSudoku[tmpPosition++] = get(x, y);
      }
    }
    TestUtil.paint(this);
    return tmpSolvedSudoku;
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
    if (values[y - 1][x - 1] == 0 && 0 < aValue && aValue <= DIMENSION) {
      checkSet(aValue, x, y);
      values[y - 1][x - 1] = aValue;
    } else {
      throw new SetException("Setting " + aValue + " on (" + x + "|" + y + ") not allowed.");
    }
  }

  private void checkSet(int aValue, int x, int y) throws SetException {
    Set<Integer> tmpNumbers = getColumnNumbers(x);
    if (tmpNumbers.contains(aValue)) {
      throw new SetException("Number " + aValue + " already existent in column " + x);
    }
    tmpNumbers = getRowNumbers(y);
    if (tmpNumbers.contains(aValue)) {
      throw new SetException("Number " + aValue + " already existent in row " + y);
    }
    Square tmpSquare = getSquare(x, y);
    tmpNumbers = getSquareNumbers(tmpSquare);
    if (tmpNumbers.contains(aValue)) {
      throw new SetException("Number " + aValue + " already existent in square " + tmpSquare.getNumber());
    }
  }

  /**
   * @param x
   * @param y
   * @return Returns value of field (x|y)
   */
  public int get(int x, int y) {
    return values[y - 1][x - 1];
  }

  /**
   * @param y
   * @return Returns the possible square numbers for the row with index y
   */
  public Set<Integer> getPossibleSquareNumbersForRow(int y) {
    if (y < 1 || y > DIMENSION) {
      throw new IllegalArgumentException("Row index " + y + " does not exist.");
    }
    Set<Integer> tmpPossibleSquareNumbers = new HashSet<Integer>();
    if (y > 6) {
      tmpPossibleSquareNumbers.add(7);
      tmpPossibleSquareNumbers.add(8);
      tmpPossibleSquareNumbers.add(9);
      return tmpPossibleSquareNumbers;
    } else if (y > 3) {
      tmpPossibleSquareNumbers.add(4);
      tmpPossibleSquareNumbers.add(5);
      tmpPossibleSquareNumbers.add(6);
      return tmpPossibleSquareNumbers;
    }
    tmpPossibleSquareNumbers.add(1);
    tmpPossibleSquareNumbers.add(2);
    tmpPossibleSquareNumbers.add(3);
    return tmpPossibleSquareNumbers;
  }

  /**
   * @param x
   * @return Returns the possible square numbers for the column with index x
   */
  public Set<Integer> getPossibleSquareNumbersForColumn(int x) {
    if (x < 1 || x > DIMENSION) {
      throw new IllegalArgumentException("Column index " + x + " does not exist.");
    }
    Set<Integer> tmpPossibleSquareNumbers = new HashSet<Integer>();
    if (x > 6) {
      tmpPossibleSquareNumbers.add(3);
      tmpPossibleSquareNumbers.add(6);
      tmpPossibleSquareNumbers.add(9);
      return tmpPossibleSquareNumbers;
    } else if (x > 3) {
      tmpPossibleSquareNumbers.add(2);
      tmpPossibleSquareNumbers.add(5);
      tmpPossibleSquareNumbers.add(8);
      return tmpPossibleSquareNumbers;
    }
    tmpPossibleSquareNumbers.add(1);
    tmpPossibleSquareNumbers.add(4);
    tmpPossibleSquareNumbers.add(7);
    return tmpPossibleSquareNumbers;
  }

  /**
   * @param x
   * @param y
   * @return Returns the square number belonging to the field (x|y)
   * @throws InternalException
   */
  public Square getSquare(int x, int y) throws InternalException {
    Set<Integer> tmpPossColumnSquares = getPossibleSquareNumbersForColumn(x);
    Set<Integer> tmpPossRowSquares = getPossibleSquareNumbersForRow(y);

    for (Iterator<Integer> tmpIterator = tmpPossColumnSquares.iterator(); tmpIterator.hasNext();) {
      Integer tmpElement = tmpIterator.next();
      if (tmpPossRowSquares.contains(tmpElement)) {
        return Square.getSquare(tmpElement);
      }
    }

    // should not happen
    throw new InternalException();
  }

  /**
   * @param y
   * @return All numbers in row y
   */
  public Set<Integer> getRowNumbers(int y) {
    Set<Integer> tmpRowNumbers = new HashSet<Integer>();
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
  public Set<Integer> getColumnNumbers(int x) {
    Set<Integer> tmpColumnNumbers = new HashSet<Integer>();
    for (int i = 1; i <= DIMENSION; i++) {
      int tmpValue = get(x, i);
      if (tmpValue != 0) {
        tmpColumnNumbers.add(tmpValue);
      }
    }
    return tmpColumnNumbers;
  }

  /**
   * @param aSquare
   * @return All numbers in square aSquareNo
   */
  public Set<Integer> getSquareNumbers(Square aSquare) {
    Set<Integer> tmpSquareNumbers = new HashSet<Integer>();
    for (int y = aSquare.getYUpLeft(); y <= aSquare.getYDownRight(); y++) {
      for (int x = aSquare.getXUpLeft(); x <= aSquare.getXDownRight(); x++) {
        int tmpValue = get(x, y);
        if (tmpValue != 0) {
          tmpSquareNumbers.add(tmpValue);
        }
      }
    }
    return tmpSquareNumbers;
  }

  private void setIfValueIsLastMissingValue(Set<Integer> aValueList, int x, int y) throws SetException,
      InternalException {
    Set<Integer> tmpAllNumbers = new HashSet<Integer>(NUMBERS);

    tmpAllNumbers.removeAll(aValueList);

    if (tmpAllNumbers.size() == 1) {
      set((Integer) tmpAllNumbers.toArray()[0], x, y);
    } else {
      throw new InternalException();
    }
  }

  /**
   * If there is only field (x|y) missing in row y, it is set here
   * 
   * @param x
   * @param y
   * @throws SetException
   * @throws SolveException
   */
  public void addLastMissingNumberInRow(int x, int y) throws SetException, SolveException {
    Set<Integer> tmpRowNumbers = getRowNumbers(y);
    try {
      setIfValueIsLastMissingValue(tmpRowNumbers, x, y);
    } catch (InternalException e) {
      throw new SolveException("More than one field is missing in row " + y);
    }
  }

  /**
   * If there is only field (x|y) missing in column x, it is set here
   * 
   * @param x
   * @param y
   * @throws SetException
   * @throws SolveException
   */
  public void addLastMissingNumberInColumn(int x, int y) throws SetException, SolveException {
    Set<Integer> tmpColumnNumbers = getColumnNumbers(x);
    try {
      setIfValueIsLastMissingValue(tmpColumnNumbers, x, y);
    } catch (InternalException e) {
      throw new SolveException("More than one field is missing in column " + x);
    }
  }

  /**
   * If there is only field (x|y) missing in square z, it is set here
   * 
   * @param x
   * @param y
   * @throws SolveException
   * @throws SetException
   */
  public void addLastMissingNumberInSquare(int x, int y) throws SetException, SolveException {
    Square z = getSquare(x, y);
    Set<Integer> tmpSquareNumbers = getSquareNumbers(z);
    try {
      setIfValueIsLastMissingValue(tmpSquareNumbers, x, y);
    } catch (InternalException e) {
      throw new SolveException("More than one field is missing in square " + z.getNumber());
    }
  }

  /**
   * If the field (x|y) can be filled regarding column x and row y, it is done
   * 
   * @param x
   * @param y
   * @throws SetException
   * @throws SolveException
   */
  public void addLastMissingNumberInRowAndColumn(int x, int y) throws SetException, SolveException {
    Set<Integer> tmpRowNumbers = getRowNumbers(y);
    Set<Integer> tmpColumnNumbers = getColumnNumbers(x);

    tmpRowNumbers.addAll(tmpColumnNumbers);

    if (tmpRowNumbers.size() == 8) {
      try {
        setIfValueIsLastMissingValue(tmpRowNumbers, x, y);
      } catch (InternalException e) {
        throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
            + ") regarding row and column");
      }
    } else {
      throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
          + ") regarding row and column");
    }
  }

  /**
   * If the field (x|y) can be filled regarding square z and row y, it is done
   * 
   * @param x
   * @param y
   * @throws SetException
   * @throws SolveException
   */
  public void addLastMissingNumberInRowAndSquare(int x, int y) throws SetException, SolveException {
    Square z = getSquare(x, y);
    Set<Integer> tmpSquareNumbers = getSquareNumbers(z);
    Set<Integer> tmpRowNumbers = getRowNumbers(y);

    tmpSquareNumbers.addAll(tmpRowNumbers);

    if (tmpSquareNumbers.size() == 8) {
      try {
        setIfValueIsLastMissingValue(tmpSquareNumbers, x, y);
      } catch (InternalException e) {
        throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
            + ") regarding row and square");
      }
    } else {
      throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
          + ") regarding row and square");
    }
  }

  /**
   * If the field (x|y) can be filled regarding square z and column x, it is
   * done
   * 
   * @param x
   * @param y
   * @throws SetException
   * @throws SolveException
   */
  public void addLastMissingNumberInColumnAndSquare(int x, int y) throws SetException, SolveException {
    Square z = getSquare(x, y);
    Set<Integer> tmpSquareNumbers = getSquareNumbers(z);
    Set<Integer> tmpColumnNumbers = getColumnNumbers(x);

    tmpSquareNumbers.addAll(tmpColumnNumbers);

    if (tmpSquareNumbers.size() == 8) {
      try {
        setIfValueIsLastMissingValue(tmpSquareNumbers, x, y);
      } catch (InternalException e) {
        throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
            + ") regarding column and square");
      }
    } else {
      throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
          + ") regarding column and square");
    }
  }

  /**
   * If it is possible to determ a number by combining columns, this method
   * finds the combinations. In case there are more than one field empty in the
   * identified column, the rows are used to check if it is possible to determ
   * between the two or three empty fields
   * 
   * @param x
   * @param y
   * @throws SolveException
   */
  public void addMissingNumberWithColumnCombination(int x, int y) throws SolveException {
    // identify Square
    Square tmpSquare = getSquare(x, y);

    // get all the missing numbers for this column
    Set<Integer> tmpMissingNumbers = new HashSet<Integer>(getMissingColumnNumbers(x));

    // get both other columns
    int tmpOtherColumnOne, tmpOtherColumnTwo;
    if (x - tmpSquare.getXUpLeft() == 0) {
      tmpOtherColumnOne = x + 1;
      tmpOtherColumnTwo = x + 2;
    } else if (x - tmpSquare.getXUpLeft() == 1) {
      tmpOtherColumnOne = x - 1;
      tmpOtherColumnTwo = x + 1;
    } else {
      tmpOtherColumnOne = x - 1;
      tmpOtherColumnTwo = x - 2;
    }

    // check both other fields
    int tmpOtherFieldOne, tmpOtherFieldTwo;
    if (y - tmpSquare.getYUpLeft() == 0) {
      tmpOtherFieldOne = y + 1;
      tmpOtherFieldTwo = y + 2;
    } else if (y - tmpSquare.getYUpLeft() == 1) {
      tmpOtherFieldOne = y - 1;
      tmpOtherFieldTwo = y + 1;
    } else {
      tmpOtherFieldOne = y - 1;
      tmpOtherFieldTwo = y - 2;
    }

    // check if other fields are empty
    int tmpEmptyFields = 0;
    if (get(x, tmpOtherFieldOne) == 0) {
      tmpEmptyFields++;
    }
    if (get(x, tmpOtherFieldTwo) == 0) {
      tmpEmptyFields++;
    }

    // Algorithm for only one empty field in the column / square
    if (tmpEmptyFields == 0) {
      for (Iterator<Integer> tmpIterator = tmpMissingNumbers.iterator(); tmpIterator.hasNext();) {
        int tmpItem = tmpIterator.next();
        if (getColumnNumbers(tmpOtherColumnOne).contains(tmpItem)
            && getColumnNumbers(tmpOtherColumnTwo).contains(tmpItem)) {
          try {
            set(tmpItem, x, y);
            return;
          } catch (SetException e) {
            throw new SolveException("Columncombination is not definite");
          }
        }
      }
      throw new SolveException("Columncombination is not definite");
    }
    // Algorithm for two empty fields in the column / square
    else if (tmpEmptyFields == 1) {
      for (Iterator<Integer> tmpIterator = tmpMissingNumbers.iterator(); tmpIterator.hasNext();) {
        int tmpItem = tmpIterator.next();
        if (getColumnNumbers(tmpOtherColumnOne).contains(tmpItem)
            && getColumnNumbers(tmpOtherColumnTwo).contains(tmpItem)) {
          if (get(x, tmpOtherFieldOne) == 0) {
            Set<Integer> tmpNumbersInRow = new HashSet<Integer>(getRowNumbers(tmpOtherFieldOne));
            if (tmpNumbersInRow.contains(tmpItem)) {
              try {
                set(tmpItem, x, y);
                return;
              } catch (SetException e) {
                throw new SolveException("Columncombination is not definite");
              }
            }
          } else if (get(x, tmpOtherFieldTwo) == 0) {
            Set<Integer> tmpNumbersInRow = new HashSet<Integer>(getRowNumbers(tmpOtherFieldTwo));
            if (tmpNumbersInRow.contains(tmpItem)) {
              try {
                set(tmpItem, x, y);
                return;
              } catch (SetException e) {
                throw new SolveException("Columncombination is not definite");
              }
            }
          }
        }
      }
      throw new SolveException("Columncombination is not definite");
    }
    // Algorithm for three empty fields in the column / square
    else if (tmpEmptyFields == 2) {
      for (Iterator<Integer> tmpIterator = tmpMissingNumbers.iterator(); tmpIterator.hasNext();) {
        int tmpItem = tmpIterator.next();
        if (getColumnNumbers(tmpOtherColumnOne).contains(tmpItem)
            && getColumnNumbers(tmpOtherColumnTwo).contains(tmpItem)) {

          Set<Integer> tmpNumbersInRowOne = new HashSet<Integer>(getRowNumbers(tmpOtherFieldOne));
          Set<Integer> tmpNumbersInRowTwo = new HashSet<Integer>(getRowNumbers(tmpOtherFieldTwo));

          if (tmpNumbersInRowOne.contains(tmpItem) && tmpNumbersInRowTwo.contains(tmpItem)) {
            try {
              set(tmpItem, x, y);
              return;
            } catch (SetException e) {
              throw new SolveException("Columncombination is not definite");
            }
          }
        }
      }
      throw new SolveException("Columncombination is not definite");
    }
  }

  /**
   * If it is possible to determ a number by combining rows, this method finds
   * the combinations. In case there are more than one field empty in the
   * identified row, the columns are used to check if it is possible to determ
   * between the two or three empty fields
   * 
   * @param x
   * @param y
   * @throws SolveException
   */
  public void addMissingNumberWithRowCombination(int x, int y) throws SolveException {
    // identify Square
    Square tmpSquare = getSquare(x, y);

    // get all the missing numbers for this column
    Set<Integer> tmpMissingNumbers = new HashSet<Integer>(getMissingRowNumbers(y));

    // check both other rows
    int tmpOtherRowOne, tmpOtherRowTwo;
    if (y - tmpSquare.getYUpLeft() == 0) {
      tmpOtherRowOne = y + 1;
      tmpOtherRowTwo = y + 2;
    } else if (y - tmpSquare.getYUpLeft() == 1) {
      tmpOtherRowOne = y - 1;
      tmpOtherRowTwo = y + 1;
    } else {
      tmpOtherRowOne = y - 1;
      tmpOtherRowTwo = y - 2;
    }

    // get both other fields
    int tmpOtherFieldOne, tmpOtherFieldTwo;
    if (x - tmpSquare.getXUpLeft() == 0) {
      tmpOtherFieldOne = x + 1;
      tmpOtherFieldTwo = x + 2;
    } else if (x - tmpSquare.getXUpLeft() == 1) {
      tmpOtherFieldOne = x - 1;
      tmpOtherFieldTwo = x + 1;
    } else {
      tmpOtherFieldOne = x - 1;
      tmpOtherFieldTwo = x - 2;
    }

    // check if other fields are empty
    int tmpEmptyFields = 0;
    if (get(tmpOtherFieldOne, y) == 0) {
      tmpEmptyFields++;
    }
    if (get(tmpOtherFieldTwo, y) == 0) {
      tmpEmptyFields++;
    }

    // Algorithm for only one empty field in the row / square
    if (tmpEmptyFields == 0) {

      for (Iterator<Integer> tmpIterator = tmpMissingNumbers.iterator(); tmpIterator.hasNext();) {
        int tmpItem = tmpIterator.next();
        if (getRowNumbers(tmpOtherRowOne).contains(tmpItem) && getRowNumbers(tmpOtherRowTwo).contains(tmpItem)) {
          try {
            set(tmpItem, x, y);
            return;
          } catch (SetException e) {
            throw new SolveException("Rowcombination is not definite");
          }
        }
      }
      throw new SolveException("Rowncombination is not definite");
    }
    // Algorithm for two empty fields in the row / square
    else if (tmpEmptyFields == 1) {
      for (Iterator<Integer> tmpIterator = tmpMissingNumbers.iterator(); tmpIterator.hasNext();) {
        int tmpItem = tmpIterator.next();
        if (getRowNumbers(tmpOtherRowOne).contains(tmpItem) && getRowNumbers(tmpOtherRowTwo).contains(tmpItem)) {
          if (get(tmpOtherFieldOne, y) == 0) {
            Set<Integer> tmpNumbersInColumn = new HashSet<Integer>(getColumnNumbers(tmpOtherFieldOne));
            if (tmpNumbersInColumn.contains(tmpItem)) {
              try {
                set(tmpItem, x, y);
                return;
              } catch (SetException e) {
                throw new SolveException("Rowcombination is not definite");
              }
            }
          } else if (get(tmpOtherFieldTwo, y) == 0) {
            Set<Integer> tmpNumbersInColumn = new HashSet<Integer>(getColumnNumbers(tmpOtherFieldTwo));
            if (tmpNumbersInColumn.contains(tmpItem)) {
              try {
                set(tmpItem, x, y);
                return;
              } catch (SetException e) {
                throw new SolveException("Rowcombination is not definite");
              }
            }
          }
        }
      }
      throw new SolveException("Rowcombination is not definite");
    }
    // Algorithm for three empty fields in the column / square
    else if (tmpEmptyFields == 2) {
      for (Iterator<Integer> tmpIterator = tmpMissingNumbers.iterator(); tmpIterator.hasNext();) {
        int tmpItem = tmpIterator.next();
        if (getRowNumbers(tmpOtherRowOne).contains(tmpItem) && getRowNumbers(tmpOtherRowTwo).contains(tmpItem)) {

          Set<Integer> tmpNumbersInColumnOne = new HashSet<Integer>(getColumnNumbers(tmpOtherFieldOne));
          Set<Integer> tmpNumbersInColumnTwo = new HashSet<Integer>(getColumnNumbers(tmpOtherFieldTwo));

          if (tmpNumbersInColumnOne.contains(tmpItem) && tmpNumbersInColumnTwo.contains(tmpItem)) {
            try {
              set(tmpItem, x, y);
              return;
            } catch (SetException e) {
              throw new SolveException("Rowcombination is not definite");
            }
          }
        }
      }
      throw new SolveException("Rowcombination is not definite");
    }
  }

  private Set<Integer> getMissingRowNumbers(int y) {

    Set<Integer> tmpAllNumbers = new HashSet<Integer>(NUMBERS);
    tmpAllNumbers.removeAll(getRowNumbers(y));
    return tmpAllNumbers;
  }

  private Set<Integer> getMissingColumnNumbers(int x) {

    Set<Integer> tmpAllNumbers = new HashSet<Integer>(NUMBERS);
    tmpAllNumbers.removeAll(getColumnNumbers(x));
    return tmpAllNumbers;
  }

  private Set<Integer> getMissingSquareNumbers(Square aSquare) {
    Set<Integer> tmpAllNumbers = new HashSet<Integer>(NUMBERS);
    tmpAllNumbers.removeAll(getSquareNumbers(aSquare));
    return tmpAllNumbers;
  }

  /**
   * Looks in a square and identifies the missing numbers. Then try to make a
   * cross with a row and a column that both contain the missing number. If only
   * one of the left four fields is empty, the missing number is to be set
   * there!
   * 
   * @param x
   * @param y
   * @throws SolveException
   */
  public void addMissingNumberInSquareWithRowColumnCross(int x, int y) throws SolveException {
    Square tmpSquare = getSquare(x, y);

    Set<Integer> tmpMissingNumbers = new HashSet<Integer>(getMissingSquareNumbers(tmpSquare));

    for (Iterator<Integer> tmpIterator = tmpMissingNumbers.iterator(); tmpIterator.hasNext();) {
      int tmpItem = tmpIterator.next();
      for (int i = tmpSquare.getXUpLeft(); i < tmpSquare.getXDownRight() + 1; i++) {
        for (int j = tmpSquare.getYUpLeft(); j < tmpSquare.getYDownRight() + 1; j++) {
          if (getRowNumbers(j).contains(tmpItem) && getColumnNumbers(i).contains(tmpItem)) {
            int tmpEmptyFields = 0;
            int tmpXEmptyField = 0;
            int tmpYEmptyField = 0;
            // left column is checked
            if (i - tmpSquare.getXUpLeft() == 0) {
              // up row is checked
              if (j - tmpSquare.getYUpLeft() == 0) {
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
              // mid row is checked
              else if (j - tmpSquare.getYUpLeft() == 1) {
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
              // down row is checked
              else if (j - tmpSquare.getYUpLeft() == 2) {
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
            }
            // mid column is checked
            else if (i - tmpSquare.getXUpLeft() == 1) {
              // up row is checked
              if (j - tmpSquare.getYUpLeft() == 0) {
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
              // mid row is checked
              else if (j - tmpSquare.getYUpLeft() == 1) {
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
              // down row is checked
              else if (j - tmpSquare.getYUpLeft() == 2) {
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 2, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 2;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
            }
            // right column is checked
            else if (i - tmpSquare.getXUpLeft() == 2) {
              // up row is checked
              if (j - tmpSquare.getYUpLeft() == 0) {
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
              // mid row is checked
              else if (j - tmpSquare.getYUpLeft() == 1) {
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 2) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 2;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
              // down row is checked
              else if (j - tmpSquare.getYUpLeft() == 2) {
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft(), tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft();
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft()) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft();
                }
                if (get(tmpSquare.getXUpLeft() + 1, tmpSquare.getYUpLeft() + 1) == 0) {
                  tmpEmptyFields++;
                  tmpXEmptyField = tmpSquare.getXUpLeft() + 1;
                  tmpYEmptyField = tmpSquare.getYUpLeft() + 1;
                }

                if (tmpEmptyFields == 1 && tmpXEmptyField == x && tmpYEmptyField == y) {
                  try {
                    set(tmpItem, tmpXEmptyField, tmpYEmptyField);
                    return;
                  } catch (SetException e) {
                    throw new SolveException("Number in square is not definite with row-column-cross.");
                  }
                }
              }
            }
          }
        }
      }

    }
    throw new SolveException("Number in square is not definite with row-column-cross.");
  }

  /**
   * Identifies all missing numbers, regarding row, column and square
   * 
   * @param x
   * @param y
   * @return A Set of all missing numbers, regarding row, column and square
   */
  public Set<Integer> getAllPossibleMissingNumbers(int x, int y) {
    Set<Integer> tmpMissingNumbers = new HashSet<Integer>(NUMBERS);
    tmpMissingNumbers.removeAll(getColumnNumbers(x));
    tmpMissingNumbers.removeAll(getRowNumbers(y));
    tmpMissingNumbers.removeAll(getSquareNumbers(getSquare(x, y)));

    return tmpMissingNumbers;
  }

  /**
   * Identifies all existing numbers, regarding row, column and square
   * 
   * @param x
   * @param y
   * @return A Set of all existing numbers, regarding row, column and square
   */
  public Set<Integer> getAllExistingNumbers(int x, int y) {
    Set<Integer> tmpExistingNumbers = new HashSet<Integer>();
    tmpExistingNumbers.addAll(getColumnNumbers(x));
    tmpExistingNumbers.addAll(getRowNumbers(y));
    tmpExistingNumbers.addAll(getSquareNumbers(getSquare(x, y)));

    return tmpExistingNumbers;
  }

  /**
   * Identifies the numbers that are missing in ALL empty fields of that row,
   * regarding row, column and square
   * 
   * @param y
   * @return A Set of all missing numbers, regarding row, column and square
   */
  public Set<Integer> getAllMissingNumbersInCombinationOfARow(int y) {

    Set<Integer> tmpMissingNumbers = new HashSet<Integer>(NUMBERS);
    for (int x = 1; x < DIMENSION + 1; x++) {
      if (get(x, y) == 0) {
        tmpMissingNumbers.removeAll(getAllExistingNumbers(x, y));
      }
    }
    return tmpMissingNumbers;
  }

  /**
   * Identifies the numbers that are missing in ALL empty fields of that row,
   * regarding row, column and square. All numbers minus the set of the row
   * numbers, minus the set of the numbers from the method
   * "getAllMissingNumbersInCombinationOfARow" can determ a missing number if
   * the resulting set only contains a single number
   * 
   * @param x
   * @param y
   * @throws SolveException
   */
  public void addMissingRowNumberInMultipleMissingFields(int x, int y) throws SolveException {

    Set<Integer> tmpMissingNumbers = new HashSet<Integer>(NUMBERS);
    tmpMissingNumbers.removeAll(getRowNumbers(y));
    tmpMissingNumbers.removeAll(getAllMissingNumbersInCombinationOfARow(y));

    if (tmpMissingNumbers.size() == 1) {
      if (get(x, y) == 0) {
        try {
          set((Integer) tmpMissingNumbers.toArray()[0], x, y);
          return;
        } catch (SetException e) {
          throw new SolveException("Not able to determ value in a row with multiple missing fields.");
        }
      }
    }

  }

}