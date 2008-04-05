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
   * tests isFloat
   */
  public void testIsFloat() {

    assertTrue(MathUtil.isFloat("3.4"));
    assertTrue(MathUtil.isFloat("0.4"));
    assertFalse(MathUtil.isFloat("a.4"));
    assertTrue(MathUtil.isFloat("-3.4"));
    assertFalse(MathUtil.isFloat(".4"));
    assertFalse(MathUtil.isFloat("3."));
    assertFalse(MathUtil.isFloat("3..4"));
    assertFalse(MathUtil.isFloat("3.4.5"));
    assertFalse(MathUtil.isFloat("."));
    assertTrue(MathUtil.isFloat("4"));
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
