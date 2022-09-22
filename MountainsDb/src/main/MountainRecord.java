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

    public MountainRecord(String line) {
        _rawLine = line;
        String[] lineParts = _rawLine.split("\t");
        if (lineParts.length != 6) {
            throw new RuntimeException(
                    String.format("Corrupted db record: [%d] parts instead of 6.", lineParts.length));
        }

        _country = lineParts[0];
        _type = lineParts[1];
        _name = lineParts[2];
        _latitude = Double.parseDouble(lineParts[3]);
        _longitude = Double.parseDouble(lineParts[4]);
        Scanner elevParser = new Scanner(lineParts[5]);
        _elevation = elevParser.nextDouble();
        _elevationUnit = elevParser.hasNext() ? elevParser.next().trim() : "m";
        elevParser.close();
        if (!_elevationUnit.equalsIgnoreCase("m") && !_elevationUnit.equalsIgnoreCase("ft")) {
            throw new RuntimeException(
                    String.format("Corrupted db record: '%s' elevation unit is unrecognized.", _elevationUnit));
        }
    }

    public String getRawLine() {
        return _rawLine;
    }
    
    public String getFuzzyLine(int code) {
        switch(code) {
        case 0: // country<spaces>type...
            return String.format("%s    %s\t%s\t%f\t%f\t%d %s",
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation,
                    _elevationUnit);
        case 1: // ..type<spaces>name...
            return String.format("%s\t%s    %s\t%f\t%f\t%d %s",
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation,
                    _elevationUnit);
        case 2: // ..name<spaces>lat...
            return String.format("%s\t%s\t%s    %f\t%f\t%d %s",
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation,
                    _elevationUnit);
        case 3: // ..lat<space>long..
            return String.format("%s\t%s\t%s\t%f %f\t%d %s",
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation,
                    _elevationUnit);
        case 4: // ..long<space>elevation
            return String.format("%s\t%s\t%s\t%f\t%f %d %s",
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation,
                    _elevationUnit);
        case 5: // ..elevation<extra tab>
            return String.format("%s\t%s\t%s\t%f\t%f\t%d\tm",
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation);
        case 6: // unsupported elevationUnit
            return String.format("%s\t%s\t%s\t%f\t%f\t%d ??",
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation);
        case 7: // corrupted lat & long
            return String.format("%s\t%s\t%s\t??????\t??????\t%d %s",
                    _country, _type, _name,
                    (int)_elevation,
                    _elevationUnit);
        case 8: // corrupted elevation
            return String.format("%s\t%s\t%s\t%f\t%f\t???? %s",
                    _country, _type, _name,
                    _latitude, _longitude,
                    _elevationUnit);
        case 9: // glued elevation & elevationUnit
            return String.format("%s\t%s\t%s\t%f\t%f\t%d%s", 
                    _country, _type, _name,
                    _latitude, _longitude,
                    (int)_elevation,
                    _elevationUnit);
        case 10: // invalid lat
            return String.format("%s\t%s\t%s\t%d\t%f\t%d %s", 
                    _country, _type, _name,
                    (int)(_latitude*1000), _longitude,
                    (int)_elevation,
                    _elevationUnit);
        case 11: // invalid long
            return String.format("%s\t%s\t%s\t%f\t%d\t%d %s", 
                    _country, _type, _name,
                    _latitude, (int)(_longitude*1000),
                    (int)_elevation,
                    _elevationUnit);
        default:
            return _rawLine;
        }
    }

    public double getElevation(String elevationUnit) {
        if (!elevationUnit.equalsIgnoreCase("m") && !elevationUnit.equalsIgnoreCase("ft")) {
            throw new RuntimeException(
                    String.format("Invalid request: '%s' elevation unit is unrecognized.", elevationUnit));
        }

        double factor = 1;
        if (!elevationUnit.equalsIgnoreCase(_elevationUnit)) {
            factor = _elevationUnit.equalsIgnoreCase("m") ? 3.2808399 // meters to feet
                    : 0.3048;// feet to meters
        }

        return _elevation * factor;
    }

    @Override
    public String toString() {
        return String.format("%s %s in %s [lat:%f, lon:%f], elevation %f ft", _type, _name, _country, _latitude,
                _longitude, getElevation("ft"));
    }
}
