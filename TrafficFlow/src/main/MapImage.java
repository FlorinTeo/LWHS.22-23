package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

import graphics.Drawing;

public class MapImage extends Drawing {
    // File name for the base map image (i.e. "Ravenna")
    private String _mapName;
    // Map<overlay_name, overlay_image> (i.e. {<"AB", imageAB>, <"AC", imageAC>, ..})
    private HashMap<String, BufferedImage> _mapOverlays;
    // overlay_names to put on the map
    private List<String> _overlays = new ArrayList<String>();

    // Region: File IO
    private class MapMetadata {
        private String _mapName = "";
        private HashMap<String, String> _mapOverlaysRaw = new HashMap<String, String>();
    };
    
    private static String imageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }
    
    private static BufferedImage base64ToImage(String base64) throws IOException {
        byte[] imgBytes = Base64.getDecoder().decode(base64);
        InputStream imgStream = new ByteArrayInputStream(imgBytes);
        BufferedImage image = ImageIO.read(imgStream);
        return image;
    }
    
    private static byte[] toByteArray(BigInteger big, int minLength) {
        byte[] base=big.toByteArray();
        byte[] returnArray=new byte[Math.max(base.length, minLength)];
        if ((base[0]&128)!=0) {
            Arrays.fill(returnArray, (byte) 0xFF);
        }
        System.arraycopy(base,0,returnArray,returnArray.length-base.length,base.length);
        return returnArray;
    }
    
    private static MapImage loadFromFile(File file) throws IOException {
        Path filePath = Paths.get(file.getAbsolutePath());
        byte[] rawBytes = Files.readAllBytes(filePath);
        byte[] rawOffset = Arrays.copyOfRange(rawBytes, rawBytes.length-4, rawBytes.length);
        BigInteger offset = new BigInteger(rawOffset);
        byte[] rawJsonBytes = Arrays.copyOfRange(rawBytes,  offset.intValue(), rawBytes.length - 4);
        String rawjson = new String(rawJsonBytes);
        Gson deserializer = new Gson();
        MapMetadata mapMetadata = deserializer.fromJson(rawjson, MapMetadata.class);
        byte[] rawImageBytes = Arrays.copyOfRange(rawBytes, 0, offset.intValue());
        InputStream imageStream = new ByteArrayInputStream(rawImageBytes);
        BufferedImage image = ImageIO.read(imageStream);
        
        MapImage mapImage = new MapImage(mapMetadata._mapName, image);
        for(Map.Entry<String, String> mapOverlayRaw : mapMetadata._mapOverlaysRaw.entrySet())
        {
            mapImage._mapOverlays.put(
                    mapOverlayRaw.getKey(), 
                    base64ToImage(mapOverlayRaw.getValue()));
        }
        
        return mapImage;
    }
    
    private static MapImage loadFromDir(File dir) throws IOException {
        // Load the baseMap and create the mapImage
        String mapName = dir.getName();
        File mapFile = new File(dir.getName() + "/" + mapName + "_.jpg");
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
     * Reads a MapImage content from a folder or a file.
     * <p>If mapImagePath points to a folder, the folder is expected to contain
     * the base map as a one-part name (i.e. "Ravenna_.jpg") and a set of overlay
     * file names as a two-parts names (i.e. "Ravenna_AB.png").</p>
     * <p>If mapImagePath points to a file, the file is expected to be a jpeg image
     * enriched with route overlay.
     * </p>
     * @param mapImagePath - path to a folder or to an enriched .jpg file
     * @returns a new MapImage object containing the map.
     */
    public static MapImage load(String mapImagePath) throws IOException {
        File file = new File(mapImagePath);
        if (!file.exists()) {
            throw new IOException();
        }
        
        if (file.isDirectory()) {
            return loadFromDir(file);
        } else {
            return loadFromFile(file);
        }
    }
    
    /**
     * Saves the map image in the given file. The file is a custom format,
     * containing the base map jpeg, followed by a json serialized object
     * containing a map with all routes for this map.
     * @throws IOException
     */
    public void save(String mapImageFileName) throws IOException {
        MapMetadata mapMetadata = new MapMetadata();
        mapMetadata._mapName = _mapName;
        for(Map.Entry<String, BufferedImage> mapOverlay : _mapOverlays.entrySet())
        {
            mapMetadata._mapOverlaysRaw.put(
                    mapOverlay.getKey(),
                    imageToBase64(mapOverlay.getValue()));
        }
        Gson serializer = new Gson();
        String jsonMapRoutes = serializer.toJson(mapMetadata);
        Path mapImagePath = Paths.get(mapImageFileName);
        ByteArrayOutputStream mapImageStream = new ByteArrayOutputStream();
        ImageIO.write(this._image, "jpg", mapImageStream);
        byte[] mapImageBytes = mapImageStream.toByteArray();
        Files.write(mapImagePath, mapImageBytes, StandardOpenOption.TRUNCATE_EXISTING);
        byte[] mapRoutesBytes = jsonMapRoutes.getBytes();
        Files.write(mapImagePath, mapRoutesBytes, StandardOpenOption.APPEND);
        byte[] mapImageLenBytes = toByteArray(BigInteger.valueOf(mapImageBytes.length), 4);
        Files.write(mapImagePath, mapImageLenBytes, StandardOpenOption.APPEND);
    }
    // EndRegion: FileIO
    
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
    
    List<String> getOverlays() {
        return _overlays;
    }
    
    /**
     * Returns the full set of overlay names in this mapImage
     */
    public Set<String> getRoutes() {
        return _mapOverlays.keySet();
    }
    
    /**
     * Shows the selected overlays on the image
     */
    public void showRoutes(String... routes) {
        _overlays = Arrays.asList(routes);
    }
    
    public boolean collide(String... routes) {
        List<BufferedImage> overlays = new ArrayList<BufferedImage>();
        for(String route : routes) {
            overlays.add(_mapOverlays.get(route));
        }
        
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int aChannel = 0;
                for(BufferedImage overlay : overlays) {
                    int aC = overlay.getRGB(x, y) >>> 24;
                    if (aC != 0) {
                        if (aChannel != 0) {
                            return true;
                        } else {
                            aChannel = aC;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Rendering callback for this mapImage
     */
    @Override
    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g=image.getGraphics();
        g.drawImage(_image,0,0,null);
        for (String overlay : _overlays) {
            if (_mapOverlays.containsKey(overlay)) {
                g.drawImage(_mapOverlays.get(overlay),0,0,null);
            }
        }
        return image;
    }
}
