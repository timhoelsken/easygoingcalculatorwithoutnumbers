package calculator.elements;

/**
 *
 */
public class Variable extends Operand {

  private char value;

  /**
   * @return the value
   */
  public char getValue() {
    return value;
  }

  /**
   * constructor
   *
   * @param aValue
   */
  public Variable(char aValue) {
    value = aValue;
  }

  /**
   * gives the value of the variable
   * @see java.lang.Object#toString()
   */
  //TODO @Schreiber Die Methode wurde wegen dem Konsolenbaum überladen. Brauchen wir hier nicht den Namen der Variablen? Wenn ja, warum hat diese Klasse keinen Setter?
  //Antwort: der name steht in value drinn, das ist ein char. er wird direkt im konstruktor gesetzt. ein setter hat keine verendung, aber wenn du unbenutzte setter magst,
  //kann ich den noch implementieren :P
  public String toString()
  {
    return Character.toString(value);
  }
}
