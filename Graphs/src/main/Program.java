package main;

public class Program {

    public static void main(String[] args) {
        DGraph<String> dgs = new DGraph<String>();
        dgs.addNode("abc");
        dgs.addNode("def");
        dgs.addEdge("abc", "def");
        dgs.addNode("xyz");
        dgs.addEdge("xyz", "def");
        System.out.println(dgs);
        
        DGraph<Integer> dgi = new DGraph<Integer>();
        dgi.addNode(1);
        dgi.addNode(2);
        dgi.addNode(3);
        dgi.addNode(4);
        dgi.addNode(5);
        dgi.addEdge(1, 2);
        dgi.addEdge(2, 3);
        dgi.addEdge(3, 1);
        dgi.addEdge(4, 5);
        System.out.println(dgi);
    }

}
