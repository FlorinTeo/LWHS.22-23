package main;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapFrame implements Closeable, WindowListener {

    private static final String TITLE = "Traffic Flow Manager";
    private static final int PADDING = 4;

    private MapImage _mapImage = null;
    private Frame _frame = null;
    private MapButton[] _dbgButtons = null;
    private MapCanvas _mapCanvas = null;
    
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

    private void setupDbgButtons(int xAnchor, int yAnchor) throws IOException {
        _dbgButtons = new MapButton[4];
        for (int i = 0; i < _dbgButtons.length; i++) {
            if (i < _dbgButtons.length - 1) {
                _dbgButtons[i] = new MapButton(
                        xAnchor,
                        yAnchor,
                        String.format("res/%d_up.png", i+1),
                        String.format("res/%d_down.png", i+1));
                xAnchor += _dbgButtons[i].getWidth() + PADDING;
            } else {
                xAnchor += 3 * PADDING;
                _dbgButtons[i] = new MapButton(
                        xAnchor,
                        yAnchor,
                        "res/ff_up.png",
                        "res/ff_down.png");
            }
        }
    }
    
    public MapFrame(MapImage mapImage) throws IOException {
        _mapImage = mapImage;
        
        // hook in the key intercepts
        _keyInterceptor.setKeyTypedHook('A', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('C', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('D', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('E', _onKeyACDE);
        _keyInterceptor.setKeyPressedHook(KeyEvent.VK_DELETE, _onKeyDelete);
        
        // create the frame and get the insets
        _frame = new Frame(TITLE);
        _frame.pack();
        Insets insets = _frame.getInsets();
        
        // setup the xAnchor and yAnchor to anchor controls
        int xAnchor = insets.left + PADDING;
        int yAnchor = insets.top + PADDING;
        
        // create the debug buttons
        setupDbgButtons(xAnchor, yAnchor);
        yAnchor += _dbgButtons[0].getHeight() + PADDING;
        
        // create the map canvas
        _mapCanvas = new MapCanvas(xAnchor, yAnchor, _mapImage);
        _mapCanvas.addKeyListener(_keyInterceptor);
        yAnchor += _mapCanvas.getHeight() + PADDING;
        
        // layout the frame size and attributes
        _frame.setSize(
                xAnchor + _mapImage.getWidth() + PADDING + insets.right,
                yAnchor + insets.bottom);
        _frame.setLayout(null);
        _frame.setLocationRelativeTo(null);
        _frame.setResizable(false);
        
        // add the controls
        for(MapButton dbgButton : _dbgButtons) {
            _frame.add(dbgButton);
        }
        
        _frame.add(_mapCanvas);
        
        // add the listeners
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
