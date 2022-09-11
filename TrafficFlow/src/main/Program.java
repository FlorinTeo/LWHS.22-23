package main;
import java.awt.Frame;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MainFrame mainFrame = new MainFrame("Ravenna/Ravenna.jpg");
        mainFrame.open();
        System.out.println("Sleep 5 sec");
        Thread.sleep(5000);
        System.out.println("after open, into step -1");
        mainFrame.step(-1);
        System.out.println("after step -1, into step 1");
        mainFrame.step(1);
        System.out.println("after step 1, into step 2");
        mainFrame.step(2);
        System.out.println("after step 2, into step 0");
        mainFrame.step(0);
        System.out.println("after step 0, before more steps");
        mainFrame.step();
        System.out.println("More steps 1");
        mainFrame.step();
        System.out.println("More steps 2");
        mainFrame.step();
        System.out.println("More steps 3");
        mainFrame.step();
        System.out.println("Last step 2");
        mainFrame.step(2);
        mainFrame.close();
    }
}
