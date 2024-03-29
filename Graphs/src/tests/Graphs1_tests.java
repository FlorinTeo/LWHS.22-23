package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import main.Graph;

class Graphs1_tests extends TestsCore {

    /**____ UNDIRECTED GRAPH ____<br>
     * Unit tests for checking if a Graph is undirected.
     * 
     * A generic Graph is undirected if and only if for any Edge linking 
     * two nodes A and B, there is an Edge linking back B to A.
     */
    @Test
    void test_isUGraph() throws FileNotFoundException {
        // Read an undirected Graph from the file "basic1.txt".
        // Verify the output of the isUGraph() method and that
        // the graph internal state and structure is left un-altered.
        Graph<Integer> g1 = readGraph("data/basic1.txt", Integer.class);
        assertTrue(g1.isUGraph());
        assertTrue(g1.checkState(0));
        assertSameGraph("data/basic1.txt", g1);
        
        // Read a directed Graph from the file "basic2.txt"
        // and redo the verifications.
        Graph<String> g2 = readGraph("data/basic2.txt");
        assertFalse(g2.isUGraph());
        assertTrue(g2.checkState(0));
        assertSameGraph("data/basic2.txt", g2);
    }
    
    /**____ CONNECTED GRAPH _____<br>
     * Unit tests for checking if a Graph is weakly-connected.
     * 
     * A connected Graph is a Graph in which every pair of vertices 
     * is connected by a path. The Graph is strongly-connected if the
     * path is respecting the direction of the Edge and weakly-connected
     * if the Edge direction does not matter.
     * For instance:
     *     A > B
     *     B > C 
     *     C > A
     * is a strongly connected graph: From every node you can reach 
     * any other node. But:
     *     A > B C
     * is a weakly connected graph: following the direction of the edges you
     * cannot reach A starting from C. But if the direction is ignored,
     * every node can reach the other nodes.
     * 
     * A Graph that is not connected is said to be disconnected.
     * A Graph with zero or just one vertex is connected.
     */
    @Test
    void test_isConnected() throws FileNotFoundException {
        // Verify an empty Graph is connected
        Graph<Character> g = new Graph<Character>();
        assertTrue(g.isConnected());
        
        // Read a disconnected Graph from a file and verify
        // the output of the isConnected() method.  
        g = readGraph("data/basic3.txt", Character.class);
        assertFalse(g.isConnected());
        
        // Add a few Edges to make it strongly-connected and verify again.
        g.addEdge('B', 'E');
        g.addEdge('D', 'C');
        assertTrue(g.isConnected());
        // Change the orientation of an edge to check weakly-connected.
        g.removeEdge('D', 'C');
        g.addEdge('C', 'D');
        assertTrue(g.isConnected());
    }
    
    /**____ DIRECTED ACYCLIC GRAPH (DAG) ____<br>
     * Unit tests for checking if a Graph is a DAG.
     * 
     * A Directed Acyclic Graph (DAG) is a directed graph that has no cycles.
     * A cycle is a closed loop that starts and ends at the same Node.
     */
    @Test
    void test_isDAGraph() throws FileNotFoundException {
        // Read a partitioned directed acyclic Graph from the file
        // and verify the output of the isDAGraph() method.
        Graph<String> g = readGraph("data/basic4.txt");
        assertTrue(g.isDAGraph());
        
        // Add an edge to make the graph connected DAG
        // and verify again.
        g.addEdge("a2", "b3");
        assertTrue(g.isDAGraph());
        
        // Add another edge to create a cycle and
        // verify this time isDAGraph this time returns false.
        g.addEdge("b4", "a2");
        assertFalse(g.isDAGraph());
    }
    
    /**____ ADJACENCY MATRIX ____<br>
     * Unit tests for checking the adjacency matrix of a Graph.
     * 
     * In a Graph of N nodes each Node is assigned an index in the range [0, N-1].
     * The adjacency matrix is a matrix of N x N integers. If two Nodes in the Graph
     * A (index i) and B (index j) are linked by an Edge, then
     * adjMatrix[i][j] = 1, otherwise adjMatrix[i][j] = 0.
     */
    @Test
    void test_adjacencyMatrix() throws FileNotFoundException {
        // Read a complex (partitioned) graph from a file
        // and verify the adjacency matrix returned by getAdjacencyMatrix()
        Graph<String> g = readGraph("data/basic4.txt");
        String[] expArr = {
          ".xxx.....",
          ".........",
          "....x....",
          "....x....",
          ".........",
          "......xx.",
          "........x",
          "........x",
          ".........",
        };
        int[][] adjMatrix = g.getAdjacencyMatrix();
        for(int r = 0; r < adjMatrix.length; r++) {
            String row = "";
            for (int c = 0; c < adjMatrix[r].length; c++) {
                row += adjMatrix[r][c] == 1 ? "x" : ".";
            }
            assertEquals(expArr[r], row);
        }
    }
}
