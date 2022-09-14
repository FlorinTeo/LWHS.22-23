package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Generic KeyInterceptor class, to be used for customized key interaction.
 * By default, the interceptor is handling the keys ' ', '1', '2', 'C' and 'Q'
 * implementing UI level debugging on 2 levels, and Quit.
 * @author Florin
 */
public class KeyInterceptor implements KeyListener {
    
    // To customize key hooks, consuming classes need to define their own
    // functional interface and pass it to one of the setKey**Hook() method
    // along with the KeyEvent.VK_ or Character to trigger upon.
    // I.e:
    // KeyIterceptor.KeyHook onSTyped = (KeyEvent keyEvent) -> {..}
    // myKeyInterceptor.setKeyTypedHook('S', onSTyped)
    public interface KeyHook {
        public void keyHook(KeyEvent keyEvent);
    }
    
    // Region: Data fields
    private Object _sync = new Object();
    private Integer _keyStepLevel = Integer.MIN_VALUE;
    private HashMap<Integer, KeyHook> _keyTypedHooks = new HashMap<Integer, KeyHook>();
    private HashMap<Integer, KeyHook> _keyPressedHooks = new HashMap<Integer, KeyHook>();
    private HashMap<Integer, KeyHook> _keyReleasedHooks = new HashMap<Integer, KeyHook>();
    // EndRegion: Data fields
    
    // Region: Keys hooking methods
    public void setKeyTypedHook(int keyEventKey, KeyHook keyHook) {
        _keyTypedHooks.put(keyEventKey, keyHook);
    }
    
    public void setKeyPressedHook(int keyEventKey, KeyHook keyHook) {
        _keyPressedHooks.put(keyEventKey, keyHook);
    }
    
    public void setKeyReleasedHook(int keyEventKey, KeyHook keyHook) {
        _keyReleasedHooks.put(keyEventKey, keyHook);
    }
    
    private void forwardKeyEvent(KeyEvent e, HashMap<Integer, KeyHook> keyHooks) {
        int hookKey = e.getKeyCode();
        if (hookKey == KeyEvent.VK_UNDEFINED) {
            hookKey = Character.toUpperCase(e.getKeyChar());
        }
        
        if (keyHooks.containsKey(hookKey)) {
            KeyHook targetKeyHook = keyHooks.get(hookKey);
            if (targetKeyHook != null) {
                targetKeyHook.keyHook(e);
            }
        }
    }
    // EndRegion: Keys hooking methods
    
    // Region: KeyListener overrides
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        synchronized (_sync) {
            char ch = keyEvent.getKeyChar();
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
                break;
            default:
                forwardKeyEvent(keyEvent, _keyTypedHooks);
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        forwardKeyEvent(keyEvent, _keyPressedHooks);
    }
    
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        forwardKeyEvent(keyEvent, _keyReleasedHooks);
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
