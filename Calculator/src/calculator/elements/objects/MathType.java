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

  //TODO @André does anybody need this?
  /**
   * end of formula
   */
  END_OF_TERM
}
