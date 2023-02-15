package tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import main.Graph;

class Basic_tests extends TestsRoot {

    @Test
    void test_addNode() {
        Graph<Integer> g = new Graph<Integer>();
        g.addNode(1);
        g.addNode(2);
        assertEquals(2, g.size());
        String expected = "[0] 1 > \n"
                        + "[0] 2 > ";
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
        assertEquals(3, g.size());
        String expected = "[0] abc > xyz\n"
                        + "[0] def > xyz\n"
                        + "[0] xyz > abc def";
        assertEquals(expected, g.toString());
    }
    
    @Test
    void test_readGraph() throws FileNotFoundException {
        Graph<String> g = readGraph("data/basic1_in.txt");
        assertEquals(5, g.size());
        assertSame("data/basic1_out.txt", g);
    }    
}
