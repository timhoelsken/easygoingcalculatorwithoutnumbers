package pse3;

public class NumberObj extends MathObj {
  private float p_value;

  public float getValue() {
    return p_value;
  }

  public NumberObj(float i_Value) {
    super(MathType.Number);
    p_value = i_Value;
  }
}
