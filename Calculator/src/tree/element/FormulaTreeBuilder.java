package tree.element;


import math.element.object.Formula;
import math.element.object.MathObj;
import math.element.object.MathType;
import math.element.object.NumberObj;
import math.element.object.Operator;
import math.element.object.Operand;
import math.element.object.OperatorType;

/**
 * 
 */
public final class FormulaTreeBuilder {

	/**
	 * Calculates the result of a formula-tree
	 * @param aTree the tree from which the result should be calculated
	 * @return the result of the tree is returned as float value
	 * @throws Exception It is not possible to calculate the tree. An internal error occurred.
	 */
	public static float EvaluateTree(Tree aTree) throws Exception {
		//no tree? no calculation!
		if (aTree == null)	return 0;

		float tmpResult = 0;

		//is it a tree where there is only a number at the root?
		if (aTree.getRoot() instanceof NumberObj) {
			tmpResult = ((NumberObj) aTree.getRoot()).getValue();
		} 
		else if (aTree.getRoot() instanceof Operator){ 
			
			Operator tmpOperator = (Operator) aTree.getRoot();
			
			if (tmpOperator.getOperatorType() == OperatorType.ADDITION) {				
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())	+ FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.DIVISION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())	/ FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.MULTIPLICATION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon())	* FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			} else if (tmpOperator.getOperatorType() == OperatorType.SUBTRACTION) {
				tmpResult = FormulaTreeBuilder.EvaluateTree(aTree.getLeftSon()) - FormulaTreeBuilder.EvaluateTree(aTree.getRightSon());
			}
		}
		else throw (new Exception("Not possible to calculate the formula-tree."));

		return tmpResult;
	}
	


	/**
	 * Inserts an operand into an existing tree
	 * @param anExistingTree 
	 * @param anOperand
	 * @return the tree is returned with the focus on the father-operator of the inserted operand
	 * @throws Exception
	 */
	private void InsertOperandIntoTree(Operand anOperand){

		if (formulaTree==null){
			formulaTree=new Tree(anOperand);
			return;
		}
	
		Tree tmpTree=formulaTree;
		while (tmpTree.getRightSon()!=null) tmpTree=tmpTree.getRightSon();
		tmpTree.setRightSon(new Tree(anOperand,tmpTree,null,null));
		
	}

	/**
	 * Inserts an operator into an existing tree
	 * @param anExistingTree
	 * @param anOperator
	 * @return the tree is returned with the focus on the new operator
	 */
	private void InsertOperatorIntoTree(Operator anOperator) {

		assert(formulaTree!=null);	
		
		// is the root in the existing tree an operand?		
		if (formulaTree.getRoot() instanceof Operand) {
			// if it is, build a new tree with Operator (father), Operand (Left Son)
			Tree tmpTree = new Tree(anOperator, null,formulaTree, null);
			formulaTree.setFather(tmpTree);
			formulaTree=tmpTree;
		}		
		// is the root in the existing tree an operator?
		else {
			
			assert(formulaTree.getRoot() instanceof Operator);
			
			Tree tmpTree = formulaTree;
			
			while (tmpTree.getRightSon()!=null                                               && 
				   tmpTree.getRightSon().getRoot() instanceof Operator                       &&
				  ((Operator)tmpTree.getRoot()).getPriority() < anOperator.getPriority())	{
				
				tmpTree=tmpTree.getRightSon();
			}
			
			if (((Operator)tmpTree.getRoot()).getPriority() >= anOperator.getPriority())
			{
			   Tree tmpHelpTree = new Tree(anOperator, tmpTree.getFather(),tmpTree,null);
			   if (tmpTree.getFather()!=null)   tmpTree.getFather().setRightSon(tmpHelpTree);
			   tmpTree.setFather(tmpHelpTree);
			}
			else
			{
				Tree tmpHelpTree = new Tree(anOperator, tmpTree,tmpTree.getRightSon(),null);
				tmpTree.setRightSon(tmpHelpTree);		
			}			
		}	
	}

	/**t
	 * Builds a binary formula tree :-)
	 * 
	 * @param aFunction
	 * @throws Exception 
	 */
	public Tree BuildTree(String aFunction) throws Exception {

		// prepare formula, mathobj and tmptree
		Formula tmpFormula = new Formula(aFunction);
		MathObj tmpNextElement = null;
		formulaTree = null;		

		// iterate over all elements of the formula
		tmpFormula.setStartElement();
		tmpNextElement = tmpFormula.getNextElement();
		while (tmpNextElement.getMathType() != MathType.END_OF_TERM) {

			// is the next element an Operand??
			if (tmpNextElement instanceof Operand) {
				this.InsertOperandIntoTree((Operand) tmpNextElement);
			} 
			else if (tmpNextElement instanceof Operator) {
				this.InsertOperatorIntoTree((Operator) tmpNextElement);
			}
			
			tmpNextElement = tmpFormula.getNextElement();
		}
		
		return formulaTree;
	}
	
	private Tree formulaTree = null;
}
