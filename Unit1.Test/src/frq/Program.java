package frq;

public class Program {

    public static void main(String[] args) {
        LinkedIntList list = new LinkedIntList(1, 2, 3, 4, 5);
//        System.out.println(list);
//        list.swap(0);
//        System.out.println(list);
//        list.swap(1);
//        System.out.println(list);
//        list.swap(3);
//        System.out.println(list);
//        list.swap(-1);
        System.out.println(list);
        list.insert(3);
        System.out.println(list);
        list.insert(0);
        System.out.println(list);
        list.insert(7);
        System.out.println(list);
    }
}
 