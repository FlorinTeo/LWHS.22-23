package main;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapCanvas extends Canvas {
    private static final long serialVersionUID = 1L;
    
    private MapImage _mapImage;
    private List<String> _overlays = new ArrayList<String>();
    
    public MapCanvas(int xAnchor, int yAnchor, MapImage mapImage) {
        _mapImage = mapImage;
        setBounds(
            xAnchor, yAnchor,
            _mapImage.getWidth(), _mapImage.getHeight());
    }
    
    public void setOverlays(String... overlays) {
        _overlays = Arrays.asList(overlays);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_mapImage.getBaseMap(), 0, 0, null);
        for (String overlay : _overlays) {
            BufferedImage overlayImage = _mapImage.getOverlay(overlay);
            if (overlayImage != null) {
                g.drawImage(overlayImage, 0, 0, null);
            }
        }
    }
}
