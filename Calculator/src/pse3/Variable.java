package pse3;

public class Variable extends MathObj {

  private char p_value;

  public float getValue() {
    return p_value;
  }

  public Variable(char i_Value) {
    super(MathType.Variable);
    p_value = i_Value;
  }
}
