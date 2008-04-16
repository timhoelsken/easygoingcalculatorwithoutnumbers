/**
 *
 */
package calculator.tests;

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
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   * The three examples out of the "requirement"
   *
   * @throws Exception
   */
  public void testRequirements() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("(3x + 5) * 15");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "(3*x+5)*15".equals(tmpConvertedString));

    tmpErrorOccured = false;
    tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("x^2 + 2*x*y + y^2");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "x^2+2*x*y+y^2".equals(tmpConvertedString));

    tmpErrorOccured = false;
    tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("c*cos(x) + s * sin(sqrt(2) - x)");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "c*~(x)+s*%(&(2)-x)".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString1() throws Exception {

    String tmpString = new String("2a + a b");
    assertTrue("The String has Variables separated by *", ConverterUtil.termToStandardString(tmpString)
        .equals("2*a+a*b"));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString2() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("3 sin(4 + 5) - (-3)");
    } catch (IllegalArgumentException e) {
      tmpErrorOccured = true;
      e.printStackTrace();
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "3*%(4+5)-(-3)".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString4() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("abcossin(cos(-3))");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "a*b*c*o*s*%(~((-3)))".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString5() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      ConverterUtil.termToStandardString("a^cos((-3+4)^(2+1))*s df ghj+3-");
    } catch (FormulaConversionException e) {
      assertTrue("The formula ends with an operator.".equals(e.getMessage()));
      tmpErrorOccured = true;
    }
    assertTrue("The String is not correct", tmpErrorOccured);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString6() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      ConverterUtil.termToStandardString("-");
    } catch (FormulaConversionException e) {
      assertEquals("The formula ends with an operator.", e.getMessage());
      tmpErrorOccured = true;
    }
    assertTrue("The String is not correct", tmpErrorOccured);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString7() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      ConverterUtil.termToStandardString("a");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("The String is correct", !tmpErrorOccured);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString8() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("3+(-a+b)");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue(!tmpErrorOccured);
    assertEquals("3+((-a)+b)", tmpConvertedString);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString9() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("sin4");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("The String is correct", !tmpErrorOccured && "s*i*n*4".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString10() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("-a+45");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("The String is correct", !tmpErrorOccured && "(-a)+45".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString11() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("5*x+y*z");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is correct", !tmpErrorOccured && "5*x+y*z".equals(tmpConvertedString));
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString12() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("sin(3^cos(-4))");
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
  public void testTermToStandardString13() throws Exception {

    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("2*0,0000559");
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
  public void testTermToStandardString14() throws Exception {
    // positive test
    String tmpString = null;
	try {
      tmpString = ConverterUtil.termToStandardString("sin(abc)+cos(abcd)+tan+tan(abc)+sqrt(23)");
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
  public void testTermToStandardString15() throws Exception {
    // negative test
    boolean tmpErrorOccured = false;
	try {
      ConverterUtil.termToStandardString("4,7.2");
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
  public void testTermToStandardString16() throws Exception {
    // negative test
    boolean tmpErrorOccured = false;
	try {
      ConverterUtil.termToStandardString("2+32.a");
    } catch (FormulaConversionException e) {
      tmpErrorOccured = true;
    }
    assertTrue("Wrong comma.", tmpErrorOccured);
  }

  /**
   * @throws Exception
   */
  public void testTermToGUIStandardString14() throws Exception {
    // positive test
    String tmpString = null;
    tmpString = ConverterUtil.termToGUIStandardString("Ya* aA+ B   - x");
    assertEquals("ya*aa+b-x", tmpString);
  }

  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#insertMultiplicationOperators(java.lang.String)}.
   */
  public void testCleanVariables() {
    String tmpString = new String("2a+ab");
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
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(StringConvertTest.class);
  }
}
