package main;
import java.io.IOException;
import java.util.Set;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        MapImage mapImage = MapImage.load("maps/Ravenna.jpg");
        MapFrame mapFrame = new MapFrame(mapImage);
        mapFrame.open();
        
        Set<String> routes = mapImage.getRoutes();
        System.out.println(routes);
        System.out.println(mapImage.collide("AB", "AC"));
        
        mapFrame.stop();
        mapFrame.close();
        System.out.println("Goodbye!");
    }
}
