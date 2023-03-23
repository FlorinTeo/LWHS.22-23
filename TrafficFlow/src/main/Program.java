package main;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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
    
    private static Queue<String> _lastCollisions = new LinkedList<String>();
    
    private static KeyHook _onCollisionTyped = (KeyEvent keyEvent) -> {
        Set<String> overlays = _mapImage.getOverlays();
        
        // if no previous colliding routes are overlaid and there's only one
        // route displayed, extract and display its collisions and mark it as the _testRoute.
        if (_lastCollisions.isEmpty()) {
            if (overlays.size() == 1) {
                String testRoute = overlays.iterator().next();
                Set<String> collidingRoutes = _collisionsGraph.getNeighbors(testRoute);
                
                overlays.addAll(collidingRoutes);
                _mapImage.setOverlays(overlays);
                _mapFrame.setStatusMessage(collidingRoutes.toString());
                
                _lastCollisions.add(testRoute);
                _lastCollisions.addAll(collidingRoutes);
            } else {
                // no collisions displayed previously, but more than one
                // route on the map => do nothing
            }
        } else {
            // Previous collisions were displayed.
            // If they match what's overlaid now, just clear them out and leave only
            // the test route. 
            if (_lastCollisions.containsAll(overlays) && overlays.containsAll(_lastCollisions)) {
                _mapImage.setOverlays(_lastCollisions.remove());
                _mapFrame.setStatusMessage("");
            }
            // In all cases, clear out the history
            _lastCollisions.clear();
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
        _mapImage = MapImage.load("maps/Woodlawn.jpg");
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
