import java.util.HashMap;
import java.util.Map;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private Map<Integer, Node<T>> _edges;
    private T _data;
    
    public Node(T data) {
        _data = data;
        _edges = new HashMap<Integer, Node<T>>();
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
}
