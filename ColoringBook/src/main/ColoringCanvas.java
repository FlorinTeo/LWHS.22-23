package main;
import java.awt.Canvas;
import java.awt.Graphics;

public class ColoringCanvas extends Canvas {
    private static final long serialVersionUID = 1L;
    
    private DrwImage _drwImage;
    
    public ColoringCanvas(int xAnchor, int yAnchor, DrwImage drwImage) {
        _drwImage = drwImage;
        setBounds(
            xAnchor, yAnchor,
            _drwImage.getWidth(), _drwImage.getHeight());
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_drwImage.getDrwImage(), 0, 0, null);
    }
}
