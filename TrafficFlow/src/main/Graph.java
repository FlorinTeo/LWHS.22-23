package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class Graph<T extends Comparable<T>>{
	private Map<Integer, Node<T>> _nodes;
	
	public Graph() {
        _nodes = new TreeMap<Integer, Node<T>>();
    }
	
	public void addNode(T data) {
        int nodeHash = data.hashCode();
        if (_nodes.containsKey(nodeHash)) {
            throw new RuntimeException("Ambiguous graph!");
        }
        
        _nodes.put(nodeHash, new Node<T>(data));
    }
	
	public void addEdge(T from, T to) {
        Node<T> fromNode = _nodes.get(from.hashCode());
        Node<T> toNode = _nodes.get(to.hashCode());
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }
        
        fromNode.addEdge(toNode);
    }
	
	public String toString() {
        String output = "";
        boolean first = true;
        for(Node<?> n : _nodes.values()) {
            if (!first) {
                output += "\n";
            }
            output += n.toString();
            first = false;
        }
        
        return output;
    }
	
	public Set<T> getNeighbors(T route) {
		Node<T> routeNode = _nodes.get(route.hashCode());
		if(routeNode == null) {
			return new HashSet<T>();
		}
	
		
		return routeNode.getCollidingRoutes();
		
	}
	public void graphColoring() {
		Queue<Node<T>> unlabeledQueue = new LinkedList<Node<T>>();
		unlabeledQueue.addAll(_nodes.values());
		int label = 1;
		while(!unlabeledQueue.isEmpty()) {
			greedy(label, unlabeledQueue);
			label++;
			
		}
		
	}
	
	public void greedy(int label, Queue<Node<T>> unlabeledQ) {
		Set<Node<T>> labelSet = new HashSet<Node<T>>();
		int nUnlabeled = unlabeledQ.size();
	    for (int i = 0; i < nUnlabeled; i++) {
	        Node<T> node = unlabeledQ.remove();
			if(!node.isConnected(labelSet)) {
				node.label(label);
				labelSet.add(node);
			} else {
			    unlabeledQ.add(node);
			}
		}
	}
	
	public Set<T> getLabeledData(int label) {
	    Set<T> labeledDataSet = new HashSet<T>();
	    for(Node<T> node : _nodes.values()) {
	        if (node.getLabel() == label) {
	            labeledDataSet.add(node.getData());
	        }
	    }
	    return labeledDataSet;
	}
}