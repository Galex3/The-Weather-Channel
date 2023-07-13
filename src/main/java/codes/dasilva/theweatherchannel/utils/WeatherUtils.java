package codes.dasilva.theweatherchannel.utils;

public class WeatherUtils {

    private WeatherUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * @param temp Temperature in Celsius
     * @return Temperature temp converted to Fahrenheit
     */
    public static float celsiusToFahrenheit(float temp) {
        return temp * 1.8f + 32f;
    }

}
