package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import main.Graph;

class Graphs_test extends TestsCore {

    @Test
    /**
     * Unit tests for Graph<T>.hasPath(T fromData, T toData) -> boolean
     * @throws FileNotFoundException
     */
    void test_hasPath() throws FileNotFoundException {
        // Read the test graph (three linked nodes) from the file
        Graph<String> g = readGraph("data/test.txt");
        
        // Test the paths which exists in the graph
        assertTrue(g.hasPath("A", "A"));
        assertTrue(g.hasPath("A", "B"));
        assertTrue(g.hasPath("A", "D"));
        assertTrue(g.hasPath("C", "D"));
        assertTrue(g.hasPath("D", "B"));
        
        // Test the paths which do not exist in the graph 
        assertFalse(g.hasPath("D", "A"));
        assertFalse(g.hasPath("D", "C"));
        
        // Test the error case (non-existent node)
        try {
            g.hasPath("A", "X");
            fail("Should have thrown!");
        } catch(RuntimeException e) {
        }
    }

}
