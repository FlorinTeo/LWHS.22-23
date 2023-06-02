package problem_3;
import java.util.ArrayList;

public class WeatherData {

    /** Guaranteed not to be null and to contain only non-null entries */
    private ArrayList<Double> temperatures;
    
    /**
     * Cleans the data by removing from temperatures all values that are less than
     * lower and all values that are greater than upper, as described in part (a)
     */
    public void cleanData(double lower, double upper) {
        /* implemented for part (a) */
        for(int i = 0; i < temperatures.size(); i++) {
            double temp = temperatures.get(i);
            if (temp < lower || temp > upper) {
                temperatures.remove(i);
                i--;
            }
        }
    }
    
    /**
     * Returns the length of the longest heat wave found in the temperatures, as described in
     * part (b)
     * Precondition: There is at least one heat wave in temperatures based on threshold.
     */
    public int longestHeatWave(double threshold) {
        /* implemented for part (b) */
        int start = 0;
        int maxLength = 0;
        for (int i = 1; i < temperatures.size(); i++) {
            if (temperatures.get(i-1) <= threshold && temperatures.get(i) > threshold) {
                // beginning of a new heat wave
                start = i;
            } else if (temperatures.get(i-1) > threshold && temperatures.get(i) <= threshold) {
                // ending of the current heat wave
                maxLength = Math.max(maxLength, i - start);
            }
        }
        
        return maxLength;
    }
    
    // Instance variables, constructors and methods below are not shown in the exam text.
    
    public WeatherData(double[] tempArr) {
        temperatures = new ArrayList<Double>();
        for(double temp : tempArr) {
            temperatures.add(temp);
        }
    }
    
    public double[] getTemperatures() {
        double[] tempArr = new double[temperatures.size()];
        for(int i = 0; i < temperatures.size(); i++) {
            tempArr[i] = temperatures.get(i);
        }
        return tempArr;
    }
}
