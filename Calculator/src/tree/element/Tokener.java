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
	    		
	    		case MathType.NUMBER:
	    			
	    			InsertNumber(aTree, (NumberObj) NextElement);
	    			break;
	    			
	    		case MathType.OPERATOR :	    			 			
	    			
	    			InsertOperator(aTree, NextElement);
	    			break;
	    	}	
	    	
	    	NextElement = aFormula.getNextElement();
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
    		return aTree;
    	}	
		
		//case 1: the node is a Number -> append it to the left
	    if (aTree.getRoot().getMathType() == MathType.NUMBER)
	    {
	    	Tree NewTree;
	    	
	    	//case 1.1: the existing number has no father
	    	//=> set number as left son of operator
	    	if (aTree.getFather()== null){
	    		NewTree = new Tree(anOperator,null,aTree,null);
	    		aTree.setFather(NewTree);			
	    	}
	    	
	    	//case 1.1: the existing number has an operator as father
	    	//=> check priority of father
	    	if ( aTree.getFather().getRoot().getMathType() == MathType.OPERATOR ){
	    		//check priority
	    		Operator anOperator2 = (Operator) aTree.getFather().getRoot().getMathType();
	    		
	    		if (anOperator2.getOperatorType().getPriority() >  anOperator.getOperatorType().getPriority())
	    		{
	    			NewTree = new Tree(anOperand,aTree.getFather().getFather(),)
	    		}    		
	    	}   	
	    }
	    
	    return NewTree;
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
		// if the right is already assigned an error has occurred (assuming a syntax error)
		if (aTree.getRightSon()!= null)	{
			throw(new Exception("Syntax Error"));
		}
		
		//create a new tree and append it to the right of the father
		Tree NewTree = new Tree(anNumber,aTree,null,null);
		aTree.setRightSon(NewTree);
		aTree = NewTree; //set view on included node
	}	
}
