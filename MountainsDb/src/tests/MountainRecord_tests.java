package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.MountainRecord;

class MountainRecord_tests {

    @Test
    void test_MountainRecord() {
        MountainRecord mtRainier = new MountainRecord(
                "United States	Volcano	Mount Rainier	46.8528543410397	-121.760530471802	4392 m");
        assertEquals(
                "Volcano Mount Rainier in United States [lat:46.852854, lon:-121.760530], elevation 14409.448841 ft",
                mtRainier.toString());
    }

    @Test
    void test_getElevation() {
        MountainRecord mtBaker = new MountainRecord(
                "United States	Volcano	Mount Baker	48.7767249675674	-121.814603805542	3286 m");
        assertEquals(3286.0, mtBaker.getElevation("m"));
        assertEquals(10780.8399114, mtBaker.getElevation("ft"));
        try {
            mtBaker.getElevation("abc");
            fail("Invalid elevation unit didn't trigger exception");
        } catch (RuntimeException re) {
            assertEquals("Invalid request: 'abc' elevation unit is unrecognized.", re.getMessage());
        }
    }
}
