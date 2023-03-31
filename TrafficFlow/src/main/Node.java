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
	
    /**
     * Gives a String representation of this Node as a space-separated sequence of token:
     * The string representation of the <i>_data</i> followed by ' > ' followed by a space
     * separated sequence of tokens, one for each of this Node's neighbors.
     * <br>E.g: If this node is A and is linked to nodes B and C, this method returns:
     * <pre>"A > B C"</pre>
     * If this node is A and has no neighbors (no outogoing / egress Edges), this method returns:
     * <pre>"A > "</pre>
     * @return String reflecting the content and structure of this Node.
     */
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
}