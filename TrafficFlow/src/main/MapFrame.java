package main;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapFrame implements Closeable, WindowListener {

    private static final String TITLE = "Traffic Flow Manager";
    private static final int PADDING = 4;

    private Frame _frame = null;
    private MapCanvas _mapCanvas = null;
    private MapImage _mapImage = null;
    
    // Region: KeyInterceptor wiring
    private KeyInterceptor _keyInterceptor = new KeyInterceptor();
    
    public void step() {
        _keyInterceptor.step(1);
    }
    
    public void step(int level) {
        _keyInterceptor.step(level);
    }
    
    private String[][] _demoOverlays = {
            {"AB", "AC", "AD"},
            {},
            {"CA", "CB", "CD"},
            {"DA", "DB", "DC"},
            {"EA", "EB", "EC", "ED"}
    };
    private int[] _iOverlays = {-1, -1, -1, -1, -1};
    private KeyInterceptor.KeyHook _onKeyACDE = (keyEvent) -> {
        
        int iO = Character.toUpperCase(keyEvent.getKeyChar()) - 'A';
        _iOverlays[iO]++;
        if (_iOverlays[iO] == _demoOverlays[iO].length) {
            _iOverlays[iO] = -1;
        }
        
        List<String> overlays = new ArrayList<String>();
        for (int i = 0; i < _iOverlays.length; i++) {
            if (_iOverlays[i] >= 0) {
                overlays.add(_demoOverlays[i][_iOverlays[i]]);
            }
        }
        String[] overlaysArr = overlays.toArray(new String[overlays.size()]);
        _mapCanvas.setOverlays(overlaysArr);
        _mapCanvas.repaint();
    };
    
    private KeyInterceptor.KeyHook _onKeyDelete = (keyEvent) -> {
        _iOverlays = new int[] {-1, -1, -1, -1, -1};
        _mapCanvas.setOverlays();
        _mapCanvas.repaint();
    };
    // EndRegion: KeyInterceptor wiring

    public MapFrame(MapImage mapImage) throws IOException {
        _mapImage = mapImage;
        
        // hook in the key intercepts
        _keyInterceptor.setKeyTypedHook('A', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('C', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('D', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('E', _onKeyACDE);
        _keyInterceptor.setKeyPressedHook(KeyEvent.VK_DELETE, _onKeyDelete);
        
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
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            this.close();
        } catch (IOException e1) {
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
