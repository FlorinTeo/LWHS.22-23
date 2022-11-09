package main;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import graphics.DrawingFrame;
import graphics.Drawing;

public class Program {
    
    private static Drawing _drawing;
    private static DrawingFrame _frame;
    
    public static boolean isWhite(Color c) {
        return c.getRed() > 220 && c.getGreen() > 220 && c.getBlue() > 220;
    }
    
    public static boolean isBlack(Color c) {
        return c.getRed() < 20 && c.getGreen() < 20 && c.getBlue() < 20;
    }
    
    public static void flood(int xSeed, int ySeed, Color col) throws InterruptedException {
        _drawing.getImage().setRGB(xSeed, ySeed, col.getRGB());
        for (int x = xSeed - 1; x <= xSeed + 1; x++) {
            for (int y = ySeed - 1; y <= ySeed + 1; y++) {
                _frame.step(4);
                if (x < 0 || x > _drawing.getWidth()-3 || y < 0 || y > _drawing.getHeight()-3) {
                    continue;
                }
                if (x == xSeed && y == ySeed) {
                    continue;
                }
                Color crtCol = new Color(_drawing.getImage().getRGB(x, y));
                if (crtCol == col) {
                    continue;
                }
                if (crtCol != col && isWhite(crtCol)) {
                    flood(x, y, col);
                }
            }
        }
    }
    
    /**
     * Demonstrate a simple alteration to the drawing:
     * On a square section of the image replace the white tones with red
     * and the dark strokes with yellow.
     * @throws InterruptedException 
     */
    public static void paint() throws InterruptedException {
        BufferedImage img = _drawing.getImage();
        for(int x = 40; x < 140; x++) {
            for (int y = 30; y < 130; y++) {
                Color crt = new Color(img.getRGB(x, y));
                if (crt.getBlue() > 200 && crt.getGreen() > 200 && crt.getRed() > 200) {
                    img.setRGB(x, y, Color.red.getRGB());
                } else {
                    img.setRGB(x, y, Color.yellow.getRGB());
                }
            }
        }
        _frame.stop();
        flood(140, 320, Color.green);
        _frame.stop();
        flood(275, 423, Color.yellow);
        _frame.stop();
        flood(59, 457, Color.magenta);
    }
    
    /**
     * Main entrance in the program.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to the Coloring Book app!");
        
        // pick a drawing
        _drawing = new Drawing("drawings/bird.jpg");
        
        // put it in a frame
        _frame = new DrawingFrame(_drawing);

        // put the frame on display and pause to admire it.
        _frame.open();
        _frame.stop();
        
        // make some change to the drawing, then pause for applause.
        paint();
        _frame.stop();
        
        // shut down the exhibit.
        _frame.close();
        
        System.out.println("DONE");
    }
}
