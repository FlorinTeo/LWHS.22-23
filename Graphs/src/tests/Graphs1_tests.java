package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import main.DGraph;

class Graphs1_tests extends TestsCore {

    // Region: Core tests
    @Test
    void test_addNode() {
        DGraph<Integer> g = new DGraph<Integer>();
        g.addNode(1);
        g.addNode(2);
        assertEquals(2, g.size());
        assertTrue(g.checkState(0));
        String expected = "1 > \n"
                        + "2 > ";
        assertEquals(expected, g.toString());
    }
    
    @Test
    void test_addEdge() {
        DGraph<String> g = new DGraph<String>();
        g.addNode("abc");
        g.addNode("def");
        g.addNode("xyz");
        g.addEdge("abc", "xyz");
        g.addEdge("def", "xyz");
        g.addEdge("abc", "def");
        assertEquals(3, g.size());
        assertTrue(g.checkState(0));
        String expected = "abc > def xyz\n"
                        + "def > xyz\n"
                        + "xyz > ";
        assertEquals(expected, g.toString());
    }
    
    @Test
    void test_readGraph() throws FileNotFoundException {
        DGraph<String> g = readGraph("data/basic1.txt");
        assertEquals(5, g.size());
        assertTrue(g.checkState(0));
        assertSame("data/basic1.txt", g);
    }
    // EndRegion: Core tests
    
    @Test
    void test_deepCopy() throws FileNotFoundException {
        fail("Not yet implemented");
    }

    @Test
    void test_isUGraph() throws FileNotFoundException {
        DGraph<Integer> g1 = readGraph("data/basic1.txt", Integer.class);
        assertTrue(g1.isUGraph());
        assertTrue(g1.checkState(0));
        assertSame("data/basic1.txt", g1);
        
        DGraph<String> g2 = readGraph("data/basic2.txt");
        assertFalse(g2.isUGraph());
        assertTrue(g2.checkState(0));
        assertSame("data/basic2.txt", g2);
    }
    
    @Test
    void test_isDAGraph() throws FileNotFoundException {
        fail("Not yet implemented");
    }
    
    @Test
    void test_isConnected() throws FileNotFoundException {
        fail("Not yet implemented");
    }
    
    @Test
    void test_isEulerian() throws FileNotFoundException {
        fail("Not yet implemented");
    }
}
