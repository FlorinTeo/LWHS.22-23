package main;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Closeable;
import java.io.IOException;

public class MapFrame implements Closeable, WindowListener {

    private static final String TITLE = "Traffic Flow Manager";
    private static final int PADDING = 4;

    private Frame _frame = null;
    private MapCanvas _mapCanvas = null;
    private MapImage _mapImage = null;
    
    // Region: KeyInterceptor wiring
    private KeyInterceptor _keyInterceptor = new KeyInterceptor();
    
    public void step() {
        _keyInterceptor.step(0);
    }
    
    public void step(int level) {
        _keyInterceptor.step(level);
    }
    
    private KeyInterceptor.KeyHook _onKeyArrowUp = (keyEvent) -> {
        int nOverlays = _mapImage.getCountOverlays();
        int overlayMask = (int)((1 << nOverlays) * Math.random());
        _mapCanvas.setOverlayMask(overlayMask);
        _mapCanvas.repaint();
    };
    
    private KeyInterceptor.KeyHook _onKeyB = (keyEvent) -> {
        _mapCanvas.setOverlayMask(0);
        _mapCanvas.repaint();
    };
    // EndRegion: KeyInterceptor wiring

    public MapFrame(MapImage mapImage) throws IOException {
        _mapImage = mapImage;
        _keyInterceptor.setKeyPressedHook(KeyEvent.VK_UP, _onKeyArrowUp);
        _keyInterceptor.setKeyTypedHook('B', _onKeyB);
        
        _frame = new Frame(TITLE);
        _frame.pack();
        Insets insets = _frame.getInsets();
        _frame.setSize(insets.left + PADDING + _mapImage.getWidth() + PADDING + insets.right,
                insets.top + PADDING + _mapImage.getHeight() + PADDING + insets.bottom);
        _frame.setLayout(null);
        _frame.setLocationRelativeTo(null);
        _frame.setResizable(false);

        _mapCanvas = new MapCanvas(
                insets.left + PADDING, 
                insets.top + PADDING,
                _mapImage);
        _mapCanvas.addKeyListener(_keyInterceptor);
        
        _frame.add(_mapCanvas);
        _frame.addWindowListener(this);
    }

    public void open() {
        _frame.setVisible(true);
    }

    @Override
    public void close() throws IOException {
        _frame.setVisible(false);
        _frame.dispose();
        _frame = null;
    }

    // Region: WindowListener overrides
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing");
        try {
            this.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    // EndRegion: WindowListener overrides
}
