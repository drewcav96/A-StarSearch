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
	private boolean _isTraversable;
	private boolean _isStart;
	private boolean _isEnd;
	private boolean _isPath;
	private Node _parent;
	
	public Node(int row, int col, boolean isTraversable) {
		_row = row;
		_col = col;
		_isTraversable = isTraversable;
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
	
	public void setPath() {
		_isPath = true;
	}
	
	public void setStart() throws IllegalStateException {
		if (!_isEnd) {
			_isStart = true;
		} else {
			throw new IllegalStateException(String.format("The Node at %s, %s is already an ending position!", _row, _col));
		}
	}
	
	public void setEnd() throws IllegalStateException {
		if (!_isStart) {
			_isEnd = true;
		} else {
			throw new IllegalStateException(String.format("The Node at %s, %s is already a starting position!", _row, _col));
		}
	}
	
	public boolean isTraversable() {
		return _isTraversable;
	}
	
	public boolean isStart() {
		return _isStart;
	}
	
	public boolean isEnd() {
		return _isEnd;
	}
	
	public boolean isPath() {
		return _isPath;
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
		return String.format("Node: (%d, %d)", _row + 1, _col + 1);
	}
}
