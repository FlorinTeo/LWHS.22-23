package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws Exception {
        MountainsDb mountainsDb = new MountainsDb("mountains.tsv");
        System.out.println(mountainsDb);
    }
}
