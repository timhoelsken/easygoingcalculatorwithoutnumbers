/**
 *
 */
package calculator.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * @author Tobias
 *
 */
public class AllTests extends TestCase {

  /**
   * @return all test suites of package pse3.tests
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(MathUtilTest.class);
    suite.addTestSuite(StringConvertTest.class);
    suite.addTestSuite(IntegrationTest.class);
    return suite;
  }

  /**
   * Start the pse3.tests here.
   *
   * @param args
   */
  public static void main(String[] args) {
    TestRunner.run(AllTests.class);
  }
}