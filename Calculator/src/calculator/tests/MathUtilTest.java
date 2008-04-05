/**
 *
 */
package calculator.tests;

import calculator.elements.NumberObj;
import calculator.utils.MathUtil;
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
    //TODO @ Andre? Test doof... liefert 1.0 zurück anstatt 1,45
    NumberObj tmpNumber = MathUtil.getNextNumber(tmpFunction, tmpCharPos);
    assertEquals((float) 1.45, tmpNumber.getValue());

    tmpFunction = "ThisIsATest243432,5ggdf";
    tmpCharPos = 12;
    tmpNumber = MathUtil.getNextNumber(tmpFunction, tmpCharPos);
    assertEquals((float) 43432.5, tmpNumber.getValue());

  }
  
  /**
   * tests isFloat
   */
  public void testIsFloat(){
    
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
