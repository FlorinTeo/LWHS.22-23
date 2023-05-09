package problem_1;
public class AppointmentBook {
    /**
     * Returns true if {minute} in period is available for an appointment and returns
     * false otherwise
     * Preconditions: 1 <= period <= 8; 0 <= minute <= 59
     */
    private boolean isMinuteFree(int period, int minute) {
        /* implementation below is not shown in exam text */
        return (_scheduledMinutes[period-1][minute] == false);
    }
    
    /**
     * Marks the block of minutes that starts at {startMinute} in {period} and
     * is {duration} minutes long as reserved for an appointment
     * Preconditions: 1 <= period <= 8; 0 <= startMinute <= 59;
     *     1 <= duration <= 60
     */
    private boolean reserveBlock(int period, int startMinute, int duration) {
        /* implementation below is not shown in exam text */
        for (int m = startMinute; m < startMinute + duration; m++) {
            _scheduledMinutes[period-1][m] = true;
        }
        return false;
    }
    
    /**
     * Searches for the first block of {duration} free minutes during {period}, as described in
     * part (a). Returns the first minute in the block if such a block is found or returns -1 if no
     * such block is found.
     * Preconditions: 1 <= period <= 8; 1 <= duration <= 60
     */
    public int findFreeBlock(int period, int duration) {
        /* implementation of part (a) */
        int d = duration;
        int m = 0;
        while(m < 60 && d > 0) {
            if (isMinuteFree(period, m)) {
                d--;
            } else {
                d = duration;
            }
            m++;
        }
        
        if (d == 0) {
            return m - duration;
        } else {
            return -1;
        }
    }
    
    /**
     * Searches periods from {startPeriod} to {endPeriod}, inclusive, for a block
     * of {duration} free minutes, as described in part (b). If such a block is found,
     * calls {reserveBlock} to reserve the block of minutes and returns true; otherwise
     * returns false.
     * Preconditions: 1 <= startPeriod <= endPeriod <= 8; 1 <= duration <= 60
     */
    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
        /* implementation of part (b) */
        for (int p = startPeriod; p <= endPeriod; p++) {
            int fb = findFreeBlock(p, duration);
            if (fb != -1) {
                reserveBlock(p, fb, duration);
                return true;
            }
        }
        return false;
    }
    
    // Instance variables, constructors and methods below are not shown in the exam text.

    // Double array holding the 60 minutes of each of the 8 periods.
    // true means scheduled, false means free. When constructed, all minutes are free.
    private boolean[][] _scheduledMinutes = new boolean[8][60];
    
    public void reserveMinutes(int period, int startMinute, int duration) {
        for (int m = startMinute; m < startMinute + duration; m++) {
            _scheduledMinutes[period-1][m] = true;
        }
    }
    
    public boolean checkAvailable(int period, int startMinute, int duration) {
        for (int m = startMinute; m < startMinute + duration; m++) {
            if (_scheduledMinutes[period-1][m]) {
                return false;
            }
        }
        return true;
    }
}
