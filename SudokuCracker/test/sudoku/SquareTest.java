package sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Tobias
 *
 */
public class SquareTest {

  /**
   * There has to be a 1 to 1 assignment from number to square constant
   */
  @Test
  public void getRightSquaresByNumber() {
    assertEquals(1, Square.getSquare(1).getNumber());
    assertEquals(2, Square.getSquare(2).getNumber());
    assertEquals(3, Square.getSquare(3).getNumber());
    assertEquals(4, Square.getSquare(4).getNumber());
    assertEquals(5, Square.getSquare(5).getNumber());
    assertEquals(6, Square.getSquare(6).getNumber());
    assertEquals(7, Square.getSquare(7).getNumber());
    assertEquals(8, Square.getSquare(8).getNumber());
    assertEquals(9, Square.getSquare(9).getNumber());
  }

  /**
   * Square numbers are from 1 to 9
   */
  @Test (expected=IllegalArgumentException.class)
  public void avoidWrongSquareGetting1() {
    Square.getSquare(0);
  }

  /**
   * Square numbers are from 1 to 9
   */
  @Test (expected=IllegalArgumentException.class)
  public void avoidWrongSquareGetting2() {
    Square.getSquare(10);
  }
}