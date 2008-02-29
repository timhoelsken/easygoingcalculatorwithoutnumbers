package tree.element;

import math.element.object.MathObj;

/**
 *
 */
public class Tree {
	private MathObj root;
	private Tree left;
	private Tree right;

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
	public Tree getLeft() {
		return left;
	}

	/**
	 * @param aLeft
	 *            tree to set
	 */
	public void setLeft(Tree aLeft) {
		left = aLeft;
	}

	/**
	 * @return the right son tree
	 */
	public Tree getRight() {
		return right;
	}

	/**
	 * @param aRight
	 *            tree to set
	 */
	public void setRight(Tree aRight) {
		right = aRight;
	}

	/**
	 * constructor (sets both sons to NULL)
	 *
	 * @param aRoot
	 */
	public Tree(MathObj aRoot) {
		root = aRoot;
		left = right = null;
	}

	/**
	 * constructor
	 *
	 * @param aRoot
	 * @param aLeft
	 * @param aRight
	 */
	public Tree(MathObj aRoot, Tree aLeft, Tree aRight) {
		root = aRoot;
		left = aLeft;
		right = aRight;
	}

}
