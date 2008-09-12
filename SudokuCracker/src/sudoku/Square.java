package sudoku;

/**
 * @author Tobias
 * 
 */
public class Square {
  private final int number;
  private final int xUpLeft;
  private final int yUpLeft;
  private final int xDownRight;
  private final int yDownRight;

  private static final Square ONE = new Square(1, 1, 1, 3, 3);
  private static final Square TWO = new Square(2, 4, 1, 6, 3);
  private static final Square THREE = new Square(3, 7, 1, 9, 3);
  private static final Square FOUR = new Square(4, 1, 4, 3, 6);
  private static final Square FIVE = new Square(5, 4, 4, 6, 6);
  private static final Square SIX = new Square(6, 7, 4, 9, 6);
  private static final Square SEVEN = new Square(7, 1, 7, 3, 9);
  private static final Square EIGHT = new Square(8, 4, 7, 6, 9);
  private static final Square NINE = new Square(9, 7, 7, 9, 9);

  private Square(int aNumber, int anXupLeft, int aYupLeft, int anXdownRight, int aYdownRight) {
    number = aNumber;
    xUpLeft = anXupLeft;
    yUpLeft = aYupLeft;
    xDownRight = anXdownRight;
    yDownRight = aYdownRight;
  }

  /**
   * @param aNumber
   * @return The square according to the number
   */
  public static Square getSquare(int aNumber) {
    switch (aNumber) {
      case 1:
        return ONE;
      case 2:
        return TWO;
      case 3:
        return THREE;
      case 4:
        return FOUR;
      case 5:
        return FIVE;
      case 6:
        return SIX;
      case 7:
        return SEVEN;
      case 8:
        return EIGHT;
      case 9:
        return NINE;
      default:
        throw new IllegalArgumentException("Square number " + aNumber + " does not exist");
    }
  }

  /**
   * @return the number
   */
  public int getNumber() {
    return number;
  }

  /**
   * @return the xUpLeft
   */
  public int getXUpLeft() {
    return xUpLeft;
  }

  /**
   * @return the yUpLeft
   */
  public int getYUpLeft() {
    return yUpLeft;
  }

  /**
   * @return the xDownRight
   */
  public int getXDownRight() {
    return xDownRight;
  }

  /**
   * @return the yDownRight
   */
  public int getYDownRight() {
    return yDownRight;
  }
}