/**
 *
 */
package calculator.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import calculator.FormulaTree;
import calculator.elements.Tree;
import calculator.utils.ConverterUtil;

/**
 * @author Tobias
 *
 */
public class IntegrationTest extends TestCase {

  /**
   * tests the whole workflow
   * @throws Exception
   */
  public void testCalculator1() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("2 + 5 - (2*2.5)");
    Tree tmpTree = FormulaTree.BuildTree(tmpString);
    double tmpResult = FormulaTree.EvaluateTree(tmpTree, null);
    assertEquals((double) 2.0 , tmpResult);
  }

  /**
   * tests the whole workflow
   * @throws Exception
   */
  public void testCalculator2() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("1234567890");
    Tree tmpTree = FormulaTree.BuildTree(tmpString);
    double tmpResult = FormulaTree.EvaluateTree(tmpTree, null);
    assertEquals((double) 1234567890.0 , tmpResult);
  }

  /**
   * tests the whole workflow
   * @throws Exception
   */
  public void testCalculator3() throws Exception {
    String tmpString = ConverterUtil.termToStandardString("sqrt(4)^((5-3)-2)");
    Tree tmpTree = FormulaTree.BuildTree(tmpString);
    double tmpResult = FormulaTree.EvaluateTree(tmpTree, null);
    assertEquals((double) 1.0 , tmpResult);
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(IntegrationTest.class);
  }
}
