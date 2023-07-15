package codes.dasilva.theweatherchannel.utils;

public class SensorDataUtils {

    private SensorDataUtils() {
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
