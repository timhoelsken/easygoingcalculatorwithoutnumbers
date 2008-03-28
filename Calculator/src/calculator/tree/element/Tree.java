package calculator.tree.element;

import calculator.math.element.object.MathObj;

/**
 * 
 */
public class Tree {
  private MathObj root;
  private Tree LeftSon;
  private Tree RightSon;
  private Tree Father;

  /**
   * @return the father
   */
  public Tree getFather() {
    return Father;
  }

  /**
   * @param aFather
   */
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
   * @param aFather
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
    int tmpDepth = getDepth(this);
    System.out.println("TreeDepth: " + tmpDepth);
    System.out.println();
    if (tmpDepth == 0) {
      System.out.println("Tree is empty.");
    } else {
      int tmpLineWidth = ((int) Math.pow(2.0, tmpDepth - 1)) * 3;

      String tmpRootValue = new String();
      for (int i = 0; i < (tmpLineWidth / 2); i++) {
        tmpRootValue += " ";
      }
      tmpRootValue += getRoot().toString();
      System.out.println(tmpRootValue);
      System.out.println();

      if (tmpDepth > 1) {
        String tmpOutput;
        for (int i = 1; i < tmpDepth; i++) {
          tmpOutput = new String();
          int tmpExpectedObjects = (int) Math.pow(2.0, i);
          for (int j = 1; j <= tmpExpectedObjects; j++) {
            String tmpSpaces = new String();
            for (int k = 0; k <= (tmpLineWidth / tmpExpectedObjects); k++) {
              tmpSpaces += " ";
            }
            MathObj tmpMathObj = getMathObj(i, j);
            if (tmpMathObj != null) {
              String tmpObjValue = tmpMathObj.toString();
              tmpOutput += centerStringInSpaces(tmpSpaces, tmpObjValue);
            } else {
              tmpOutput += centerStringInSpaces(tmpSpaces, "");
            }
          }
          System.out.println(tmpOutput);
          System.out.println();
        }
      }
    }
  }

  // TODO in util klasse ausgliedern
  private String centerStringInSpaces(String someSpaces, String aString) {
    String tmpReturnString = new String();
    int tmpSpaceLength = someSpaces.length();
    int tmpStringLength = aString.length();
    if (tmpSpaceLength <= tmpStringLength) {
      return aString;
    }
    int tmpStart = tmpSpaceLength / 2 - tmpStringLength / 2;
    tmpReturnString = someSpaces.substring(0, tmpStart);
    tmpReturnString += aString;
    for (int i = tmpReturnString.length() + 1; i < tmpSpaceLength; i++) {
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

  private int getDepth(Tree aTree) {
    if (aTree == null) {
      return 0;
    }
    int tmpRight;
    int tmpLeft;
    tmpRight = getDepth(aTree.getRightSon()) + 1;
    tmpLeft = getDepth(aTree.getLeftSon()) + 1;
    if (tmpRight > tmpLeft) {
      return tmpRight;
    } else {
      return tmpLeft;
    }
  }
}