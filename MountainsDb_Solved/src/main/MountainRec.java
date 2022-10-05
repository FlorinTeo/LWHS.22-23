package main;

import java.util.Scanner;

/**
 * Class containing all information about one mountain peak. The information is
 * extracted from database string, given in the class constructor.
 */
public class MountainRec {

    /**
     * Class fields include all relevant attributes of a mountain such as Country,
     * Type, Name, lat/long, elevation, elevation unit, as well as any other data
     * points, such as if this is a valid record or not.
     **/
    private String _country;
    private String _type;
    private String _name;
    private double _latitude, _longitude;
    private double _elevation;
    private String _elevUnit;
    
    private String _error;

    /**
     * A MountainRec object is given one database line and parses it into the
     * corresponding mountain attributes. If successful, it marks the object as
     * valid. In case of any error/exception, mark the object as invalid.
     */
    public MountainRec(String dbRecord) {
        try {
            // split the dbRecord around the '\t' character, into a String[]
            String[] parts = dbRecord.split("\t");
            if (parts.length != 6) {
                throw new RuntimeException("Invalid number of columns.");
            }
            
            // fill in class attributes from the corresponding part of the record
            _country = parts[0];
            _type = parts[1];
            _name = parts[2];
            _latitude = Double.parseDouble(parts[3]);
            _longitude = Double.parseDouble(parts[4]);
            
            Scanner elevParser = new Scanner(parts[5]);
            _elevation = elevParser.nextDouble();            
            _elevUnit = elevParser.hasNext() ? elevParser.next() : "m";
            elevParser.close();
            
            if (!_elevUnit.equalsIgnoreCase("m") && !_elevUnit.equalsIgnoreCase("ft")) {
                throw new RuntimeException("Invalid elevation unit.");
            }
            if (_elevation <= 0) {
                throw new RuntimeException("Invalid negative or zero elevation.");
            }

            // set _error to null indicating object is valid
            _error = null;
        } catch (Exception e) {
            // save the exception text, indicating the object is invalid
            _error = e.getClass().getTypeName() + ":" + e.getMessage();
        }
    }

    /**
     * Returns true if the object is valid, false otherwise
     */
    public boolean isValid() {
        return (_error == null);
    }
    
    /**
     * Returns the error message, as/if encounted when creating this object.
     */
    public String getError() {
        return _error;
    }

    /**
     * Uses the elevation & elevation unit class attributes of this mountain to
     * calculate and return its accurate elevation, in meters.
     */
    public double getElevationInM() {
        // check _elevUnit. In case of ft, convert _elevation to meters,
        // otherwise return it as is.
        if (_elevUnit.equalsIgnoreCase("ft")) {
            return _elevation * 0.3048;
        } else {
            return _elevation;
        }
    }

    /**
     * Compares the elevation of this mountain with the elevation of the
     * otherMountain provided as parameter. Returns -1 if this mountain is shorter
     * than otherMountain, 1 if it is greater and 0 in all other cases.
     */
    public int compareElevations(MountainRec otherMountain) {
        // Use getElevationInM() method for comparing both this and otherMountain.
        double elevDiff = getElevationInM() - otherMountain.getElevationInM();
        return (int)Math.signum(elevDiff);
    }

    /**
     * Returns a string representation of this mountain record. This includes all
     * the relevant mountain attributes.
     */
    @Override
    public String toString() {
        String output = "[" + _country + "]";
        output += "[" + _type + "]";
        output += "[" + _name + "]";
        output += "[" + _latitude + "," + _longitude +"]";
        output += "[" + (int)getElevationInM() + " meters]";
        return output;
    }
}
