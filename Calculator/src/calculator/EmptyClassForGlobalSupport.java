package calculator;

/**
 * just to kill the warnings :P
 */
public class EmptyClassForGlobalSupport {

  // comments by Raphael

  // comments by Tim

  // TODO @Raphi wenn du es noch schaffst in der evaluateTree von
  // FormulaTreeUtil zwischen einer Exception wegen falscher Variablen zu
  // unterscheiden, dann kann ich auch den consolen TR toll machen :D genauer
  // gemeint ist der Fall, wenn der User die Formel sqrt(a) eingibt und dann a
  // mit -1 belegt. Wenn du mir da als Exception etwas zur�ck gibst wie
  // "Variable forced calculating error", dann kann ich das unterscheiden und in
  // der Variablenschleife bleiben und der User muss nicht eine neue Formel
  // eingeben.
  // @Tim: die Fehlermeldung kann ich dir leider nicht zur�ckgeben, da in der Klammer ein beliebiger neuer Ausdruck
  // stehen kann (sqrt(s*sin(x)+9*2^x+23*x(-1))) - da m�sste ich eine sehr aufw�ndige Analyse fahren... 
  // Ich kann leider nur eine Fehlermeldung ausgeben, dass der Term in sqrt() negativ ist. Mehr Informationen habe ich nicht
  // zur Verf�gung

  // TODO Soweit ich das sehe ist jetzt alles bei der GUI toll. Ich stell jetzt
  // aber noch die Frage in den Raum, ob wir nicht das calculator.userinterface
  // Paket in die zwei Pakete "calculator.userinterface.console" und
  // "calculator.userinterface.frame" aufteilen sollen?
  //von raphi: bin daf�r :-) ist eine sinnvolle struktur

  // TODO @tobi - was ich noch vorhabe zu �ndern ist das Icon. Muss ich aber nochmal von
  // Tobi bekommen.

  // TODO @all: im Moment ist es in der Console so, dass der Baum "gespeichert"
  // wird und nicht immer neu berechnet wird, solange kein neuer eingegeben
  // wurde. Im Frame wird der Baum jedes mal neu berechnet, was _meiner_ Ansicht
  // nach nicht der Aufgabenstellung entspricht... Sollen wir da noch was
  // optimieren oder es so lassen?
  // von Raphi: Mir ist das schnurzpiepegal :-) lass es so. wir haben die anforderung strukturell erf�llt :-)
  // wenn er meckert ist es in der console realisiert.

  // comments by Andre

  // comments by Tobi
}
