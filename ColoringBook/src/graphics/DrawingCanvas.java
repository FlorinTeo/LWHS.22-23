package graphics;
import java.awt.Canvas;
import java.awt.Graphics;

public class DrawingCanvas extends Canvas {
    private static final long serialVersionUID = 1L;
    
    private Drawing _drwImage;
    
    public DrawingCanvas(int xAnchor, int yAnchor, Drawing drwImage) {
        _drwImage = drwImage;
        setBounds(
            xAnchor, yAnchor,
            _drwImage.getWidth(), _drwImage.getHeight());
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_drwImage.getImage(), 0, 0, null);
    }
}
