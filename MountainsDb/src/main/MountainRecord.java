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
                    String.format("Corrupted db record: [%d] parts instead of 6.",
                            lineParts.length));
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
                    String.format("Corrupted db record: '%s' elevation unit is unrecognized.",
                            _elevationUnit));
        }
    }
    
    public String getRawLine() {
    	return _rawLine;
    }
    
    public double getElevation(String elevationUnit) {
        if (!elevationUnit.equalsIgnoreCase("m") && !elevationUnit.equalsIgnoreCase("ft")) {
            throw new RuntimeException(
                    String.format("Invalid request: '%s' elevation unit is unrecognized.",
                            elevationUnit));
        }
        
        double factor = 1;
        if (!elevationUnit.equalsIgnoreCase(_elevationUnit)) {
        	factor = _elevationUnit.equalsIgnoreCase("m") 
        			? 3.2808399 // meters to feet 
        			: 0.3048 ;// feet to meters
        }
        
        return _elevation * factor;
    }
    
    @Override
    public String toString() {
    	return String.format("%s %s in %s [lat:%f, lon:%f], elevation %f ft",
    			_type, _name,
    			_country, _latitude, _longitude,
    			getElevation("ft"));
    }
}
