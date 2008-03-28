/**
 *
 */
package calculator.tests;

import math.element.object.MathUtil;
import math.element.object.NumberObj;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.textui.TestRunner;

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
    String tmpFunction = "1,45";
    int tmpCharPos = 0;
    NumberObj tmpNumber = MathUtil.getNextNumber(tmpFunction, tmpCharPos);
    assertEquals((float) 1.45, tmpNumber.getValue());

    tmpFunction = "ThisIsATest243432,5ggdf";
    tmpCharPos = 12;
    tmpNumber = MathUtil.getNextNumber(tmpFunction, tmpCharPos);
    assertEquals((float) 43432.5, tmpNumber.getValue());

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
