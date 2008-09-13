/**
 *
 */
package sudoku;

import java.util.ArrayList;

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

  private int[][] values;

  /**
   * Standard constructor
   */
  public Sudoku() {
    values = new int[DIMENSION][DIMENSION];
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
                    } catch (SolveException e5) {
                      try {
                        addLastMissingNumberInColumnAndSquare(x, y);
                      } catch (SolveException e6) {
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
    ArrayList<Integer> tmpNumbers = getColumnNumbers(x);
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
   * @param aSquare
   * @return All numbers in square aSquareNo
   */
  public ArrayList<Integer> getSquareNumbers(Square aSquare) {
    ArrayList<Integer> tmpSquareNumbers = new ArrayList<Integer>();
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

  private void setIfValueIsLastMissingValue(ArrayList<Integer> aValueList, int x, int y) throws SetException,
      InternalException {
    if (aValueList.size() == DIMENSION - 1) {
      for (int i = 1; i <= DIMENSION; i++) {
        if (!aValueList.contains(i)) {
          set(i, x, y);
          return;
        }
      }
    }
    throw new InternalException();
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
    ArrayList<Integer> tmpRowNumbers = getRowNumbers(y);
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
    ArrayList<Integer> tmpColumnNumbers = getColumnNumbers(x);
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
    ArrayList<Integer> tmpSquareNumbers = getSquareNumbers(z);
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
    ArrayList<Integer> tmpNumbers = getRowNumbers(y);
    ArrayList<Integer> tmpColumnNumbers = getColumnNumbers(x);
    for (Integer tmpColumnNumber : tmpColumnNumbers) {
      if (!tmpNumbers.contains(tmpColumnNumber)) {
        tmpNumbers.add(tmpColumnNumber);
      }
    }
    try {
      setIfValueIsLastMissingValue(tmpNumbers, x, y);
    } catch (InternalException e) {
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
    ArrayList<Integer> tmpNumbers = getSquareNumbers(z);
    ArrayList<Integer> tmpRowNumbers = getRowNumbers(y);
    for (Integer tmpRowNumber : tmpRowNumbers) {
      if (!tmpNumbers.contains(tmpRowNumber)) {
        tmpNumbers.add(tmpRowNumber);
      }
    }
    try {
      setIfValueIsLastMissingValue(tmpNumbers, x, y);
    } catch (InternalException e) {
      throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
          + ") regarding row and square");
    }
  }

  /**
   * If the field (x|y) can be filled regarding square z and column x, it is done
   *
   * @param x
   * @param y
   * @throws SetException
   * @throws SolveException
   */
  public void addLastMissingNumberInColumnAndSquare(int x, int y) throws SetException, SolveException {
    Square z = getSquare(x, y);
    ArrayList<Integer> tmpNumbers = getSquareNumbers(z);
    ArrayList<Integer> tmpColumnNumbers = getColumnNumbers(x);
    for (Integer tmpColumnNumber : tmpColumnNumbers) {
      if (!tmpNumbers.contains(tmpColumnNumber)) {
        tmpNumbers.add(tmpColumnNumber);
      }
    }
    try {
      setIfValueIsLastMissingValue(tmpNumbers, x, y);
    } catch (InternalException e) {
      throw new SolveException("More than one field is missing to determine field (" + x + "|" + y
          + ") regarding column and square");
    }
  }
}