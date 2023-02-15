package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import main.DGraph;

class Graphs1_tests extends TestsCore {

    @Test
    void test_addNode() {
        DGraph<Integer> g = new DGraph<Integer>();
        g.addNode(1);
        g.addNode(2);
        assertEquals(2, g.size());
        String expected = "[0] 1 > \n"
                        + "[0] 2 > ";
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
        assertEquals(3, g.size());
        String expected = "[0] abc > xyz\n"
                        + "[0] def > xyz\n"
                        + "[0] xyz > ";
        assertEquals(expected, g.toString());
    }
    
    @Test
    void test_readGraph() throws FileNotFoundException {
        DGraph<String> g = readGraph("data/basic1_in.txt");
        assertEquals(5, g.size());
        assertSame("data/basic1_out.txt", g);
    }    

    @Test
    void test_isUGraph() throws FileNotFoundException {
        fail("Not yet implemented");
    }
    
    @Test
    void test_isDAGraph() throws FileNotFoundException {
        fail("Not yet implemented");
    }
    
    @Test
    void test_isEulerian() throws FileNotFoundException {
        fail("Not yet implemented");
    }
    
    @Test
    void test_isConnected() throws FileNotFoundException {
        fail("Not yet implemented");
    }
    
    @Test
    void test_deepCopy() throws FileNotFoundException {
        fail("Not yet implemented");
    }
}
