package main;
import java.io.IOException;

import graphics.DrawingFrame;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapImage mapImage = MapImage.read("Ravenna");
        DrawingFrame mapFrame = new DrawingFrame(mapImage);
        //MapFrame mapFrame = new MapFrame(mapImage);
        mapFrame.open();
        
        System.out.println("a -> step()");
        mapFrame.step();

        System.out.println("b -> stop()");
        mapFrame.stop();

        System.out.println("c -> step()");
        mapFrame.step();

        System.out.println("d -> step()");
        mapFrame.step();

        System.out.println("e -> step()");
        mapFrame.step();

        System.out.println("f -> stop()");
        mapFrame.stop();

        mapFrame.close();
        System.out.println("DONE");
    }
}
