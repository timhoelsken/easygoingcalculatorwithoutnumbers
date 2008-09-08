package sudoku;

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

  /**
   * Sets the given value on the field with the coordinates x and y
   *
   * @param anX
   * @param aY
   * @param aValue
   */
  public void setContent(int aValue, int anX, int aY) {
    aSudoku[aY-1][anX-1] = aValue;
  }

  /**
   * @param anX
   * @param aY
   * @return Returns the value of the field with the coordinates x and y
   */
  public int getField(int anX, int aY) {
    return aSudoku[aY-1][anX-1];
  }
}