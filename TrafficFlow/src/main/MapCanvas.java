package main;
import java.awt.Canvas;
import java.awt.Graphics;

public class MapCanvas extends Canvas {
    private static final long serialVersionUID = 1L;
    
    private MapImage _mapImage;
    private int _overlayMask = 0;
    
    public MapCanvas(int xAnchor, int yAnchor, MapImage mapImage) {
        _mapImage = mapImage;
        setBounds(
            xAnchor, yAnchor,
            _mapImage.getWidth(), _mapImage.getHeight());
    }
    
    public void setOverlayMask(int overlayMask) {
        _overlayMask = overlayMask;
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_mapImage.getBaseMap(), 0, 0, null);
        int crtMask = _overlayMask;
        int iOverlay = 0;
        while(crtMask > 0) {
            if ((crtMask & 1) == 1) {
                g.drawImage(_mapImage.getOverlay(iOverlay), 0, 0, null);
            }
            crtMask >>= 1;
            iOverlay++;
        }
    }
}
