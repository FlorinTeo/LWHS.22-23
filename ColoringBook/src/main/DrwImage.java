package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrwImage {
    private BufferedImage _drwImage = null;
    
    public DrwImage(String drwPath) throws IOException {
        File drwFile = new File(drwPath);
        if (!drwFile.exists() || drwFile.isDirectory()) {
            throw new IOException();
        }
        _drwImage = ImageIO.read(drwFile);
    }
    
    public BufferedImage getDrwImage() {
        return _drwImage;
    }

    public int getWidth() {
        return _drwImage.getWidth();
    }
    
    public int getHeight() {
        return _drwImage.getHeight();
    }
}
