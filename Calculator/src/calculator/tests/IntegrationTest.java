/**
 *
 */
package calculator.tests;

import java.util.Hashtable;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import calculator.elements.Tree;
import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;

/**
 * @author Tobias
 *
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
      Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString(""));
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
    } catch (Exception e) {
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("+1"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, null);

    assertEquals((double) 1.0, tmpResult);
  }

  /**
   * test of iterations 0.1 and 0.2
   *
   * @throws Exception
   */
  public void testIteration0104() throws Exception {

    boolean tmpErrorOccured = false;
    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("+1"));

    try {
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
    } catch (Exception e) {
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
    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("*1"));

    try {
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
    } catch (Exception e) {
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
    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("/1"));

    try {
      FormulaTreeUtil.EvaluateTree(tmpTree, null);
    } catch (Exception e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
    // TODO +1 sehe ich ja noch ein, aber /1 = 0.0?
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
    } catch (Exception e) {
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
    } catch (Exception e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
    // TODO @all Result ist "infinity" ... sollen wir das so lassen?? ->Raphi: Es wird nun eine Exception geworfen. Diese sollte dann ncoh von der konsole/fenster verarbeitet werden
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
      tmpErrorOccured = true;
    }
    assertTrue(tmpErrorOccured);
    // TODO @all Result ist "infinity" ... sollen wir das so lassen?? ->Raphi: Es wird nun eine Exception geworfen. Diese sollte dann ncoh von der konsole/fenster verarbeitet werden
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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

    Tree tmpTree = FormulaTreeUtil.BuildTree(ConverterUtil.termToStandardString("sqrt(-x)"));

    double tmpResult = FormulaTreeUtil.EvaluateTree(tmpTree, tmpHashtable);

    assertEquals((double) 2.0, tmpResult);
  }

  // === End Iteration Tests ===
  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(IntegrationTest.class);
  }
}
