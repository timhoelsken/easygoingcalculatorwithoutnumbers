package tree.element;

import user.util.input.Converter;
import math.element.object.MathObj;
import math.element.object.NumberObj;
import math.element.object.Operator;
import math.element.object.OperatorType;

/**
 *
 */
public class Tree {
  private MathObj root;
  private Tree LeftSon;
  private Tree RightSon;
  private Tree Father;

  public Tree getFather() {
    return Father;
  }

  public void setFather(Tree aFather) {
    Father = aFather;
  }

  /**
   * @return the root
   */
  public MathObj getRoot() {
    return root;
  }

  /**
   * @param aRoot
   *            to set
   */
  public void setRoot(MathObj aRoot) {
    root = aRoot;
  }

  /**
   * @return the left son tree
   */
  public Tree getLeftSon() {
    return LeftSon;
  }

  /**
   * @param aLeft
   *            tree to set
   */
  public void setLeftSon(Tree aLeft) {
    LeftSon = aLeft;
  }

  /**
   * @return the right son tree
   */
  public Tree getRightSon() {
    return RightSon;
  }

  /**
   * @param aRight
   *            tree to set
   */
  public void setRightSon(Tree aRight) {
    RightSon = aRight;
  }

  /**
   * constructor (sets both sons to NULL)
   *
   * @param aRoot
   */
  public Tree(MathObj aRoot) {
    root = aRoot;
    LeftSon = RightSon = Father = null;
  }

  /**
   * constructor
   *
   * @param aRoot
   * @param aLeft
   * @param aRight
   */
  public Tree(MathObj aRoot, Tree aFather, Tree aLeft, Tree aRight) {
    root = aRoot;
    LeftSon = aLeft;
    RightSon = aRight;
    Father = aFather;
  }

  /**
   * paints current tree on the console
   *
   * @author Tobias
   */
  public void paintMe() {
    int tmpDepth = getDepth();
    if (tmpDepth == 0) {
      System.out.println("Tree is empty.");
    } else {
      int tmpLineWidth = ((int) Math.pow(2.0, tmpDepth - 1)) * 3;

      String tmpRootValue = new String();
      for (int i = 0; i < (tmpLineWidth / 2); i++) {
        tmpRootValue += " ";
      }
      tmpRootValue += getValue(getRoot());
      System.out.println(tmpRootValue);
      System.out.println();

      if (tmpDepth > 1) {
        String tmpOutput;
        for (int i = 1; i < tmpDepth; i++) {
          tmpOutput = new String();
          int tmpExpectedObjects = (int) Math.pow(2.0, i);
          for (int j = 1; j <= tmpExpectedObjects; j++) {
            String tmpSpaces = new String();
            for (int k = 0; k <= (tmpLineWidth / tmpExpectedObjects); k++){
              tmpSpaces += " ";
            }
            MathObj tmpMathObj = getMathObj(i, j);
            String tmpObjValue = getValue(tmpMathObj);
            tmpOutput += centerStringInSpaces(tmpSpaces, tmpObjValue);
          }
          System.out.println(tmpOutput);
          System.out.println();
        }
      }
    }
  }

  private String centerStringInSpaces (String someSpaces, String aString) {
    String tmpReturnString = new String();
    int tmpSpaceLength = someSpaces.length();
    int tmpStringLength = aString.length();
    int tmpStart = tmpSpaceLength / 2 - tmpStringLength / 2;
    tmpReturnString = someSpaces.substring(0, tmpStart);
    tmpReturnString += aString;
    for (int i = tmpReturnString.length(); i < tmpSpaceLength; i++) {
      tmpReturnString += " ";
    }
    return tmpReturnString;
  }

  private MathObj getMathObj(int aTargetLevel, int aTargetObject) {
    return getMathObj(aTargetLevel, aTargetObject, this);
  }

  private MathObj getMathObj(int aLevel, int anObject, Tree aTree) {
    if (aLevel == 1) {
      Tree tmpSon = null;
      switch (anObject){
        case 1:
          tmpSon = aTree.getLeftSon();
          break;
        case 2:
          tmpSon = aTree.getRightSon();
      }
      if (tmpSon != null) {
        return tmpSon.getRoot();
      }
      return null;
    }
    int tmpNumberOfObjectsOfTargetLevel = (int) Math.pow(2.0, aLevel);
    Tree tmpPartTree;
    if ((tmpNumberOfObjectsOfTargetLevel / 2) < anObject) {
      tmpPartTree = aTree.getRightSon();
      anObject = anObject - tmpNumberOfObjectsOfTargetLevel / 2;
    } else {
      tmpPartTree = aTree.getLeftSon();
    }
    if (tmpPartTree != null) {
      return getMathObj(--aLevel, anObject, tmpPartTree);
    }
    return null;
  }

  // TODO @Andr� Mach, dass MathObj mir sagt, was es ist (als String)
  private String getValue(MathObj aMathObj) {
    String tmpReturnString = null;
    if (aMathObj instanceof NumberObj) {
      NumberObj tmpObj = (NumberObj) aMathObj;
      tmpReturnString = "" + tmpObj.getValue();
    } else if (aMathObj instanceof Operator) {
      Operator tmpObj = (Operator) aMathObj;
      switch (tmpObj.getOperatorType()){
        case ADDITION:
          tmpReturnString = "+";
          break;
        case SUBTRACTION:
          tmpReturnString = "-";
          break;
        case MULTIPLICATION:
          tmpReturnString = "*";
          break;
        case DIVISION:
          tmpReturnString = "/";
      }
    } else {
      tmpReturnString = " ";
    }
    return tmpReturnString;
  }

  // TODO @Raphi rekursiv implementieren :P
  private int getDepth() {
    return 7;
  }

  /**
   * start for testing paintMe()
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    Tree tmpTree = FormulaTreeBuilder.BuildTree("5*3*2+6+5*2-30*2+8*2/3*4-14");
    tmpTree.paintMe();
  }
}
