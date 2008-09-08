/**
 *
 */
package sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Tobe
 *
 */
public class FieldTest {

  /**
   *
   */
  @Test
  public void testSetter(){
    Field tmpField = new Field();
    assertTrue(tmpField.setValue(1));
  }
}
