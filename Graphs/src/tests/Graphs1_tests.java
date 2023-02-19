package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import main.Graph;

class Graphs1_tests extends TestsCore {

    @Test
    void test_isUGraph() throws FileNotFoundException {
        Graph<Integer> g1 = readGraph("data/basic1.txt", Integer.class);
        assertTrue(g1.isUGraph());
        assertTrue(g1.checkState(0));
        assertSameGraph("data/basic1.txt", g1);
        Graph<String> g2 = readGraph("data/basic2.txt");
        assertFalse(g2.isUGraph());
        assertTrue(g2.checkState(0));
        assertSameGraph("data/basic2.txt", g2);
    }
    
    @Test
    void test_isConnected() throws FileNotFoundException {
        Graph<Character> g = new Graph<Character>();
        assertTrue(g.isConnected());
        g = readGraph("data/basic3.txt", Character.class);
        assertFalse(g.isConnected());
        g.addEdge('B', 'E');
        g.addEdge('D', 'C');
        assertTrue(g.isConnected());
    }
    
    @Test
    void test_isDAGraph() throws FileNotFoundException {
        Graph<String> g = readGraph("data/basic4.txt");
        assertTrue(g.isDAGraph());
        g.addEdge("a2", "b3");
        assertTrue(g.isDAGraph());
        g.addEdge("b4", "a2");
        assertFalse(g.isDAGraph());
    }
}
