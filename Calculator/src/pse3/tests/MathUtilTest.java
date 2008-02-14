/**
 *
 */
package pse3.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import pse3.MathUtil;
import pse3.NumberObj;

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
   * tests getNextNumber()
   */
  public void testGetNextNumber() {
    String tmpFunction = "1,45ggdf";
    int tmpCharPos = 0;
    NumberObj tmpNumber = MathUtil.getNextNumber(tmpFunction, tmpCharPos);
    assertEquals((float) 1.45, tmpNumber.getValue());
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(MathUtilTest.class);
  }

  /**
   * Start the MathUtil here.
   *
   * @param args
   */
  public static void main(String[] args) {
    TestRunner.run(MathUtilTest.class);
  }
}
