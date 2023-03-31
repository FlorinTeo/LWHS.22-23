package main;

import java.util.HashMap;
import java.util.Map;

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
}