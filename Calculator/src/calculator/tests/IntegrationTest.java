/**
 *
 */
package calculator.tests;

import java.util.Hashtable;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import calculator.elements.Tree;
import calculator.exceptions.CalculatingException;
import calculator.exceptions.FormulaConversionException;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;

/**
 */
public class IntegrationTest extends TestCase {

  /**
   * tests the whole workflow
   *
   * @throws Exception
   */
  public void testCalculator1() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("2 + 5 - (2*2.5)");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * tests the whole workflow
   *
   * @throws Exception
   */
  public void testCalculator2() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("1234567890");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
    assertEquals((double) 1234567890.0, tmpResult);
  }

  /**
   * tests the whole workflow
   *
   * @throws Exception
   */
  public void testCalculator3() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("sqrt(4)^((5-3)-2)");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * tests the whole workflow with variables
   *
   * @throws Exception
   */
  public void testCalculator4() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("x^2+5-y");
    Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
    tmpHashtable.put("x", new Double(2));
    tmpHashtable.put("y", new Double(4));
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);
    assertEquals((double) 5.0, tmpResult);
  }

  /**
   * tests priority of operator ^
   *
   * @throws Exception
   */
  public void testCalculator5() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("2^sin(90)");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * tests priority of operator ^
   *
   * @throws Exception
   */
  public void testCalculator6() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("-2^sin(90)^2");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      String tmpString = ConverterUtil.termToStandardString("sqrt(-1)");
      Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
    } catch (CalculatingException e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
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
      String tmpString = ConverterUtil.termToStandardString("");
      Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

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
      ConverterUtil.termToStandardString("+1");
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
      ConverterUtil.termToStandardString("*1");
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
      ConverterUtil.termToStandardString("/1");
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
      ConverterUtil.termToStandardString("^1");
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1+"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
    } catch (FormulaConversionException e) {
    	//TODO what kind of exception?
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1+1"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("2/0"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1+1*2"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

    assertEquals((double) 3.0, tmpResult);
  }




  /**
   * test of iteration 0.3
   *
   * @throws Exception
   */
  public void testIteration0302() throws Exception {

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1/1-1"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

    assertEquals((double) 0.0, tmpResult);
  }

  /**
   * test of iteration 0.3
   *
   * @throws Exception
   */
  public void testIteration0303() throws Exception {

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1.5+1.2"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("3+-2"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("("));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString(")"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("()"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("(1+1"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1+1)"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("()1-1"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("()+1"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("()+1+1"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1()"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("()1"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1()1+1)"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString(")(1+3"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("5-(3-4)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

    assertEquals((double) 6.0, tmpResult);
  }

  /**
   * test of iteration 0.4
   *
   * @throws Exception
   */
  public void testIteration0414() throws Exception {

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("5-(3)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

    assertEquals((double) 2.0, tmpResult);
  }

  /**
   * test of iteration 0.4
   *
   * @throws Exception
   */
  public void testIteration0415() throws Exception {

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("5+(3)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("1/(1-1)"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("x"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("x/0"));
      FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("x/x"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("2dirty4you"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sin"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
    //TODO @Raphi Test auf nullpointerexception wegen fehlender variablenwerte so gewollt?
    } catch (NullPointerException e) {
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sin()"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sin)"));
      FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("cosin(us)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 4.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   *
   * @throws Exception
   */
  public void testIteration0705() throws Exception {

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sin(90)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   *
   * @throws Exception
   */
  public void testIteration0706() throws Exception {

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sin((-90))"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("^"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("2^"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("^2"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("2^2"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("x^x"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sin(x^1)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("x^sin(90)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sqrt(4)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sqrt(-4)"));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
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

    String tmpString = ConverterUtil.termToStandardString("sqrt(-x)");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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

    String tmpString = ConverterUtil.termToStandardString("-x");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 4.0, tmpResult);
  }

  /**
   * test of iteration 0.7
   *
   * @throws Exception
   */
  public void testIteration0718() throws Exception {

    String tmpString = ConverterUtil.termToStandardString("-55");
    Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

    assertEquals((double) -55.0, tmpResult);
  }

 /**
  * positiv test
  *
  * @author André
  * @throws Exception
  */
 public void testPositiv001() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("sin(55)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
   assertEquals((double) 0.81915204428899178968448838591684, tmpResult);
 }

 /**
  * positiv test
  *
  * @author André
  * @throws Exception
  */
 public void testPositiv002() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("(2*5)+12*3-((3/4)*1,76)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
   assertEquals((double) 44.68, tmpResult);
 }

 /**
  * positiv test
  *
  * @author André
  * @throws Exception
  */
 public void testPositiv003() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("sin((1.76/3)*(700/(7*5))+cos(78.5))");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
   assertEquals((double)  0.20676263068784143, tmpResult);
 }



 /**
  * positiv test
  *
  * @author André
  * @throws Exception
  */
 public void testPositiv004() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("2^(sqrt(3*cos(89)*sin(3)*tan(9)))*1.187237432");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
   assertEquals((double)  1.2045056319101493, tmpResult);
 }

 /**
  * positiv test
  *
  * @author André
  * @throws Exception
  */
 public void testPositiv005() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("(-5)*sqrt(12*(12/sin(90)))*2^(-(1/2))");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
   assertEquals((double)  -42.426406871192846, tmpResult);
 }

 /**
  * positiv test
  *
  * @author André
  * @throws Exception
  */
 public void testPositiv006() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("2*12/5+sin(12*(2*3+2^(sin(3.4*9.8+4711)+9)/2)*tan(57))");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
   assertEquals((double)  3.9884201073146137, tmpResult);
 }

 /**
  * positiv test
  *
  * @author André
  * @throws Exception
  */
 public void testPositiv007() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("2*12/6+4*8+10/2+98-43-67-981*3+2+7612634+87221-38263-19273+4846+16383+1635/5*3");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);
   assertEquals((double)  7661617, tmpResult);
 }


 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  * The three examples out of the "requirement"
  *
  * @throws Exception
  */
 public void testRequirements() throws Exception {

   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("x", new Double(2));

   String tmpString = ConverterUtil.termToStandardString("(3x + 5) * 15");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) 165.0, tmpResult);

   tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("x", new Double(-6));
   tmpHashtable.put("y", new Double(3));

   tmpString = ConverterUtil.termToStandardString("x^2 + 2*x*y + y^2");
   tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) 9.0, tmpResult);


   tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("c", new Double(-0.2));
   tmpHashtable.put("s", new Double(100));
   tmpHashtable.put("x", new Double(-1));

   tmpString = ConverterUtil.termToStandardString("c*cos(x) + s * sin(sqrt(2) - x)");
   tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) 40123.0, Math.floor(tmpResult*10000));

 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString1() throws Exception {

   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("a", new Double(-99));
   tmpHashtable.put("b", new Double(99));

   String tmpString = ConverterUtil.termToStandardString("2a + a b");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) -9999.0, tmpResult);
  }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString2() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("3 sin(4 + 5) - (-3)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) 34693.0, Math.floor(tmpResult*10000));
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString4() throws Exception {

   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("a", new Double(1));
   tmpHashtable.put("b", new Double(2));
   tmpHashtable.put("c", new Double(3));
   tmpHashtable.put("o", new Double(4));
   tmpHashtable.put("s", new Double(5));

   String tmpString = ConverterUtil.termToStandardString("abcossin(cos(-3))");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) 20914.0, Math.floor(tmpResult*10000));
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString7() throws Exception {
   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("a", new Double(-31));

   String tmpString = ConverterUtil.termToStandardString("a");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) -31.0, tmpResult);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString8() throws Exception {
   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("a", new Double(-1));
   tmpHashtable.put("b", new Double(2));

   String tmpString = ConverterUtil.termToStandardString("3+(-a+b)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) 6.0, tmpResult);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString9() throws Exception {
   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("s", new Double(-1));
   tmpHashtable.put("i", new Double(2));
   tmpHashtable.put("n", new Double(3));

   String tmpString = ConverterUtil.termToStandardString("sin4");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) -24.0, tmpResult);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString10() throws Exception {
   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("a", new Double(500));

   String tmpString = ConverterUtil.termToStandardString("-a+45");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) -455.0, tmpResult);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString11() throws Exception {
   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("x", new Double(5));
   tmpHashtable.put("y", new Double(-5));
   tmpHashtable.put("z", new Double(66));

   String tmpString = ConverterUtil.termToStandardString("5*x+y*z");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) -305.0, tmpResult);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
  *
  * @throws Exception
  */
 public void testTermToStandardString12() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("sin(3^cos(-4))");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) 521.0, Math.floor(tmpResult*10000));

 }

 /**
  * a new test :-)
  * @throws Exception
  */
 public void testTermToStandardString13() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("2*0,0000559");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) 11180.0, Math.floor(tmpResult*100000000));

}

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#insertMultiplicationOperators(java.lang.String)}.
  * @throws Exception
  */
 public void testCleanVariables() throws Exception {

   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("a", new Double(5));
   tmpHashtable.put("b", new Double(-5.5));

   String tmpString = ConverterUtil.termToStandardString("2a+ab");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

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
     ConverterUtil.termToStandardString("ä");
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
     ConverterUtil.termToStandardString("()");
   } catch (FormulaConversionException e) {
     tmpErrorOccurred = true;
   }
   assertTrue(tmpErrorOccurred);
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#removeBlanks()}.
  * @throws Exception
  */
 public void testRemoveBlanks() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("2+  3+4 5 + 666");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double)716.0, tmpResult);

 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#unifyCommas()}.
  */
 public void testUnifyCommas() {
   boolean tmpErrorOccurred = false;
   try {
     ConverterUtil.termToStandardString(",,dfa34.,.,.");
   } catch (FormulaConversionException e) {
     tmpErrorOccurred = true;
   }
   assertTrue(tmpErrorOccurred);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
  * @throws Exception
  */
 public void testCheckDecimalNumbers1() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("5,6+34+2.0+100.9");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double)142.5, tmpResult);

 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
  */
 public void testCheckDecimalNumbers2() {
   // negative test
   boolean tmpErrorOccurred = false;

   try {
     ConverterUtil.termToStandardString("5.6.3+34+2.0+100.9");
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
     ConverterUtil.termToStandardString(".");
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
     ConverterUtil.termToStandardString(".999+7");
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
     ConverterUtil.termToStandardString("999+7.");
   } catch (FormulaConversionException e) {
     tmpErrorOccurred = true;
   }
   assertTrue(tmpErrorOccurred);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#insertMultiplicationOperators()}.
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

   String tmpString = ConverterUtil.termToStandardString("3+5.5xa4bc3def5gh+sin+cos6");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) 951003.0, tmpResult);
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
  * @throws Exception
  */
 public void testCheckOperators1() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("5+6-98*(23^2)-98/8");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) -51843.25, tmpResult);

 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
  */
 public void testCheckOperators2() {
   // negative test
   boolean tmpErrorOccurred = false;
   try {
     ConverterUtil.termToStandardString("+(23*55)");
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
     ConverterUtil.termToStandardString("23+-99+9*8");
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
     ConverterUtil.termToStandardString("23*(+99)");
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
     ConverterUtil.termToStandardString("23*(2+99^)");
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
     ConverterUtil.termToStandardString("23*(2+99)**2");
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
     ConverterUtil.termToStandardString("3+5*");
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
     ConverterUtil.termToStandardString("+e");
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
     ConverterUtil.termToStandardString("^2");
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
     ConverterUtil.termToStandardString("2*(*2)");
   } catch (FormulaConversionException e) {
     tmpErrorOccurred = true;
   }
   assertTrue(tmpErrorOccurred);
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
  * @throws Exception
  */
 public void testCheckOperators11() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("-(3+4)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) -7.0, tmpResult);
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
  * @throws Exception
  */
 public void testCheckOperators12() throws Exception {
   // positive test
   String tmpString = ConverterUtil.termToStandardString("-sin(3+4)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) -1219.0, Math.floor(tmpResult*10000));
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
  * @throws Exception
  */
 public void testCheckOperators13() throws Exception {

   String tmpString = ConverterUtil.termToStandardString("7-(3+4)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) 0.0, tmpResult);
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
  * @throws Exception
  */
 public void testCheckOperators14() throws Exception {
   // positive test
   String tmpString = ConverterUtil.termToStandardString("5-cos(3+4)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) 40074.0, Math.floor(tmpResult*10000));
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#setBracketsAroundNegativeNumbers()}.
  * @throws Exception
  */
 public void testSetBracketsAroundNegativeNumbers1() throws Exception {

   Hashtable<String, Double> tmpHashtable = new Hashtable<String, Double>();
   tmpHashtable.put("a", new Double(1));
   tmpHashtable.put("b", new Double(-2));

   String tmpString = ConverterUtil.termToStandardString("-8+(-23.23434+(-34))*(-5)+(-23^3)+(-1*(-2*(-5)))+(-a+b)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) -11902.0, Math.floor(tmpResult));


 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#setBracketsAroundNegativeNumbers()}.
  */
 public void testSetBracketsAroundNegativeNumbers2() {
   boolean tmpErrorOccurred = false;
   try {
     ConverterUtil.termToStandardString("-8+(-23.23434+-34)*-5+(-23^3)+(-1*(-2*(-5)))))+(-a+b)");
   } catch (FormulaConversionException e) {
     tmpErrorOccurred = true;
   }
   assertTrue(tmpErrorOccurred);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
  * @throws Exception
  */
 public void testCheckNegativeNumbers1() throws Exception {
   String tmpString = ConverterUtil.termToStandardString("(-5)*(-2)+((-4)+5)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

   assertEquals((double) 11.0, tmpResult);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
  */
 public void testCheckNegativeNumbers2() {
   boolean tmpErrorOccurred = false;
   try {
     ConverterUtil.termToStandardString("-5)*(-2)+((-4)+5)");
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
     ConverterUtil.termToStandardString("(-5)*-2+(-4)+5)");
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
     ConverterUtil.termToStandardString("(-5)*(-2)+((-4)+-5)");
   } catch (FormulaConversionException e) {
     tmpErrorOccurred = true;
   }
   assertTrue(tmpErrorOccurred);
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
  * @throws Exception
  */
 public void testCheckBrackets1() throws Exception {

     String tmpString = ConverterUtil.termToStandardString("(((5+5+5+5)))");
     Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

     double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

     assertEquals((double) 20.0, tmpResult);
 }

 /**
  * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
  */
 public void testCheckBrackets2() {

   // negative test
   boolean tmpErrorOccurred = false;
   try {
     ConverterUtil.termToStandardString(")(((5+5+5+5)))");
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
     ConverterUtil.termToStandardString("(((5+5+5+5))))");
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
     ConverterUtil.termToStandardString("(((((5+5+5+5))))");
   } catch (FormulaConversionException e) {
     tmpErrorOccurred = true;
   }
   assertTrue(tmpErrorOccurred);
 }

 /**
  * Test method for
  * {@link calculator.utils.ConverterUtil#changeFunctionsIntoSigns()}.
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

   String tmpString = ConverterUtil.termToStandardString("sin(abc)+cos(abcd)+tan+tan(abc)+sqrt(23)");
   Tree tmpTree = FormulaTreeUtil.BuildTree(tmpString);

   double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

   assertEquals((double) 95935.0, Math.floor(tmpResult*1000));

 }


  // === End Iteration Tests ===
  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(IntegrationTest.class);
  }
}
