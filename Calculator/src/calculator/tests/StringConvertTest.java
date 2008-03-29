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
  public void testTermToStandardString() throws Exception {
    String tmpString = new String("2a + a b");
    assertTrue("The String has Variables separated by *", ConverterUtil.termToStandardString(tmpString)
        .equals("2*a+a*b"));
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
  public void testCheckIfValidSignsOnly() {

    // positive test
    try {
      ConverterUtil
          .checkIfValidSignsOnly(".,abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-*/+^()");
    } catch (Exception e) {
      assertTrue(false);
    }

    // negative test
    Boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkIfValidSignsOnly("%");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkIfValidSignsOnly("ä");
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
  public void testCheckDecimalNumbers() {   
    Boolean tmpErrorOccurred = false;
    // positive test
    try {
      ConverterUtil.checkDecimalNumbers("5.6+34+2.0+100.9");
    } catch (Exception e) {
      assertTrue(false);
    }

    // negative test
    tmpErrorOccurred = false;

    try {
      ConverterUtil.checkDecimalNumbers("5.6.3+34+2.0+100.9");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers(".");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkDecimalNumbers(".999+7");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
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
  public void testCheckOperators() {
    // positive test
    try {
      ConverterUtil.checkOperators("5+6-98*(23^2)-98/8");
    } catch (Exception e) {
      assertTrue(false);
    }

    // negative test
    Boolean tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("+(23*55)");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23+-99+9*8");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23*(+99)");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23*(2+99^)");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
    try {
      ConverterUtil.checkOperators("23*(2+99)**2");
    } catch (Exception e) {
      tmpErrorOccurred = true;
    }
    assertTrue(tmpErrorOccurred);

    // negative test
    tmpErrorOccurred = false;
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
  public void testSetBracketsAroundNegativeNumbers() {
	  
	  String tmp = ConverterUtil.setBracketsAroundNegatives(
      "-8+(-23.23434+(-34))*(-5)+(-23^3)+(-1*(-2*(-5)))))+(-a+b)");
    assertTrue(tmp.equals(
        "(-8)+((-23.23434)+(-34))*(-5)+((-23)^3)+((-1)*((-2)*(-5)))))+((-a)+b)"));
	  
	  
	  tmp = ConverterUtil.setBracketsAroundNegatives(
      "-8+(-23.23434+-34)*-5+(-23^3)+(-1*(-2*(-5)))))+(-a+b)");
    assertTrue(tmp.equals(
        "(-8)+((-23.23434)+-34)*-5+((-23)^3)+((-1)*((-2)*(-5)))))+((-a)+b)"));
  }

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
