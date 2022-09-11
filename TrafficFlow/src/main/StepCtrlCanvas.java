package main;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StepCtrlCanvas extends Canvas implements KeyListener {
    // default serial version
    private static final long serialVersionUID = 1L;
    
    // Region: Data fields
    private Object _sync = new Object();
    private Integer _keyStepLevel = Integer.MIN_VALUE;
    // EndRegion: Data fields
    
    public StepCtrlCanvas() {
        this.addKeyListener(this);
    }
    
    // Region: KeyListener overrides
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        char ch = keyEvent.getKeyChar();
        synchronized (_sync) {
            switch (Character.toUpperCase(ch)) {
            case ' ':
                _keyStepLevel = 0;
                _sync.notifyAll();
                break;
            case '1':
                _keyStepLevel = 1;
                _sync.notifyAll();
                break;
            case '2':
                _keyStepLevel = 2;
                _sync.notifyAll();
                break;
            case 'C':
                _keyStepLevel = Integer.MAX_VALUE;
                _sync.notifyAll();
                break;
            case 'Q':
                System.exit(0);
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    // EndRegion: KeyListener overrides
    
    public void step(int level) {
        synchronized (_sync) {
            try {
                // block if level is same or greater than the key-typed level.
                // (i.e step_0 won't block if user typed 2)
                if (level >= _keyStepLevel) {
                    _sync.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
