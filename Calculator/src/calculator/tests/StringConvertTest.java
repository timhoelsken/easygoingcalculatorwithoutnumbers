/**
 *
 */
package calculator.tests;

import calculator.utils.ConverterUtil;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @author Tim
 *
 */
public class StringConvertTest extends TestCase {

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
  public void testTermToStandardString3() throws Exception {
    // Es folgen die drei Beispiele aus dem Aufgabenblatt
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("(3x + 5) * 15");
    } catch (Exception e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "(3*x+5)*15".equals(tmpConvertedString));

    tmpErrorOccured = false;
    tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("x^2 + 2*x*y + y^2");
    } catch (Exception e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is converted correctly", !tmpErrorOccured
        && "x^2+2*x*y+y^2".equals(tmpConvertedString));

    tmpErrorOccured = false;
    tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("c*cos(x) + s * sin(sqrt(2) - x)");
    } catch (Exception e) {
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
  public void testTermToStandardString4() throws Exception {
    boolean tmpErrorOccured = false;
    String tmpConvertedString = null;

    try {
      tmpConvertedString = ConverterUtil.termToStandardString("abcossin(cos(-3))");
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
  public void testTermToStandardString7() throws Exception {
    boolean tmpErrorOccured = false;

    try {
      ConverterUtil.termToStandardString("a");
    } catch (Exception e) {
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
    } catch (Exception e) {
      tmpErrorOccured = true;
    }
    assertTrue("The String is correct", !tmpErrorOccured && "3+((-a)+b)".equals(tmpConvertedString));
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
      tmpErrorOccured = true;
    }

    assertTrue("The String is correct", !tmpErrorOccured && "5*x+y*z".equals(tmpConvertedString));

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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
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


  //TODO @anyone go one, split test methods ;)
  /**
   * Test method for
   * {@link calculator.utils.ConverterUtil#checkNegativeNumbers()}.
   */
  public void testCheckNegativeNumbers() {
    // positive test
    try {
      ConverterUtil.checkNegativeNumbers("(-5)*(-2)+((-4)+5)");
    } catch (Exception e) {
      assertTrue(false);
    }

    // positive test
    Boolean tmpErrorOccurred = false;
    try {
      String tmp = ConverterUtil.setBracketsAroundNegatives("-5)*(-2)+((-4)+5)");

      ConverterUtil.checkNegativeNumbers(tmp);
    } catch (Exception e) {
      assertTrue(false);
    }

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkNegativeNumbers("(-5)*-2+(-4)+5)");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkNegativeNumbers("(-5)*(-2)+((-4)+-5)");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);
  }

  /**
   * Test method for {@link calculator.utils.ConverterUtil#checkBrackets()}.
   */
  public void testCheckBrackets() {
    try {
      ConverterUtil.checkBrackets("(((5+5+5+5)))");
    } catch (Exception e) {
      assertTrue(false);
    }

    // negative test
    Boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkBrackets(")(((5+5+5+5)))");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkBrackets("(((5+5+5+5))))");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkBrackets("(((((5+5+5+5))))");
    } catch (Exception e) {
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
   *
   */
  public void testEverythingEspaciallyTheSyntax() {
    /**
     * es sollte geprüft werden, dass keine Sonderzeichen im Text drinne sind
     * "5+6+9+9@7" -> Fehler => checkIfValidSignsOnly()
     *
     * Prüfe Operatoren folgende Kombos sind unmöglich: "5+6*7-*2" -> Fehler
     * (binär/binär) => checkOperators() "5+sincos()+9" -> Ok "5*sin*6" -> Ok
     * "9+sin(5+2)" -> Ok
     *
     * Auf einen unären Operator muss nicht ein Klammerausdruck folgen (soweit
     * er nicht anders interpretiert werden kann) ;) "5*sin(6)+3" -> ok
     * "5*sin6+3" -> ok
     *
     * vor und hinter einem unären operator muss ein binärer operator stehen, es
     * sei denn er steht am anfang oder ende der formel NÖ :) "5*sin(6)cos(3)" ->
     * ok "5*sin(6)*cos(3)" -> ok
     *
     * Es muss immer folgende Reihenfolge eingehalten werden: Zahl oder unärer
     * Operator (mit Klammerausdruck), Binärer Operator, Zahl oder unärer
     * Operator (mit Klammerausdruck), binärer operator.... NÖ :)
     * "5sin(66)4cos(3)" -> ok "5*sin(66)*4*cos(3)" -> ok
     *
     * Zahlen prüfen Zahl darf nicht an Zahl angegrenzt sein (in Klammern schon)
     * "5 5" -> nicht ok "5(5)" -> ok "5*(5)" -> ok
     *
     * zahl darf nicht zwei oder mehr ., enthalten "5,23.43*2.22.22+2312" ->
     * fehler "5.3*(5,2)" -> ok => checkDecimalNumbers() (vorher unifyCommas())
     *
     * Klammercheck "5*)3+2(" "5*(3+2)" => checkBrackets()
     *
     * vor einer geschlossenen klammer kann kein binärer operator stehen
     * "(3+4+)*5" "(7*9*2+23)*5" => checkOperators()
     *
     * "(5+6)" -> ok "4(5+6)" -> ok "sin(56)" -> ok "(5+6)sin(3)" -> ok
     * "5+6+(3+3)*1+2" -> ok "5+6+(3+3)1+2" -> ok
     */
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(StringConvertTest.class);
  }
}
