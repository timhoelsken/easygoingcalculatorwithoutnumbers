/**
 *
 */
package calculator.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import calculator.utils.MathUtil;

/**
 * @author Tobias
 *
 */
public class MathUtilTest extends TestCase {

  /**
   * All things that should happen before each test in this class.
   */
  protected void setUp() {
    // nothing yet
  }

  /**
   * All things that should happen after each test in this class.
   */
  protected void tearDown() {
    // nothing yet
  }

  /**
   * test method for {@link calculator.utils.MathUtil#isNumber(char)}
   */
  public void testIsNumber() {
    assertTrue(MathUtil.isNumber('0'));
    assertTrue(MathUtil.isNumber('9'));
    assertFalse(MathUtil.isNumber('x'));
  }

  /**
   * test method for {@link calculator.utils.MathUtil#isDouble(java.lang.String)}
   */
  public void testIsFloat() {
    assertTrue(MathUtil.isDouble("3.4"));
    assertTrue(MathUtil.isDouble("0.4"));
    assertFalse(MathUtil.isDouble("a.4"));
    assertTrue(MathUtil.isDouble("-3.4"));
    assertFalse(MathUtil.isDouble(".4"));
    assertFalse(MathUtil.isDouble("3."));
    assertFalse(MathUtil.isDouble("3..4"));
    assertFalse(MathUtil.isDouble("3.4.5"));
    assertFalse(MathUtil.isDouble("."));
    assertTrue(MathUtil.isDouble("4"));
  }

  /**
   * test method for {@link calculator.utils.MathUtil#isFunction(char)}
   */
  public void testIsFunction() {
    assertTrue(MathUtil.isFunction('%'));
    assertTrue(MathUtil.isFunction('~'));
    assertTrue(MathUtil.isFunction('#'));
    assertTrue(MathUtil.isFunction('&'));
    assertFalse(MathUtil.isFunction('§'));
  }

  /**
   * test method for {@link calculator.utils.MathUtil#isVariable(char)}
   */
  public void testIsVariable() {
    assertTrue(MathUtil.isVariable('a'));
    assertTrue(MathUtil.isVariable('b'));
    assertTrue(MathUtil.isVariable('z'));
    assertTrue(MathUtil.isVariable('A'));
    assertTrue(MathUtil.isVariable('B'));
    assertTrue(MathUtil.isVariable('Z'));
    assertFalse(MathUtil.isVariable('ß'));
  }

  /**
   * test method for {@link calculator.utils.MathUtil#isNumberOrVariable(char)}
   */
  public void testIsNumberOrVariable() {
    assertTrue(MathUtil.isNumberOrVariable('5'));
    assertTrue(MathUtil.isNumberOrVariable('g'));
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(MathUtilTest.class);
  }

  /**
   * Start the MathUtilTest here.
   *
   * @param args
   */
  public static void main(String[] args) {
    TestRunner.run(MathUtilTest.class);
  }
}
