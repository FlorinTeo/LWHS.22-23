package main;

public class Program {

    public static void main(String[] args) throws Exception {
        MountainsDatabase mountainsDb = new MountainsDatabase("mountains.tsv");
        System.out.println(mountainsDb);
        int i = 0;
        do {
            mountainsDb.split();
            System.out.printf(".");
            i++;
            if (i % 50 == 0) {
                System.out.println();
            }
            if (i == 1000) {
                mountainsDb._debug = true;
            }
            if (i > 1100) {
                break;
            }
        } while(!mountainsDb.merge());
        System.out.println("SORTED!");
    }
}
