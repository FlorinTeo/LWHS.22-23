package main;

public class Program {

    public static void main(String[] args) {
        Graph<String> gs = new Graph<String>();
        gs.addNode("abc");
        gs.addNode("def");
        gs.addEdge("abc", "def");
        gs.addNode("xyz");
        gs.addEdge("xyz", "def");
        System.out.println(gs);
        
        Graph<Integer> gi = new Graph<Integer>();
        gi.addNode(1);
        gi.addNode(2);
        gi.addNode(3);
        gi.addNode(4);
        gi.addNode(5);
        gi.addEdge(1, 2);
        gi.addEdge(2, 3);
        gi.addEdge(3, 1);
        gi.addEdge(4, 5);
        System.out.println(gi);
    }

}
