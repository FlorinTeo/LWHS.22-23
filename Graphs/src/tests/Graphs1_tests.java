package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import main.Graph;

class Graphs1_tests extends TestsCore {

    // Region: Core tests
    @Test
    void test_addNode() {
        Graph<Integer> g = new Graph<Integer>();
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
        Graph<String> g = new Graph<String>();
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
        Graph<String> g = readGraph("data/basic1.txt");
        assertEquals(5, g.size());
        assertTrue(g.checkState(0));
        assertSame("data/basic1.txt", g);
    }

    @Test
    void test_removeEdge() throws FileNotFoundException {
        Graph<Character> g = new Graph<Character>();
        g.addNode('A');
        g.addNode('B');
        g.addEdge('A', 'B');
        assertEquals(2, g.size());
        String expected = "A > B\n"
                        + "B > ";
        assertEquals(expected, g.toString());
        
        g.removeEdge('A', 'B');
        expected = "A > \n"
                 + "B > ";
        assertEquals(expected, g.toString());
    }
    
    @Test
    void test_removeNode() throws FileNotFoundException {
        Graph<String> g = readGraph("data/basic0.txt");
        assertSame("data/basic0.txt", g);
        g.removeNode("three");
        String expected = "one > two\n"
                        + "two > ";
        assertEquals(expected, g.toString());

        g.removeNode("two");
        expected = "one > ";
        assertEquals(expected, g.toString());

        g.removeNode("one");
        assertEquals("", g.toString());
    }
    // EndRegion: Core tests

    @Test
    void test_isUGraph() throws FileNotFoundException {
        Graph<Integer> g1 = readGraph("data/basic1.txt", Integer.class);
        assertTrue(g1.isUGraph());
        assertTrue(g1.checkState(0));
        assertSame("data/basic1.txt", g1);
        
        Graph<String> g2 = readGraph("data/basic2.txt");
        assertFalse(g2.isUGraph());
        assertTrue(g2.checkState(0));
        assertSame("data/basic2.txt", g2);
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
