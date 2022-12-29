package mcq;

public class Program {

    public static void main(String[] args) {
        System.out.println("Welcome to the Unit3 MCQ!");
        IntTree t = IntTree.createSearchable(7, 5, 6, 8, 9);
        System.out.println(t);
        //System.out.println(t.sumAll());
        t.mystery2();
    }
}
