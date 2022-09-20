package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Program {
    
    public static void demo() throws IOException {
        File f = new File("mountains.tsv");
        // FileReader fr = new FileReader(f);
        FileInputStream fis = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        // BufferedReader br = new BufferedReader(fr);
        BufferedReader bsr = new BufferedReader(isr);
        for(int i = 0; i < 100 && bsr.ready(); i++) {
            System.out.println(bsr.readLine());
        }
    }

    public static void main(String[] args) throws Exception {
        demo();
//        MountainsDatabase mountainsDb = new MountainsDatabase("mountains.tsv");
//        System.out.println(mountainsDb);
//        int i = 0;
//        do {
//            mountainsDb.split();
//            System.out.printf(".");
//            i++;
//            if (i % 50 == 0) {
//                System.out.println();
//            }
//            if (i == 1000) {
//                mountainsDb._debug = true;
//            }
//            if (i > 1100) {
//                break;
//            }
//        } while(!mountainsDb.merge());
//        System.out.println("SORTED!");
    }
}
