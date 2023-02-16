package main;
import java.util.HashMap;
import java.util.Map;

public class DGraph<T extends Comparable<T>> {
    private Map<Integer, Node<T>> _nodes;
    
    // Region: Core Directed Graph methods
    public DGraph() {
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
    }
    
    public int size() {
        return _nodes.size();
    }
    
    public boolean checkState(int marker) {
        for (Node<?> n : _nodes.values()) {
            if (marker != n.getState()) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
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
    // EndRegion: Core Directed Graph methods
    
    public void reset() {
        for(Node<?> node : _nodes.values()) {
            node.reset();
        }
    }
    
    public boolean isUGraph() {
        boolean uGraph = true;
        for(Node<?> node : _nodes.values()) {
            if (!node.isUNode()) {
                uGraph = false;
                break;
            }
        }
        
        reset();
        return uGraph;
    }
}
