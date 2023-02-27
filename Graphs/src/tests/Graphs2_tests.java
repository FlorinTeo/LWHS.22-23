package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import main.Graph;

class Graphs2_tests extends TestsCore {
    
    //____ OUT-DEGREES ____
    /** The out-degree of a Node is the number of Edges leading out from it.<br>
     * Code the following method in the Graph class:
     * <pre>TreeMap&lt;Integer, TreeSet&lt;String>> getOutDegrees() {...}</pre>
     * It returns a TreeMap associating each out-degree count (key) to the 
     * set of Nodes having that out-degree (value).
     */
    @Test
    void test_outDegrees() throws FileNotFoundException {
        Graph<String> g = readGraph("data/medium1.txt");
        TreeMap<Integer, TreeSet<String>> mapOutDeg = g.getOutDegrees();
        assertEquals("{0=[E], 1=[F], 2=[B, C], 3=[A, D]}", mapOutDeg.toString());
    }
    
    //____ IN-DEGREES ____
    /** The in-degree of a Node is the number of Edges leading into it.<br>
     * Code the following method in the Graph class:
     * <pre>TreeMap&lt;Integer, TreeSet&lt;String>> getInDegrees() {...}</pre>
     * It returns a TreeMap associating each in-degree count (key) to the 
     * set of Nodes having that in-degree (value).
     */
    @Test
    void test_inDegrees() throws FileNotFoundException {
        Graph<String> g = readGraph("data/medium1.txt");
        TreeMap<Integer, TreeSet<String>> mapInDeg = g.getInDegrees();
        System.out.println(mapInDeg);
        assertEquals("{0=[A], 1=[D], 2=[B, F], 3=[C, E]}", mapInDeg.toString());
    }
    
    //____ TOPOLOGICAL SORT ____
    /**A topological sort or topological ordering of a Directed Acyclic Graph 
     * is a linear ordering of its vertices such that for every directed edge uv 
     * from vertex u to vertex v, u comes before v in the ordering.<br>
     * Code the following method in the Graph class:
     * <pre>TreeMap&lt;Integer, TreeSet&lt;String>> topoSort() {...}</pre>
     * It returns a TreeMap associating each position in the topological sort (key)
     * with the set of Nodes at that position (value). If the Graph is not DAG, the method 
     * returns null.
     * Notes: Multiple nodes may have the same position in the sort.
     * For instance:<pre>
     *     A > B C
     *     B > 
     *     C >
     * <pre>should return {A=0, B=1, C=1}
     */
    @Test
    void test_topologicalSort() throws FileNotFoundException {
        Graph<String> g = readGraph("data/medium1.txt");
        assertTrue(g.isDAGraph());
        TreeMap<Integer, TreeSet<String>> mapTopoSort = g.getTopologicalSort();
        assertEquals("{0=[A], 1=[D], 2=[B], 3=[C], 4=[F], 5=[E]}", mapTopoSort.toString());
    }
}
