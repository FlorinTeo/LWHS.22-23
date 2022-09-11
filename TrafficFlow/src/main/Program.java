package main;
import java.awt.Frame;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MainFrame mainFrame = new MainFrame("Ravenna/Ravenna.jpg");
        mainFrame.open();
        mainFrame.step();
        mainFrame.close();
    }
}
