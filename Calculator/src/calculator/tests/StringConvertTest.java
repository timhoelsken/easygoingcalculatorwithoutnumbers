/**
 *
 */
package calculator.tests;

import java.util.ArrayList;
import java.util.Hashtable;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import calculator.exceptions.FormulaConversionException;
import calculator.utils.ConverterUtil;

/**
 */
public class StringConvertTest extends TestCase {

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   * The three examples out of the "requirement"
   *
   * @throws Exception
   */
  public void testRequirements() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("(3x + 5) * 15");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "(3*x+5)*15".equals(tmpConvertedString));

    tmpErrorOccured = false;
    tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("x^2 + 2*x*y + y^2");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "x^2+2*x*y+y^2".equals(tmpConvertedString));

    tmpErrorOccured = false;
    tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("c*cos(x) + s * sin(sqrt(2) - x)");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "c*~(x)+s*%(&(2)-x)".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString1() throws Exception {

    String tmpString = "2a + a b";
    assertTrue("The String has Variables separated by *", ConverterUtil.formulaToStandardString(tmpString)
        .equals("2*a+a*b"));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString2() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("3 sin(4 + 5) - (-3)");
    } catch (IllegalArgumentException e) {
      tmpErrorOccured = true;
      e.printStackTrace();
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "3*%(4+5)-(-3)".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString4() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("abcossin(cos(-3))");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "a*b*c*o*s*%(~((-3)))".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString5() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      ConverterUtil.formulaToStandardString("a^cos((-3+4)^(2+1))*s df ghj+3-");
    } catch (FormulaConversionException e) {
      assertTrue("The formula ends with an operator.".equals(e.getMessage()));
      tmpErrorOccured = true;
    }
    assertTrue("The String is not correct", tmpErrorOccured);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString6() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      ConverterUtil.formulaToStandardString("-");
    } catch (FormulaConversionException e) {
      assertEquals("The formula ends with an operator.", e.getMessage());
      tmpErrorOccured = true;
    }
    assertTrue("The String is not correct", tmpErrorOccured);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString7() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      ConverterUtil.formulaToStandardString("a");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("The String is correct", !tmpErrorOccured);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString8() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("3+(-a+b)");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(!tmpErrorOccured);
    assertEquals("3+((-a)+b)", tmpConvertedString);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString9() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("sin4");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("The String is correct", !tmpErrorOccured && "s*i*n*4".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString10() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("-a+45");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("The String is correct", !tmpErrorOccured && "(-a)+45".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString11() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("5*x+y*z");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is correct", !tmpErrorOccured && "5*x+y*z".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#formulaToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testFormulaToStandardString12() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("sin(3^cos(-4))");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is correct", !tmpErrorOccured && "%(3^~((-4)))".equals(tmpConvertedString));
  }

  /**
   * a new test :-)
   *
   * @throws Exception
   */
  public void testFormulaToStandardString13() throws Exception {

    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.formulaToStandardString("2*0,0000559");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue(!tmpErrorOccured);
    assertEquals("2*0.0000559", tmpConvertedString);

  }

  /**
   * another new test :-)
   *
   * @throws Exception
   */
  public void testFormulaToStandardString14() throws Exception {
    // positive test
    String tmpString = null;
    try {
      tmpString = ConverterUtil.formulaToStandardString("sin(abc)+cos(abcd)+tan+tan(abc)+sqrt(23)");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
    assertEquals("%(a*b*c)+~(a*b*c*d)+t*a*n+#(a*b*c)+&(23)", tmpString);
  }

  /**
   * another new test :-)
   *
   * @throws Exception
   */
  public void testFormulaToStandardString15() throws Exception {
    // negative test
    boolean tmpErrorOccured = false;
    try {
      ConverterUtil.formulaToStandardString("4,7.2");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("Two commas in one number.", tmpErrorOccured);
  }

  /**
   * another new test ...
   *
   * @throws Exception
   */
  public void testFormulaToStandardString16() throws Exception {
    // negative test
    boolean tmpErrorOccured = false;
    try {
      ConverterUtil.formulaToStandardString("2+32.a");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("Wrong comma.", tmpErrorOccured);
  }

  /**
   * @throws Exception
   */
  public void testFormulaToGUIStandardString14() throws Exception {
    // positive test
    String tmpString = null;
    tmpString = ConverterUtil.formulaToGUIStandardString("Ya* aA+ B   - x");
    assertEquals("ya*aa+b-x", tmpString);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#insertMultiplicationOperators(java.lang.String)}.
   */
  public void testCleanVariables() {
    String tmpString = "2a+ab";
    assertTrue("The String has Variables separated by *", ConverterUtil.insertMultiplicationOperators(
        tmpString).equals("2*a+a*b"));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkIfValidSignsOnly()}.
   */
  public void testCheckIfValidSignsOnly1() {
    // positive test
    try {
      ConverterUtil
          .checkIfValidSignsOnly(".,abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-*/+^()");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkIfValidSignsOnly()}.
   */
  public void testCheckIfValidSignsOnly2() {
    // negative test
    Boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkIfValidSignsOnly("%");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkIfValidSignsOnly()}.
   */
  public void testCheckIfValidSignsOnly3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkIfValidSignsOnly("ä");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkIfEmptyBrackets()}.
   */
  public void testCheckIfEmptyBrackets() {
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkIfEmptyBrackets("()");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#removeBlanks()}.
   */
  public void testRemoveBlanks() {
    assertTrue(ConverterUtil.removeBlanks("2+  3+4 5 + 666").equalsIgnoreCase("2+3+45+666"));
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#unifyCommas()}.
   */
  public void testUnifyCommas() {
    assertTrue(ConverterUtil.unifyCommas(",,dfa34.,.,.").equalsIgnoreCase("..dfa34....."));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers1() {
    // positive test
    try {
      ConverterUtil.checkDecimalNumbers("5.6+34+2.0+100.9");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers2() {
    // negative test
    boolean tmpErrorOccurred = false;

    try {
      ConverterUtil.checkDecimalNumbers("5.6.3+34+2.0+100.9");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers(".");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers(".999+7");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers5() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers("999+7.");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers6() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers("7.a");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers8() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers("3.+2");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkDecimalNumbers()}.
   */
  public void testCheckDecimalNumbers9() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers("a.2");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#insertMultiplicationOperators()}.
   */
  public void testInsertMultiplicationOperators() {
    assertTrue(ConverterUtil.insertMultiplicationOperators("3+5.5xa4bc3def5gh+sin+cos6").equals(
        "3+5.5*x*a*4*b*c*3*d*e*f*5*g*h+s*i*n+c*o*s*6"));
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators1() {
    // positive test
    try {
      ConverterUtil.checkOperators("5+6-98*(23^2)-98/8");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators2() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("+(23*55)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23+-99+9*8");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23*(+99)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators5() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23*(2+99^)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators6() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23*(2+99)**2");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators7() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("3+5*");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators8() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("+e");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators9() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("^2");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators10() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("2*(*2)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators11() {
    // positive test
    try {
      ConverterUtil.checkOperators("-(3+4)");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators12() {
    // positive test
    try {
      ConverterUtil.checkOperators("-%(3+4)");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators13() {
    // positive test
    try {
      ConverterUtil.checkOperators("(((-12122.2))*2321*%(12))");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators14() {
    // positive test
    try {
      ConverterUtil.checkOperators("x/z*%(12)");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkOperators()}.
   */
  public void testCheckOperators15() {
    // positive test
    try {
      ConverterUtil.checkOperators("%(abc)+~(abcd)+tan+#(abc)+&(23)");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#setBracketsAroundNegativeNumbers()}.
   */
  public void testSetBracketsAroundNegativeNumbers1() {

    String tmpString = ConverterUtil
        .setBracketsAroundNegatives("-8+(-23.23434+(-34))*(-5)+(-23^3)+(-1*(-2*(-5)))))+(-a+b)");
    assertTrue(tmpString.equals("(-8)+((-23.23434)+(-34))*(-5)+((-23)^3)+((-1)*((-2)*(-5)))))+((-a)+b)"));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#setBracketsAroundNegativeNumbers()}.
   */
  public void testSetBracketsAroundNegativeNumbers2() {
    String tmpString = ConverterUtil
        .setBracketsAroundNegatives("-8+(-23.23434+-34)*-5+(-23^3)+(-1*(-2*(-5)))))+(-a+b)");
    assertTrue(tmpString.equals("(-8)+((-23.23434)+-34)*-5+((-23)^3)+((-1)*((-2)*(-5)))))+((-a)+b)"));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers1() {
    // positive test
    try {
      ConverterUtil.checkNegativeNumbers("(-5)*(-2)+((-4)+5)");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers2() {
    // positive test
    try {
      String tmp = ConverterUtil.setBracketsAroundNegatives("-5)*(-2)+((-4)+5)");

      ConverterUtil.checkNegativeNumbers(tmp);
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers3() {
    // negative test
    boolean tmpErrorOccurred = false;

    try {
      ConverterUtil.checkNegativeNumbers("(-5)*-2+(-4)+5)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkNegativeNumbers("(-5)*(-2)+((-4)+-5)");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets1() {
    try {
      ConverterUtil.checkBrackets("(((5+5+5+5)))");
    } catch (FormulaConversionException e) {
      assertTrue(false);
    }
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets2() {

    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkBrackets(")(((5+5+5+5)))");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets3() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkBrackets("(((5+5+5+5))))");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets4() {
    // negative test
    boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkBrackets("(((((5+5+5+5))))");
    } catch (FormulaConversionException e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#changeFunctionsIntoSigns()}.
   */
  public void testChangeFunctionsIntoSigns() {
    assertTrue(ConverterUtil.changeFunctionsIntoSigns("sin(abc)+cos(abcd)+tan+tan(abc)+sqrt(23)").equals(
        "%(abc)+~(abcd)+tan+#(abc)+&(23)"));
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#hasVariables()}.
   */
  public void testHasVariables() {
    assertTrue("a is a variable", ConverterUtil.hasVariables("a"));
    assertTrue("z is a variable", ConverterUtil.hasVariables("z"));
    assertFalse("There are no variables", ConverterUtil.hasVariables("1+2"));
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#getVariables()}.
   */
  public void testGetVariables() {
    ArrayList<String[]> tmpVariables = ConverterUtil.getVariables("3*a+2n-x");
    assertEquals(3, tmpVariables.size());
    for (String[] tmpStrings : tmpVariables) {
      String tmpVariable = tmpStrings[0];
      assertTrue("Should be a, n or x", "a".equals(tmpVariable) || "n".equals(tmpVariable) || "x".equals(tmpVariable) );
    }
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#centerStringInSpaces()}.
   */
  public void testCenterStringInSpaces() {
    assertEquals(" a ", ConverterUtil.centerStringInSpaces("   ", "a"));
    assertEquals("ab ", ConverterUtil.centerStringInSpaces("   ", "ab"));
    assertEquals("abc", ConverterUtil.centerStringInSpaces("   ", "abc"));
    assertEquals("abc", ConverterUtil.centerStringInSpaces("  ", "abc"));
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#isInList()}.
   */
  public void testIsInList(){
    ArrayList<String[]> tmpArrayListOne = new ArrayList<String[]>();
    ArrayList<String[]> tmpArrayListTwo = new ArrayList<String[]>();
    String[] tmpVariableArrayOne = new String[2];
    String[] tmpVariableArrayTwo = new String[2];
    String[] tmpVariableArrayThree = new String[2];
    String[] tmpVariableArrayEmpty = new String[2];
    tmpVariableArrayOne[0] = "a";
    tmpVariableArrayOne[1] = "0";
    tmpArrayListOne.add(tmpVariableArrayOne);
    tmpVariableArrayTwo[0] = "b";
    tmpVariableArrayTwo[1] = "1";
    tmpArrayListOne.add(tmpVariableArrayTwo);
    tmpVariableArrayThree[0] = "c";
    tmpVariableArrayThree[1] = "1";
    tmpArrayListOne.add(tmpVariableArrayThree);
    tmpArrayListTwo.add(tmpVariableArrayEmpty);
    
    assertTrue("Variable not in list.", ConverterUtil.isInList(tmpArrayListOne, "a"));
    assertFalse("Variable in list.", ConverterUtil.isInList(tmpArrayListOne, "d"));
    assertFalse("Variable in list", ConverterUtil.isInList(tmpArrayListTwo, "a"));
  }
  
  /**
   * 
   */
  public void testPutArrayListIntoHashtable(){
    ArrayList<String[]> tmpArrayList = new ArrayList<String[]>();
    String[] tmpVariableArrayOne = new String[2];
    String[] tmpVariableArrayTwo = new String[2];
    tmpVariableArrayOne[0] = "a";
    tmpVariableArrayOne[1] = "0.1";
    tmpVariableArrayTwo[0] = "b";
    tmpVariableArrayTwo[1] = "1.5";
    tmpArrayList.add(tmpVariableArrayOne);
    tmpArrayList.add(tmpVariableArrayTwo);
    Hashtable<String, Double> tmpVariableDictionary = ConverterUtil.putArrayListIntoHashtable(tmpArrayList);
    
    assertEquals(2, tmpVariableDictionary.size());
    assertEquals(0.1, tmpVariableDictionary.get("a"));
    assertEquals(1.5, tmpVariableDictionary.get("b"));
    assertTrue("Variable in Dictionary", null ==tmpVariableDictionary.get("c"));
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(StringConvertTest.class);
  }
}
