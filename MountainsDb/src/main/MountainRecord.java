package main;

import java.util.Scanner;

public class MountainRecord {

    private String _country;
    private String _type;
    private String _name;
    private double _latitude;
    private double _longitude;
    private double _elevation;
    private String _elevationUnit;
    
    public MountainRecord(String line) {
        String[] lineParts = line.split("\t");
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
}
