package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    public void merge() throws IOException {
        BufferedReader reader1 = Files.newBufferedReader(Paths.get(_dbPath + "_1"), StandardCharsets.UTF_8);
        BufferedReader reader2 = Files.newBufferedReader(Paths.get(_dbPath + "_2"), StandardCharsets.UTF_8);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(_dbPath + "_merged"), StandardCharsets.UTF_8);

        String header = reader1.readLine();
        reader2.readLine();
        writer.write(header + "\n");

        MountainRecord rec1 = null;
        MountainRecord rec2 = null;
        while (rec1 == null || rec2 == null) {
            if (rec1 == null) {
                rec1 = new MountainRecord(reader1.readLine());
            }
            if (rec2 == null) {
                rec2 = new MountainRecord(reader2.readLine());
            }

            if (rec1.getElevation("m") < rec2.getElevation("m")) {
                writer.write(rec1.getRawLine() + "\n");
                if (reader1.ready()) {
                    rec1 = null;
                }
            } else {
                writer.write(rec2.getRawLine() + "\n");
                if (reader2.ready()) {
                    rec2 = null;
                }
            }
        }

        while (reader1.ready()) {
            writer.write(reader1.readLine() + "\n");
        }

        while (reader2.ready()) {
            writer.write(reader2.readLine() + "\n");
        }

        writer.close();
        reader1.close();
        reader2.close();
        Files.delete(Paths.get(_dbPath + "_1"));
        Files.delete(Paths.get(_dbPath + "_2"));
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
