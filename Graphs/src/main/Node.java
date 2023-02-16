package main;
import java.util.HashMap;
import java.util.Map;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private Map<Integer, Node<T>> _edges;
    private T _data;
    private int _state;
    
    // Region: Core Graph Node methods
    public Node(T data) {
        _data = data;
        _edges = new HashMap<Integer, Node<T>>();
        _state = 0;
    }
    
    public T getData() {
        return _data;
    }
    
    public int getState() {
        return _state;
    }
    
    public void addEdge(Node<T> otherNode) {
        _edges.put(otherNode._data.hashCode(), otherNode);
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

    @Override
    public int compareTo(Node<T> o) {
        return _data.compareTo(o._data);
    }
    // EndRegion: Core Graph Node methods

    public void reset() {
        _state = 0;
    }
    
    public boolean isUNode() {
        boolean uNode = true;
        for (Node<?> n : _edges.values()) {
            if (n._state != 1 && n._edges.get(_data.hashCode()) != this) {
                uNode = false;
                break;
            }
        }
        _state = 1;
        return uNode;
    }
}
