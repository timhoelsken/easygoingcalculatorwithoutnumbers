package pse3;

public enum OperatorType {
	Addition('+'),Subtraction('-'),Multiplication('*'),Division('/');
	
	private char p_Op;
	
	private OperatorType(char i_Op) { p_Op = i_Op;}
	
	public char getOpAsChar() { return p_Op; }
}
