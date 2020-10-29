package edu.uncc.rcavana1;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The Agent observer class which performs the A* Search.
 * 
 * @author Drew Cavanaugh (rcavana1@uncc.edu)
 * @since 10/29/2020
 */
public class Agent {
	public static final int HEURISTIC_MODIFIER = 10;
	
	private World _world;
	private Node _startNode;
	private Node _endNode;
	private ArrayList<Node> _closedList;
	private ArrayList<Node> _openList;
	private boolean _isPathFound;
	
	public Agent(World world) {
		_world = world;
		_closedList = new ArrayList<Node>();
		_openList = new ArrayList<Node>();
		_isPathFound = false;
	}
	
	public void findPath() {
		_startNode = _world.getStartingPosition();
		_endNode = _world.getEndingPosition();
		_startNode.setG(0);
		_startNode.setH(getHeuristic(_startNode));
		_startNode.setF();
		_startNode.setParent(null);
		_openList.add(_startNode);
		Node currentNode = null;
		
		while (!_openList.isEmpty()) {
			currentNode = getSmallestF();
			_openList.remove(currentNode);
			if (currentNode.isEnd()) {
				_isPathFound = true;
			}
			updateOpenList(currentNode);
			_closedList.add(currentNode);
		}
	}
	
	public boolean hasShortestPath() {
		return _isPathFound;
	}
	
	public LinkedList<Node> getShortestPath() {
		LinkedList<Node> shortestPath = new LinkedList<Node>();
		Node currentNode = _endNode;
		
		do {
			shortestPath.add(currentNode);
			currentNode.setPath();
			currentNode = currentNode.getParent();
		} while (currentNode != null);
		return shortestPath;
	}
	
	public int getHeuristic(Node node) {
		int heuristic = 0;
		
		// perform Manhattan method for rows
		heuristic += HEURISTIC_MODIFIER * Math.abs(_endNode.getRow() - node.getRow());
		// perform Manhattan method for columns
		heuristic += HEURISTIC_MODIFIER * Math.abs(_endNode.getCol() - node.getCol());
		return heuristic;
	}
	
	private void updateOpenList(Node currentNode) {
		ArrayList<Node> nodeNeighbors = _world.getNodeNeighbors(currentNode);
		
		for (Node node : nodeNeighbors) {
			if (!_closedList.contains(node) && node.isTraversable()) {
				int rowOffset = node.getRow() - currentNode.getRow();
				int colOffset = node.getCol() - currentNode.getCol();
				int distance = (int) Math.round(10.0 * Math.sqrt(Math.pow(rowOffset, 2) + Math.pow(colOffset, 2)));
				int cost = currentNode.getG() + distance;
				
				if (node.getG() == 0 || cost < node.getG()) {
					node.setG(cost);
					node.setH(getHeuristic(node));
					node.setF();
					node.setParent(currentNode);
					if (!_openList.contains(node)) {
						_openList.add(node);
					}
				}
			}
		}
	}
	
	private Node getSmallestF() {
		Node smallFNode = null;
		
		for (Node node : _openList) {
			if (smallFNode == null) {
				smallFNode = node;
			} else if (node.getF() < smallFNode.getF()) {
				smallFNode = node;
			}
		}
		return smallFNode;
	}
}
