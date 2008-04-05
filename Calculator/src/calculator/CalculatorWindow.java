package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author Tim
 * 
 */
public class CalculatorWindow extends JFrame {
  
  /**
   * 
   */
  private static final long serialVersionUID = -4971700820441624660L;

  private JButton buttonCalculate;
  private JPanel panelButton;
  private JPanel panelInput;
  private JPanel panelLabel;
  private JLabel top;
  private JLabel formulaLabel;
  private JLabel bottom;
  private JTextField inputField;

  /**
   * Displays a window with an inputfield and a button
   * 
   * @param args
   */
  public static void main(String[] args) {
    CalculatorWindow tmpCalculator = new CalculatorWindow();
    tmpCalculator.setVisible(true);
  }

  /**
   * the CalculatorWindow
   */
  public CalculatorWindow() {
    super("PSE III Taschenrechner");
    setLocation(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(5, 5));

    buttonCalculate = new JButton("berechnen");

    inputField = new JTextField();
    inputField.setText("Geben Sie hier ihre Formel ein.");

    panelButton = new JPanel(new GridLayout(1, 1));
    panelInput = new JPanel(new GridLayout(1, 1));
    panelLabel = new JPanel(new GridLayout(1, 1));

    panelButton.add(buttonCalculate);
    panelInput.add(inputField);

    // Listener für Buttons
    addButtonListener(buttonCalculate);

    // Labels erzeugen
    top = new JLabel("PSE III Taschenrechner");
    top.setHorizontalAlignment(JLabel.CENTER);
    formulaLabel = new JLabel("Formel:");

    //panelLabel.add(formulaLabel);
    panelLabel.add(formulaLabel);

    bottom = new JLabel("ERGEBNISANZEIGE");
    bottom.setHorizontalAlignment(JLabel.CENTER);

    // Labels auf Frame packen
    getContentPane().add(BorderLayout.NORTH, top);
    getContentPane().add(BorderLayout.CENTER, panelInput);
    getContentPane().add(BorderLayout.EAST, panelButton);
    getContentPane().add(BorderLayout.WEST, panelLabel);
    getContentPane().add(BorderLayout.SOUTH, bottom);

    pack();
    inputField.selectAll();

  }

  private void addButtonListener(JButton aButton) {
    aButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        //TODO insert logic HERE
        displayInput(ae.getActionCommand());
        JFrame tmpVariableInput = new JFrame("Variable Input");
        tmpVariableInput.setLocation(400, 400);
        tmpVariableInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tmpVariableInput.getContentPane().setLayout(new BorderLayout(3, 3));
        JTextField tmpInputField = new JTextField();
        tmpInputField.setText("Geben Sie hier ihren Wert für X ein.");
        JLabel tmpLabel = new JLabel("X = ");
        JButton tmpButton = new JButton("Eingabe");
        tmpVariableInput.getContentPane().add(BorderLayout.WEST, tmpLabel);
        tmpVariableInput.getContentPane().add(BorderLayout.CENTER, tmpInputField);
        tmpVariableInput.getContentPane().add(BorderLayout.EAST, tmpButton);
        tmpVariableInput.pack();
        tmpVariableInput.setVisible(true);
      }
    });
  }

  private void displayInput(String aString) {
    bottom.setText(inputField.getText());
  }
}
