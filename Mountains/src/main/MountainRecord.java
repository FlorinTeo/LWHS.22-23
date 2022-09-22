package main;

import java.util.Scanner;

public class MountainRecord {

    private String _rawLine;
    private String _country;
    private String _type;
    private String _name;
    private double _latitude;
    private double _longitude;
    private double _elevation;
    private String _elevationUnit;

    public MountainRecord(String rawLine) {
        _rawLine = rawLine;
        String[] lineParts = rawLine.split("\t");
        if (lineParts.length != 6) {
            throw new RuntimeException("Invalid number of columns");
        }
        _country = lineParts[0];
        _type = lineParts[1];
        _name = lineParts[2];
        _latitude = Double.parseDouble(lineParts[3]);
        if (_latitude < -90 || _latitude > 90) {
            throw new RuntimeException("Invalid latitude value");
        }
        _longitude = Double.parseDouble(lineParts[4]);
        if (_longitude < -180 || _longitude > 180) {
            throw new RuntimeException("Invalid longitude value");
        }
        Scanner elevationParser = new Scanner(lineParts[5]);
        _elevation = elevationParser.nextDouble();
        _elevationUnit = "m";
        if (elevationParser.hasNext()) {
            _elevationUnit = elevationParser.next();
        }
        elevationParser.close();
        if (!_elevationUnit.equalsIgnoreCase("m") && !_elevationUnit.equalsIgnoreCase("ft")) {
            throw new RuntimeException("Invalid elevation unit");
        }
    }

    public String getLine() {
        return _rawLine;
    }

    public double getElevationInM() {
        if (_elevationUnit.equalsIgnoreCase("ft")) {
            return _elevation * 3.2808399;
        } else {
            return _elevation;
        }
    }

    @Override
    public String toString() {
        return _rawLine;
    }
}
