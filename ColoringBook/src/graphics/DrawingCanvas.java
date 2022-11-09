package graphics;
import java.awt.Canvas;
import java.awt.Graphics;

class DrawingCanvas extends Canvas {

    private static final long serialVersionUID = 1L;
    private Drawing _drwImage;
    
    DrawingCanvas(int xAnchor, int yAnchor, Drawing drwImage) {
        _drwImage = drwImage;
        setBounds(
            xAnchor, yAnchor,
            _drwImage.getWidth(), _drwImage.getHeight());
    }
    
    // Region: [Public] Canvas overrides
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_drwImage.getImage(), 0, 0, null);
    }
    // EndRegion: [Public] Canvas overrides
}
