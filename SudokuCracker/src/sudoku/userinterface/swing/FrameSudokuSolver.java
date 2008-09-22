package sudoku.userinterface.swing;

import sudoku.Sudoku;
import sudoku.exceptions.SetException;

/**
 *
 * @author Tim
 *
 */
public class FrameSudokuSolver extends Thread{

//defines the speed of "loading"
  private static int DELAY = 100;

private Sudoku sudoku = new Sudoku();

  /**
   * The constructor
   *
   * @param aSudoku
   */
  public FrameSudokuSolver(Sudoku aSudoku) {
    sudoku = aSudoku;
  }

  /**
   * starts the "loading"
   */
  @Override
  public void run() {

    // is called by start() !!!

    boolean tmpRepeat = true;
    while (tmpRepeat) {
      try {
        tmpRepeat = sudoku.solveInFrame();

        try {
          Thread.sleep(DELAY);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } catch (SetException e) {
        System.out.println("Error while solving!");
      }
    }
  }
}
