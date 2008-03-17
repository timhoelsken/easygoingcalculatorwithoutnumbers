/**
 *
 */
package pse3.tests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import user.util.input.Converter;

/**
 * @author Tim
 *
 */
public class StringConvertTest extends TestCase {

  /**
   * Test method for {@link user.util.input.Converter#termToStandardString(java.lang.String)}.
   */
  public void testTermToStandardString() {
    String tmpString = new String("2a + a b");
    Converter tmpConverter = new Converter();
    assertTrue("The String has Variables separated by *", tmpConverter.termToStandardString(tmpString).equals("2*a+a*b"));
  }

  /**
   * Test method for {@link user.util.input.Converter#removeBlanks(java.lang.String)}.
   */
  public void testRemoveBlanks() {
    String tmpString = new String("2 + 4");
    Converter tmpConverter = new Converter();
    assertTrue(tmpConverter.removeBlanks(tmpString).equals("2+4"));
  }

  /**
   * Test method for {@link user.util.input.Converter#cleanVariables(java.lang.String)}.
   */
  public void testCleanVariables() {
    String tmpString = new String("2a+ab");
    Converter tmpConverter = new Converter();
    assertTrue("The String has Variables separated by *", tmpConverter.cleanVariables(tmpString).equals("2*a+a*b"));
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(StringConvertTest.class);
  }
}
