package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Drawing {
    
    private BufferedImage _image = null;
    
    public Drawing(String drwPath) throws IOException {
        File drwFile = new File(drwPath);
        if (!drwFile.exists() || drwFile.isDirectory()) {
            throw new IOException();
        }
        _image = ImageIO.read(drwFile);
    }
    
    BufferedImage getImage() {
        return _image;
    }

    public int getWidth() {
        return _image.getWidth();
    }
    
    public int getHeight() {
        return _image.getHeight();
    }
    
    public boolean isValidPixel(int x, int y) {
        return (x >= 0 && x <= _image.getWidth()-3 && y >=0 && y <= _image.getHeight()-3);
    }
    
    public boolean isBrightPixel(int x, int y) {
        Color c = new Color(_image.getRGB(x, y));
        return c.getRed() > 220 && c.getGreen() > 220 && c.getBlue() > 220;
    }
    
    public boolean isDarkPixel(int x, int y) {
        Color c = new Color(_image.getRGB(x, y));
        return c.getRed() < 30 && c.getGreen() < 30 && c.getBlue() < 30;
    }
    
    public Color getPixel(int x, int y) {
        return new Color(_image.getRGB(x, y));
    }
    
    public void setPixel(int x, int y, Color c) {
        _image.setRGB(x, y, c.getRGB());
    }
}
