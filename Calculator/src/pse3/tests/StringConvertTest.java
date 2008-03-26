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
   * @throws Exception
   */
  public void testTermToStandardString() throws Exception {
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
   * Test method for {@link user.util.input.Converter#insertMultiplicationOperators(java.lang.String)}.
   */
  public void testCleanVariables() {
    String tmpString = new String("2a+ab");
    Converter tmpConverter = new Converter();
    assertTrue("The String has Variables separated by *", tmpConverter.insertMultiplicationOperators(tmpString).equals("2*a+a*b"));
  }

  //todo @Tim/Tobi Denkansätze/Testfälle fürs das Syntax-checken
  public void testEverythingEspaciallyTheSyntax()
  {
	  /**
	  //es sollte geprüft werden, dass keine Sonderzeichen im Text drinne sind
	  //"5+6+9+9@7" -> Fehler
	  //Prüfe Operatoren
	  //folgende Kombos sind unmöglich:
	  //"5*sin*6" -> Fehler (Unär/Binär)
	  "5+6*7-*2" -> Fehler (binär/binär)
	  "5+sincos()+9" -> Fehler(Unnär/unnär)
	  //erlaubt
	  "9+sin(5+2)" -> Ok
	  
	  //Auf einen unären Operator muss ein Klammerausdruck folgen
	  "5*sin(6)+3" -> ok
	  "5*sin6+3" -> nicht ok
	  //vor und hinter einem unären operator muss ein binärer operator stehen, es sei denn er steht am anfang oder ende der formel
	  "5*sin(6)cos(3)" -> fehler
	  "5*sin(6)*cos(3)" -> ok
	  
	  //Es muss immer folgende Reihenfolge eingehalten werden: Zahl oder unärer Operator (mit Klammerausdruck), Binärer Operator, Zahl oder unärer Operator (mit Klammerausdruck), binärer operator....
	  "5sin(66)4cos(3)" -> fehler
	  "5*sin(66)*4*cos(3)" -> ok
	  
	  //Zahlen prüfen
	  //Zahl darf nicht an Zahl angegrenzt sein
	  "5(5)" -> fehler
	  "5*(5)" -> ok
	  //zahl darf nicht zwei oder mehr ., enthalten
	  "5,23.43*2.22.22+2312" -> fehler
	  "5.3*(5,2)" -> ok
	  
	  //Klammer bewertung -> ( = 1, ) = -1 
	  //=> Summe muss immer 0 sein
	  "((((5+6+3+2))" -> Fehler
	  "((((5+6+3+2))))" -> ok
	  
	  //bei der ermittlung der endsumme darf die zwischensumme niemals <0 sein.
	  "5*)3+2("
	  "5*(3+2)"
	  
	  //vor einer geschlossenen klammer kann kein binärer operator stehen
	  "(3+4+)*5"
	  "(7*9*2+23)*5"
	  
	  //vor und nach einer Klammerung steht ein operator (wenn nicht das formelende oder anfang erreicht ist). links kann ein binärer oder unärer stehen, rechts nur ein binärer
	  "(5+6)" -> ok
	  "4(5+6)" -> nicht ok
	  "sin(56)" -> ok
	  "(5+6)sin(3)" -> fehler
	  "5+6+(3+3)*1+2" -> ok
	  "5+6+(3+3)1+2" -> nicht ok */
  }
  
  /**
   * @return the test suite
   */
  public static Test suite() {
    return new JUnit4TestAdapter(StringConvertTest.class);
  }
}
