package calculator;

/**
 * just to kill the warnings :P
 */
public class EmptyClassForGlobalSupport {

  // comments by Raphael
  //TODO FEHLER FÜR DIE GUI FRONT: Solange die progressbar läuft, sollte der button calculate gesperrt werden (nebenläufigkeiten vermeiden). sonst kann man ihn mehrmals betätigen. Im zusammenhang mit variablen und baumausgabe kann das zu null-pointer-exceptions führen.
  //TODO @wer will: Klassendiagramm gerade ziehen
  // comments by Tim

  // comments by Andre

  // comments by Tobi
  // TODO @all NEVER EVER: Im Tests nicht allgemein die "Exception" abfangen,
  // sondern den speziellen Typ / die Exception
  // der gewünschten Nachricht. Exception bringt Unglück, weil auch NPEs oder
  // sonstwelche RuntimeExceptions abgefangen werden
  // und dann der Test evtl. wegen erkanntem "Fehler in der Formel" grün ist...
  // dabei ist's ein Fehler in der Verarbeitung...
}
