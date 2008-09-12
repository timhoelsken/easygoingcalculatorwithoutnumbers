/**
 *
 */
package sudoku;

/**
 * @author Tobias
 * 
 */
public class TestUtil {

  /**
   * Paints aSudoku on console
   * 
   * @param aSudoku
   */
  public static void paint(Sudoku aSudoku) {
    for (int i = 1; i <= Sudoku.DIMENSION; i++) {
      for (int j = 1; j <= Sudoku.DIMENSION; j++) {
        int tmpValue = aSudoku.get(j, i);
        System.out.print(tmpValue);
        if (j % 3 == 0) {
          System.out.print(" ");
        }
      }
      System.out.println("");
    }
    System.out.println("___________");
    System.out.println("");
  }

}
