package problem_1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests_AppointmentBook {

    @Test
    void test_findFreeBlock() {
        AppointmentBook appBook = new AppointmentBook();
        appBook.reserveMinutes(2, 0, 10);
        appBook.reserveMinutes(2, 15, 15);
        appBook.reserveMinutes(2, 45, 5);
        
        assertEquals(30, appBook.findFreeBlock(2, 15));
    }
    
    @Test
    void test_makeAppointment() {
        AppointmentBook appBook = new AppointmentBook();
        appBook.reserveMinutes(2, 0, 25);
        appBook.reserveMinutes(2, 30, 30);
        appBook.reserveMinutes(3, 15, 26);
        appBook.reserveMinutes(4, 0, 5);
        appBook.reserveMinutes(4, 30, 14);
        
        assertTrue(appBook.makeAppointment(2, 4, 22));
        assertTrue(appBook.makeAppointment(3, 4, 3));
        assertFalse(appBook.makeAppointment(2, 4, 30));
        
        assertFalse(appBook.checkAvailable(2, 0, 25));
        assertTrue(appBook.checkAvailable(2, 25, 5));
        assertFalse(appBook.checkAvailable(2, 30, 30));
        assertFalse(appBook.checkAvailable(3, 0, 3));
        assertTrue(appBook.checkAvailable(3, 3, 12));
        assertFalse(appBook.checkAvailable(3, 15, 26));
        assertTrue(appBook.checkAvailable(3, 41, 19));
        assertFalse(appBook.checkAvailable(4, 0, 27));
        assertTrue(appBook.checkAvailable(4, 27, 3));
        assertFalse(appBook.checkAvailable(4, 30, 14));
        assertTrue(appBook.checkAvailable(4, 44, 16));
    }
}
