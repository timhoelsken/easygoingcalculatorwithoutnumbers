package calculator;

/**
 * just to kill the warnings :P
 */
public class EmptyClassForGlobalSupport {

  // comments by Raphael
  //TODO FEHLER F�R DIE GUI FRONT: Solange die progressbar l�uft, sollte der button calculate gesperrt werden (nebenl�ufigkeiten vermeiden). sonst kann man ihn mehrmals bet�tigen. Im zusammenhang mit variablen und baumausgabe kann das zu null-pointer-exceptions f�hren.
  //TODO @wer will: Klassendiagramm gerade ziehen
  // comments by Tim

  // comments by Andre

  // comments by Tobi
  // TODO @all NEVER EVER: Im Tests nicht allgemein die "Exception" abfangen,
  // sondern den speziellen Typ / die Exception
  // der gew�nschten Nachricht. Exception bringt Ungl�ck, weil auch NPEs oder
  // sonstwelche RuntimeExceptions abgefangen werden
  // und dann der Test evtl. wegen erkanntem "Fehler in der Formel" gr�n ist...
  // dabei ist's ein Fehler in der Verarbeitung...
}
