package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MountainsDb {
    private Path _dbPath;
    private boolean _scanned;
    private Path _absolutePath;
    private long _size;
    private long _nLines;
    
    public MountainsDb(String dbFilePath) {
        _dbPath = Paths.get(dbFilePath);
        _absolutePath = null;
        _scanned = false;
        _size = -1;
        _nLines = -1;
    }
    
    public void scan() throws IOException {
        if (!_scanned) {
            System.out.print("Scanning ... ");
            BufferedReader reader = Files.newBufferedReader(_dbPath);
            _absolutePath = _dbPath.toAbsolutePath();
            _size = Files.size(_dbPath);
            _nLines = 0;
            while(reader.ready()) {
                String line = reader.readLine();
                _nLines++;
                
                // skip the header line
                if (_nLines == 1) {
                    continue;
                }
                
                try {
                    MountainRecord record = new MountainRecord(line);
                } catch (RuntimeException re) {
                    throw new RuntimeException(
                        String.format("[%,d] %s", _nLines, re.getMessage()));
                }
            }
            
            _scanned = true;
            System.out.println("DONE");
        }
    }
    
    @Override
    public String toString() {
        String output = String.format("Database '%s':\n", _dbPath);
        
        try {
            scan();
            output += String.format("  Path: '%s'\n", _absolutePath);
            output += String.format("  Size: %,d bytes\n", _size);
            output += String.format("  Number of lines: %,d\n", _nLines);
        } catch (Exception e) {
            output += String.format("  ##ERROR##: %s\n", e);
        }
        
        return output;
    }

}
