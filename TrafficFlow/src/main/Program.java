package main;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapImage mapImage = new MapImage("Ravenna");
        MapFrame mapFrame = new MapFrame(mapImage);
        mapFrame.open();
        
        System.out.println("a -> step(1)");
        mapFrame.step();

        System.out.println("b -> step(2)");
        mapFrame.step(2);

        System.out.println("c -> step(1)");
        mapFrame.step();

        System.out.println("d -> step(1)");
        mapFrame.step();

        System.out.println("e -> step(1)");
        mapFrame.step();

        System.out.println("f -> step(2)");
        mapFrame.step(2);

        mapFrame.close();
        System.out.println("DONE");
    }
}
