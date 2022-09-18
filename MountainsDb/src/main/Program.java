package main;

public class Program {

    public static void main(String[] args) throws Exception {
        MountainsDb mountainsDb = new MountainsDb("mountains.tsv");
        System.out.println(mountainsDb);
        mountainsDb.split();
        mountainsDb.merge();
    }
}
