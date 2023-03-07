package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import main.Graph;

class Graphs3_tests extends TestsCore {

    //____ PARTITIONS COUNT ____
    /**In Graph theory, a graph G is a tuple (V, E) where V is the set of vertices and
     * E is a set of Edges connecting vertices from V.<br>
     * A partition in the graph G is a graph g = (v, e) where v &#x2286; V, e &#x2286; E,
     * every edge in e is connecting vertices from v and and every edge in {E \ e} 
     * is connecting vertices from {V \ v}.<br>
     * In other words, there is no edge connecting a vertex in a partition with a vertex from 
     * outside that partition, in either direction.<br>
     * Code the following method in the Graph class, counting the number of 
     * partitions in the Graph:
     * <pre>int countPartitions() {...}</pre>
     * For instance:<br>
     * - an empty graph has 0 partitions,<br>
     * - a weakly connected graph has 1 partition,<br>
     * - the graph in the example below has 2 partitions.
     * <pre>
     *     A > B C
     *     B > 
     *     C >
     *     D > E
     *     E > D</pre>
     * @throws FileNotFoundException 
     */
    @Test    
    void test_partitionsCount() throws FileNotFoundException {
        Graph<String> g = new Graph<String>();
        assertEquals(0, g.countPartitions());
        g = readGraph("data/basic0.txt");
        assertEquals(1, g.countPartitions());
        g = readGraph("data/basic1.txt");
        assertEquals(2, g.countPartitions());
        g = readGraph("data/medium2.txt");
        assertEquals(3, g.countPartitions());
    }
    
    @Test
    void test_dijkstra() {
        fail("Not yet implemented");
    }
    
    @Test
    void test_eulerianCircuit() {
        fail("Not yet implemented");
    }
}
