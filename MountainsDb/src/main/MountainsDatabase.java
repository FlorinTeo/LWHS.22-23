package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class MountainsDatabase {
    private Path _dbPath;
    private boolean _scanned;
    private Path _absolutePath;
    private long _size;
    private long _nLines;
    
    public boolean _debug = false;

    private static MountainRecord nextRecord(BufferedReader reader) throws IOException {
        return reader.ready()
                ? new MountainRecord(reader.readLine())
                : null;
    }
    
    public MountainsDatabase(String dbFilePath) {
        _dbPath = Paths.get(dbFilePath);
        _absolutePath = null;
        _scanned = false;
        _size = -1;
        _nLines = -1;
    }

    public void scan() throws IOException {
        if (!_scanned) {
            System.out.print("Scanning ... ");
            BufferedReader reader = Files.newBufferedReader(_dbPath, StandardCharsets.UTF_8);
            _absolutePath = _dbPath.toAbsolutePath();
            _size = Files.size(_dbPath);
            _nLines = 0;
            while (reader.ready()) {
                String line = reader.readLine();
                _nLines++;

                // skip the header line
                if (_nLines == 1) {
                    continue;
                }

                try {
                    new MountainRecord(line);
                } catch (RuntimeException re) {
                    throw new RuntimeException(String.format("[%,d] %s", _nLines, re.getMessage()));
                }
            }

            _scanned = true;
            System.out.println("DONE");
        }
    }

    public void fuzzy(String dbFuzzyPath) throws IOException {
        BufferedReader inClean = Files.newBufferedReader(_dbPath, StandardCharsets.UTF_8);
        BufferedWriter outFuzzy = Files.newBufferedWriter(Paths.get(dbFuzzyPath), StandardCharsets.UTF_8);
        
        Random r = new Random();
        int iRec = 0;
        int nFuzzy = 0;
        while(inClean.ready()) {
            String line = inClean.readLine();
            iRec++;
            if (iRec == 1) {
                outFuzzy.write(line + "\n");
            } else try {
                MountainRecord m = new MountainRecord(line);
                int rnd = r.nextInt(35389);
                if (rnd < 200) {
                    nFuzzy++;
                    outFuzzy.write(m.getFuzzyLine(rnd % 12) +"\n");
                    System.out.printf("\n[%d]>%d", iRec, rnd % 12);
                } else {
                    outFuzzy.write(m.getRawLine() +"\n");
                    System.out.print(".");
                    if (iRec % 100 == 0) {
                        System.out.println();
                    }
                }
            } catch(Exception e) {
                System.out.printf("[%d] %s\n", iRec, e.getMessage());
            }
        }
        
        System.out.printf("\nFuzzied %d records\n", nFuzzy);
        
        outFuzzy.close();
        inClean.close();
    }
    
    public void split() throws IOException {
        scan();
        BufferedReader reader = Files.newBufferedReader(_dbPath, StandardCharsets.UTF_8);
        BufferedWriter writer1 = Files.newBufferedWriter(Paths.get(_dbPath + "_1"), StandardCharsets.UTF_8);
        BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(_dbPath + "_2"), StandardCharsets.UTF_8);
        int nLine = 0;
        while (reader.ready()) {
            String line = reader.readLine();

            // write the header line and all subsequent even lines
            if (nLine % 2 == 0) {
                writer1.write(line + "\n");
            }

            // write the header line and all subsequent odd lines
            if (nLine == 0 || nLine % 2 == 1) {
                writer2.write(line + "\n");
            }
            nLine++;
        }

        writer2.close();
        writer1.close();
    }

    public boolean merge() throws IOException {
        boolean isSorted = true;
        BufferedReader reader1 = Files.newBufferedReader(Paths.get(_dbPath + "_1"), StandardCharsets.UTF_8);
        BufferedReader reader2 = Files.newBufferedReader(Paths.get(_dbPath + "_2"), StandardCharsets.UTF_8);
        BufferedWriter writer = Files.newBufferedWriter(_dbPath, StandardCharsets.UTF_8);

        String header = reader1.readLine();
        reader2.readLine();
        writer.write(header + "\n");

        MountainRecord rec1 = nextRecord(reader1);
        MountainRecord rec2 = nextRecord(reader2);
        double lastElevation = -1;
        while (rec1 != null || rec2 != null) {
            MountainRecord rec = null;
            if (rec1 != null && rec2 != null) {
                if (rec1.getElevation("ft") < rec2.getElevation("ft")) {
                    rec = rec1;
                    rec1 = nextRecord(reader1);
                } else {
                    rec = rec2;
                    rec2 = nextRecord(reader2);
                }
            } else if (rec1 != null) {
                rec = rec1;
                rec1 = nextRecord(reader1);
            } else if (rec2 != null) {
                rec = rec2;
                rec2 = nextRecord(reader2);
            }
            
            writer.write(rec.getRawLine() + "\n");
            if (_debug && lastElevation > rec.getElevation("ft")) {
                System.out.println(rec);
            }
            isSorted = isSorted && (lastElevation <= rec.getElevation("ft"));
            lastElevation = rec.getElevation("ft");
        }
        
        writer.close();
        reader1.close();
        reader2.close();
        Files.delete(Paths.get(_dbPath + "_1"));
        Files.delete(Paths.get(_dbPath + "_2"));

        return isSorted;
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
