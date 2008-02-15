package pse3;

public enum OperatorType {
  ADDITION('+'), SUBTRACTION('-'), MULTIPLICATION('*'), DIVISION('/');

  private char operatorType;

  private OperatorType(char anOperatorType) {
    operatorType = anOperatorType;
  }

  public char getOperatorTypeAsChar() {
    return operatorType;
  }
}
