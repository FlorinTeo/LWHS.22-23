package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class handling database read/write operations.
 */
public class MountainsDb {
    /**
     * Class fields include file names/paths of the database file, clean file and
     * error file and all needed statistics fields.
     **/
    private Path _dbPath;
    private Path _cleanPath;
    private Path _errPath;
    private long _nValid;
    private long _nCorrupted;
    private MountainRec _shortestMtn, _tallestMtn;

    /**
     * A MountainsDb object needs only the database file name to determine where to
     * read lines from and where to write the clean and corrupted lines. i.e. dbFile
     * = "mountains_db.tsv" implies cleanFile = "mountains_clean.tsv" and errFile =
     * "mountains_err.tsv".
     */
    public MountainsDb(String dbFileName) {
        // initializes class fields from the given dbFileName.
        _dbPath = Paths.get(dbFileName);
        _cleanPath = Paths.get(dbFileName.replace("_db", "_clean"));
        _errPath = Paths.get(dbFileName.replace("_db", "_err"));
        _nValid = _nCorrupted = -1;
        _shortestMtn = _tallestMtn = null;
    }

    /**
     * Reads the database line by line, separating the clean lines from the
     * corrupted lines into their respective files. It collects all needed
     * statistics as the database is scanned.
     * 
     * @throws IOException
     */
    public void clean() throws IOException {
        // create the needed BufferedReader BufferedWriters
        BufferedReader dbReader = Files.newBufferedReader(_dbPath, StandardCharsets.UTF_8);
        BufferedWriter cleanWriter = Files.newBufferedWriter(_cleanPath, StandardCharsets.UTF_8);
        BufferedWriter errWriter = Files.newBufferedWriter(_errPath, StandardCharsets.UTF_8);

        // initializes statistics variables.
        _nValid = 0;
        _nCorrupted = 0;
        _shortestMtn = null;
        _tallestMtn = null;

        // for each line in the BufferedReader(dbFile) do
        boolean isHeader = true;
        while(dbReader.ready()) {

            String line = dbReader.readLine();
            // special case: header line is transferred to both clean and err writers.
            if (isHeader) {
                cleanWriter.write(line + "\n");
                errWriter.write(line + "\tError\n");
                isHeader = false;
            } else {
                // create a MountainRec for that line
                MountainRec crtMtn = new MountainRec(line);
                // if MountainRec is valid then
                if (crtMtn.isValid()) {
                    // write the line to the cleanFile
                    cleanWriter.write(line + "\n");
                    // update (clean) statistics
                    _nValid++;
                    if (_shortestMtn == null || _shortestMtn.compareElevations(crtMtn) > 0) {
                        _shortestMtn = crtMtn;
                    }
                    
                    if (_tallestMtn == null || _tallestMtn.compareElevations(crtMtn) < 0) {
                        _tallestMtn = crtMtn;
                    }
                } else {
                    // write the line to the errFile
                    errWriter.write(line + "\t" + crtMtn.getError() + "\n");
                    // update (err) statistics
                    _nCorrupted++;
                }
            }
            
        }

        // close all BufferedReader/Writers.
        dbReader.close();
        cleanWriter.close();
        errWriter.close();
    }

    /**
     * Returns a string representation of the database. This includes all the
     * required statistics.
     */
    @Override
    public String toString() {
        // Compose all statistics fields into one output string
        String output = "";
        
        // Size of the original and of the cleaned databases can be obtained as below:
        // dbPath.toFile().length();
        // cleanPath.toFile().length();
        output += _dbPath.getFileName() + ": " + _dbPath.toFile().length() + " bytes\n";
        output += _cleanPath.getFileName() + ": " + _cleanPath.toFile().length() + " bytes\n";
        output += "Count of valid records: " + _nValid + "\n";
        output += "Count of corrupted records: " + _nCorrupted + "\n";
        output += "Tallest mountain: " + _tallestMtn.toString() + "\n";
        output += "Shortest mountain: " + _shortestMtn.toString() + "\n";
        return output;
    }
}
