package main;

/**
 * Main class of the Mountains project
 */
public class Program {

    /**
     * Entry point for the execution of the Mountains project: It is instantiating a
     * MountainsDb object, calls its clean method and prints it out at the end. In
     * case of any exception, prints out details of that exception.
     */
    public static void main(String[] args) {
        try {
            MountainsDb mDb = new MountainsDb("mountains_db.tsv");
            mDb.clean();
            System.out.println(mDb);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getClass().getTypeName());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
