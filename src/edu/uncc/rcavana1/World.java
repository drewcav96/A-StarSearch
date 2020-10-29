package edu.uncc.rcavana1;

import java.util.ArrayList;

/**
 * Tile-based environment containing Nodes.
 * 
 * @author Drew Cavanaugh (rcavana1@uncc.edu)
 * @since 10/29/2020
 */
public class World {
	public final static double UNPATHABLE_PROBABILITY = 0.1;
	public final static String TRAVERSABLE = " - ";
	public final static String NON_TRAVERSABLE = " X ";
	public final static String PATH_TILE = " o ";
	
	private int _gridSize;
	private Node[][] _grid;
	
	public World(int gridSize) {
		_gridSize = gridSize;
		_grid = new Node[_gridSize][_gridSize];
		worldGen();
	}
	
	public int getSize() {
		return _gridSize;
	}
	
	public Node getNode(int row, int col) {
		return _grid[row][col];
	}
	
	public ArrayList<Node> getNodeNeighbors(Node node) {
		int upperBound = node.getRow() == 0 ? 0 : -1;
		int leftBound = node.getCol() == 0 ? 0 : -1;
		int lowerBound = node.getRow() == _gridSize - 1 ? 0 : 1;
		int rightBound = node.getCol() == _gridSize - 1 ? 0 : 1;
		ArrayList<Node> nodeNeighbors = new ArrayList<Node>();
		
		for (int i = upperBound; i <= lowerBound; i++) {
			for (int j = leftBound; j <= rightBound; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				nodeNeighbors.add(getNode(node.getRow() + i, node.getCol() + j));
			}
		}
		return nodeNeighbors;
	}
	
	public void setStartingPosition(int row, int col) {
		Node node = _grid[row][col];
		
		node.setStart();
	}
	
	public void setEndingPosition(int row, int col) {
		Node node = _grid[row][col];
		
		node.setEnd();
	}
	
	public Node getStartingPosition() {
		for (Node[] subGrid : _grid) {
			for (Node node : subGrid) {
				if (node.isStart()) {
					return node;
				}
			}
		}
		throw new IllegalStateException("The starting position is not set!");
	}
	
	public Node getEndingPosition() {
		for (Node[] subGrid : _grid) {
			for (Node node : subGrid) {
				if (node.isEnd()) {
					return node;
				}
			}
		}
		throw new IllegalStateException("The ending position is not set!");
	}
	
	public String toString() {
		String str = "\t   ";
		
		for (int i = 1; i <= _gridSize; i++) {
			if (i < 10) {
				str += String.format(" %d ", i);
			} else {
				str += String.format("%d ", i);
			}
		}
		str += "\r\n\t";
		for (int i = 0; i < _gridSize; i++) {
			if (i < 9) {
				str += String.format(" %d ", i + 1);
			} else {
				str += String.format("%d ", i + 1);
			}
			for (Node node : _grid[i]) {
				if (node.isTraversable()) {
					if (node.isPath()) {
						str += PATH_TILE;
					} else {
						str += TRAVERSABLE;
					}
				} else {
					str += NON_TRAVERSABLE;
				}
			}
			str += "\r\n\t";
		}
		return str;
	}
	
	private void worldGen() {
		for (int i = 0; i < _gridSize; i++) {
			for (int j = 0; j < _gridSize; j++) {
				double rng = Math.random();
				
				if (rng <= UNPATHABLE_PROBABILITY) {
					_grid[i][j] = new Node(i, j, false);
				} else {
					_grid[i][j] = new Node(i, j, true);
				}
			}
		}
	}
}
