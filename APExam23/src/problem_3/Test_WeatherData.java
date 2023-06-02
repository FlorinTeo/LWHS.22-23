package problem_3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_WeatherData {

    @Test
    void test_cleanData() {
        double[] temperatures = {99.1, 142.0, 85.0, 85.1, 84.6, 94.3, 124.9, 98.0, 101.0, 102.5};
        WeatherData weatherData = new WeatherData(temperatures);
        weatherData.cleanData(85.0, 120.0);
        assertArrayEquals(
            new double[] {99.1, 85.0, 85.1, 94.3, 98.0, 101.0, 102.5},
            weatherData.getTemperatures());
    }
    
    @Test
    void test_longestHeatWave() {
        double[] temperatures = {100.5, 98.5, 102.0, 103.9, 87.5, 105.2, 90.3, 94.8, 109.1, 102.1, 107.4, 93.2};
        WeatherData weatherData = new WeatherData(temperatures);
        assertEquals(3, weatherData.longestHeatWave(100.5));
        assertEquals(4, weatherData.longestHeatWave(95.2));
    }

}
