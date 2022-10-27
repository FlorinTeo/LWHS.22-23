package mc;

import frq.LinkedIntList;

public class q4 {

    public static void main(String[] args) {
        LinkedIntList list = new LinkedIntList(1, 2, 3, 4);
        System.out.println(list);
        System.out.println(list.remove(1));
        System.out.println(list);
        System.out.println(list.remove(0));
        System.out.println(list);
        System.out.println(list.remove(1));
        System.out.println(list);
        System.out.println(list.remove(0));
        System.out.println(list);
    }
}
