package tree.element;

import math.element.object.MathObj;

/**
 *
 */
public class Tree {
	private MathObj root;
	private Tree LeftSon;
	private Tree RightSon;
	private Tree Father;

	
	public Tree getFather()
	{
		return Father;
	}
	
	public void setFather(Tree aFather)
	{
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
	public Tree(MathObj aRoot, Tree aFather ,Tree aLeft, Tree aRight) {
		root = aRoot;
		LeftSon = aLeft;
		RightSon = aRight;
		Father = aFather;
	}

}
