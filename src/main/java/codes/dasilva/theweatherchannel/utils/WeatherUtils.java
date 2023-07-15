package codes.dasilva.theweatherchannel.utils;

public class WeatherUtils {

    private WeatherUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param temp Temperature in Celsius
     * @return Temperature temp converted to Fahrenheit
     */
    public static double celsiusToFahrenheit(double temp) {
        return temp * 1.8 + 32;
    }

}
