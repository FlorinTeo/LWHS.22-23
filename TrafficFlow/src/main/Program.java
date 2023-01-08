package main;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapImage mapImage = MapImage.load("maps/Woodlawn.jpg");
        MapFrame mapFrame = new MapFrame(mapImage);
        mapFrame.open();
        
        mapFrame.setStatusMessage("step()");
        mapFrame.step();

        mapFrame.setStatusMessage("stop()");
        mapFrame.stop();
        
        mapFrame.close();
        System.out.println("Goodbye!");
    }
}
