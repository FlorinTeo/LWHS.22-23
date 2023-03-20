package main;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import drawing.KeyInterceptor.KeyHook;
import mapFramework.MapFrame;
import mapFramework.MapImage;

public class Program {
    
    private static MapImage _mapImage;
    private static MapFrame _mapFrame;
    
    private static HashMap<Character, Set<String>> _locationsMap;
    private static Graph<String> _collisionsGraph;

    // Region: Determine and overlay node routes
    private static void buildLocationsMap() {
        _locationsMap = new HashMap<Character, Set<String>>();
        for(String route: _mapImage.getRoutes()) {
            char from = route.charAt(0);
            char to = route.charAt(1); 
            Set<String> nodeRoutes;
            nodeRoutes = _locationsMap.get(from);
            if (nodeRoutes == null) {
                nodeRoutes = new HashSet<String>();
                _locationsMap.put(from, nodeRoutes);
                _mapFrame.setKeyTypedHook(from, _onNodeTyped);
                _mapFrame.setKeyTypedHook(to, _onNodeTyped);
            }
            nodeRoutes.add(route);
        }
        _mapFrame.setKeyPressedHook('T', _onKeyT);
    }
    
    private static KeyHook _onNodeTyped = (KeyEvent keyEvent) -> {
        // clear the status message and the overlays, if any
        _mapFrame.setStatusMessage("");
        _mapImage.setOverlays();
        // get the key from the keyEvent, indicating which node should be displayed
        char key = Character.toUpperCase(keyEvent.getKeyChar());
        Set<String> nodeRoutes = _locationsMap.get(key);
        if (nodeRoutes != null) {
            // get the routes corresponding to the selected node
            String[] nrArr = nodeRoutes.toArray(new String[nodeRoutes.size()]);
            _mapFrame.setStatusMessage(nodeRoutes.toString());
            _mapImage.setOverlays(nrArr);
        }
        // do not forget to repaint the window!
        _mapFrame.repaint();
    };
    // EndRegion: Determine and overlay node routes
    
    // Region: Determine and overlay collision routes
    private static void buildCollisionsGraph() {
        _collisionsGraph = new Graph<String>();
        for(String route : _mapImage.getRoutes()) {
            _collisionsGraph.addNode(route);
        }
        
        for(String route1 : _mapImage.getRoutes()) {
            for (String route2 : _mapImage.getRoutes()) {
                _mapFrame.setStatusMessage(route1 + " & " + route2);
                if (_mapImage.collide(route1, route2)) {
                    _collisionsGraph.addEdge(route1, route2);
                    _collisionsGraph.addEdge(route2, route1);
                }
            }
        }
        
        _mapFrame.setKeyTypedHook('X', _onCollisionTyped);
    }
    
    private static String _lastOverlay = null;
    
    private static KeyHook _onCollisionTyped = (KeyEvent keyEvent) -> {
        if (_lastOverlay != null) {
            _mapImage.setOverlays(_lastOverlay);
            _mapFrame.setStatusMessage("");
            _lastOverlay = null;
        } else {
            Set<String> overlays = _mapImage.getOverlays();
            if (overlays.size() != 1) {
                return;
            }
            
            _lastOverlay = _mapImage.getOverlays().iterator().next();
            Set<String> collisions = _collisionsGraph.getNeighbors(_lastOverlay);
            overlays.addAll(collisions);
            String[] overlaysArr = new String[overlays.size()];
            overlays.toArray(overlaysArr);
            _mapImage.setOverlays(overlaysArr);
            _mapFrame.setStatusMessage(collisions.toString());
        }
        // do not forget to repaint the window!
        _mapFrame.repaint();
    };
    // EndRegion: Determine and overlay collision routes
    
    private static KeyHook _onKeyT = (KeyEvent keyEvent) -> {
        _mapFrame.setStatusMessage(_mapImage.getRoutes().toString());
    };
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // loads an intersection image file and displays it in a map frame.
        _mapImage = MapImage.load("maps/Loyal.jpg");
        _mapFrame = new MapFrame(_mapImage);
        
        // registers the key T with the method _onKeyT
        _mapFrame.setKeyTypedHook('T', _onKeyT);

        // opens the GUI window
        _mapFrame.open();
        
        // stops, waiting for user action
        _mapFrame.setStatusMessage("inspect individual routes for each location");
        _mapFrame.stop();

        // builds the locationsMap, and re-registers the locations keys
        //buildLocationsMap();
        
        // stops again, waiting for user action
        //_mapFrame.setStatusMessage("inspect egress routes for each location");
        //_mapFrame.stop();
        
        // builds the collisions graph, and registers the collision inspection key ('X')
        buildCollisionsGraph();
        
        // stops again, waiting for user action
        _mapFrame.setStatusMessage("inspect collisions for each location");
        _mapFrame.stop();
        
        // close the window and terminate the program
        _mapFrame.close();
    }
}
