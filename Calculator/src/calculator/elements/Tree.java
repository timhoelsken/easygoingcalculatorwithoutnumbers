package calculator.elements;

import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;

/**
 * @author Raphael
 */
public class Tree {
  private MathObj root;
  private Tree LeftSon;
  private Tree RightSon;
  private Tree Father;

  /**
   * @return the father
   * @uml.property name="father"
   */
  public Tree getFather() {
    return Father;
  }

  /**
   * @param aFather
   * @uml.property name="father"
   */
  public void setFather(Tree aFather) {
    Father = aFather;
  }

  /**
   * @return the root
   * @uml.property name="root"
   */
  public MathObj getRoot() {
    return root;
  }

  /**
   * @return the left son tree
   * @uml.property name="leftSon"
   */
  public Tree getLeftSon() {
    return LeftSon;
  }

  /**
   * @param aLeft
   *            tree to set
   * @uml.property name="leftSon"
   */
  public void setLeftSon(Tree aLeft) {
    LeftSon = aLeft;
  }

  /**
   * @return the right son tree
   * @uml.property name="rightSon"
   */
  public Tree getRightSon() {
    return RightSon;
  }

  /**
   * @param aRight
   *            tree to set
   * @uml.property name="rightSon"
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
   * paints current tree on the console
   */
  public void paintMe() {
    System.out.println(paintMeAsString());
  }

  /**
   * paints current tree in a String
   *
   * @return a String containing a tree painted in chars
   */
  public String paintMeAsString() {
    String tmpPaintedTree = "";
    int tmpDepth = FormulaTreeUtil.getDepth(this);
    int tmpMaximumDepth = 7;
    if (tmpDepth > tmpMaximumDepth) {
      tmpPaintedTree = "Only trees with a depth < " + (tmpMaximumDepth + 1) + " are painted.";
    } else {
      tmpPaintedTree += "TreeDepth: " + tmpDepth + "\n";

      if (tmpDepth == 0) {
        tmpPaintedTree += "Tree is empty.";
      } else {
        int tmpLineWidth = ((int) Math.pow(2.0, tmpDepth - 1)) * 3;

        String tmpRootValue = "";
        for (int i = 0; i < (tmpLineWidth / 2); i++) {
          tmpRootValue += " ";
        }
        tmpRootValue += getRoot().toString();
        tmpPaintedTree += tmpRootValue + "\n";

        if (tmpDepth > 1) {
          String tmpOutput;
          for (int i = 1; i < tmpDepth; i++) {
            tmpOutput = "";
            int tmpExpectedObjects = (int) Math.pow(2.0, i);
            for (int j = 1; j <= tmpExpectedObjects; j++) {
              String tmpSpaces = "";
              for (int k = 0; k <= (tmpLineWidth / tmpExpectedObjects); k++) {
                tmpSpaces += " ";
              }
              MathObj tmpMathObj = getMathObj(i, j);
              if (tmpMathObj != null) {
                String tmpObjValue = tmpMathObj.toString();
                tmpOutput += ConverterUtil.centerStringInSpaces(tmpSpaces, tmpObjValue);
              } else {
                tmpOutput += ConverterUtil.centerStringInSpaces(tmpSpaces, "");
              }
            }
            tmpPaintedTree += tmpOutput + "\n";
          }
        }
      }
    }
    return tmpPaintedTree;
  }

  private MathObj getMathObj(int aTargetLevel, int aTargetObject) {
    return getMathObj(aTargetLevel, aTargetObject, this);
  }

  private MathObj getMathObj(int aLevel, int anObject, Tree aTree) {
    if (aLevel == 1) {
      Tree tmpSon = null;
      switch (anObject) {
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
}