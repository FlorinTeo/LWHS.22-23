package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapButton extends Canvas {
    private static final long serialVersionUID = 1L;
    private BufferedImage[] _btnFaces;
    private int _crtFace;
    
    public MapButton(int xAnchor, int yAnchor, String... btnFaceFiles) throws IOException {
        _btnFaces = new BufferedImage[btnFaceFiles.length];
        for(int i = 0; i < _btnFaces.length; i++) {
            _btnFaces[i] = ImageIO.read(new File(btnFaceFiles[i]));
        }
        _crtFace = 0;
        this.setBounds(
                xAnchor, yAnchor,
                _btnFaces[_crtFace].getWidth(), _btnFaces[_crtFace].getHeight());
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_btnFaces[_crtFace], 0, 0, null);
    }
}
