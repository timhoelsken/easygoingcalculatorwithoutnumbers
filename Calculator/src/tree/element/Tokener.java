package tree.element;

import math.element.object.*;
import tree.element;

/**
 *
 */
public final class Tokener {

	/**
	 * parse the input function
	 *
	 * @param aFunction
	 */
	public void ParseFunction(String aFunction) {
	
	    //mathematical formular
		Formula aFormula = new Formula(aFunction);
		//An element of the formular
		MathObj NextElement = null;
		//Tree
		Tree aTree = null;
	    
	    NextElement = aFormula.getNextElement();
	    while ( NextElement.getMathType() != MathType.END_OF_TERM)
	    {
	    	switch (NextElement.getMathType()) {
	    		
	    		case MathType.NUMBER.:
	    			InsertNumber(aTree, (NumberObj) NextElement);
	    			break;
	    			
	    		case MathType.OPERATOR :	    			 			
	    			
	    			InsertOperator(aTree, NextElement);
	    			break;
	    	}	    	
	    }
	}
	
	/**
	 * @author Raphael
	 * @param aTree The tree in which the new operator should be inserted
	 * @param anOperator The operator which will be inserted into the tree
	 * @return the inserted node (operator)
	 * 
	 * This method inserts an operator into an existing tree and returns the new node
	 */
	private Tree InsertOperator(Tree aTree, Operator anOperator)
	{
		//is it a empty tree? init it!
		if ( aTree == null) {
    		aTree = new Tree(anOperator);
    	}	
		
		//first case: the node is a Number -> append it to the left
	    if (aTree.getRoot().getMathType() == MathType.NUMBER)
	    {
	    	Tree NewTree = new Tree(anOperator,null,aTree,null);
			aTree.setFather(NewTree);
			return NewTree;
	    }
	    
	    //second case: the node is an operator -> check priority!
	    if (aTree.getRoot().getMathType() == MathType.OPERATOR)
	    {
	    	Operator ExistingOp = (Operator) aTree.getRoot();
	    	
	    	if (ExistingOp.getOperatorType().getPriority() < anOperator.getOperatorType().getPriority()) {
	    		//the priority is lower than the one in the node
	    		//create a new node and append the existing node to the left!
	    		Tree NewTree = new Tree(anOperator,null,aTree,null);
	    		aTree.setFather(NewTree);	
	    	}
	    	else
	    	{	    		
	    		//the priority is higher or equal than the one in the existing node
	    		//append the new one to the right
	    		Tree NewTree = new Tree(anOperator,null,aTree,null);
	    		aTree.setFather(NewTree);
	    	}
	  
	    }
		
	}
	
	/**
	 * @author Raphael
	 * @param aTree The tree in which the new number should be inserted
	 * @param anElement The number which will be inserted into the tree
	 * @return the inserted node (number)
	 * 
	 * This method inserts a number into an existing tree and returns the new node
	 */
	private Tree InsertNumber(Tree aTree, NumberObj anNumber)
	{
		//is it a empty tree? init it!
		if ( aTree == null) {
    		aTree = new Tree(anNumber);
    		return aTree;
    	}	
		
		// a number will always be appended to the right
		// if the right is already assigned an error has occurred (assuming a syntax error9
		if (aTree.getRightSon()!= null)	{
			throw(new Exception("Syntax Error"));
		}
		
		//create a new tree and append it to the right of the father
		Tree NewTree = new Tree(anNumber,aTree,null,null);
		aTree.setRightSon(NewTree);
	}	
}
