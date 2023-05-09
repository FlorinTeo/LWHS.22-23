public class AppointmentBook {

    private boolean isMinuteFree(int period, int minute) {
        return false;
    }
    
    private boolean reserveBlock(int period, int startMinute, int duration) {
        return false;
    }
    
    public int findFreeBlock(int period, int duration) {
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
    
    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
        for (int p = startPeriod; p <= endPeriod; p++) {
            int fb = findFreeBlock(p, duration);
            if (fb != -1) {
                reserveBlock(p, fb, duration);
                return true;
            }
        }
        return false;
    }
}
