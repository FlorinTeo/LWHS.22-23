package frq;

public class Program {

    public static void main(String[] args) {
        System.out.println("Welcome to the Tournament!");
        Tournament t = new Tournament("a","b","a","b");
        System.out.println(t);
        t.checkValid();
    }
}
