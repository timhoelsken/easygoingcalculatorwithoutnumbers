package calculator;

import java.util.ArrayList;

import junit.framework.Assert;

import calculator.elements.MathObj;
import calculator.elements.NumberObj;
import calculator.elements.Operand;
import calculator.elements.Operator;
import calculator.elements.OperatorType;
import calculator.elements.Tree;
import calculator.utils.MathUtil;


/**
 *
 */
public final class FormulaTree {
	
	private Tree formulaTree = null;

  /**
   * Calculates the result of a formula-tree
   *
   * @param aTree
   *            the tree from which the result should be calculated
   * @return the result of the tree is returned as float value
   * @throws Exception
   *             It is not possible to calculate the tree. An internal error
   *             occurred.
   */
  public static double EvaluateTree(Tree aTree) throws Exception {
    // no tree? no calculation!
    if (aTree == null)
      return 0;

    double tmpResult = 0;

    // is it a tree where there is only a number at the root?
    if (aTree.getRoot() instanceof NumberObj) {
      tmpResult = ((NumberObj) aTree.getRoot()).getValue();
    } else if (aTree.getRoot() instanceof Operator) {

      Operator tmpOperator = (Operator) aTree.getRoot();

      if (tmpOperator.getOperatorType() == OperatorType.ADDITION) {
        tmpResult = FormulaTree.EvaluateTree(aTree.getLeftSon())
            + FormulaTree.EvaluateTree(aTree.getRightSon());
      } else if (tmpOperator.getOperatorType() == OperatorType.DIVISION) {
        tmpResult = FormulaTree.EvaluateTree(aTree.getLeftSon())
            / FormulaTree.EvaluateTree(aTree.getRightSon());
      } else if (tmpOperator.getOperatorType() == OperatorType.MULTIPLICATION) {
        tmpResult = FormulaTree.EvaluateTree(aTree.getLeftSon())
            * FormulaTree.EvaluateTree(aTree.getRightSon());
      } else if (tmpOperator.getOperatorType() == OperatorType.SUBTRACTION) {
        tmpResult = FormulaTree.EvaluateTree(aTree.getLeftSon())
            - FormulaTree.EvaluateTree(aTree.getRightSon());
      }
      else if (tmpOperator.getOperatorType() == OperatorType.SIN) 
      {
    	  tmpResult = Math.sin(FormulaTree.EvaluateTree(aTree.getRightSon())); 
      }
      else if (tmpOperator.getOperatorType() == OperatorType.SQRT) 
      {
    	  tmpResult = Math.sqrt(FormulaTree.EvaluateTree(aTree.getRightSon())); 
      }
      else if (tmpOperator.getOperatorType() == OperatorType.TAN) 
      {
    	  tmpResult = Math.tan(FormulaTree.EvaluateTree(aTree.getRightSon())); 
      }
      else if (tmpOperator.getOperatorType() == OperatorType.COS) 
      {
    	  tmpResult = Math.cos(FormulaTree.EvaluateTree(aTree.getRightSon())); 
      }
    } else
      throw (new Exception("Not possible to calculate the formula-tree."));

    return tmpResult;
  }

  private static Tree InsertMathObjIntoTree(Tree aOldestFatherTree, Tree aTreeToBeInserted)
  {
	  assert(aTreeToBeInserted!=null);
	  
	  //is the tree still empty? return the given tree as result
	  if (aOldestFatherTree == null){			  
	      return aTreeToBeInserted;	      
	  }
	  
	  Tree tmpTree = aOldestFatherTree; 
	  	  
	  //go into the right branch as long as, a.) there is a right branch b.) the priority of the current branch is lower than the mathobj�s priority
	  while (tmpTree.getRightSon()           != null && 
			 tmpTree.getRoot().getPriority() <  aTreeToBeInserted.getRoot().getPriority()) 
	  {
        tmpTree = tmpTree.getRightSon();
      }
	  
	  if (tmpTree.getRoot().getPriority() >= aTreeToBeInserted.getRoot().getPriority() )
	  {
		  aTreeToBeInserted.setFather(tmpTree.getFather());
		  aTreeToBeInserted.setLeftSon(tmpTree);		  

		  if (tmpTree.getFather() != null) // is there a father
		  {
	          tmpTree.getFather().setRightSon(aTreeToBeInserted);
		  }
		  else
		  {
			  aOldestFatherTree = aTreeToBeInserted; //it is the oldest father
		  }
	      tmpTree.setFather(aTreeToBeInserted);  
	  }       
	  else 
	  {
		  aTreeToBeInserted.setFather(tmpTree);
		  aTreeToBeInserted.setLeftSon(tmpTree.getRightSon());		 
          tmpTree.setRightSon(aTreeToBeInserted);
      }
	  
	  return aOldestFatherTree;
  }
  
  /**
   * this builds a binary formula tree :-)
   *
   * @param aFunction
   * @return the built tree
   * @throws Exception
   */
  public Tree BuildTree(String aFunction) throws Exception {

	// prepare formula, mathobj and tmptree	
	ArrayList<Object> MathList = MathUtil.FormulaToArrayList(aFunction);
	
	return BuildTree(MathList);	
  }  

  @SuppressWarnings("unchecked")
public static Tree BuildTree(ArrayList<Object> MathList) throws Exception
  {
	  	Tree Oldestfather = null;
	
	  	// iterate over all elements of the formula
	    for (Object tmpNextElement:MathList){
	      	
	    	Tree aTreeToBeInserted = null;
	    	
	    	if (tmpNextElement instanceof MathObj)
	    	{
	    		aTreeToBeInserted = new Tree ((MathObj)tmpNextElement);
	    	}
	    	else if (tmpNextElement instanceof ArrayList) 
	    	{
	    		aTreeToBeInserted = FormulaTree.BuildTree((ArrayList<Object>)tmpNextElement);	    		
	    	}	
	    	
	    	if (aTreeToBeInserted == null)
	    	{
	    		throw new Exception("Error in Bulding the Tree by reading from MathList");
	    	}	    	
	    	
	    	//insert into Tree
	    	Oldestfather = FormulaTree.InsertMathObjIntoTree(Oldestfather, aTreeToBeInserted);

	    }

	    return Oldestfather;
  }  
  
  
  
  /**
   * Inserts an operand into an existing tree
   *
   * @param anExistingTree
   * @param anOperand
   * @throws Exception
   */
/*  private void InsertOperandIntoTree(Operand anOperand) {

    if (formulaTree == null) {
      formulaTree = new Tree(anOperand);
      return;
    }

    Tree tmpTree = formulaTree;
    while (tmpTree.getRightSon() != null)
      tmpTree = tmpTree.getRightSon();
    tmpTree.setRightSon(new Tree(anOperand, tmpTree, null, null));
  }*/
  
  

 /* *//**
   * Inserts an operator into an existing tree
   *
   * @param anExistingTree
   * @param anOperator
   *//*
  private void InsertOperatorIntoTree(Operator anOperator) {

    assert (formulaTree != null);

    // is the root in the existing tree an operand?
    if (formulaTree.getRoot() instanceof Operand) {
      // if it is, build a new tree with Operator (father), Operand (Left Son)
      Tree tmpTree = new Tree(anOperator, null, formulaTree, null);
      formulaTree.setFather(tmpTree);
      formulaTree = tmpTree;
    }
    // is the root in the existing tree an operator?
    else {

      assert (formulaTree.getRoot() instanceof Operator);

      Tree tmpTree = formulaTree;

      while (tmpTree.getRightSon() != null && tmpTree.getRightSon().getRoot() instanceof Operator
          && ((Operator) tmpTree.getRoot()).getPriority() < anOperator.getPriority()) {

        tmpTree = tmpTree.getRightSon();
      }

      if (((Operator) tmpTree.getRoot()).getPriority() >= anOperator.getPriority()) {
        Tree tmpHelpTree = new Tree(anOperator, tmpTree.getFather(), tmpTree, null);
        if (tmpTree.getFather() != null)
          tmpTree.getFather().setRightSon(tmpHelpTree);
        tmpTree.setFather(tmpHelpTree);
        if (tmpHelpTree.getFather() == null)
          formulaTree = tmpHelpTree;
      } else {
        Tree tmpHelpTree = new Tree(anOperator, tmpTree, tmpTree.getRightSon(), null);
        tmpTree.setRightSon(tmpHelpTree);
      }
    }
  }*/

  
}