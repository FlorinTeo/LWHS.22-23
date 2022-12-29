package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import graphics.Drawing;

public class MapImage extends Drawing {
    // File name for the base map image (i.e. "Ravenna")
    private String _mapName;
    // Map<overlay_name, overlay_image> (i.e. {<"AB", imageAB>, <"AC", imageAC>, ..})
    private HashMap<String, BufferedImage> _mapOverlays;

    /**
     * Reads a MapImage content from a folder. The folder is expected to contain
     * the base map as a one-part name (i.e. "Ravenna.jpg") and a set of overlay
     * file names as a two-parts names (i.e. "Ravenna_AB.png").
     * Creates and returns a new MapImage instance containing the map.
     */
    public static MapImage read(String mapImageDir) throws IOException {
        File dir = new File(mapImageDir);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IOException();
        }
        
        // Load the baseMap and create the mapImage
        String mapName = dir.getName();
        File mapFile = new File(dir.getName() + "/" + mapName + ".jpg");
        MapImage mapImage = new MapImage(mapName, ImageIO.read(mapFile));
        
        // Load the overlays into the mapImage
        FilenameFilter overlayFilter = (file, name)-> { return name.matches(dir + "_.+\\.png"); };
        for (String overlayFileName : dir.list(overlayFilter)) {
            File overlayFile = new File(dir.getName() + "/" + overlayFileName);
            String overlayName = overlayFileName.split("_|\\.")[1];
            mapImage._mapOverlays.put(
                    overlayName,
                    ImageIO.read(overlayFile));
        }
        
        // return the newly created and loaded mapImage
        return mapImage;
    }
    
    /**
     * Default MapImage constructor
     */
    public MapImage(String mapName, BufferedImage baseMap) {
        super(baseMap);
        _mapName = mapName;
        _mapOverlays = new HashMap<String, BufferedImage>();
    }
    
    public String getMapName() {
        return _mapName;
    }
    
    public BufferedImage getOverlay(String layerName) {
        return _mapOverlays.get(layerName);
    }
    
    public int getOverlaysCount() {
        return _mapOverlays.size();
    }
    
    public int getWidth() {
        return _image.getWidth();
    }
    
    public int getHeight() {
        return _image.getHeight();
    }
}
