package calculator.elements.objects;

/**
 *
 */
public enum MathType {
  /**
   * operator + - * ...
   */
  OPERATOR,

  /**
   * number 0 1 2 3 ...
   */
  OPERAND,

  /*
   * end of formula
   */
  //TODO @André does anybody need this?
  END_OF_TERM
}
