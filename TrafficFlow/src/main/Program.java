package main;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapImage mapImage = new MapImage("Ravenna");
        MapFrame mainFrame = new MapFrame(mapImage);
        mainFrame.open();
        mainFrame.step();
        mainFrame.close();
    }
}
