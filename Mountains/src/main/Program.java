package main;

import java.io.IOException;

public class Program {

    public static void main(String[] args) throws IOException {
        MountainsDb mDb = new MountainsDb("mountains_db.tsv");
        mDb.clean();
        System.out.println(mDb);
    }

}
