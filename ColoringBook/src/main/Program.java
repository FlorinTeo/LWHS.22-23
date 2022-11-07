package main;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to the Coloring Book app!");
        DrwImage drwImage = new DrwImage("drawings/bird.gif");
        ColoringFrame coloringFrame = new ColoringFrame(drwImage);
        coloringFrame.open();
        coloringFrame.step();
        
        BufferedImage bi = drwImage.getDrwImage();
        for(int x = 0; x < 100; x++) {
            bi.setRGB(x, x, Color.blue.getRGB());
            coloringFrame.repaint();
        }
        coloringFrame.step();
        coloringFrame.close();
        
        System.out.println("DONE");
    }
}
