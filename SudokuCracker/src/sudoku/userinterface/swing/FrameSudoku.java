package sudoku.userinterface.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sudoku.Sudoku;
import sudoku.exceptions.SetException;

/**
 * 
 * @author Tim
 * 
 */
public class FrameSudoku extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  // private Image sudokuIcon;

  private JPanel panelLeft;
  private JPanel panelRight = new JPanel(new GridLayout(3, 1));

  /**
   * 
   */
  public static JLabel[][] labelArray;

  private static JButton solveButton = new JButton("solve!");

  private Sudoku sudoku = new Sudoku();

  /**
   * the constructor of a FrameCalculator
   * 
   * @throws Exception
   */
  public FrameSudoku() throws Exception {

    super("Sudoku Cracker 1.0");

    // set Icon
    // ClassLoader tmpClassLoader = this.getClass().getClassLoader();
    // URL tmpUrl = tmpClassLoader.getResource("SudokuIcon.jpg");
    // sudokuIcon = getToolkit().getImage(tmpUrl);
    // setIconImage(sudokuIcon);

    // define frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new GridLayout(1, 2));

    if (sudoku.getDimension()!=9) {
      throw new Exception("Size of sudoku is not a usable number");
    }
    panelLeft = new JPanel(new GridLayout(sudoku.getDimension(), sudoku.getDimension()));

    labelArray = new JLabel[sudoku.getDimension()][sudoku.getDimension()];

    panelLeft.setBorder(BorderFactory.createLineBorder(Color.black));

    insertSudoku();

    add(panelLeft);

    panelRight.add(solveButton);
    add(panelRight);

    // add listeners for solving
    solveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        FrameSudokuSolver tmpFrameSudokuSolver = new FrameSudokuSolver(sudoku);
        solveButton.setEnabled(false);
        tmpFrameSudokuSolver.start();

        // solveButton.setEnabled(true);
      }
    });

    // generate frame correctly
    pack();

    // set location of the frame
    Dimension tmpDimension = getToolkit().getScreenSize();
    int tmpX = tmpDimension.width / 2 - getWidth() / 2;
    int tmpY = tmpDimension.height / 2 - getHeight() / 2;
    setLocation(tmpX, tmpY);
  }

  private void insertSudoku() {
    try {

      sudoku.set(3, 1, 1);
      sudoku.set(1, 2, 1);
      sudoku.set(6, 7, 1);
      sudoku.set(5, 9, 1);
      sudoku.set(6, 2, 2);
      sudoku.set(5, 3, 2);
      sudoku.set(2, 4, 2);
      sudoku.set(9, 8, 2);
      sudoku.set(1, 9, 2);
      sudoku.set(9, 1, 3);
      sudoku.set(1, 4, 3);
      sudoku.set(5, 5, 3);
      sudoku.set(2, 8, 3);
      sudoku.set(8, 4, 4);
      sudoku.set(5, 6, 4);
      sudoku.set(7, 7, 4);
      sudoku.set(4, 8, 4);
      sudoku.set(1, 3, 5);
      sudoku.set(9, 5, 5);
      sudoku.set(5, 7, 5);
      sudoku.set(4, 2, 6);
      sudoku.set(8, 3, 6);
      sudoku.set(7, 4, 6);
      sudoku.set(2, 6, 6);
      sudoku.set(3, 2, 7);
      sudoku.set(7, 5, 7);
      sudoku.set(1, 6, 7);
      sudoku.set(6, 9, 7);
      sudoku.set(1, 1, 8);
      sudoku.set(2, 2, 8);
      sudoku.set(3, 6, 8);
      sudoku.set(9, 7, 8);
      sudoku.set(8, 8, 8);
      sudoku.set(7, 1, 9);
      sudoku.set(9, 3, 9);
      sudoku.set(3, 8, 9);
      sudoku.set(4, 9, 9);

      for (int y = 0; y < sudoku.getDimension(); y++) {
        for (int x = 0; x < sudoku.getDimension(); x++) {
          labelArray[x][y] = new JLabel("   ");
          if (sudoku.get(x + 1, y + 1) != 0) {
            labelArray[x][y].setText(" " + sudoku.get(x + 1, y + 1) + " ");
          }
          labelArray[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
          panelLeft.add(labelArray[x][y]);
        }
      }

    } catch (SetException e) {
      System.out.println("ERROR!!!\n");
      e.getMessage();
    }

  }
}
