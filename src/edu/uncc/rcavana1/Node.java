package edu.uncc.rcavana1;

/**
 * Node class.
 * @author Drew Cavanaugh (rcavana1@uncc.edu)
 * @since 10/29/2020
 */
public class Node {
	private int _row;
	private int _col;
	private int _f;
	private int _g;
	private int _h;
	// type 0 is traversable, 1 is not
	private int _type;
	private Node _parent;
	
	public Node(int row, int col, int type) {
		_row = row;
		_col = col;
		_type = type;
		_parent = null;
	}
	
	// mutator methods to set values
	
	public void setF() {
		_f = _g + _h;
	}
	
	public void setG(int value) {
		_g = value;
	}
	
	public void setH(int value) {
		_h = value;
	}
	
	public void setParent(Node node) {
		_parent = node;
	}
	
	// accessor methods to get values
	
	public int getF() {
		return _f;
	}
	
	public int getG() {
		return _g;
	}
	
	public int getH() {
		return _h;
	}
	
	public Node getParent() {
		return _parent;
	}
	
	public int getRow() {
		return _row;
	}
	
	public int getCol() {
		return _col;
	}
	
	public boolean equals(Object in) {
		// typecast to Node
		Node n = (Node) in;
		
		return _row == n.getRow() &&
				_col == n.getCol();
	}
	
	public String toString() {
		return "Node :" + _row + "_" + _col;
	}
}
