package pse3;

public class Operator extends MathObj {
  private OperatorType p_OpType;

  public Operator(OperatorType i_OpType) {
    super(MathType.OPERATOR);
    p_OpType = i_OpType;
  }

  public OperatorType getOpType() {
    return p_OpType;
  }
}
