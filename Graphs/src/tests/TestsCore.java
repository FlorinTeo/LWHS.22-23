package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import main.DGraph;

public class TestsCore {
    
    public static <T extends Comparable<T>> T parseT(String s, Class<T> realType) {
        if (realType == Integer.class) {
            return realType.cast(Integer.parseInt(s));
        } else if (realType == String.class) {
            return realType.cast(s);
        } else if (realType == Double.class) {
            return realType.cast(Double.parseDouble(s));
        } else {
            throw new RuntimeException("Unsupported type in graph parsing!");
        }
    }

    public static <T extends Comparable<T>> DGraph<T> readGraph(String graphFile, Class<T> realType) throws FileNotFoundException {
        Scanner input = new Scanner(new File(graphFile));
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] tokens = line.split(" ");
            if (tokens.length < 2 || !tokens[1].equals(">")) {
                throw new RuntimeException("Syntax error in parsing graph!");
            }
            map.put(
                    tokens[0],
                    Arrays.asList(Arrays.copyOfRange(tokens, 2, tokens.length)));
        }
        
        DGraph<T> graph = new DGraph<T>();
        for(String node : map.keySet()) {
            T n = parseT(node, realType);
            graph.addNode(n);
        }
        
        for(Map.Entry<String, List<String>> kvp : map.entrySet()) {
            T fromNode = parseT(kvp.getKey(), realType);
            for(String v : kvp.getValue()) {
                T toNode = parseT(v, realType);
                graph.addEdge(fromNode, toNode);
            }
        }

        return graph;
    }

    public static DGraph<String> readGraph(String graphFile) throws FileNotFoundException {
        return readGraph(graphFile, String.class);
    }
    
    public static void assertSame(String graphFile, DGraph<?> g) throws FileNotFoundException {
        String expected = "";
        boolean first = true;
        Scanner parser = new Scanner(new File(graphFile));
        while(parser.hasNextLine()) {
            if (!first) {
                expected += "\n";
            }
            expected += parser.nextLine();
            first = false;
        }
        assertEquals(expected, g.toString());
    }
}
