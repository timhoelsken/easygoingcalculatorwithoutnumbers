package sudoku;

import sudoku.userinterface.swing.FrameSudoku;

/**
 * 
 * @author Tim
 *
 */
public class SudokuStarter {
  
  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    
    try{
      FrameSudoku tmpFrame = new FrameSudoku();      
      tmpFrame.setVisible(true);
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }

}
