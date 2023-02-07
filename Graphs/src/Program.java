
public class Program {

    public static void main(String[] args) {
        Graph<String> g = new Graph<String>();
        g.addNode("abc");
        g.addNode("def");
        g.addEdge("abc", "def");
        g.addNode("xyz");
        g.addEdge("xyz", "def");
        System.out.println(g);
    }

}
