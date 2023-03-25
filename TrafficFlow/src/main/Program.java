package main;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
    
    
    // Region: Determine collisions between routes
    private static void buildCollisionsGraph() {
        // TODO: build a Graph<String> where each route is a node
        // TODO: and any two colliding routes are linked by an edge.
        
        _mapFrame.setKeyTypedHook('X', _onKeyX);
    }
    
    /**
     * Global Queue containing all the colliding routes displayed on the map
     * as a result of the user pressing the 'X' key. The queue contains at
     * its head the test route, following by all the other routes colliding
     * with it. If no colliding routes are displayed the queue is empty.
     */
    private static Queue<String> _collisionsQueue = new LinkedList<String>();
    
    private static KeyHook _onKeyX = (KeyEvent keyEvent) -> {
       Set<String> overlays = _mapImage.getOverlays();
       
       // The user pressed the 'X' key.
       // There are only three possible cases:
       if (overlays.size() == 1) {
           // (1) there is one route displayed on the map => we need to
           // determine and overlay the other routes it is colliding with.
           String testRoute = overlays.iterator().next();
           
           // TODO: Use the collisions graph built in the method buildCollisionsGraph()
           // TODO: to determine the collisions for the testRoute. Then add the
           // TODO: testRoute and its collisions to the _collisionsQueue such that
           // TODO: the testRoute is in front of the queue. It will be needed to
           // TODO: restore the map if the user is pressing 'X' again
           _collisionsQueue.clear();
           _collisionsQueue.add(testRoute);
           _collisionsQueue.add("EA");
           _collisionsQueue.add("DC");
           _collisionsQueue.add(testRoute);
           
           // Overlay the testRoute and its collisions on the map
           _mapImage.setOverlays(_collisionsQueue);
           _mapFrame.repaint();
           _mapFrame.setStatusMessage("Show collisions for " + testRoute);
       } else if (!_collisionsQueue.isEmpty()) {
           // (2) there are more than one routes displayed, because we just
           // overlaid the collision routes! => we need to remove all the 
           // colliding routes leaving only the original test route on the map!
           
           // The front of the _collisionsQueue contains the original testRoute
           // _collisionsQueue.remove() returns it, such that it ends up being
           // the only route overlaid on the map (hence removing its collisions)
           _mapImage.setOverlays(_collisionsQueue.remove());
           _mapFrame.repaint();
           
           // Clear the "history" since we no longer store any collisions.
           _collisionsQueue.clear();
       } else {
           // (3) there are either 0 or more than 1 routes on the map,
           // because of how the user selected them, not because we overlaid
           // collisions. => do nothing
           _mapFrame.setStatusMessage(overlays.toString());
       }
    };
    // EndRegion: Determine collisions between routes
    
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
//        _mapFrame.setStatusMessage("inspect individual routes for each location");
//        _mapFrame.stop();

//        // builds the locationsMap, and re-registers the locations keys
//        buildLocationsMap();
//        
//        // stops again, waiting for user action
//        _mapFrame.setStatusMessage("inspect egress routes for each location");
//        _mapFrame.stop();
        
        buildCollisionsGraph();
        _mapFrame.setStatusMessage("show collisions with one selected route!");
        _mapFrame.stop();
        
        // close the window and terminate the program
        _mapFrame.close();
    }
}
