/**
 * 
 */
package pse3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import user.util.input.Converter;

/**
 * @author Tim
 *
 */
public class TestStringConvert {

  /**
   * Test method for {@link user.util.input.Converter#termToStandardString(java.lang.String)}.
   */
  @Test
  public void testTermToStandardString() {
    String tmpString = new String("2a + a b");
    Converter tmpConverter = new Converter();
    assertTrue("The String has Variables separated by *", tmpConverter.termToStandardString(tmpString).equals("2*a+a*b"));
  }

  /**
   * Test method for {@link user.util.input.Converter#removeBlanks(java.lang.String)}.
   */
  @Test
  public void testRemoveBlanks() {
    String tmpString = new String("2 + 4");
    Converter tmpConverter = new Converter();
    assertTrue("The String has Variables separated by *", tmpConverter.removeBlanks(tmpString).equals("2+4"));
  }

  /**
   * Test method for {@link user.util.input.Converter#cleanVariables(java.lang.String)}.
   */
  @Test
  public void testCleanVariables() {
    String tmpString = new String("2a+ab");
    Converter tmpConverter = new Converter();
    assertTrue("The String has Variables separated by *", tmpConverter.cleanVariables(tmpString).equals("2*a+a*b"));
  }

}
