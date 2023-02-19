package main;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Graph<T extends Comparable<T>> {
    private Map<Integer, Node<T>> _nodes;
    
    // Region: Core Graph methods
    public Graph() {
        _nodes = new HashMap<Integer, Node<T>>();
    }
    
    public int size() {
        return _nodes.size();
    }
    
    public boolean checkState(int state) {
        for (Node<?> n : _nodes.values()) {
            if (state != n.getState()) {
                return false;
            }
        }
        
        return true;
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
    
    public void removeEdge(T fromData, T toData) {
        Node<T> fromNode = _nodes.get(fromData.hashCode());
        Node<T> toNode = _nodes.get(toData.hashCode());
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }
        
        fromNode.removeEdge(toNode);
    }
    
    public void removeNode(T data) {
        Node<T> node = _nodes.get(data.hashCode());
        for(Node<T> n : _nodes.values()) {
            n.removeEdge(node);
        }
        _nodes.remove(data.hashCode());
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
    // EndRegion: Core Graph methods
    
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
    
    public boolean isConnected() {
        boolean connected = true;
        Iterator<Node<T>> iNodes = _nodes.values().iterator();
        while(connected && iNodes.hasNext()) {
            Node<T> node = iNodes.next();
            node.traverse();
            for (Node<?> n : _nodes.values()) {
                if (n.getState() != 1) {
                    connected = false;
                    break;
                }
            }
            reset();
        }
        return connected;
    }
    
    public boolean isDAGraph() {
        boolean dag = true;
        Iterator<Node<T>> iNodes = _nodes.values().iterator();
        while(dag && iNodes.hasNext()) {
            Node<T> n = iNodes.next();
            dag = !n.loops(n);
            reset();
        }        
        return dag;
    }
}
