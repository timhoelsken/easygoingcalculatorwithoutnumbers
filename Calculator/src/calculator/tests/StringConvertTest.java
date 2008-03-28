/**
 *
 */
package calculator.tests;

import calculator.user.util.input.ConverterUtil;
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
   * {@link calculator.user.util.input.ConverterUtil#termToStandardString(java.lang.String)}.
   *
   * @throws Exception
   */
  public void testTermToStandardString() throws Exception {
    String tmpString = new String("2a + a b");
    assertTrue("The String has Variables separated by *", ConverterUtil.termToStandardString(tmpString).equals(
        "2*a+a*b"));
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#removeBlanks(java.lang.String)}.
   */
  public void testRemoveBlanks() {
    String tmpString = new String("2 + 4");
    assertTrue(ConverterUtil.removeBlanks(tmpString).equals("2+4"));
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#insertMultiplicationOperators(java.lang.String)}.
   */
  public void testCleanVariables() {
    String tmpString = new String("2a+ab");
    assertTrue("The String has Variables separated by *", ConverterUtil
        .insertMultiplicationOperators(tmpString).equals("2*a+a*b"));
  }


  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#checkIfValidSignsOnly()}.
   */
  public void checkIfValidSignsOnly() {

	//positive test
	try
	{
		ConverterUtil.checkIfValidSignsOnly(".,abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-*/+^()");
	}
	catch (Exception e)
	{
		assert(false);
	}

	//negative test
	Boolean tmpErrorOccurred = false;
	try
	{
		ConverterUtil.checkIfValidSignsOnly("%");
	}
	catch (Exception e)
	{
		tmpErrorOccurred = true;
	}
	assert( !tmpErrorOccurred );

	//negative test
	tmpErrorOccurred = false;
	try
	{
		ConverterUtil.checkIfValidSignsOnly("ä");
	}
	catch (Exception e)
	{
		tmpErrorOccurred = true;
	}
	assert( !tmpErrorOccurred );

  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#checkIfValidBlanksOnly()}.
   */
  public void checkIfValidBlanksOnly()
  {
	  assert(false); //Test noch nicht geschrieben!!!
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#removeBlanks()}.
   */
  public void removeBlanks()
  {
	  assert(ConverterUtil.removeBlanks("2+  3+4 5 + 666").equalsIgnoreCase("2+3+45+666"));
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#unifyCommas()}.
   */
  public void unifyCommas()
  {
	  assert(ConverterUtil.unifyCommas(",,dfa34.,.,.").equalsIgnoreCase("..dfa34....."));
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#checkDecimalNumbers()}.
   */
  public void checkDecimalNumbers()
  {
	  assert(false); //noch kein code review stattgefunden

	//positive test
		try
		{
			ConverterUtil.checkDecimalNumbers("5.6+34+2.0+100.9");
		}
		catch (Exception e)
		{
			assert(false);
		}

		//negative test
		Boolean tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkDecimalNumbers("5.6.3+34+2.0+100.9");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );

		//negative test
		tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkDecimalNumbers(".");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );

		//negative test
		tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkDecimalNumbers(".999+7");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#insertMultiplicationOperators()}.
   */
  public void insertMultiplicationOperators()
  {
	  assert(ConverterUtil.insertMultiplicationOperators("3+5.5xa4bc3def5gh+sin+cos6").equals("3+5.5*x*a*4*b*c*3*d*e*f*5*g*h+s*i*n+c*o*s*6"));
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#checkOperators()}.
   */
  public void checkOperators()
  {
	//positive test
		try
		{
			ConverterUtil.checkOperators("5+6-98*(23^2)-98/8");
		}
		catch (Exception e)
		{
			assert(false);
		}

		//negative test
		Boolean tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkOperators("+(23*55)");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );

		//negative test
		tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkOperators("23+-99+9*8");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );

		//negative test
		tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkOperators("23*(+99)");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );

		//negative test
		tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkOperators("23*(2+99^)");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );

		//negative test
		tmpErrorOccurred = false;
		try
		{
			ConverterUtil.checkOperators("23*(2+99)**2");
		}
		catch (Exception e)
		{
			tmpErrorOccurred = true;
		}
		assert( !tmpErrorOccurred );
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#setBracketsAroundNegativeNumbers()}.
   */
  public void setBracketsAroundNegativeNumbers()
  {
	  assert(false);
  }


  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#putBracketsAroundNegativeNumber()}.
   */
  public void putBracketsAroundNegativeNumber()
  {
	  assert(false);
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#checkNegativeNumbers()}.
   */
  public void checkNegativeNumbers()
  {
	  assert(false);
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#checkBrackets()}.
   */
  public void checkBrackets()
  {
	  assert(false);
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#changeFunctionsIntoSigns()}.
   */
  public void changeFunctionsIntoSigns()
  {
	  assert(false);
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#getNextBlankPosition()}.
   */
  public void getNextBlankPosition()
  {
	  assert(false);
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#isNumericOrVariable()}.
   */
  public void isNumericOrVariable()
  {
	  assert(false);
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#isNumeric}.
   */
  public void isNumeric()
  {
	  assert(false);
  }

  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#isVariable}.
   */
  public void isVariable()
  {
	  assert(false);
  }


  /**
   * Test method for
   * {@link calculator.user.util.input.ConverterUtil#termToStandardString()}.
   */
  public void termToStandardString()
  {
	  assert(false);
  }

  // TODO @Raphi Tests fürs ConverterUtil schreiben (alles testbare ist jetzt
  // public - siehe Methodennamen im Kommentar)
  // Ich hab deine Überlegungen soweit hier gelassen, kann man ja alles in
  // Unit-Tests umformen. Hab meinen Senf nur dazugeschrieben (und sei es nur
  // ein 'NÖ'). Methoden, die hier nicht aufgeführt aber trotzdem
  // public sind, können natürlich trotzdem getestet werden (s. JavaDoc).
  //
  // es gibt auch eine Methode, die alles nacheinander abfackelt und dann den
  // fertigen String zurückliefert: termToStandardString(String); Hier ist besonders
  // die Reihenfolge der Checks und Stringveränderungen wichtig. Für verschiedene
  // Fehler werden IllegalArgumentExceptions mit entsprechender Fehlermeldung geworfen,
  // die auch abgetestet werden können.
  //
  // mfg wörger
  /**
   *
   */
  public void testEverythingEspaciallyTheSyntax() {
    /**
     * es sollte geprüft werden, dass keine Sonderzeichen im Text drinne sind
     * "5+6+9+9@7" -> Fehler
     * => checkIfValidSignsOnly()
     *
     * Prüfe Operatoren folgende Kombos sind unmöglich:
     * "5+6*7-*2" -> Fehler (binär/binär)
     * => checkOperators()
     * "5+sincos()+9" -> Ok
     * "5*sin*6" -> Ok
     * "9+sin(5+2)" -> Ok
     *
     * Auf einen unären Operator muss nicht ein Klammerausdruck folgen (soweit
     * er nicht anders interpretiert werden kann) ;)
     * "5*sin(6)+3" -> ok
     * "5*sin6+3" -> ok
     *
     * vor und hinter einem unären operator muss ein binärer operator stehen, es
     * sei denn er steht am anfang oder ende der formel
     * NÖ :)
     * "5*sin(6)cos(3)" -> ok
     * "5*sin(6)*cos(3)" -> ok
     *
     * Es muss immer folgende Reihenfolge eingehalten werden: Zahl oder unärer
     * Operator (mit Klammerausdruck), Binärer Operator, Zahl oder unärer
     * Operator (mit Klammerausdruck), binärer operator....
     * NÖ :)
     * "5sin(66)4cos(3)" -> ok
     * "5*sin(66)*4*cos(3)" -> ok
     *
     * Zahlen prüfen
     * Zahl darf nicht an Zahl angegrenzt sein (in Klammern schon)
     * "5 5" -> nicht ok
     * "5(5)" -> ok
     * "5*(5)" -> ok
     *
     * zahl darf nicht zwei oder mehr ., enthalten
     * "5,23.43*2.22.22+2312" -> fehler
     * "5.3*(5,2)" -> ok
     * => checkDecimalNumbers() (vorher unifyCommas())
     *
     * Klammercheck
     * "5*)3+2(" "5*(3+2)"
     * => checkBrackets()
     *
     * vor einer geschlossenen klammer kann kein binärer operator stehen
     * "(3+4+)*5" "(7*9*2+23)*5"
     * => checkOperators()
     *
     * "(5+6)" -> ok
     * "4(5+6)" -> ok
     * "sin(56)" -> ok
     * "(5+6)sin(3)" -> ok
     * "5+6+(3+3)*1+2" -> ok
     * "5+6+(3+3)1+2" -> ok
     */
  }

  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(StringConvertTest.class);
  }
}
