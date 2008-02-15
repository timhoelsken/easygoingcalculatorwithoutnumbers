package pse3;

public class Tree {
  private MathObj root;
  private Tree left;
  private Tree right;

  public MathObj getRoot() {
    return root;
  }

  public void setRoot(MathObj aRoot) {
    root = aRoot;
  }

  public Tree getLeft() {
    return left;
  }

  public void setLeft(Tree aLeft) {
    left = aLeft;
  }

  public Tree getRight() {
    return right;
  }

  public void setRight(Tree aRight) {
    right = aRight;
  }

  public Tree(MathObj aRoot) {
    root = aRoot;
    left = right = null;
  }

  public Tree(MathObj aRoot, Tree aLeft, Tree aRight) {
    root = aRoot;
    left = aLeft;
    right = aRight;
  }

}
