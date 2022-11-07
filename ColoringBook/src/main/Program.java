package main;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Program {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to the Coloring Book app!");
        DrwImage drwImage = new DrwImage("drawings/bird.jpg");
        ColoringFrame coloringFrame = new ColoringFrame(drwImage);
        coloringFrame.open();
        coloringFrame.step();
        
        BufferedImage bi = drwImage.getDrwImage();
        Color c = new Color(255, 0, 0);
        for(int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                bi.setRGB(x+100, y+40, c.getRGB());
                coloringFrame.repaint();
            }
        }
        coloringFrame.step();
        coloringFrame.close();
        
        System.out.println("DONE");
    }
}
