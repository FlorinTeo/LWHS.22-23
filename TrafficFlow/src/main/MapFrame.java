package main;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import graphics.DrawingFrame;
import graphics.KeyInterceptor;

public class MapFrame extends DrawingFrame {

    private MapImage _mapImage;

    // Region: Overlays manual display
    private class OverlayInfo {
        private int _overlayIndex;
        private ArrayList<String> _overlayNames; 
        
        public OverlayInfo() {
            _overlayIndex = -1;
            _overlayNames = new ArrayList<String>();
        }
    }
    private HashMap<Character, OverlayInfo> _overlayInfoMap = new HashMap<Character, OverlayInfo>();
    
    private void buildOverlayInfoMap() {
        Set<String> overlays = _mapImage.getOverlays();
        for(String overlayName : overlays) {
            char key = Character.toUpperCase(overlayName.charAt(0));
            OverlayInfo info = null;
            if (_overlayInfoMap.containsKey(key)) {
                info = _overlayInfoMap.get(key);
            } else {
                info = new OverlayInfo();
                _overlayInfoMap.put(key, info);
            }
            info._overlayNames.add(overlayName);
        }
    }
    
    private void showOverlays() {
        List<String> overlays = new ArrayList<String>();
        for(OverlayInfo oi : _overlayInfoMap.values()) {
            if (oi._overlayIndex >= 0) {
                overlays.add(oi._overlayNames.get(oi._overlayIndex));
            }
        }
        String[] overlaysArr = overlays.toArray(new String[overlays.size()]);
        _mapImage.showOverlays(overlaysArr);
        repaint();
    }
    // EndRegion: Overlays manual display

    // Region: KeyInterceptor hooks
    private KeyInterceptor.KeyHook _onKeyACDE = (keyEvent) -> {
        char key = Character.toUpperCase(keyEvent.getKeyChar());
        if (!_overlayInfoMap.containsKey(key)) {
            return;
        }
        OverlayInfo oInfo = _overlayInfoMap.get(key);
        oInfo._overlayIndex++;
        if (oInfo._overlayIndex == oInfo._overlayNames.size()) {
            oInfo._overlayIndex = -1;
        }
        showOverlays();
    };
    
    private KeyInterceptor.KeyHook _onKeyDelete = (keyEvent) -> {
        for(OverlayInfo oi : _overlayInfoMap.values()) {
            oi._overlayIndex = -1;
        }
        showOverlays();
    };
    // EndRegion: KeyInterceptor hooks
    
    public MapFrame(MapImage mapImage) throws IOException {
        super(mapImage);
        
        _mapImage = mapImage;
        buildOverlayInfoMap();
        
        // hook in the key intercepts
        _keyInterceptor.setKeyTypedHook('A', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('C', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('D', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('E', _onKeyACDE);
        _keyInterceptor.setKeyPressedHook(KeyEvent.VK_DELETE, _onKeyDelete);
    }
}
