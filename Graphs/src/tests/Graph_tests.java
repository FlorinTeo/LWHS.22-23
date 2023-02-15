package tests;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import main.Graph;

class Graph_tests extends Helper {

    @Test
    void test_readGraph() throws FileNotFoundException {
        Graph<String> g = readGraph("data/graph1_in.txt");
        assertSame("data/graph1_out.txt", g);
    }
}
