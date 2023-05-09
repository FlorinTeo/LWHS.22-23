package problem_3;
import java.util.ArrayList;

public class WeatherData {

    private ArrayList<Double> temperatures;
    
    public void cleanData(double lower, double upper) {
        for(int i = 0; i < temperatures.size(); i++) {
            double temp = temperatures.get(i);
            if (temp < lower || temp > upper) {
                temperatures.remove(i);
                i--;
            }
        }
    }
    
    public int longestHeatWave(double threshold) {
        int start = 0;
        int maxLength = 0;
        for (int i = 0; i < temperatures.size(); i++) {
            if (temperatures.get(i) > threshold) {
                maxLength++;
            } else {
                if (i - start > maxLength) {
                    maxLength = i - start;
                }
                start = i;
            }
        }
        
        return maxLength;
    }
}
