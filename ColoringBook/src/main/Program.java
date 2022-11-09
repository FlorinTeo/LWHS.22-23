package main;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import graphics.DrawingFrame;
import graphics.Drawing;

public class Program {
    
    private static Drawing _drawing;
    private static DrawingFrame _frame;
    
    public static class WorkItem {
        public int _x;
        public int _y;
        public WorkItem(int x, int y) {
            _x = x;
            _y = y;
        }
    };
    
    public static void floodQ(int xSeed, int ySeed, Color col) throws InterruptedException {
        Queue<WorkItem> queue = new LinkedList<WorkItem>();
        WorkItem work = new WorkItem(xSeed, ySeed);
        _drawing.setPixel(work._x, work._y, col);
        queue.add(work);
        while (!queue.isEmpty()) {
            _frame.step(2);
            _frame.setStatusMessage(String.format("%d",queue.size()));
            work = queue.remove();
            for(int x = work._x - 1; x <= work._x + 1; x ++) {
                for (int y = work._y - 1; y <= work._y + 1; y++) {
                    if (x == xSeed && y == ySeed) {
                        continue;
                    }
                    if (_drawing.isValidPixel(x, y) && _drawing.isBrightPixel(x, y)) {
                        _drawing.setPixel(x, y, col);
                        queue.add(new WorkItem(x, y));
                    }
                }
            }
        }
    }
    
    public static void floodS(int xSeed, int ySeed, Color col) throws InterruptedException {
        Stack<WorkItem> stack = new Stack<WorkItem>();
        WorkItem work = new WorkItem(xSeed, ySeed);
        _drawing.setPixel(work._x, work._y, col);
        stack.push(work);
        while (!stack.empty()) {
            _frame.step(2);
            _frame.setStatusMessage(String.format("%d",stack.size()));
            work = stack.pop();
            for(int x = work._x - 1; x <= work._x + 1; x ++) {
                for (int y = work._y - 1; y <= work._y + 1; y++) {
                    if (x == xSeed && y == ySeed) {
                        continue;
                    }
                    if (_drawing.isValidPixel(x, y) && _drawing.isBrightPixel(x, y)) {
                        _drawing.setPixel(x, y, col);
                        stack.push(new WorkItem(x, y));
                    }
                }
            }
        }
    }
    
    public static void floodR(int xSeed, int ySeed, Color col) throws InterruptedException {
        _drawing.setPixel(xSeed, ySeed, col);
        _frame.step(2);
        for (int x = xSeed - 1; x <= xSeed + 1; x++) {
            for (int y = ySeed - 1; y <= ySeed + 1; y++) {
                if (x < 0 || x > _drawing.getWidth()-3 || y < 0 || y > _drawing.getHeight()-3) {
                    continue;
                }
                if (x == xSeed && y == ySeed) {
                    continue;
                }
                Color crtCol = _drawing.getPixel(x, y);
                if (crtCol == col) {
                    continue;
                }
                if (crtCol != col && _drawing.isBrightPixel(x, y)) {
                    floodR(x, y, col);
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
        for(int x = 40; x < 140; x++) {
            for (int y = 30; y < 130; y++) {
                if (_drawing.isDarkPixel(x, y)) {
                    _drawing.setPixel(x, y, Color.yellow);
                } else {
                    _drawing.setPixel(x, y, Color.red);
                }
            }
        }
        _frame.stop();
        floodS(140, 320, Color.orange.darker());
        _frame.stop();
        floodQ(275, 423, Color.orange);
        _frame.stop();
        floodR(59, 457, Color.green.brighter());
        _frame.stop();
        floodS(151, 253, Color.pink);
        _frame.stop();
        floodQ(246,113, Color.cyan);
        _frame.stop();
    }
    
    /**
     * Main entrance in the program.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to the Coloring Festival!");
        
        // pick a drawing
        _drawing = new Drawing("drawings/bird.jpg");
        
        // put it in a frame
        _frame = new DrawingFrame(_drawing);

        // put the frame on display and stop to admire it.
        _frame.open();
        _frame.stop();
        
        // make some change to the drawing, then stop for applause.
        paint();
        _frame.stop();
        
        // the show is over.
        //_frame.close();
        
        System.out.println("Well done, goodbye!");
    }
}
