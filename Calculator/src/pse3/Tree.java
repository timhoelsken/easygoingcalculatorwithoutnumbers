package pse3;

public class Tree {
  private MathObj p_root;
  private Tree p_left;
  private Tree p_right;

  public MathObj getRoot() {
    return p_root;
  }

  public void setRoot(MathObj i_root) {
    p_root = i_root;
  }

  public Tree getLeft() {
    return p_left;
  }

  public void setLeft(Tree i_left) {
    p_left = i_left;
  }

  public Tree getRight() {
    return p_right;
  }

  public void setRight(Tree i_right) {
    p_right = i_right;
  }

  public Tree(MathObj i_Root) {
    p_root = i_Root;
    p_left = p_right = null;
  }

  public Tree(MathObj i_Root, Tree i_Left, Tree i_Right) {
    p_root = i_Root;
    p_left = i_Left;
    p_right = i_Right;
  }

}
