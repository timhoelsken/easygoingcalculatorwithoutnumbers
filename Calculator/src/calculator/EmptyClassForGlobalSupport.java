package calculator;

/**
 * just to kill the warnings :P
 */
public class EmptyClassForGlobalSupport {

  // comments by Raphael
  // TODO wer tests schreibt für die util klassen der macht den selben test
  // bitte auch einmal als integrationstest
  // TODO @irgendwer, alle großbuchstaben werden der einfach heit am anfang in
  // kleinbuchstaben konvertiert :P
  // comments by Tim

  // TODO @all ich habe im ConsoleCalculator eine Abfrage eingebaut, die prüft
  // ob bereits ein Baum existiert. Hier kann ich mir sicher sein, dass
  // zwischendurch kein anderer Baum eingegeben wurde, weil zuerst die
  // Variablenschleife abgearbeitet werden muss. Im FrameCalculator funktioniert
  // das leider nicht so einfach, da muss ich mir noch was einfallen lassen
  // ==> Hintergrund des ganzen ist, dass wir als eine Aufgabe haben, dass die
  // Formel im Baum "gespeichert" wird und einfach nur die Variablen ausgelesen
  // und eingesetzt werden um die Formel zu berechnen... Bisher hatten wir jedes
  // Mal den Baum neu erstellt und nicht den schon vorhandenen genutzt.

  // comments by Andre

  // comments by Tobi
  // TODO @all NEVER EVER: Im Tests nicht allgemein die "Exception" abfangen,
  // sondern den speziellen Typ / die Exception
  // der gewünschten Nachricht. Exception bringt Unglück, weil auch NPEs oder
  // sonstwelche RuntimeExceptions abgefangen werden
  // und dann der Test evtl. wegen erkanntem "Fehler in der Formel" grün ist...
  // dabei ist's ein Fehler in der Verarbeitung...
}
