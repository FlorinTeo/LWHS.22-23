package graphics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;

import graphics.DbgButton.BtnState;

public class DrawingFrame implements Closeable, WindowListener, MouseListener, MouseMotionListener {

    private static final String TITLE = "Coloring Book";
    private static final int PADDING = 4;
    private static final int STATUS_XY_WIDTH = 32;
    private static final int STATUS_TEXT_WIDTH = 200;
    private static final int STATUS_HEIGHT = 18;

    private Drawing _drawing = null;
    private Frame _frame = null;
    private DbgButton[] _dbgButtons = null;
    private DrawingCanvas _canvas = null;
    private TextField _statusX = null;
    private TextField _statusY = null;
    private TextField _statusText = null;
    
    // Region: KeyInterceptor wiring
    private KeyInterceptor _keyInterceptor = new KeyInterceptor();
    
    public void step() throws InterruptedException {
        step(1, 0);
    }
    
    public void step(long delay) throws InterruptedException {
        step(1, delay);
    }
    
    public void stop() throws InterruptedException {
        step(2, 0);
    }
    
    private void step(int level, long delay) throws InterruptedException {
        if (_keyInterceptor.blocksOnLevel(level) || delay > 0) {
                repaint();
        }
        if (_keyInterceptor.isFastFwd() || _keyInterceptor.blocksOnLevel(level)) {
            dbgButtonsEnable();
        }
        _keyInterceptor.step(level, delay);
    }
    // EndRegion: KeyInterceptor wiring

    // Region: DbgButtons management
    private void dbgButtonsSetup(int xAnchor, int yAnchor) throws IOException {
        _dbgButtons = new DbgButton[3];
        for (int i = 0; i < _dbgButtons.length; i++) {
            if (i < _dbgButtons.length - 1) {
                _dbgButtons[i] = new DbgButton(
                        xAnchor,
                        yAnchor,
                        String.format("src/graphics/res/%d_up.png", i+1),
                        String.format("src/graphics/res/%d_down.png", i+1));
                xAnchor += _dbgButtons[i].getWidth();
            } else {
                xAnchor += 4 * PADDING;
                _dbgButtons[i] = new DbgButton(
                        xAnchor,
                        yAnchor,
                        "src/graphics/res/ff_up.png",
                        "src/graphics/res/ff_down.png");
            }
            _dbgButtons[i].setState(BtnState.DISABLED);
        }
    }
    
    private void dbgButtonClick(int iButton) {
        char[] dbgKeys = {'1', '2', ' '};
        for (int i = iButton; i < _dbgButtons.length; i++) {
            if (i != _dbgButtons.length-1) {
                _dbgButtons[i].setState(BtnState.DISABLED);
            }
        }
        _keyInterceptor.simulateKeyTyped(_dbgButtons[iButton], dbgKeys[iButton]);
    }
    
    private void dbgButtonsEnable() {
        for(DbgButton dbgButton : _dbgButtons) {
            if (dbgButton.getState() != BtnState.ENABLED) {
                dbgButton.setState(BtnState.ENABLED);
            }
        }
    }
    // EndRegion: DbgButtons management
    
    // Region: StatusBar management
    private void statusBarSetup(int xAnchor, int yAnchor, int width) {
        int x = xAnchor;
        _statusX = new TextField();
        _statusX.setEditable(false);
        _statusX.setBackground(Color.LIGHT_GRAY);
        _statusX.setBounds(x, yAnchor, STATUS_XY_WIDTH, STATUS_HEIGHT);
        x += STATUS_XY_WIDTH;
        _statusY = new TextField();
        _statusY.setEditable(false);
        _statusY.setBackground(Color.LIGHT_GRAY);
        _statusY.setBounds(x, yAnchor, STATUS_XY_WIDTH, STATUS_HEIGHT);
        x += STATUS_XY_WIDTH;
        _statusText = new TextField();
        _statusText.setEditable(false);
        _statusText.setBackground(Color.LIGHT_GRAY);
        int w = Math.min(STATUS_TEXT_WIDTH, width - x);
        _statusText.setBounds(
                Math.max(x, xAnchor + width - STATUS_TEXT_WIDTH),
                yAnchor, 
                w,
                STATUS_HEIGHT);
    }
    // EndRegion: StatusBar management
    
    public DrawingFrame(Drawing drwImage) throws IOException {
        _drawing = drwImage;
        
        // create the frame and get the insets
        _frame = new Frame(TITLE);
        _frame.setBackground(Color.LIGHT_GRAY);
        _frame.pack();
        Insets insets = _frame.getInsets();
        
        // setup the xAnchor and yAnchor to anchor controls
        int xAnchor = insets.left + PADDING;
        int yAnchor = insets.top + PADDING;
        
        // create the debug buttons
        dbgButtonsSetup(xAnchor, yAnchor);
        yAnchor += _dbgButtons[0].getHeight() + PADDING;
        
        // create the map canvas
        _canvas = new DrawingCanvas(xAnchor, yAnchor, _drawing);
        _canvas.addKeyListener(_keyInterceptor);
        _canvas.addMouseMotionListener(this);
        _canvas.addMouseListener(this);
        yAnchor += _canvas.getHeight() + PADDING;
        
        // create the status bar indicators
        statusBarSetup(xAnchor, yAnchor, _drawing.getWidth());
        yAnchor += STATUS_HEIGHT + PADDING;
        
        // layout the frame size and attributes
        _frame.setSize(
                xAnchor + _drawing.getWidth() + PADDING + insets.right,
                yAnchor + insets.bottom);
        _frame.setLayout(null);
        _frame.setLocationRelativeTo(null);
        _frame.setResizable(false);
        
        // add the controls
        for(DbgButton dbgButton : _dbgButtons) {
            dbgButton.addMouseListener(this);
            dbgButton.addKeyListener(_keyInterceptor);
            _frame.add(dbgButton);
        }
        
        _frame.add(_canvas);
        _frame.add(_statusX);
        _frame.add(_statusY);
        _frame.add(_statusText);
        
        // add the listeners
        _frame.addKeyListener(_keyInterceptor);
        _frame.addWindowListener(this);
    }

    public void open() {
        _frame.setVisible(true);
    }
    
    public void repaint() {
        _canvas.repaint();
    }

    @Override
    public void close() throws IOException {
        if (_frame != null) {
            _frame.setVisible(false);
            _frame.dispose();
            _frame = null;
        }
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

    // Region: MouseListener overrides
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() != _canvas) {
            for (int i = 0; i < _dbgButtons.length; i++) {
                if (_dbgButtons[i].getState() != BtnState.ENABLED) {
                    continue;
                }
                
                if (e.getSource() == _dbgButtons[i]) {
                    dbgButtonClick(i);
                    break;
                }
            }
        } else {
            BufferedImage bi = _drawing.getImage();
            Color c = new Color(bi.getRGB(e.getX(), e.getY()));
            _statusText.setText(String.format("R:%d, G:%d, B:%d", c.getRed(), c.getGreen(), c.getBlue()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    // EndRegion: MouseListener overrides

    // Region: MouseMotionListener overrides
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        _statusX.setText(""+e.getX());
        _statusY.setText(""+e.getY());
        _statusText.setText("");
    }
    // EndRegion: MouseMotionListener overrides
}
