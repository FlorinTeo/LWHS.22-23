package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {
    
    //Region: demo reading code
    public static void demoRead1() throws IOException {
        File f = new File("mountains_db.tsv");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        while(br.ready()) {
            System.out.println(br.readLine());
        }
        br.close();
    }
    
    public static void demoRead2() throws IOException {
        File f = new File("mountains_db.tsv");
        FileInputStream fis = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(
                fis,
                StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        while(br.ready()) {
            System.out.println(br.readLine());
        }
        br.close();
    }

    public static void demoRead3() throws IOException {
        Path dbPath = Paths.get("mountains_db.tsv");
        BufferedReader br = Files.newBufferedReader(
                dbPath,
                StandardCharsets.UTF_8);
        while(br.ready()) {
            System.out.println(br.readLine());
        }
        br.close();
    }
    //EndRegion: demo reading code

    //Region: demo writing code
    public static void demoWrite2() throws IOException {
        File f = new File("mountains_err.tsv");
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("New Zealand\tMountain\tOutlaw Peak..." + "\n");
        bw.close();
    }
    
    public static void demoWrite1() throws IOException {
        File f = new File("mountains_err.tsv");
        FileOutputStream fos = new FileOutputStream(f);
        OutputStreamWriter osw = new OutputStreamWriter (
                fos,
                StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write("New Zealand\tMountain\tOutlaw Peak..." + "\n");
        bw.close();
    }
    
    public static void demoWrite3() throws IOException {
        Path dbPath = Paths.get("mountains_err.tsv");
        BufferedWriter bw = Files.newBufferedWriter(
                dbPath,
                StandardCharsets.UTF_8);
        bw.write("New Zealand\tMountain\tOutlaw Peak..." + "\n");
        bw.close();
    }
    //EndRegion:demo writing code

    public static void main(String[] args) throws Exception {
        MountainsDatabase mountainsDb = new MountainsDatabase("mountains.tsv");
        System.out.println(mountainsDb);
        mountainsDb.fuzzy("mountains_db.tsv");
    }
}
