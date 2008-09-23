package sudoku.userinterface.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sudoku.Sudoku;
import sudoku.exceptions.SetException;


/**
 * 
 * @author Tim
 *
 */
public class AddSudokuDialog extends JDialog {

  /**
   * 
   */
  public static JTextField[][] textArray;
  
  private JButton buttonAdd = new JButton("Add"); 
  private JButton buttonClose = new JButton("Cancel"); 
  
  private JPanel panelLeft = new JPanel(new GridLayout(9, 9));
  private JPanel panelRight = new JPanel(new GridLayout(3, 1));
  
  private Sudoku sudoku;
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   * @param aParentFrame
   * @param aSudoku 
   */
  public AddSudokuDialog(JFrame aParentFrame, Sudoku aSudoku) {

    // define dialog window
    super(aParentFrame, "Generate Sudoku", Dialog.ModalityType.DOCUMENT_MODAL);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new GridLayout(1, 2));
    panelLeft.setBorder(BorderFactory.createLineBorder(Color.black));
    sudoku = aSudoku;
  }
  
  /**
   * loads the about dialog
   */
  public void load() {

    textArray = new JTextField[9][9];

  
    add(panelLeft);
    panelLeft.removeAll();
    insertFields();
    

    panelRight.add(buttonAdd);
    panelRight.add(buttonClose);
    add(panelRight);

    // add ActionListener to Add-Button
    buttonAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        saveSudoku();
        
        setVisible(false);
        dispose();
        Component tmpComponent = getParent();
        while ((tmpComponent != null) && !(tmpComponent instanceof JFrame)){
          tmpComponent = tmpComponent.getParent();
        }
        
        FrameSudoku tmpJFrame = (FrameSudoku)tmpComponent;
        tmpJFrame.insertSudoku();
      }

      private void saveSudoku() {
        for (int y = 0; y < 9; y++) {
          for (int x = 0; x < 9; x++) {
            if (!textArray[x][y].getText().equals("   ")) {
              try {
                sudoku.setInFrame(Integer.parseInt(""+textArray[x][y].getText().toCharArray()[1]), x+1, y+1);
              } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Error in numberconvertion.");
              } catch (SetException e) {
                e.printStackTrace();
                System.out.println("Value can't be set!.");
              }
            }
            else{
              try {
                sudoku.setInFrame(0, x+1, y+1);
              } catch (SetException e) {
                e.printStackTrace();
                System.out.println("Value can't be set!.");
              }
            }
            panelLeft.add(textArray[x][y]);
          }
        }
        
      }
    });
    
    buttonClose.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae) {

        
        setVisible(false);
        dispose();
      }
    });

    // generate frame correctly
    pack();

    // set location of the dialog
    Point tmpPoint = getParent().getLocationOnScreen();
    int tmpX = tmpPoint.x + getParent().getWidth() / 2 - getWidth() / 2;
    int tmpY = tmpPoint.y;
    setLocation(tmpX, tmpY);

    // set the "Enter"-button as defaultButton to activate
    // enter-functionality
    getRootPane().setDefaultButton(buttonClose);

    // disable resizing the dialog and display it
    setResizable(false);
    setVisible(true);
  }

  private void insertFields() {
    for (int y = 0; y < 9; y++) {
      for (int x = 0; x < 9; x++) {
        textArray[x][y] = new JTextField("   ");
        if (sudoku.get(x + 1, y + 1) != 0) {
          textArray[x][y].setText(" " + sudoku.get(x + 1, y + 1) + " ");
        }
        panelLeft.add(textArray[x][y]);
      }
    }
  }
}
