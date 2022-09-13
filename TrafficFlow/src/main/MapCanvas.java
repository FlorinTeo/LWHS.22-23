package main;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MapCanvas extends StepCtrlCanvas {
    private static final long serialVersionUID = 1L;
    
    private MapImage _mapImage;
    private int _iOverlay = -1;
    
    public MapCanvas(int xAnchor, int yAnchor, MapImage mapImage) {
        _mapImage = mapImage;
        setBounds(
            xAnchor, yAnchor,
            _mapImage.getWidth(), _mapImage.getHeight());
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            _iOverlay++;
            if (_iOverlay == _mapImage.getCountOverlays()) {
                _iOverlay = -1;
            }
            this.repaint();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(_mapImage.getBaseMap(), 0, 0, null);
        if (_iOverlay >= 0) {
            g.drawImage(_mapImage.getOverlay(_iOverlay), 0, 0, null);
        }
    }
}
