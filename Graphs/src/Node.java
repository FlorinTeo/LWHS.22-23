import java.util.HashSet;
import java.util.Set;

public class Node<T> implements Comparable {
    private Set<Node<T>> _edges;
    private T _data;
    
    public Node(T data) {
        _data = data;
        _edges = new HashSet<Node<T>>();
    }
    
    public void addEdge(Node<T> otherNode) {
        _edges.add(otherNode);
    }
    
    @Override
    public String toString() {
        String output = _data.toString() + " > [";
        boolean first = true;
        for(Node<?> n : _edges) {
            if (!first) {
                output += ", ";
            }
            output += n._data.toString();
            first = false;
        }
        output += "]";
        return output;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Node<?>)) {
            throw new RuntimeException("Invalid comparison!");
        }
        return ((Comparable) _data).compareTo(((Node<?>)o)._data);
    }
}
