package main;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import graphics.DrawingFrame;
import graphics.KeyInterceptor;

public class MapFrame extends DrawingFrame {

    private MapImage _mapImage;
    
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
        _mapImage.setOverlays(overlaysArr);
        repaint();
    };
    
    private KeyInterceptor.KeyHook _onKeyDelete = (keyEvent) -> {
        _iOverlays = new int[] {-1, -1, -1, -1, -1};
        _mapImage.setOverlays();
        repaint();
    };
    
    public MapFrame(MapImage mapImage) throws IOException {
        super(mapImage);
        _mapImage = mapImage;
        
        // hook in the key intercepts
        _keyInterceptor.setKeyTypedHook('A', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('C', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('D', _onKeyACDE);
        _keyInterceptor.setKeyTypedHook('E', _onKeyACDE);
        _keyInterceptor.setKeyPressedHook(KeyEvent.VK_DELETE, _onKeyDelete);
    }

}
