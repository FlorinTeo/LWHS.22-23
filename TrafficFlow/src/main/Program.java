package main;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import drawing.KeyInterceptor.KeyHook;

public class Program {
    
    private static MapImage _mapImage;
    private static MapFrame _mapFrame;
    
    // Region: Determine and overlay node routes
    private static HashMap<Character, Set<String>> _mapNodeRoutes;
    
    private static void mapNodes() {
        _mapNodeRoutes = new HashMap<Character, Set<String>>();
        for(String route: _mapImage.getRoutes()) {
            char from = route.charAt(0);
            char to = route.charAt(1); 
            Set<String> nodeRoutes;
            nodeRoutes = _mapNodeRoutes.get(from);
            if (nodeRoutes == null) {
                nodeRoutes = new HashSet<String>();
                _mapNodeRoutes.put(from, nodeRoutes);
                _mapFrame.setKeyTypedHook(from, _onNodeTyped);
                _mapFrame.setKeyTypedHook(to, _onNodeTyped);
            }
            nodeRoutes.add(route);
        }
        _mapFrame.setKeyPressedHook('T', _onTextTyped);
    }
    
    private static KeyHook _onNodeTyped = (KeyEvent keyEvent) -> {
        // clear the status message and the overlays, if any
        _mapFrame.setStatusMessage("");
        _mapImage.setOverlays();
        // get the key from the keyEvent, indicating which node should be displayed
        char key = Character.toUpperCase(keyEvent.getKeyChar());
        Set<String> nodeRoutes = _mapNodeRoutes.get(key);
        if (nodeRoutes != null) {
            // get the routes corresponding to the selected node
            String[] nrArr = nodeRoutes.toArray(new String[nodeRoutes.size()]);
            _mapFrame.setStatusMessage(nodeRoutes.toString());
            _mapImage.setOverlays(nrArr);
        }
        // do not forget to repaint the window!
        _mapFrame.repaint();
    };
    
    private static KeyHook _onTextTyped = (KeyEvent keyEvent) -> {
        _mapFrame.setStatusMessage(_mapImage.getRoutes().toString());
    };
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Traffic Flow Manager!");
        _mapImage = MapImage.load("maps/Ravenna.jpg");
        _mapFrame = new MapFrame(_mapImage);
        _mapFrame.open();
        
        // EXplore individual node routes by pressing the node keys.
        _mapFrame.setStatusMessage("All routes: " + _mapImage.getRoutes());
        _mapFrame.stop();

        // Explore all node egress routes by pressing the node keys.
        _mapFrame.setStatusMessage("All routes: " + _mapImage.getRoutes());
        _mapImage.setOverlays();
        mapNodes();
        _mapFrame.stop();
        
        // cleanup and goodbye
        _mapFrame.close();
        System.out.println("Goodbye!");
    }
}
