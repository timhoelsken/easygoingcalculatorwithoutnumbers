package calculator.userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;
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

import calculator.elements.Tree;
import calculator.exceptions.CalculatingException;
import calculator.exceptions.FormulaConversionException;
import calculator.utils.ActionListenerUtil;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;

/**
 * This openes the calculator in a frame.
 */
public class FrameCalculator extends JFrame {

  private static final long serialVersionUID = -4971700820441624660L;

  private BufferedImage calculatorIcon;
  
  // dialogs
  private FrameCalculatorVariableDialog dialogEnterVariables = new FrameCalculatorVariableDialog(this);
  private FrameCalculatorManualDialog dialogHelpText = new FrameCalculatorManualDialog(this);
  private FrameCalculatorAboutDialog dialogInfoText = new FrameCalculatorAboutDialog(this);
  private FrameCalculatorTreeDialog dialogShowTree = new FrameCalculatorTreeDialog(this);

  // sub panels
  private JPanel panelBottom = new JPanel(new GridLayout(1, 2));
  private JPanel panelCenter = new JPanel(new GridLayout(2, 1));

  // labels
  private JLabel labelCalculatorTitle = new JLabel("PSE III Calculator");
  private JLabel labelEnterFormula = new JLabel("Formula:");
  private JLabel labelResult = new JLabel("RESULT:");

  // input / output fields
  private JTextField textTermInput = new JTextField(16);
  private static JTextField textFormulaOutput = new JTextField();

  // button to calculate formula
  private JButton buttonCalculateTerm = new JButton("calculate");

  // == Menu Components ==
  private JMenuBar menuBarcalculator = new JMenuBar();

  private JMenu menuFile = new JMenu("File");
  private JMenu menuView = new JMenu("View");
  private JMenu menuHelp = new JMenu("Help");

  private JMenuItem menuItemExit = new JMenuItem("Exit");
  private JMenuItem menuItemProgressBar = new JMenuItem("Enable ProgressBar");
  private JMenuItem menuItemShowTree = new JMenuItem("Show Tree");
  private JMenuItem menuItemManual = new JMenuItem("Manual");
  private JMenuItem menuItemAbout = new JMenuItem("About");
  // == Menu Components ==

  // the progressbar :)
  private JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);

  // define if a progressBar is loading and if a tree should be drawn
  private static boolean loadProgressBar = false;
  private static boolean displayTree = false;

  // list and dictionary for variables
  private ArrayList<String[]> listOfVariables = new ArrayList<String[]>();
  private static Hashtable<String, Double> dictionaryOfEnteredVariables = new Hashtable<String, Double>();

  private Tree calculatorTree;

  // the formula entered by the user
  private String convertedFormula = new String("");
  private static String calculatedFormula = new String("");
  private static final String ERROR = "ERROR";

  /**
   * the constructor of a FrameCalculator
   */
  public FrameCalculator() {

    super("PSE III Calculator");

    // set Icon
    try{
      File tmpFile = new File("misc/images/CalcIcon.jpg");
      calculatorIcon = ImageIO.read(tmpFile);
    }catch (IOException e){
      e.printStackTrace();
    }
    
    setIconImage(calculatorIcon);
    // define frame
    setLocation(330, 330);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout(10, 10));

    // align elements
    labelCalculatorTitle.setHorizontalAlignment(JLabel.CENTER);
    labelEnterFormula.setVerticalAlignment((JLabel.TOP));
    buttonCalculateTerm.setVerticalAlignment(JButton.CENTER);
    labelResult.setHorizontalAlignment(JLabel.LEFT);
    textFormulaOutput.setHorizontalAlignment(JTextField.CENTER);

    // define menu shortcuts
    menuFile.setMnemonic('F');
    menuItemExit.setMnemonic('E');

    menuView.setMnemonic('V');
    menuItemProgressBar.setMnemonic('P');
    menuItemShowTree.setMnemonic('T');

    menuHelp.setMnemonic('H');
    menuItemManual.setMnemonic('M');
    menuItemAbout.setMnemonic('A');

    // build menu
    menuFile.add(menuItemExit);

    menuView.add(menuItemProgressBar);
    menuView.add(menuItemShowTree);

    menuHelp.add(menuItemManual);
    menuHelp.add(menuItemAbout);

    menuBarcalculator.add(menuFile);
    menuBarcalculator.add(menuView);
    menuBarcalculator.add(menuHelp);

    // place label and textField on panel
    panelBottom.add(labelResult);
    panelBottom.add(textFormulaOutput);

    panelCenter.add(textTermInput);
    panelCenter.add(progressBar);

    // place labels, textField, button and panel on frame
    getContentPane().add(BorderLayout.NORTH, labelCalculatorTitle);
    getContentPane().add(BorderLayout.WEST, labelEnterFormula);
    getContentPane().add(BorderLayout.CENTER, panelCenter);
    getContentPane().add(BorderLayout.EAST, buttonCalculateTerm);
    getContentPane().add(BorderLayout.SOUTH, panelBottom);

    // place menu on the frame
    setJMenuBar(menuBarcalculator);

    // add listeners
    ActionListenerUtil.putCalculateFormulaListener(this, buttonCalculateTerm);
    ActionListenerUtil.putMenuItemOpenDialogListener(menuItemManual, dialogHelpText);
    ActionListenerUtil.putMenuItemOpenDialogListener(menuItemAbout, dialogInfoText);
    // == ActionListener of the menu ==
    ActionListenerUtil.putFrameCalculatorCloseListener(this, menuItemExit);
    ActionListenerUtil.putProgressBarActivateListener(this, menuItemProgressBar);
    ActionListenerUtil.putShowTreeActivateListener(this, menuItemShowTree);
    // == ActionListener of the menu ==

    // generate frame correctly
    pack();

    // focus on textField to enter a formula directly
    textTermInput.setFocusable(true);

    // set progressBar invisible by default
    progressBar.setVisible(false);

    // set the "Calculate"-button as defaultButton to activate
    // enter-functionality
    getRootPane().setDefaultButton(buttonCalculateTerm);

    // disable result textField
    textFormulaOutput.setEditable(false);

    // disable resizing the frame
    setResizable(false);
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
    dictionaryOfEnteredVariables = ConverterUtil.putArrayListIntoHashtable(aFrameCalculator
        .getListOfVariables());

    // calculate!
    try {
      aFrameCalculator.setCalculatorTree(FormulaTreeUtil.BuildTree(aFrameCalculator.getConvertedFormula()));
      calculatedFormula = ""
          + FormulaTreeUtil.EvaluateTree(aFrameCalculator.getCalculatorTree(), dictionaryOfEnteredVariables);

      if (!loadProgressBar) {
        textFormulaOutput.setText(calculatedFormula);
      }
      if (displayTree) {
        aFrameCalculator.dialogShowTree.paintTree(aFrameCalculator);
      }
      showCalculation();
    } catch (CalculatingException e) {
      calculatedFormula = ERROR;
      showCalculation();
      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  /**
   * @param aParentFrame
   */
  public static void convertAndCalculate(FrameCalculator aParentFrame) {
    // convert the user's input to standard string
    try {
      aParentFrame.getTextTermInput().setText(
          (ConverterUtil.termToGUIStandardString(aParentFrame.getTextTermInput().getText())));

      aParentFrame.setConvertedFormula(ConverterUtil.termToStandardString(aParentFrame.getTextTermInput()
          .getText()));

      FrameCalculator.textFormulaOutput.setText("");
      calculatedFormula = "";

      // if the formula has Variables, a new frame is opened
      if (ConverterUtil.hasVariables(aParentFrame.getConvertedFormula())) {

        ArrayList<String[]> tmpOldVariables = aParentFrame.getListOfVariables();
        ArrayList<String[]> tmpNewVariables = ConverterUtil.getVariables(aParentFrame.getConvertedFormula());
        for (String[] tmpNewStrings : tmpNewVariables) {
          String tmpNewVariable = tmpNewStrings[0];
          for (String[] tmpOldStrings : tmpOldVariables) {
            String tmpOldVariable = tmpOldStrings[0];
            if (tmpNewVariable.equals(tmpOldVariable)) {
              tmpNewStrings[1] = tmpOldStrings[1];
            }
          }
        }
        aParentFrame.setListOfVariables(tmpNewVariables);

        // openVariableDialog();
        aParentFrame.getDialogEnterVariables().load(aParentFrame.getListOfVariables());

        // otherwise the formula is calculated directly
      } else {

        // reset the list to avoid errors
        aParentFrame.setListOfVariables(new ArrayList<String[]>());

     // use the progressBar?
        if (FrameCalculator.isLoadProgressBar()) {
          Thread tmpProgressBarThread = new ProgressBarThread(aParentFrame);
          tmpProgressBarThread.start();
        }else{
          FrameCalculator.calculateFormula(aParentFrame);
          
        }
      }
      showCalculation();
    } catch (FormulaConversionException e) {
      calculatedFormula = ERROR;
      showCalculation();
      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  /**
   * @return the calculatedFormula
   */
  public static String getCalculatedFormula() {
    return calculatedFormula;
  }

  /**
   * @param aCalculatedFormula
   *            the calculatedFormula to set
   */
  public static void setCalculatedFormula(String aCalculatedFormula) {
    calculatedFormula = aCalculatedFormula;
  }

  /**
   * @param aConvertedFormula
   *            the convertedFormula to set
   */
  public void setConvertedFormula(String aConvertedFormula) {
    convertedFormula = aConvertedFormula;
  }

  /**
   * 
   */
  public static void showCalculation() {
    textFormulaOutput.setText(calculatedFormula);
  }

  /**
   * @return the progressBar
   */
  public boolean getProgressBarStatus() {
    return loadProgressBar;
  }

  /**
   * @return the progressBar
   */
  public JProgressBar getProgressBar() {
    return progressBar;
  }

  /**
   * @return the progressBar
   */
  public String getConvertedFormula() {
    return convertedFormula;
  }

  /**
   * @return the listOfVariables
   */
  public ArrayList<String[]> getListOfVariables() {
    return listOfVariables;
  }

  /**
   * @param aListOfVariables
   *            the listOfVariables to set
   */
  public void setListOfVariables(ArrayList<String[]> aListOfVariables) {
    listOfVariables = aListOfVariables;
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
   * @return the textTermInput
   */
  public JTextField getTextTermInput() {
    return textTermInput;
  }

  /**
   * @return the dialogEnterVariables
   */
  public FrameCalculatorVariableDialog getDialogEnterVariables() {
    return dialogEnterVariables;
  }

  /**
   * @return the loadProgressBar
   */
  public static boolean isLoadProgressBar() {
    return loadProgressBar;
  }

  /**
   * @param aLoadProgressBar
   *            the loadProgressBar to set
   */
  public static void setLoadProgressBar(boolean aLoadProgressBar) {
    loadProgressBar = aLoadProgressBar;
  }

  /**
   * @return the displayTree
   */
  public static boolean isDisplayTree() {
    return displayTree;
  }

  /**
   * @param aDisplayTree
   *            the displayTree to set
   */
  public static void setDisplayTree(boolean aDisplayTree) {
    displayTree = aDisplayTree;
  }

  /**
   * @param aTextTermInput
   *            the textTermInput to set
   */
  public void setTextTermInput(JTextField aTextTermInput) {
    textTermInput = aTextTermInput;
  }
}
