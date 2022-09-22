package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MountainsDb {

    private String _dbFileName;
    private long _nDbSize = -1;
    private long _nCleanSize = -1;
    private int _nDbLines = -1;
    private int _nCleanLines = -1;
    private int _nErrLines = -1;

    public MountainsDb(String dbFileName) {
        _dbFileName = dbFileName;
    }

    public void clean() throws IOException {
        Path dbPath = Paths.get(_dbFileName);
        BufferedReader dbReader = Files.newBufferedReader(dbPath, StandardCharsets.UTF_8);
        Path cleanPath = Paths.get(_dbFileName.replace("_db", "_clean"));
        BufferedWriter cleanWriter = Files.newBufferedWriter(cleanPath, StandardCharsets.UTF_8);
        Path errPath = Paths.get(_dbFileName.replace("_db", "_err"));
        BufferedWriter errWriter = Files.newBufferedWriter(errPath, StandardCharsets.UTF_8);

        _nDbSize = Files.size(dbPath);
        _nDbLines = 0;
        _nCleanLines = 0;
        _nErrLines = 0;
        while (dbReader.ready()) {
            String line = dbReader.readLine();
            _nDbLines++;
            if (_nDbLines == 1) {
                cleanWriter.write(line + "\n");
                errWriter.write(line + "\tException\n");
            } else
                try {
                    MountainRecord mtnRecord = new MountainRecord(line);
                    cleanWriter.write(line + "\n");
                    _nCleanLines++;
                } catch (Exception exc) {
                    String errMessage = exc.getClass().getTypeName() + ":" + exc.getMessage();
                    errWriter.write(line + "\t" + errMessage + "\n");
                    _nErrLines++;
                }
        }

        _nCleanSize = Files.size(cleanPath);
        errWriter.close();
        dbReader.close();
    }

    @Override
    public String toString() {
        String output = "";
        output = "DbSize:    " + _nDbSize + " bytes\n";
        output += "CleanSize: " + _nCleanSize + " bytes\n";
        output += "#DbLines:  " + _nDbLines + " lines\n";
        output += "#ErrLines: " + _nErrLines + " lines\n";
        output += "Shortest mountain: " + "\n";
        output += "Tallest mountain:  " + "\n";
        return output;
    }
}
