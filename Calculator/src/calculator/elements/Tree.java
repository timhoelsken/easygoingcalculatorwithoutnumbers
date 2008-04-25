package calculator.elements;

import calculator.utils.ConverterUtil;
import calculator.utils.FormulaTreeUtil;

/**
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
    // get tree depth
    int tmpDepth = FormulaTreeUtil.getDepth(this);
    // maximum painted depth
    int tmpMaximumDepth = 7;
    if (tmpDepth > tmpMaximumDepth) {
      tmpPaintedTree = "Only trees with a depth < " + (tmpMaximumDepth + 1) + " are painted.";
    } else {
      if (tmpDepth == 0) {
        tmpPaintedTree += "Tree is empty.";
      } else {
        // calculate the line width (3 signs for every leaf)
        int tmpLineWidth = ((int) Math.pow(2.0, tmpDepth - 1)) * 3;

        // place root in first line (center)
        String tmpRootValue = "";
        for (int i = 0; i < (tmpLineWidth / 2); i++) {
          tmpRootValue += " ";
        }
        tmpRootValue += getRoot().toString();
        tmpPaintedTree += tmpRootValue + "\n";

        // go deeper if tree is deep enough...
        if (tmpDepth > 1) {
          String tmpOutput;
          // for every level...
          for (int i = 1; i < tmpDepth; i++) {
            tmpOutput = "";
            // how many elements could be in this level?
            int tmpExpectedElements = (int) Math.pow(2.0, i);
            // for every possible element...
            for (int j = 1; j <= tmpExpectedElements; j++) {
              String tmpSpaces = "";
              // calculate spaces for the element to paint
              for (int k = 0; k < (tmpLineWidth / tmpExpectedElements); k++) {
                tmpSpaces += " ";
              }
              // get the element
              MathObj tmpMathObj = getMathObj(i, j);
              if (tmpMathObj != null) {
                // if available, paint it
                String tmpObjValue = tmpMathObj.toString();
                tmpOutput += ConverterUtil.centerStringInSpaces(tmpSpaces, tmpObjValue);
              } else {
                // if not, just paint spaces
                tmpOutput += tmpSpaces;
              }
            }
            // add level to paintString
            tmpPaintedTree += tmpOutput + "\n";
          }
        }
      }
    }
    return tmpPaintedTree;
  }

  private MathObj getMathObj(int aTargetLevel, int aTargetElementPosition) {
    // call the method for this tree
    return getMathObj(aTargetLevel, aTargetElementPosition, this);
  }

  private MathObj getMathObj(int aLevelCounter, int anElementPosition, Tree aTree) {
    // special handling of target tree (found it)
    if (aLevelCounter == 1) {
      Tree tmpSon = null;
      switch (anElementPosition) {
        // there are only 2 possible sons
        case 1:
          tmpSon = aTree.getLeftSon();
          break;
        case 2:
          tmpSon = aTree.getRightSon();
      }
      if (tmpSon != null) {
        // return the son's root mathObj (if not null)
        return tmpSon.getRoot();
      }
      // if the son is null, the requested element does not exist
      return null;
    }

    // search the right tree to go to
    // calculate number of elements in the current level
    int tmpNumberOfElementsOfTargetLevel = (int) Math.pow(2.0, aLevelCounter);
    Tree tmpPartTree;
    // if the element is on the right side from here, the right son is the right
    // one
    if ((tmpNumberOfElementsOfTargetLevel / 2) < anElementPosition) {
      tmpPartTree = aTree.getRightSon();
      // here we also have to change the position of the wanted element
      anElementPosition = anElementPosition - tmpNumberOfElementsOfTargetLevel / 2;
    } else {
      // ... else the left son is the new partTree
      tmpPartTree = aTree.getLeftSon();
    }
    // if partTree exists call recursively (nearer to the wanted leaf)
    if (tmpPartTree != null) {
      return getMathObj(--aLevelCounter, anElementPosition, tmpPartTree);
    }
    // if there's no partTree, the requested element does not exist
    return null;
  }
}