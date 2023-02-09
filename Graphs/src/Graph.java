import java.util.HashMap;
import java.util.Map;

public class Graph<T extends Comparable<T>> {
    private Map<Integer, Node<T>> _nodes;
    
    public Graph() {
        _nodes = new HashMap<Integer, Node<T>>();
    }
    
    public void addNode(T data) {
        int nodeHash = data.hashCode();
        if (_nodes.containsKey(nodeHash)) {
            throw new RuntimeException("Ambiguous graph!");
        }
        
        _nodes.put(nodeHash, new Node<T>(data));
    }
    
    public void addEdge(T fromData, T toData) {
        Node<T> fromNode = _nodes.get(fromData.hashCode());
        Node<T> toNode = _nodes.get(toData.hashCode());
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }
        
        fromNode.addEdge(toNode);
        toNode.addEdge(fromNode);
    }
    
    @Override
    public String toString() {
        String output = "#Nodes: " + _nodes.size();
        for(Node<?> n : _nodes.values()) {
            output += "\n" + n.toString(); 
        }
        
        return output;
    }
}
