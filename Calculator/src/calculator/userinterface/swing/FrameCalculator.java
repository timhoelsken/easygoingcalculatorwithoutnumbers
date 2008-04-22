package calculator.userinterface.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JTextField textFormulaInput = new JTextField(16);
	private static JTextField textFormulaOutput = new JTextField();

	// button to calculate formula
	private static JButton buttonCalculateFormula = new JButton("calculate");

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

		// *** add listeners ***
		ActionListenerUtil.putMenuItemOpenDialogListener(menuItemManual, dialogManualText);
		ActionListenerUtil.putMenuItemOpenDialogListener(menuItemAbout, dialogAboutText);

		// for calculating
		buttonCalculateFormula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				// reset progressbar if it is activated
				if (FrameCalculator.isLoadProgressBar()) {
					FrameCalculator.this.getProgressBar().setValue(0);
					FrameCalculator.this.getProgressBar().setString("0%");
				}
				convertAndCalculate();
			}
		});

		// == ActionListener of the menu ==
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				FrameCalculator.this.setVisible(false);
				FrameCalculator.this.dispose();
				System.exit(0);
			}
		});

		menuItemProgressBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (FrameCalculator.isLoadProgressBar()) {
					FrameCalculator.setLoadProgressBar(false);
					FrameCalculator.this.getProgressBar().setVisible(false);
					menuItemProgressBar.setText("Enable ProgressBar");
				} else {
					FrameCalculator.setLoadProgressBar(true);
					FrameCalculator.this.getProgressBar().setValue(0);
					FrameCalculator.this.getProgressBar().setStringPainted(false);
					FrameCalculator.this.getProgressBar().setVisible(true);
					menuItemProgressBar.setText("Disable ProgressBar");
				}
				FrameCalculator.this.repaint();
			}
		});

		menuItemShowTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				if (FrameCalculator.isDisplayTree()) {
					FrameCalculator.setDisplayTree(false);
					menuItemShowTree.setText("Show Tree");
				} else {
					FrameCalculator.setDisplayTree(true);
					menuItemShowTree.setText("Hide Tree");
				}
			}
		});

		// == ActionListener of the menu ==

		// *** add listeners ***

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
		progressBar.setVisible(false);

		// set the "Calculate"-button as defaultButton to activate
		// enter-functionality
		getRootPane().setDefaultButton(buttonCalculateFormula);

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
			aFrameCalculator.setCalculatorTree(FormulaTreeUtil.BuildTree(aFrameCalculator
					.getConvertedFormula()));
			calculatedFormula = ""
					+ FormulaTreeUtil.EvaluateTree(aFrameCalculator.getCalculatorTree(),
							dictionaryOfEnteredVariables);

			if (!loadProgressBar) {
				textFormulaOutput.setText(calculatedFormula);
			}

			enableButtonCalculateFormula();
			showCalculation();

			if (displayTree) {
				aFrameCalculator.dialogShowTree.paintTree(aFrameCalculator);
			}
		} catch (CalculatingException e) {
			calculatedFormula = ERROR;

			enableButtonCalculateFormula();
			showCalculation();

			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "An error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * converts the formula to standard string and calls calculate formula
	 */
	private void convertAndCalculate() {

		buttonCalculateFormula.setEnabled(false);

		// convert the user's input to standard string
		try {
			textFormulaInput.setText((ConverterUtil.formulaToGUIStandardString(textFormulaInput.getText())));

			convertedFormula = ConverterUtil.formulaToStandardString(textFormulaInput.getText());

			textFormulaOutput.setText("");
			calculatedFormula = "";

			// if the formula has Variables, a new frame is opened
			if (ConverterUtil.hasVariables(getConvertedFormula())) {

				// remember values of already entered variables
				ArrayList<String[]> tmpOldVariables = getListOfVariables();
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
				setListOfVariables(tmpNewVariables);

				// openVariableDialog();
				dialogEnterVariables.load(getListOfVariables());

				// otherwise the formula is calculated directly
			} else {

				// reset the list to avoid errors
				setListOfVariables(new ArrayList<String[]>());

				// use the progressBar?
				if (FrameCalculator.isLoadProgressBar()) {
					Thread tmpProgressBarThread = new ProgressBar(this);
					tmpProgressBarThread.start();
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
	 * @return the textFormulaInput
	 */
	public JTextField getTextFormulaInput() {
		return textFormulaInput;
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
}
