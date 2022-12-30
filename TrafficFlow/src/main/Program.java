package main;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapImage mapImage = MapImage.read("Ravenna");
        MapFrame mapFrame = new MapFrame(mapImage);
        mapFrame.open();
        
        mapFrame.setStatusMessage("a -> step()");
        mapFrame.step();

        mapFrame.setStatusMessage("b -> stop()");
        mapFrame.stop();

        mapFrame.setStatusMessage("c -> step()");
        mapFrame.step();

        mapFrame.setStatusMessage("d -> step()");
        mapFrame.step();

        mapFrame.setStatusMessage("e -> step()");
        mapFrame.step();

        mapFrame.setStatusMessage("f -> stop()");
        mapFrame.stop();
        
        System.out.println(mapImage.collide("AB", "DC"));

        mapFrame.close();
        System.out.println("Goodbye!");
    }
}
