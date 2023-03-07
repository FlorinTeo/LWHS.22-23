package main;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class definition for a generic (Directed) Graph.
 * The Graph contains a collection of Nodes, each Node contains
 * a collection of references (edges) to their neighboring Nodes.
 * @param <T> - reference type of Nodes contained in the Graph.
 * The type T is expected to implement the Comparable interface, 
 * such that Nodes can be compared to each other.<br>
 * E.g.:<pre>Graph&lt;String&gt; g = new Graph&ltString&gt();</pre>
 * @see Node
 */
public class Graph<T extends Comparable<T>> {

    /**
     * Private Map keying each Node in the Graph by the hashCode of its data
     * E.g: Given <pre>Node<String> n = new Node<String>("abc");</pre> added to the graph,
     * the _nodes map contains a Map.Entry with
     * <pre>key="abc".hashCode()<br>value=n<pre>
     * @see java.lang.Object#hashCode()
     */
    private Map<Integer, Node<T>> _nodes;
    
    /**
     * Constructs a new Graph as an empty container fit for Nodes of the type T.
     * @see Graph
     * @see Node
     */
    public Graph() {
        _nodes = new TreeMap<Integer, Node<T>>();
    }
    
    /**
     * Gets the size of this Graph. The size of the Graph is equal to the number
     * of Nodes it contains.
     * @return number of Nodes in this Graph.
     */
    public int size() {
        return _nodes.size();
    }
    
    /**
     * Checks if the state of all the Nodes in the Graph matches a given value.
     * @param state - the value to check against all Nodes in the Graph.
     * @return true if all the Nodes in the Graph have a state matching the
     * given value, false otherwise.
     * @see Node#getState()
     */
    public boolean checkState(int state) {
        for (Node<?> n : _nodes.values()) {
            if (state != n.getState()) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Adds a new Node to the Graph containing the <i>data</i>. The method 
     * throws if the Graph already contains a Node with data having the same
     * hashCode().
     * @param data - the data reference (of type T) contained in the new Node.
     * @throws RuntimeException if the Graph already contains a Node for the
     * given data.
     * @see java.lang.Object#hashCode()
     */
    public void addNode(T data) {
        int nodeHash = data.hashCode();
        if (_nodes.containsKey(nodeHash)) {
            throw new RuntimeException("Ambiguous graph!");
        }
        
        _nodes.put(nodeHash, new Node<T>(data));
    }
    
    /**
     * Adds a new directed Edge to the Graph, linking the Nodes containing
     * <i>from</i> and <i>to</i> data. It is expected the two Nodes exist
     * otherwise the method throws an exception.
     * @param from - Node where the Edge is starting.
     * @param to - Node where the Edge is ending.
     * @throws RuntimeException if either of the two Nodes are not present in the Graph.
     * @see Node
     * @see Graph#removeEdge(Comparable, Comparable)
     */
    public void addEdge(T from, T to) {
        Node<T> fromNode = _nodes.get(from.hashCode());
        Node<T> toNode = _nodes.get(to.hashCode());
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }
        
        fromNode.addEdge(toNode);
    }
    
    /**
     * Removes an existent directed Edge from the Graph, if one exists. 
     * The Edge to be removed is linking the nodes containing the <i>from</i> 
     * and <i>to</i> data references. The two Nodes must exist, otherwise the 
     * method throws an exception.
     * @param from - Node at the starting point of the Edge.
     * @param to - Node at the ending point of the Edge.
     * @throws RuntimeException if either of the two Nodes are not present in the Graph.
     * @see Node
     * @see Graph#addEdge(Comparable, Comparable)
     */
    public void removeEdge(T from, T to) {
        // TODO: Implement this method according to
        // TODO: the specification in javadocs
 
    	Node fromNode = null;
    	Node toNode = null;
    	for(Node n : _nodes.values()) {
    		if(n.getData() == from) {
    			fromNode = n;
    			
    		}
    		
    		if(n.getData() == to) {
    			toNode = n;
    		}
    	}
    	if(fromNode == null || toNode == null) {
    		throw new RuntimeException();
    	}else {
    		fromNode.removeEdgeFromNode(toNode);
    	}
    	
    	
    }
    
    /**
     * Removes a Node from the Graph if one exists, along with all
     * its outgoing (egress) and incoming (ingress) edges. If there
     * is no Node hosting the <i>data</i> reference the method does
     * nothing.
     * @param data - Node to be removed from the Graph.
     */
    public void removeNode(T data) {
        // TODO: Implement this method according to
        // TODO: the specification in javadocs
    	Node dataNode = null;
    	for(Node n: _nodes.values()) {
    		if(n.getData() == data) {
    			dataNode = n;
    		}
    	}
    	if(dataNode != null) {
    		dataNode.removeEdges();
    		_nodes.remove(dataNode);
    	}
    	
    	
    }
    
    
    
    /**
     * Gives a multi-line String representation of this Graph. Each line in the returned
     * string represent a Node in the graph, followed by its outgoing (egress) Edges.
     * E.g: If the graph contains 3 nodes, A, B an C, where A and B point to each other and
     * both of them point to C, the value returned by toString() will be as follows:
     * <pre>
     * A > B C
     * B > A C
     * C > 
     * </pre>
     * <u>Note:</u> Each line is a space-separated sequence of token. A Node with no
     * outgoing (egress) edges, like C in the example above still has a line where 
     * the ' > ' token is surrounded by the space characters.
     * @return multi-line String reflecting the content and structure of this Graph.
     */
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
    
    //Checks to see if the graph is strongly connected (You can traverse to every node from each node)
    //returns true if strongly connected, false if not
    
    public boolean isConnected() {
    	// if the graph is less than two nodes...it is strongly connected
    	if(size() < 2) {
    		return true;
    	}
    	
    	int currentMark = 1;
    	//loop through all the nodes
    	for(Node n: _nodes.values()) {
    		
    		//recursively flood the other nodes
    		n.markSelfAndNeighbors(currentMark); 
    		//did we mark everything? if not..false
    		if(!checkState(currentMark)) {
    			resetMarks();
    			return false;
    		}
    		
    		currentMark++;
    	}
    	resetMarks();
    	return true;
    }
    
    //resets the state of all marks to the default value 
    public void resetMarks() {
    	for(Node n : _nodes.values()) {
    		n.reset();
    	}
    }
    
    /** 
     * 
     * @return
     */
    public boolean isDAGraph() {
    	boolean allTrue = true;// each node has returned true so far
    	
    	// loop through each node 
    	for(Node<T> n: _nodes.values()) {
    		    	
    	//test whether each node has a cycle back to itself
    		if(n.hasCycles(n)) {
    			allTrue = false;
    			break;
    		}else {
    			this.resetMarks();	
    		}
    	}
    	//we made it to the end...return the result after resetting
    	this.resetMarks();	
    	return allTrue;
    	
    }
    
    public boolean isUGraph() {
    	boolean isUGraph = true;
    	for(Node<T> n: _nodes.values()) {
    		if(!n.isUndirectedNode()) {
    			isUGraph = false;
    			break;
    		}
    		
    	}
    	this.resetMarks();
		return isUGraph;
    }
    
    public int[][] getAdjacencyMatrix(){
    	int[][] matrix = new int[_nodes.size()][_nodes.size()];
    	Collection<Node<T>> nodes = _nodes.values();
    	Node<T>[] arrayOfNodes = new Node[nodes.size()];
    	nodes.toArray(arrayOfNodes);
    	for(int i =0; i < arrayOfNodes.length; i++) {
    		for(int j = 0; j < arrayOfNodes.length; j++) {
    			if(arrayOfNodes[i].isNeighbor(arrayOfNodes[j])) {
    				matrix[i][j] = 1;
    			}
    		}
    	}
    	
    	return matrix;
    }

	public TreeMap<Integer, TreeSet<T>> getOutDegrees() {
		// TODO Auto-generated method stub
		TreeMap<Integer, TreeSet<T>> map = new TreeMap<Integer, TreeSet<T>>();
		for(Node<T> n: _nodes.values()) {
			Integer key = n.OutDegress();
			TreeSet<T> values = map.get(key);
			if(values == null) {
				values = new TreeSet<T>();
				map.put(key, values);
			}
			values.add(n.getData());
			
		}
		return map;
	}

	public TreeMap<Integer, TreeSet<String>> getInDegrees() {
		// TODO Auto-generated method stub
		TreeMap<Integer, TreeSet<T>> map = new TreeMap<Integer, TreeSet<T>>();
		
		return null;
	}
    
}
