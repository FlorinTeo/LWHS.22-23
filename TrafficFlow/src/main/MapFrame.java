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

    // Region: Routes manual display
    private class RouteNodeInfo {
        private int _index;
        private ArrayList<String> _routes; 
        
        public RouteNodeInfo() {
            _index = -1;
            _routes = new ArrayList<String>();
        }
    }
    private HashMap<Character, RouteNodeInfo> _routeInfoMap = new HashMap<Character, RouteNodeInfo>();
    
    private void buildRouteInfoMap() {
        Set<String> routes = _mapImage.getRoutes();
        for(String routeName : routes) {
            char key = Character.toUpperCase(routeName.charAt(0));
            RouteNodeInfo info = null;
            if (_routeInfoMap.containsKey(key)) {
                info = _routeInfoMap.get(key);
            } else {
                info = new RouteNodeInfo();
                _routeInfoMap.put(key, info);
            }
            info._routes.add(routeName);
        }
    }
    
    private void showRoutes() {
        List<String> routes = new ArrayList<String>();
        for(RouteNodeInfo rni : _routeInfoMap.values()) {
            if (rni._index >= 0) {
                routes.add(rni._routes.get(rni._index));
            }
        }
        String[] routesArr = routes.toArray(new String[routes.size()]);
        _mapImage.showRoutes(routesArr);
        repaint();
    }
    // EndRegion: Routes manual display

    // Region: KeyInterceptor hooks
    private KeyInterceptor.KeyHook _onKeyABCDE = (keyEvent) -> {
        char key = Character.toUpperCase(keyEvent.getKeyChar());
        if (!_routeInfoMap.containsKey(key)) {
            return;
        }
        RouteNodeInfo oInfo = _routeInfoMap.get(key);
        oInfo._index++;
        if (oInfo._index == oInfo._routes.size()) {
            oInfo._index = -1;
        }
        showRoutes();
    };
    
    private KeyInterceptor.KeyHook _onKeyDelete = (keyEvent) -> {
        for(RouteNodeInfo oi : _routeInfoMap.values()) {
            oi._index = -1;
        }
        showRoutes();
    };
    
    private KeyInterceptor.KeyHook _onKeyX = (keyEvent) -> {
        List<String> routes = _mapImage.getOverlays();
        if (routes.size() == 0) {
            this.setStatusMessage("?");
        } else {
            boolean collide = _mapImage.collide(routes.stream().toArray(String[] ::new));
            this.setStatusMessage(collide ? "collide" : "clear");            
        }
    };
    // EndRegion: KeyInterceptor hooks
    
    public MapFrame(MapImage mapImage) throws IOException {
        super(mapImage);
        
        _mapImage = mapImage;
        buildRouteInfoMap();
        
        // hook in the key intercepts
        _keyInterceptor.setKeyTypedHook('A', _onKeyABCDE);
        _keyInterceptor.setKeyTypedHook('B', _onKeyABCDE);
        _keyInterceptor.setKeyTypedHook('C', _onKeyABCDE);
        _keyInterceptor.setKeyTypedHook('D', _onKeyABCDE);
        _keyInterceptor.setKeyTypedHook('E', _onKeyABCDE);
        _keyInterceptor.setKeyTypedHook('X', _onKeyX);
        _keyInterceptor.setKeyPressedHook(KeyEvent.VK_DELETE, _onKeyDelete);
    }
}
