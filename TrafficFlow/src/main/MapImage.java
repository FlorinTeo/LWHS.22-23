package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class MapImage {
    private BufferedImage _baseMap = null;
    private HashMap<String, BufferedImage> _mapOverlays = new HashMap<String, BufferedImage>();
    
    public MapImage(String imageMapPath) throws IOException {
        File dir = new File(imageMapPath);

        if (!dir.exists() || !dir.isDirectory()) {
            throw new IOException();
        }
        
        String dirName = dir.getName();
        
        for (String fileName : dir.list()) {
            String[] fileNameParts = fileName.split("\\.|_");
            
            if (fileNameParts[0].equalsIgnoreCase(dirName)) {
                File mapFile = new File(dirName + "/" + fileName);
                if (fileNameParts.length == 2) {
                    _baseMap = ImageIO.read(mapFile);
                } else {
                    _mapOverlays.put(
                            fileNameParts[1],
                            ImageIO.read(mapFile));
                }
            }
        }
    }
    
    public BufferedImage getBaseMap() {
        return _baseMap;
    }
    
    public BufferedImage getOverlay(String layerName) {
        return _mapOverlays.get(layerName);
    }
    
    public BufferedImage getOverlay(int iOverlay) {
        Entry<String, BufferedImage> entry = (Entry<String, BufferedImage>)(_mapOverlays.entrySet().toArray()[iOverlay]);
        return entry.getValue();
    }
    
    public int getCountOverlays() {
        return _mapOverlays.size();
    }
    
    public int getWidth() {
        return _baseMap.getWidth();
    }
    
    public int getHeight() {
        return _baseMap.getHeight();
    }
}
