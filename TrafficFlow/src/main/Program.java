package main;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import drawing.KeyInterceptor.KeyHook;
import mapFramework.MapFrame;
import mapFramework.MapImage;

public class Program {
    
    private static MapImage _mapImage;
    private static MapFrame _mapFrame;
    private static Map<Character, Set<String>> _locationsMap;
    private static boolean displayed = false;
    private static Graph<String> trafficGraph;
    
    /**
     * Lambda method which will be called each time the user
     * is pressing the key 'T'.
     * @param keyEvent - details about the key which was pressed.
     */
    private static KeyHook _onKeyT = (KeyEvent keyEvent) -> {
        String statusText = "Key: '" + keyEvent.getKeyChar() + "'; ";
        statusText += "Routes: " + _mapImage.getRoutes();
        _mapFrame.setStatusMessage(statusText);
    };
    
    private static KeyHook _onAllKeys = (KeyEvent keyEvent) -> {
    	//build an array list of routes that match this key
    	ArrayList<String> routes = new ArrayList<String>();
    	char c = Character.toUpperCase(keyEvent.getKeyChar());
    	Set<String> locations = _locationsMap.get(c);
    	for(String s: locations) {
    		routes.add(s);
    	}
    	//convert the arraylist into an array of routes
    	String[] aRoutes = new String[routes.size()];
    	aRoutes = routes.toArray(aRoutes);
    	
    	_mapImage.setOverlays(aRoutes);
    	_mapFrame.setStatusMessage(routes.toString());
    	_mapFrame.repaint();
    	
    	
    	
    	
    };
	private static String displayedRoute;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // loads an intersection image file and displays it in a map frame.
        _mapImage = MapImage.load("maps/Woodlawn.jpg");
        _mapFrame = new MapFrame(_mapImage);
        
        //build the locations map
        //_locationsMap = buildLocationsMap(_mapImage.getRoutes());
        
        // registers the key T with the method _onKeyT
        _mapFrame.setKeyTypedHook('T', _onKeyT);
        
        
        //Build's a colliding graph 
        trafficGraph = buildCollingGraph();
        trafficGraph.graphColoring();
        System.out.println(trafficGraph);
        // opens the GUI window
        _mapFrame.open();
        
        // stops, waiting for user action
        _mapFrame.stop();
        
        // close the window and terminate the program
        _mapFrame.close();
        
        
    }
    
    /**Hooks up the x key and build a collision graph so we can show
     * collisions when hitting the key X
     */
    
    private static Graph<String> buildCollingGraph() {
    	Graph<String> graph = new Graph<String>();
    	Set<String> allRoutes= _mapImage.getRoutes();
    	for(String s: allRoutes) {
    		graph.addNode(s);
    	}
    	
    	for(String from: allRoutes) {
    		for(String to: allRoutes) {
    			if(_mapImage.collide(from, to)) {
    				graph.addEdge(from, to);
    			}
    		}
    	}
    	//System.out.println(graph.toString()); 
		_mapFrame.setKeyTypedHook('X', _onKeyX);
		return graph;
				
	}
    
    private static KeyHook _onKeyX = (KeyEvent keyEvent) -> {
        String statusText = "Key: '" + keyEvent.getKeyChar() + "'; ";
        Set<String> overlays = _mapImage.getOverlays();    
        
        if(overlays.size() == 1) {
        	statusText += overlays.toString();
        	displayedRoute = overlays.iterator().next();
        	for(String r: trafficGraph.getNeighbors(displayedRoute)) {
        		overlays.add(r);
        	}
        	//overlays.add("AD");
        	_mapImage.setOverlays(overlays);
        	_mapFrame.repaint();
        	displayed = true;
        }else if(displayed){
        	overlays.clear();
        	overlays.add(displayedRoute);
        	_mapImage.setOverlays(overlays);
        	_mapFrame.repaint();
        	displayed = false;
        	
        }else{
        	displayed = false;
        	statusText += "Only works with one route selected. (" + overlays.size() + "selected)";
        }
        _mapFrame.setStatusMessage(statusText);
    };
    
    private static KeyHook _onKeyW = (KeyEvent keyEvent) -> {
    	
    };

	/*public static void buildLocationsMap() {
    	_locationsMap = new HashMap<Character, Set<String>>();
    	Set<String> set = _mapImage.getRoutes();
    	for(String s: set) {
    		Character c = s.charAt(0);
    		//if doesn't already exist..create the set
    		if(!_locationsMap.containsKey(c)) {
    			_locationsMap.put(c, new HashSet<String>());
    		}
    		//add the current route to the current set
    		_locationsMap.get(c).add(s);
    	}
    	
    	System.out.println(_locationsMap);
    	
    }*/
    
    public static Map<Character, Set<String>> buildLocationsMap(Set<String> set){
    	HashMap<Character, Set<String>> map = new HashMap<Character, Set<String>>();
    	for(String s: set) {
    		Character c = Character.toUpperCase(s.charAt(0));
    		//if doesn't already exist..create the set
    		if(!map.containsKey(c)) {
    			map.put(c, new HashSet<String>());
    			if(_mapFrame != null) _mapFrame.setKeyTypedHook(c, _onAllKeys);
    			
    		}
    		map.get(c).add(s);
    	}
		return map;
    	
    }
}
