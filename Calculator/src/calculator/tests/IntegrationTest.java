/**
 *
 */
package calculator.tests;

import java.util.Hashtable;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import calculator.Calculator;
import calculator.elements.Tree;
import calculator.exceptions.CalculatingException;
import calculator.exceptions.FormulaConversionException;
import calculator.exceptions.IllegalInputStreamException;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;
import calculator.utils.MathUtil;

/**
 */
public class IntegrationTest extends TestCase {

  /**
   * tests the whole workflow
   * 
   * @throws Exception
   */
  public void testCalculator1() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("2 + 5 - (2*2.5)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * tests the whole workflow
   * 
   * @throws Exception
   */
  public void testCalculator2() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("1234567890");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 1234567890.0, tmpResult);
  }

  /**
   * tests the whole workflow
   * 
   * @throws Exception
   */
  public void testCalculator3() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("sqrt(4)^((5-3)-2)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * tests the whole workflow with variables
   * 
   * @throws Exception
   */
  public void testCalculator4() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("x^2+5-y");
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));
    tmpHashtable.put("y", new Double(4));
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);
    assertEquals((double) 5.0, tmpResult);
  }

  /**
   * tests priority of operator ^
   * 
   * @throws Exception
   */
  public void testCalculator5() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("2^sin(90)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * tests priority of operator ^
   * 
   * @throws Exception
   */
  public void testCalculator6() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("-2^sin(90)^2");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 4.0, tmpResult);
  }

  /**
   * tests the whole workflow
   * 
   * @throws Exception
   */
  public void testCalculator7() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      String tmpString = ConverterUtil.formulaToStandardString("sqrt(-1)");
      Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * tests the whole workflow with variables
   * 
   * @throws Exception
   */
  public void testCalculator8() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("sin*sIN");
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("s", new Double(3));
    tmpHashtable.put("i", new Double(3));
    tmpHashtable.put("n", new Double(3));
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);
    assertEquals((double) 729.0, tmpResult);
  }

  /**
   * 
   * @throws Exception
   */
  public void testCalculator9() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpString = null;
    Double d = 0.0;

    try {
      tmpString = ConverterUtil.formulaToStandardString("(1+3)(2+3)+(1+2*3+3+4)");
      Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
      d = FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue("There should no error occure.", !tmpErrorOccured);
    assertEquals(34.0, d);
  }

  /**
   * 
   * @throws Exception
   */
  public void testCalculator10() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpString = null;
    double tmpResult = 0;

    try {
      tmpString = ConverterUtil.formulaToStandardString("(2-(-1+2)(2+2)-1-(2+2)(2+2)+2)(2-1)");
      Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
      tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue("There should no error occure.", !tmpErrorOccured);
    assertEquals(-17.0, tmpResult);
  }

  // === Iteration Tests ===

  // Iteration 0.1 and 0.2

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0101() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      String tmpString = ConverterUtil.formulaToStandardString("");
      Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0102() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0103() throws Exception {
    boolean tmpErrorOccured = false;
    try {
      ConverterUtil.formulaToStandardString("+1");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0104() throws Exception {
    boolean tmpErrorOccured = false;
    try {
      ConverterUtil.formulaToStandardString("*1");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0105() throws Exception {
    boolean tmpErrorOccured = false;
    try {
      ConverterUtil.formulaToStandardString("/1");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0106() throws Exception {
    boolean tmpErrorOccured = false;
    try {
      ConverterUtil.formulaToStandardString("^1");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0107() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1+"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0108() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1+1"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * test of iterations 0.1 and 0.2
   * 
   * @throws Exception
   */
  public void testIteration0109() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("2/0"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  // Iteration 0.3

  /**
   * test of iteration 0.3
   * 
   * @throws Exception
   */
  public void testIteration0301() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1+1*2"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 3.0, tmpResult);
  }

  /**
   * test of iteration 0.3
   * 
   * @throws Exception
   */
  public void testIteration0302() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1/1-1"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 0.0, tmpResult);
  }

  /**
   * test of iteration 0.3
   * 
   * @throws Exception
   */
  public void testIteration0303() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1.5+1.2"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 2.7, tmpResult);
  }

  /**
   * test of iteration 0.3
   * 
   * @throws Exception
   */
  public void testIteration0304() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("3+-2"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  // Iteration 0.4

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0401() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("("));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0402() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString(")"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0403() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("()"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0404() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("(1+1"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0405() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1+1)"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0406() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("()1-1"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0407() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("()+1"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0408() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("()+1+1"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0409() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1()"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0410() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("()1"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0411() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1()1+1)"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0412() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString(")(1+3"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0413() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("5-(3-4)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 6.0, tmpResult);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0414() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("5-(3)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0415() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("5+(3)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 8.0, tmpResult);
  }

  /**
   * test of iteration 0.4
   * 
   * @throws Exception
   */
  public void testIteration0416() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("1/(1-1)"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  // Iteration 0.5 and 0.6

  /**
   * test of iteration 0.5 and 0.6
   * 
   * @throws Exception
   */
  public void testIteration0501() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("x"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * test of iteration 0.5 and 0.6
   * 
   * @throws Exception
   */
  public void testIteration0502() throws Exception {

    boolean tmpErrorOccured = false;

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("x/0"));
      FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.5 and 0.6
   * 
   * @throws Exception
   */
  public void testIteration0503() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("x/x"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * test of iteration 0.5 and 0.6
   * 
   * @throws Exception
   */
  public void testIteration0504() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("d", new Double(2));
    tmpHashtable.put("i", new Double(2));
    tmpHashtable.put("r", new Double(2));
    tmpHashtable.put("t", new Double(2));
    tmpHashtable.put("y", new Double(2));
    tmpHashtable.put("o", new Double(2));
    tmpHashtable.put("u", new Double(2));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("2dirty4you"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 2048.0, tmpResult);
  }

  // Iteration 0.7

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0701() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sin"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0702() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sin()"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0703() throws Exception {

    boolean tmpErrorOccured = false;

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("s", new Double(2));
    tmpHashtable.put("i", new Double(2));
    tmpHashtable.put("n", new Double(2));

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sin)"));
      FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0704() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("c", new Double(2));
    tmpHashtable.put("o", new Double(2));
    tmpHashtable.put("s", new Double(2));
    tmpHashtable.put("i", new Double(2));
    tmpHashtable.put("n", new Double(2));
    tmpHashtable.put("u", new Double(45));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("cosin(us)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 4.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0705() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sin(90)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0706() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sin((-90))"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) -1.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0707() throws Exception {

    boolean tmpErrorOccured = false;
    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("^"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0708() throws Exception {

    boolean tmpErrorOccured = false;
    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("2^"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0709() throws Exception {

    boolean tmpErrorOccured = false;
    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("^2"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0710() throws Exception {

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("2^2"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 4.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0711() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("x^x"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 4.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0712() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(90));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sin(x^1)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0713() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("x^sin(90)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0714() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));

    Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sqrt(4)"));

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0715() throws Exception {

    boolean tmpErrorOccured = false;

    try {
      Tree tmpTree = FormulaTreeUtil.buildTree(ConverterUtil.formulaToStandardString("sqrt(-4)"));
      FormulaTreeUtil.evaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0716() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(-4));

    String tmpString = ConverterUtil.formulaToStandardString("sqrt(-x)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0717() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(-4));

    String tmpString = ConverterUtil.formulaToStandardString("-x");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 4.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   * 
   * @throws Exception
   */
  public void testIteration0718() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("-55");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) -55.0, tmpResult);
  }

  /**
   * positiv test
   * 
   * @throws Exception
   */
  public void testPositiv001() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("sin(55)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 0.81915204428899178968448838591684, tmpResult);
  }

  /**
   * positiv test
   * 
   * @throws Exception
   */
  public void testPositiv002() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("(2*5)+12*3-((3/4)*1,76)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 44.68, tmpResult);
  }

  /**
   * positiv test
   * 
   * @throws Exception
   */
  public void testPositiv003() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("sin((1.76/3)*(700/(7*5))+cos(78.5))");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 0.20676263068784143, tmpResult);
  }

  /**
   * positiv test
   * 
   * @throws Exception
   */
  public void testPositiv004() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("2^(sqrt(3*cos(89)*sin(3)*tan(9)))*1.187237432");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 1.2045056319101493, tmpResult);
  }

  /**
   * positiv test
   * 
   * @throws Exception
   */
  public void testPositiv005() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("(-5)*sqrt(12*(12/sin(90)))*2^(-(1/2))");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) -42.426406871192846, tmpResult);
  }

  /**
   * positiv test
   * 
   * @throws Exception
   */
  public void testPositiv006() throws Exception {

    String tmpString = ConverterUtil
        .formulaToStandardString("2*12/5+sin(12*(2*3+2^(sin(3.4*9.8+4711)+9)/2)*tan(57))");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 3.9884201073146137, tmpResult);
  }

  /**
   * positiv test
   * 
   * @throws Exception
   */
  public void testPositiv007() throws Exception {

    String tmpString = ConverterUtil
        .formulaToStandardString("2*12/6+4*8+10/2+98-43-67-981*3+2+7612634+87221-38263-19273+4846+16383+1635/5*3");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);
    assertEquals((double) 7661617, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * The three examples out of the "requirement"
   * 
   * @throws Exception
   */
  public void testRequirements() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));

    String tmpString = ConverterUtil.formulaToStandardString("(3x + 5) * 15");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 165.0, tmpResult);

    tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(-6));
    tmpHashtable.put("y", new Double(3));

    tmpString = ConverterUtil.formulaToStandardString("x^2 + 2*x*y + y^2");
    tmpTree = FormulaTreeUtil.buildTree(tmpString);

    tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 9.0, tmpResult);

    tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("c", new Double(-0.2));
    tmpHashtable.put("s", new Double(100));
    tmpHashtable.put("x", new Double(-1));

    tmpString = ConverterUtil.formulaToStandardString("c*cos(x) + s * sin(sqrt(2) - x)");
    tmpTree = FormulaTreeUtil.buildTree(tmpString);

    tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 40123.0, Math.floor(tmpResult * 10000));

  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString1() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(-99));
    tmpHashtable.put("b", new Double(99));

    String tmpString = ConverterUtil.formulaToStandardString("2a + a b");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) -9999.0, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString2() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("3 sin(4 + 5) - (-3)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 34693.0, Math.floor(tmpResult * 10000));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString4() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(1));
    tmpHashtable.put("b", new Double(2));
    tmpHashtable.put("c", new Double(3));
    tmpHashtable.put("o", new Double(4));
    tmpHashtable.put("s", new Double(5));

    String tmpString = ConverterUtil.formulaToStandardString("abcossin(cos(-3))");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 20914.0, Math.floor(tmpResult * 10000));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString7() throws Exception {
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(-31));

    String tmpString = ConverterUtil.formulaToStandardString("a");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) -31.0, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString8() throws Exception {
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(-1));
    tmpHashtable.put("b", new Double(2));

    String tmpString = ConverterUtil.formulaToStandardString("3+(-a+b)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 6.0, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString9() throws Exception {
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("s", new Double(-1));
    tmpHashtable.put("i", new Double(2));
    tmpHashtable.put("n", new Double(3));

    String tmpString = ConverterUtil.formulaToStandardString("sin4");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) -24.0, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString10() throws Exception {
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(500));

    String tmpString = ConverterUtil.formulaToStandardString("-a+45");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) -455.0, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString11() throws Exception {
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(5));
    tmpHashtable.put("y", new Double(-5));
    tmpHashtable.put("z", new Double(66));

    String tmpString = ConverterUtil.formulaToStandardString("5*x+y*z");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) -305.0, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString12() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("sin(3^cos(-4))");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 521.0, Math.floor(tmpResult * 10000));

  }

  /**
   * a new test :-)
   * 
   * @throws Exception
   */
  public void testFormulaToStandardString13() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("2*0,0000559");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 11180.0, Math.floor(tmpResult * 100000000));

  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#insertMultiplicationOperators(java.lang.String)}.
   * 
   * @throws Exception
   */
  public void testCleanVariables() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(5));
    tmpHashtable.put("b", new Double(-5.5));

    String tmpString = ConverterUtil.formulaToStandardString("2a+ab");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) -17.5, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkIfValidSignsOnly()}.
   */
  public void testCheckIfValidSignsOnly3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("�");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkIfEmptyBrackets()}.
   */
  public void testCheckIfEmptyBrackets() {
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("()");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#removeBlanks()}.
   * 
   * @throws Exception
   */
  public void testRemoveBlanks() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("2+  3+4 5 + 666");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 716.0, tmpResult);

  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#unifyCommas()}.
   */
  public void testUnifyCommas() {
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString(",,dfa34.,.,.");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   * 
   * @throws Exception
   */
  public void testCheckDecimalNumbers1() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("5,6+34+2.0+100.9");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 142.5, tmpResult);

  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers2() {
    // negative test
    boolean tmpErrorOccurred = false;

    try {
      ConverterUtil.formulaToStandardString("5.6.3+34+2.0+100.9");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString(".");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString(".999+7");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers5() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("999+7.");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers6() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("999+7.5+23.2+1.3.2");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers7() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("999+7.5+23.2+1.3.");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers8() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("999+7.5+23.2+3.");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers9() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString(".999+7.5+23.2+3.5");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#insertMultiplicationOperators()}.
   * 
   * @throws Exception
   */
  public void testInsertMultiplicationOperators() throws Exception {
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(1));
    tmpHashtable.put("x", new Double(-1));
    tmpHashtable.put("b", new Double(2));
    tmpHashtable.put("c", new Double(-2));
    tmpHashtable.put("d", new Double(3));
    tmpHashtable.put("e", new Double(-3));
    tmpHashtable.put("f", new Double(4));
    tmpHashtable.put("g", new Double(-4));
    tmpHashtable.put("h", new Double(5));
    tmpHashtable.put("s", new Double(-5));
    tmpHashtable.put("i", new Double(6));
    tmpHashtable.put("n", new Double(-6));
    tmpHashtable.put("o", new Double(7));

    String tmpString = ConverterUtil.formulaToStandardString("3+5.5xa4bc3def5gh+sin+cos6");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 951003.0, tmpResult);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   * 
   * @throws Exception
   */
  public void testCheckOperators1() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("5+6-98*(23^2)-98/8");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) -51843.25, tmpResult);

  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators2() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("+(23*55)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("23+-99+9*8");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("23*(+99)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators5() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("23*(2+99^)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators6() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("23*(2+99)**2");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators7() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("3+5*");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators8() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("+e");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators9() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("^2");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators10() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("2*(*2)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   * 
   * @throws Exception
   */
  public void testCheckOperators11() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("-(3+4)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) -7.0, tmpResult);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   * 
   * @throws Exception
   */
  public void testCheckOperators12() throws Exception {
    // positive test
    String tmpString = ConverterUtil.formulaToStandardString("-sin(3+4)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) -1219.0, Math.floor(tmpResult * 10000));
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   * 
   * @throws Exception
   */
  public void testCheckOperators13() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("7-(3+4)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 0.0, tmpResult);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   * 
   * @throws Exception
   */
  public void testCheckOperators14() throws Exception {
    // positive test
    String tmpString = ConverterUtil.formulaToStandardString("5-cos(3+4)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 40074.0, Math.floor(tmpResult * 10000));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#setBracketsAroundNegativeNumbers()}.
   * 
   * @throws Exception
   */
  public void testSetBracketsAroundNegativeNumbers1() throws Exception {

    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(1));
    tmpHashtable.put("b", new Double(-2));

    String tmpString = ConverterUtil
        .formulaToStandardString("-8+(-23.23434+(-34))*(-5)+(-23^3)+(-1*(-2*(-5)))+(-a+b)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) -11902.0, Math.floor(tmpResult));

  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#setBracketsAroundNegativeNumbers()}.
   */
  public void testSetBracketsAroundNegativeNumbers2() {
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("-8+(-23.23434+-34)*-5+(-23^3)+(-1*(-2*(-5)))))+(-a+b)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   * 
   * @throws Exception
   */
  public void testCheckNegativeNumbers1() throws Exception {
    String tmpString = ConverterUtil.formulaToStandardString("(-5)*(-2)+((-4)+5)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 11.0, tmpResult);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers2() {
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("-5)*(-2)+((-4)+5)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers3() {
    // negative test
    boolean tmpErrorOccurred = false;

    try {
      ConverterUtil.formulaToStandardString("(-5)*-2+(-4)+5)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("(-5)*(-2)+((-4)+-5)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   * 
   * @throws Exception
   */
  public void testCheckBrackets1() throws Exception {

    String tmpString = ConverterUtil.formulaToStandardString("(((5+5+5+5)))");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, null);

    assertEquals((double) 20.0, tmpResult);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets2() {

    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString(")(((5+5+5+5)))");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("(((5+5+5+5))))");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.formulaToStandardString("(((((5+5+5+5))))");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#changeFunctionsIntoSigns()}.
   * 
   * @throws Exception
   */
  public void testChangeFunctionsIntoSigns() throws Exception {
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("a", new Double(1));
    tmpHashtable.put("b", new Double(-2));
    tmpHashtable.put("c", new Double(-2));
    tmpHashtable.put("d", new Double(90));
    tmpHashtable.put("t", new Double(90));
    tmpHashtable.put("n", new Double(1));

    String tmpString = ConverterUtil.formulaToStandardString("sin(abc)+cos(abcd)+tan+tan(abc)+sqrt(23)");
    Tree tmpTree = FormulaTreeUtil.buildTree(tmpString);

    double tmpResult = FormulaTreeUtil.evaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 95935.0, Math.floor(tmpResult * 1000));

  }

  /**
   * Tests the elements of a painted tree
   * 
   * {@link calculator.elements.Tree#paintMeAsString()}
   * 
   * @throws Exception
   */
  public void testPaintTree() throws Exception {
    Tree tmpTree = null;

    try {
      String tmpString = ConverterUtil.formulaToStandardString("1.2+y-3^sin(2)*cos(2)/tan(2)-sqrt(1)");
      tmpTree = FormulaTreeUtil.buildTree(tmpString);
    } catch (Exception e) {
      assertFalse("There should not be an exception", true);
    }

    tmpTree.paintMe();
    String tmpString = tmpTree.paintMeAsString();
    assertTrue("Tree should contain '1.2'", tmpString.contains("1.2"));
    assertTrue("Tree should contain '3'", tmpString.contains("3"));
    assertTrue("Tree should contain 'y'", tmpString.contains("y"));
    assertTrue("Tree should contain 'sin'", tmpString.contains("sin"));
    assertTrue("Tree should contain 'cos'", tmpString.contains("cos"));
    assertTrue("Tree should contain 'tan'", tmpString.contains("tan"));
    assertTrue("Tree should contain 'sqrt'", tmpString.contains("sqrt"));
    assertTrue("Tree should contain 'pow'", tmpString.contains("pow"));
    assertTrue("Tree should contain '+'", tmpString.contains("+"));
    assertTrue("Tree should contain '-'", tmpString.contains("-"));
    assertTrue("Tree should contain '*'", tmpString.contains("*"));
    assertTrue("Tree should contain '/'", tmpString.contains("/"));
  }

  /**
   * Tests the elements of a non-painted tree
   * 
   * {@link calculator.elements.Tree#paintMeAsString()}
   * 
   * @throws Exception
   */
  public void testNoPaintTree() throws Exception {
    Tree tmpTree = null;

    try {
      String tmpString = ConverterUtil.formulaToStandardString("1.2+y-3^sin(2)*cos(2)/tan(2)-sqrt(1)+2+2+2");
      tmpTree = FormulaTreeUtil.buildTree(tmpString);
    } catch (Exception e) {
      assertFalse("There should not be an exception", true);
    }

    tmpTree.paintMe();
    String tmpString = tmpTree.paintMeAsString();
    assertTrue("Tree should contain 'Only trees'", tmpString.contains("Only trees"));
  }

  /**
   * Tests the elements of a non-painted tree
   * 
   * {@link calculator.elements.Tree#paintMeAsString()}
   * 
   * @throws Exception
   */
  public void testPaintEmptyTree() throws Exception {
    Tree tmpTree = new Tree(null);

    tmpTree.paintMe();
    String tmpString = tmpTree.paintMeAsString();
    assertTrue("Tree should contain 'Tree is empty'", tmpString.contains("Tree is empty"));
  }

  /**
   * Test method with negative sqrt
   */
  public void testNegativeSquareRoot() {

    boolean ok = false;

    String tmpString = null;
    try {
      tmpString = ConverterUtil.formulaToStandardString("(-2)^(1/2)");
      Tree tmpTree = null;
      try {
        tmpTree = FormulaTreeUtil.buildTree(tmpString);
        try {
          FormulaTreeUtil.evaluateTree(tmpTree, null);
        } catch (CalculatingException e) {
          ok = true;
        }
      } catch (CalculatingException e) {
        ok = false;
      }
    } catch (FormulaConversionException e) {
      ok = false;
    }

    assertTrue("True", ok);
  }

  /**
   * Test method with negative sqrt
   */
  public void testNegativeSquareRoot2() {

    boolean ok = false;

    String tmpString = null;
    try {
      tmpString = ConverterUtil.formulaToStandardString("sqrt(-5)");
      Tree tmpTree = null;
      try {
        tmpTree = FormulaTreeUtil.buildTree(tmpString);
        try {
          FormulaTreeUtil.evaluateTree(tmpTree, null);
        } catch (CalculatingException e) {
          ok = true;
        }
      } catch (CalculatingException e) {
        ok = false;
      }
    } catch (FormulaConversionException e) {
      ok = false;
    }

    assertTrue("True", ok);
  }

  /**
   * 
   */
  public void testNegativeMathUtil() {
    boolean ok = false;
    try {
      MathUtil.formulaToArrayList("2)(*/sin)");
    } catch (IllegalInputStreamException e) {
      ok = true;
    }

    assertTrue("True", ok);
  }

  /**
   * tests the start of the calculator
   */
  public void testStartCalculator() {
    String[] tmpArgs = new String[1];
    tmpArgs[0] = "calculator";
    try {
      Calculator.main(tmpArgs);
    } catch (IllegalArgumentException e) {
      assertEquals("Please start the Calculator with the parameter \"console\" or without any.", e
          .getMessage());
    }
  }

  // === End Iteration Tests ===
  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(IntegrationTest.class);
  }
}
