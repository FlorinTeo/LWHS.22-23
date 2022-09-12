package main;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapFrame mainFrame = new MapFrame("Ravenna/Ravenna.jpg");
        mainFrame.open();
        mainFrame.step();
        mainFrame.close();
    }
}
