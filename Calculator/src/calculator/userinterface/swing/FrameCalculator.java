package calculator.userinterface.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import calculator.elements.Tree;
import calculator.exceptions.CalculatingException;
import calculator.exceptions.FormulaConversionException;
import calculator.utils.ActionListenerUtil;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;

/**
 * This is the frame based calculator
 */
public class FrameCalculator extends JFrame {

  private static final long serialVersionUID = -4971700820441624660L;

  private Image calculatorIcon;

  // dialogs
  private VariableDialog dialogEnterVariables = new VariableDialog(this);
  private ManualDialog dialogManualText = new ManualDialog(this);
  private AboutDialog dialogAboutText = new AboutDialog(this);
  private TreeDialog dialogShowTree = new TreeDialog(this);

  // sub panels
  private JPanel panelBottom = new JPanel(new GridLayout(1, 2));
  private JPanel panelCenter = new JPanel(new GridLayout(2, 1));

  // labels
  private JLabel labelCalculatorTitle = new JLabel("PSE III Calculator");
  private JLabel labelEnterFormula = new JLabel("Formula:");
  private JLabel labelResult = new JLabel("RESULT:");

  // input / output fields
  private JTextField textFormulaInput = new JTextField();
  private static JTextField textFormulaOutput = new JTextField();

  // button to calculate formula
  private static JButton buttonCalculateFormula = new JButton("calculate");

  // Menu Bar
  private JMenuBar menuBarcalculator = new JMenuBar();

  // Menu Buttons
  private JMenu menuFile = new JMenu("File");
  private JMenu menuExtras = new JMenu("Extras");
  private JMenu menuHelp = new JMenu("Help");

  // Menu Items
  private JMenuItem menuItemExit = new JMenuItem("Exit");
  private JMenuItem menuItemProgressBar = new JMenuItem("Enable ProgressBar");
  private JMenuItem menuItemFlam = new JMenuItem("Enable Sound");
  private JMenuItem menuItemShowTree = new JMenuItem("Show Tree");
  private JMenuItem menuItemManual = new JMenuItem("Manual");
  private JMenuItem menuItemAbout = new JMenuItem("About");

  // the progressbar
  private JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);

  // define if a progressBar is loading and if a tree should be drawn
  private static boolean loadProgressBar = false;
  private static boolean displayTree = false;
  private static boolean playSound = false;

  /**
   * list and dictionary for variables
   */
  protected static ArrayList<String[]> listOfVariables = new ArrayList<String[]>();
  private static Hashtable<String, Double> dictionaryOfEnteredVariables = new Hashtable<String, Double>();

  private Tree calculatorTree;

  // the formula entered by the user
  private String convertedFormula;
  private static String calculatedFormula;
  private static final String ERROR = "ERROR";

  /**
   * the constructor of a FrameCalculator
   */
  public FrameCalculator() {

    super("PSE III Calculator");

    // set Icon
    ClassLoader tmpClassLoader = this.getClass().getClassLoader();
    URL tmpUrl = tmpClassLoader.getResource("CalculatorIcon.jpg");
    calculatorIcon = getToolkit().getImage(tmpUrl);
    setIconImage(calculatorIcon);

    // define frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));

    // align elements
    labelCalculatorTitle.setHorizontalAlignment(JLabel.CENTER);
    labelEnterFormula.setVerticalAlignment((JLabel.TOP));
    buttonCalculateFormula.setVerticalAlignment(JButton.CENTER);
    labelResult.setHorizontalAlignment(JLabel.LEFT);
    textFormulaOutput.setHorizontalAlignment(JTextField.CENTER);

    // define menu shortcuts
    menuFile.setMnemonic('F');
    menuItemExit.setMnemonic('E');

    menuExtras.setMnemonic('E');
    menuItemProgressBar.setMnemonic('P');
    menuItemFlam.setMnemonic('S');
    menuItemShowTree.setMnemonic('T');

    menuHelp.setMnemonic('H');
    menuItemManual.setMnemonic('M');
    menuItemAbout.setMnemonic('A');

    // build menu
    menuFile.add(menuItemExit);

    menuExtras.add(menuItemProgressBar);
    menuExtras.add(menuItemFlam);
    menuExtras.add(menuItemShowTree);

    menuHelp.add(menuItemManual);
    menuHelp.add(menuItemAbout);

    menuBarcalculator.add(menuFile);
    menuBarcalculator.add(menuExtras);
    menuBarcalculator.add(menuHelp);

    if (!loadProgressBar) {
      menuItemFlam.setEnabled(false);
    } else {
      menuItemFlam.setEnabled(true);
    }

    // place label and textField on panel
    panelBottom.add(labelResult);
    panelBottom.add(textFormulaOutput);

    panelCenter.add(textFormulaInput);
    panelCenter.add(progressBar);

    // place labels, textField, button and panels on frame
    getContentPane().add(BorderLayout.NORTH, labelCalculatorTitle);
    getContentPane().add(BorderLayout.WEST, labelEnterFormula);
    getContentPane().add(BorderLayout.CENTER, panelCenter);
    getContentPane().add(BorderLayout.EAST, buttonCalculateFormula);
    getContentPane().add(BorderLayout.SOUTH, panelBottom);

    // place menu on the frame
    setJMenuBar(menuBarcalculator);

    // add listeners for calculating
    buttonCalculateFormula.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        // reset progressbar if it is activated
        if (loadProgressBar) {
          progressBar.setValue(0);
          progressBar.setString("0%");
        }
        convertAndCalculate();
      }
    });

    // textField listener
    textFormulaInput.getDocument().addDocumentListener(new DocumentListener() {
      // update on changes made by keyboard
      public void changedUpdate(DocumentEvent aArg0) {
        setCalculatorTree(null);
      }

      // update by copy and paste
      public void insertUpdate(DocumentEvent aArg0) {
        setCalculatorTree(null);
      }

      // update on deleting
      public void removeUpdate(DocumentEvent aArg0) {
        setCalculatorTree(null);
      }
    });

    // ActionListener of the menu
    ActionListenerUtil.putMenuItemOpenDialogListener(menuItemManual, dialogManualText);
    ActionListenerUtil.putMenuItemOpenDialogListener(menuItemAbout, dialogAboutText);

    menuItemExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        setVisible(false);
        dispose();
        System.exit(0);
      }
    });

    menuItemProgressBar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        if (loadProgressBar) {
          loadProgressBar = false;
          progressBar.setVisible(false);
          menuItemFlam.setEnabled(false);
          playSound = false;
          menuItemProgressBar.setText("Enable ProgressBar");
          menuItemFlam.setText("Enable Sound");
        } else {
          loadProgressBar = true;
          progressBar.setValue(0);
          progressBar.setStringPainted(false);
          progressBar.setVisible(true);
          menuItemFlam.setEnabled(true);
          menuItemProgressBar.setText("Disable ProgressBar");
        }
        repaint();
      }
    });

    menuItemFlam.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        if (playSound) {
          playSound = false;
          menuItemFlam.setText("Enable Sound");
        } else {
          playSound = true;
          menuItemFlam.setText("Disable Sound");
        }
      }
    });

    menuItemShowTree.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {

        if (displayTree) {
          displayTree = false;
          menuItemShowTree.setText("Show Tree");
        } else {
          displayTree = true;
          menuItemShowTree.setText("Hide Tree");
        }
      }
    });

    // generate frame correctly
    pack();

    // set location of the frame
    Dimension tmpDimension = getToolkit().getScreenSize();
    int tmpX = tmpDimension.width / 2 - getWidth() / 2;
    int tmpY = tmpDimension.height / 2 - getHeight() / 2;
    setLocation(tmpX, tmpY);

    // focus on textField to enter a formula directly
    textFormulaInput.setFocusable(true);

    // set progressBar invisible by default
    progressBar.setVisible(loadProgressBar);

    // set the "Calculate"-button as defaultButton to activate
    // enter-functionality
    getRootPane().setDefaultButton(buttonCalculateFormula);

    // disable result textField
    textFormulaOutput.setEditable(false);

    // disable resizing the frame
    setResizable(false);
  }

  /**
   * converts the formula to standard string and calls calculate formula
   */
  private void convertAndCalculate() {

    buttonCalculateFormula.setEnabled(false);

    // convert the user's input to standard string
    try {
      String tmpGUIStandardString = ConverterUtil.formulaToGUIStandardString(textFormulaInput.getText());
      textFormulaInput.setText(tmpGUIStandardString);

      convertedFormula = ConverterUtil.formulaToStandardString(textFormulaInput.getText());

      textFormulaOutput.setText("");
      calculatedFormula = "";

      // if the formula has Variables, a new frame is opened
      if (ConverterUtil.hasVariables(getConvertedFormula())) {

        // remember values of already entered variables
        ArrayList<String[]> tmpOldVariables = listOfVariables;
        ArrayList<String[]> tmpNewVariables = ConverterUtil.getVariables(getConvertedFormula());

        for (String[] tmpNewStrings : tmpNewVariables) {
          String tmpNewVariable = tmpNewStrings[0];

          for (String[] tmpOldStrings : tmpOldVariables) {
            String tmpOldVariable = tmpOldStrings[0];

            if (tmpNewVariable.equals(tmpOldVariable)) {
              tmpNewStrings[1] = tmpOldStrings[1];
            }
          }
        }
        listOfVariables = tmpNewVariables;

        // openVariableDialog();
        dialogEnterVariables.load();

        // otherwise the formula is calculated directly
      } else {

        // reset the list to avoid errors
        listOfVariables = new ArrayList<String[]>();

        // use the progressBar?
        if (FrameCalculator.isLoadProgressBar()) {
          Thread tmpProgressBar = new ProgressBar(this);
          tmpProgressBar.start();
        } else {
          FrameCalculator.calculateFormula(this);
        }
      }
    } catch (FormulaConversionException e) {
      calculatedFormula = ERROR;

      enableButtonCalculateFormula();
      showCalculation();

      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  /**
   * calculates the formula
   *
   * @param aFrameCalculator
   *
   * @param aFormula
   */
  public static void calculateFormula(FrameCalculator aFrameCalculator) {

    // puts the ArrayList into the dictionary
    dictionaryOfEnteredVariables = ConverterUtil.putArrayListIntoHashtable(FrameCalculator.listOfVariables);

    // calculate!
    try {
      if (aFrameCalculator.getCalculatorTree() == null) {
        aFrameCalculator.setCalculatorTree(FormulaTreeUtil.buildTree(aFrameCalculator.getConvertedFormula()));
      }
      double tmpResult = FormulaTreeUtil.evaluateTree(aFrameCalculator.getCalculatorTree(),
          dictionaryOfEnteredVariables);
      // round the result to the eighth decimal place
      tmpResult = BigDecimal.valueOf(tmpResult).setScale(8, RoundingMode.HALF_UP).doubleValue();
      calculatedFormula = String.valueOf(tmpResult);

      showCalculation();

      if (displayTree) {
        aFrameCalculator.dialogShowTree.paintTree(aFrameCalculator);
      }
    } catch (CalculatingException e) {
      calculatedFormula = ERROR;

      showCalculation();

      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  /**
   * displays the result
   */
  public static void showCalculation() {
    textFormulaOutput.setText(calculatedFormula);
    enableButtonCalculateFormula();
  }

  /**
   * enables the calculate button
   */
  public static void enableButtonCalculateFormula() {
    buttonCalculateFormula.setEnabled(true);
  }

  /**
   * @return the progressBar
   */
  public JProgressBar getProgressBar() {
    return progressBar;
  }

  /**
   * @return the convertedFormula
   */
  public String getConvertedFormula() {
    return convertedFormula;
  }

  /**
   * @return the calculatorTree
   */
  public Tree getCalculatorTree() {
    return calculatorTree;
  }

  /**
   * @param aCalculatorTree
   *            the calculatorTree to set
   */
  public void setCalculatorTree(Tree aCalculatorTree) {
    calculatorTree = aCalculatorTree;
  }

  /**
   * @return the loadProgressBar
   */
  public static boolean isLoadProgressBar() {
    return loadProgressBar;
  }

  /**
   * @return the playSound
   */
  public static boolean isPlaySound() {
    return playSound;
  }
}
