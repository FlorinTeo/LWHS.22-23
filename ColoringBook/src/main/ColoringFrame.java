package main;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Closeable;
import java.io.IOException;

import main.DbgButton.BtnState;

public class ColoringFrame implements Closeable, WindowListener, MouseListener, MouseMotionListener {

    private static final String TITLE = "Coloring Book";
    private static final int PADDING = 4;
    private static final int STATUS_XY_WIDTH = 32;
    private static final int STATUS_XY_HEIGHT = 18;

    private DrwImage _drwImage = null;
    private Frame _frame = null;
    private DbgButton[] _dbgButtons = null;
    private ColoringCanvas _canvas = null;
    private TextField _statusX = null;
    private TextField _statusY = null;
    
    // Region: KeyInterceptor wiring
    private KeyInterceptor _keyInterceptor = new KeyInterceptor();
    
    public void step() throws InterruptedException {
        if (_keyInterceptor.blocksOnLevel(1)) {
            dbgButtonsEnable();
        }
        _keyInterceptor.step(1);
    }
    
    public void step(int level) throws InterruptedException {
        if (_keyInterceptor.blocksOnLevel(level)) {
            dbgButtonsEnable();
        }
        _keyInterceptor.step(level);
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
                        String.format("res/%d_up.png", i+1),
                        String.format("res/%d_down.png", i+1));
                xAnchor += _dbgButtons[i].getWidth();
            } else {
                xAnchor += 4 * PADDING;
                _dbgButtons[i] = new DbgButton(
                        xAnchor,
                        yAnchor,
                        "res/ff_up.png",
                        "res/ff_down.png");
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
    
    public ColoringFrame(DrwImage drwImage) throws IOException {
        _drwImage = drwImage;
        
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
        _canvas = new ColoringCanvas(xAnchor, yAnchor, _drwImage);
        _canvas.addKeyListener(_keyInterceptor);
        _canvas.addMouseMotionListener(this);
        yAnchor += _canvas.getHeight() + PADDING;
        
        // create the status bar indicators
        _statusX = new TextField();
        _statusX.setEditable(false);
        _statusX.setBackground(Color.LIGHT_GRAY);
        _statusX.setBounds(xAnchor, yAnchor, STATUS_XY_WIDTH, STATUS_XY_HEIGHT);
        _statusY = new TextField();
        _statusY.setEditable(false);
        _statusY.setBackground(Color.LIGHT_GRAY);
        _statusY.setBounds(xAnchor + STATUS_XY_WIDTH, yAnchor, STATUS_XY_WIDTH, STATUS_XY_HEIGHT);
        yAnchor += STATUS_XY_HEIGHT + PADDING;
        
        // layout the frame size and attributes
        _frame.setSize(
                xAnchor + _drwImage.getWidth() + PADDING + insets.right,
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
    }
    // EndRegion: MouseMotionListener overrides
}
