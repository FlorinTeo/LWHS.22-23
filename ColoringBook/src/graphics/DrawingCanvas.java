package graphics;
import java.awt.Canvas;
import java.awt.Graphics;

class DrawingCanvas extends Canvas {

    private static final long serialVersionUID = 1L;
    private int _xOrig = 0;
    private int _yOrig = 0;
    private int _scale = 1;
    private Drawing _drwImage;
    
    DrawingCanvas(int xAnchor, int yAnchor, Drawing drwImage) {
        _drwImage = drwImage;
        setBounds(
            xAnchor, yAnchor,
            _drwImage.getWidth(), _drwImage.getHeight());
    }
    
    // Region: [Internal] User control methods
    public void zoom(int xAnchor, int yAnchor, int levels) {
        int newScale = _scale + levels;
        if(newScale > 0 && newScale <= 8) {
            int crtDx = -_xOrig + (xAnchor - xAnchor % _scale);
            int crtDy = -_yOrig + (yAnchor - yAnchor % _scale);
            int newDx = newScale * (crtDx / _scale);
            int newDy = newScale * (crtDy / _scale);
            _xOrig = Math.min(0, (xAnchor - xAnchor % newScale) - newDx);
            _yOrig = Math.min(0, (yAnchor - yAnchor % newScale) - newDy);
            _scale = newScale;
            
            if (_xOrig % _scale != 0 || _yOrig % _scale != 0) {
                System.out.println("hmm");
            }
            
            repaint();
        }
    }
    
    // EndRegion: [Internal] User control methods
    
    // Region: [Public] Canvas overrides
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(
                _drwImage.getImage(), 
                _xOrig, _yOrig, 
                _scale * _drwImage.getWidth(),
                _scale * _drwImage.getHeight(),
                null);
    }
    // EndRegion: [Public] Canvas overrides
}
