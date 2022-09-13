package main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapCanvas extends StepCtrlCanvas {
    private static final long serialVersionUID = 1L;
    
    private MapImage _mapImage;
    
    public MapCanvas(int xAnchor, int yAnchor, MapImage mapImage) {
        _mapImage = mapImage;
        setBounds(
            xAnchor, yAnchor,
            _mapImage.getWidth(), _mapImage.getHeight());
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_mapImage.getBaseMap(), 0, 0, null);
    }
}
