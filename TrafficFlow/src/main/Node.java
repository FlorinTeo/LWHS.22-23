package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
	private Map<Integer, Node<T>> _edges;
    private Map<Integer, Node<T>> _backwardsEdges;
    private T _data;
    private int _state;
    
    public Node(T data) {
        _data = data;
        _edges = new HashMap<Integer, Node<T>>();
        _backwardsEdges = new HashMap<Integer,Node<T>>();
        _state = 0;
    }
    
    public void addEdge(Node<T> otherNode) {
        _edges.put(otherNode._data.hashCode(), otherNode);
        otherNode._backwardsEdges.put(this._data.hashCode(), this);
    }

	@Override
	public int compareTo(Node<T> other) {
        return _data.compareTo(other._data);
    }
	
	@Override
    public String toString() {
        String output = _data.toString() + " > ";
        boolean first = true;
        for(Node<?> n : _edges.values()) {
            if (!first) {
                output += " ";
            }
            output += n._data.toString();
            first = false;
        }
        return output;
    }
	
	public T getData() {
        return _data;
    }
	
	public Set<String> getCollidingRoutes(){
		Set<String> collidingR = new HashSet<String>();
		for(Node<T> n: _edges.values()) {
			collidingR.add((String) n.getData());
		}
		return collidingR;
		
	}
}