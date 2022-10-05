package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.MountainRec;

class MountainRec_tests {

    /**
     * Creates a few MountainRec objects verifying
     * the string returned by their toString() method
     * is as expected.
     */
    @Test
    void test_MountainRec() {
        MountainRec denali = new MountainRec("United States\tMountain\tDenali\t63.0695\t-151.0074\t6194 m");
        assertEquals(
            "[United States][Mountain][Denali][63.0695,-151.0074][6194 meters]",
            denali.toString());
    }
    
    /**
     * Creates a few MountainRec objects, some valid
     * some invalid. Verifies their isValid() method
     * returns expected true/false value.
     */
    @Test
    void test_isValid() {
        // valid mountain with no elevation unit (default unit is meters)
        MountainRec validMtn1 = new MountainRec("Croatia\tMountain\tDinara\t44.0624949\t16.3829451\t1831");
        assertTrue(validMtn1.isValid());

        // valid mountain with elevation in feet
        MountainRec validMtn2 = new MountainRec("United States\tMountain\tEl Capitan\t41.8691134189186\t-123.611812591553\t6814 ft");
        assertTrue(validMtn2.isValid());

        // invalid mountain, with ??? longitude and latitude
        MountainRec invalidMtn1 = new MountainRec("India\tMountain\tChanguch Mountain\t??????\t??????\t6861 m");
        assertFalse(invalidMtn1.isValid());
        assertEquals("java.lang.NumberFormatException:For input string: \"??????\"", invalidMtn1.getError());

        // invalid mountain with unrecognized ?? elevation unit
        MountainRec invalidMtn2 = new MountainRec("Australia\tMountain\tSeven Mile Hill\t-30.831350\t121.382103\t388 ??");
        assertFalse(invalidMtn2.isValid());
        assertEquals("java.lang.RuntimeException:Invalid elevation unit.", invalidMtn2.getError());

        // invalid mountain with elevation 0 meters
        MountainRec invalidMtn3 = new MountainRec("Colombia\tMountain\tCruce Rio Tucurinca y  Ferrovia\t10.6377213\t-74.1735918\t0 m");
        assertFalse(invalidMtn3.isValid());
        assertEquals("java.lang.RuntimeException:Invalid negative or zero elevation.", invalidMtn3.getError());
        
        // invalid mountain to few columns.
        MountainRec invalidMtn4 = new MountainRec("Czechia\tMountain\tBlaník\t49.370880 14.864400\t659 m");
        assertFalse(invalidMtn4.isValid());
        assertEquals("java.lang.RuntimeException:Invalid number of columns.", invalidMtn4.getError());
    }
    
    /**
     * Creates a few valid MountainRec objects, with
     * elevation expressed in various ways: "m", "ft",
     * no specified unit, integer value, double value.
     * Verifies their getElevationInM() method returns
     * expected value.
     */
    @Test
    void test_getElevationInM() {
        // valid mountain with elevation in meters
        MountainRec validMtn1 = new MountainRec("Croatia\tMountain\tDinara\t44.0624949\t16.3829451\t1831 m");
        assertEquals(1831, validMtn1.getElevationInM());

        // valid mountain with elevation in feet
        MountainRec validMtn2 = new MountainRec("United States\tMountain\tEl Capitan\t41.8691134189186\t-123.611812591553\t6814 ft");
        assertEquals(2076.9072, validMtn2.getElevationInM());
    }
    
    /**
     * Creates three valid MountainRec objects, two
     * of which have the exact same elevation.
     * Verifies that various comparisons between them
     * return expected values. 
     */
    @Test
    void test_commpareElevations() {
        // valid mountain with elevation in meters
        MountainRec shortMtn = new MountainRec("Croatia\tMountain\tDinara\t44.0624949\t16.3829451\t1831 m");
        MountainRec tallMtn = new MountainRec("United States\tMountain\tEl Capitan\t41.8691134189186\t-123.611812591553\t6814 ft");
        assertEquals(0, shortMtn.compareElevations(shortMtn));
        assertEquals(-1, shortMtn.compareElevations(tallMtn));
        assertEquals(1, tallMtn.compareElevations(shortMtn));
    }
}
