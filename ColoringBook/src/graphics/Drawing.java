package graphics;

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
    
    public BufferedImage getImage() {
        return _image;
    }

    public int getWidth() {
        return _image.getWidth();
    }
    
    public int getHeight() {
        return _image.getHeight();
    }
}
