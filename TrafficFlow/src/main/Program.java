package main;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapImage mapImage = new MapImage("Ravenna");
        MapFrame mainFrame = new MapFrame(mapImage);
        mainFrame.open();
        System.out.println("-> step(2)");
        mainFrame.step();
        System.out.println("-> step(1)");
        mainFrame.step(1);
        System.out.println("-> step(2)");
        mainFrame.step(2);
        mainFrame.close();
        System.out.println("DONE");
    }
}
