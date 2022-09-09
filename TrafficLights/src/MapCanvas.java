import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapCanvas extends Canvas {
    private static final long serialVersionUID = 1L;
    
    private BufferedImage _mapImage;
    
    public MapCanvas(int xAnchor, int yAnchor, BufferedImage mapImage) {
        _mapImage = mapImage;
        setBounds(
            xAnchor, yAnchor,
            _mapImage.getWidth(), _mapImage.getHeight());
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_mapImage, 0, 0, null);
    }
}
