import java.util.HashSet;
import java.util.Set;

public class Node<T> {
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
        return _data.toString();
    }
}
