package calculator.utils;

import java.util.ArrayList;
import java.util.Hashtable;

import calculator.elements.MathObj;
import calculator.elements.NumberObj;
import calculator.elements.Operator;
import calculator.elements.OperatorType;
import calculator.elements.Tree;
import calculator.elements.Variable;
import calculator.exceptions.CalculatingException;

/**
 * all about the formula tree
 */
public final class FormulaTreeUtil {

	/**
	 * Calculates the result of a formula-tree and returns it as a "double"
	 *
	 * @param aTree
	 *            the tree from which the result should be calculated
	 * @param aVariableHashTable
	 *            the values for all variables used
	 * @return the result of the tree is returned as double value
	 * @throws CalculatingException
	 *             It is not possible to calculate the tree. An internal error
	 *             occurred.
	 */
	public static double EvaluateTree(Tree aTree, Hashtable<String, Double> aVariableHashTable)
			throws CalculatingException {
		// no tree? no calculation!
		if (aTree == null) {
			return 0;
		}
		Double tmpResult = 0.0;

		if (aTree.getRoot() instanceof NumberObj) {
			// is it a tree where there is only a number at the root? return the
			// number!
			tmpResult = ((NumberObj) aTree.getRoot()).getValue();
		} else if (aTree.getRoot() instanceof Variable) {
			if (aVariableHashTable != null
					&& aVariableHashTable.containsKey(Character.toString(((Variable) aTree.getRoot())
							.getValue()))) {
				tmpResult = aVariableHashTable
						.get(Character.toString(((Variable) aTree.getRoot()).getValue()));
			} else
				throw (new CalculatingException("Not all variables are assigned with values."));
		} else if (aTree.getRoot() instanceof Operator) {

			Operator tmpOperator = (Operator) aTree.getRoot();

			if (tmpOperator.getOperatorType() == OperatorType.ADDITION) {

				tmpResult = FormulaTreeUtil.EvaluateTree(aTree.getLeftSon(), aVariableHashTable)
						+ FormulaTreeUtil.EvaluateTree(aTree.getRightSon(), aVariableHashTable);

			} else if (tmpOperator.getOperatorType() == OperatorType.DIVISION) {

				Double tmpLeft = FormulaTreeUtil.EvaluateTree(aTree.getLeftSon(), aVariableHashTable);
				Double tmpRight = FormulaTreeUtil.EvaluateTree(aTree.getRightSon(), aVariableHashTable);
				if (tmpRight == 0)
					throw new CalculatingException("Division by 0 is not allowed.");
				tmpResult = tmpLeft / tmpRight;

			} else if (tmpOperator.getOperatorType() == OperatorType.MULTIPLICATION) {

				tmpResult = FormulaTreeUtil.EvaluateTree(aTree.getLeftSon(), aVariableHashTable)
						* FormulaTreeUtil.EvaluateTree(aTree.getRightSon(), aVariableHashTable);

			} else if (tmpOperator.getOperatorType() == OperatorType.SUBTRACTION) {

				tmpResult = FormulaTreeUtil.EvaluateTree(aTree.getLeftSon(), aVariableHashTable)
						- FormulaTreeUtil.EvaluateTree(aTree.getRightSon(), aVariableHashTable);

			} else if (tmpOperator.getOperatorType() == OperatorType.SIN) {

				tmpResult = Math.sin(Math.toRadians(FormulaTreeUtil.EvaluateTree(aTree.getRightSon(),
						aVariableHashTable)));

			} else if (tmpOperator.getOperatorType() == OperatorType.SQRT) {

				Double tmpRight = FormulaTreeUtil.EvaluateTree(aTree.getRightSon(), aVariableHashTable);
				if (tmpRight < 0)
					throw new CalculatingException("sqrt() can only be used with positive operands.");
				tmpResult = Math.sqrt(tmpRight);

			} else if (tmpOperator.getOperatorType() == OperatorType.TAN) {

				tmpResult = Math.tan(Math.toRadians(FormulaTreeUtil.EvaluateTree(aTree.getRightSon(),
						aVariableHashTable)));

			} else if (tmpOperator.getOperatorType() == OperatorType.COS) {

				tmpResult = Math.cos(Math.toRadians(FormulaTreeUtil.EvaluateTree(aTree.getRightSon(),
						aVariableHashTable)));

			} else if (tmpOperator.getOperatorType() == OperatorType.POW) {

				tmpResult = Math.pow(FormulaTreeUtil.EvaluateTree(aTree.getLeftSon(), aVariableHashTable),
						FormulaTreeUtil.EvaluateTree(aTree.getRightSon(), aVariableHashTable));
				if (tmpResult == null || tmpResult.toString().equalsIgnoreCase("NAN"))
					throw (new CalculatingException("Negative square root not allowed."));
			}
		} else
			throw (new CalculatingException("Not possible to calculate the formulaTree."));

		return Math.floor(tmpResult * 1000) / 1000;
	}

	private static Tree InsertMathObjIntoTree(Tree anOldestFatherTree, Tree aTreeToBeInserted) {

		// is the tree still empty? return the given tree as result
		if (anOldestFatherTree == null) {
			return aTreeToBeInserted;
		}

		Tree tmpTree = anOldestFatherTree;

		// go into the right branch as long as a.) there is a right branch AND
		// b.) the priority of the current branch is lower than the mathobj's
		// priority
		while (tmpTree.getRightSon() != null
				&& tmpTree.getRoot().getPriority() < aTreeToBeInserted.getRoot().getPriority()) {
			tmpTree = tmpTree.getRightSon();
		}

		// if it has a higher priority go left or change root
		if (tmpTree.getRoot().getPriority() >= aTreeToBeInserted.getRoot().getPriority()) {
			aTreeToBeInserted.setFather(tmpTree.getFather());
			aTreeToBeInserted.setLeftSon(tmpTree);

			if (tmpTree.getFather() != null) // is there a father?
			{
				tmpTree.getFather().setRightSon(aTreeToBeInserted);
			} else {
				// it is the oldest father
				anOldestFatherTree = aTreeToBeInserted;
			}
			tmpTree.setFather(aTreeToBeInserted);
			// else set right
		} else {
			aTreeToBeInserted.setFather(tmpTree);
			if (tmpTree.getRightSon() != null)
				aTreeToBeInserted.setLeftSon(tmpTree.getRightSon());
			tmpTree.setRightSon(aTreeToBeInserted);
		}

		return anOldestFatherTree;
	}

	/**
	 * Creates a new wonderful tree
	 *
	 * @param aFunction
	 * @return the built tree
	 * @throws CalculatingException
	 */
	public static Tree BuildTree(String aFunction) throws CalculatingException {
		// convert String into Math-List and call overloaded other method
		ArrayList<Object> MathList = MathUtil.FormulaToArrayList(aFunction);
		return BuildTree(MathList);
	}

	/**
	 * Creates a new wonderful tree
	 *
	 * @param MathList
	 *            a sorted list with math objects
	 * @return a tree (surprise, surprise!)
	 * @throws CalculatingException
	 *             if an error occurred
	 */
	@SuppressWarnings("unchecked")
	private static Tree BuildTree(ArrayList<Object> MathList) throws CalculatingException {
		// the root at the top of the tree - the father of all fathers, has
		// no father himself
		Tree Oldestfather = null;

		// iterate over all elements of the formula
		for (Object tmpNextElement : MathList) {

			// first of all, the current object is transformed into a tree
			Tree aTreeToBeInserted = null;

			if (tmpNextElement instanceof MathObj) {
				aTreeToBeInserted = new Tree((MathObj) tmpNextElement);
			} else if (tmpNextElement instanceof ArrayList) // in case of
			// brackets,
			// build a tree recursive
			{
				aTreeToBeInserted = FormulaTreeUtil.BuildTree((ArrayList<Object>) tmpNextElement);
				if (aTreeToBeInserted != null) // set highest priority manually
					aTreeToBeInserted.getRoot().setPriority(MathUtil.PRIO_BRACKETS);
			}

			// the tree cannot be null! error case
			if (aTreeToBeInserted == null) {
				throw new CalculatingException("Error in building the tree by reading the MathList.");
			}

			// insert into Tree
			Oldestfather = FormulaTreeUtil.InsertMathObjIntoTree(Oldestfather, aTreeToBeInserted);

		}

		return Oldestfather;
	}

	/**
	 * @param aTree
	 * @return the depth of the tree
	 */
	public static int getDepth(Tree aTree) {
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