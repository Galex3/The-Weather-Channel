package codes.dasilva.theweatherchannel.utils;

import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;

import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

/**
 * Class used for weather and sensor data manipulation.
 * @author Gustavo Silva
 * @since 1.0.0
 */
public class WeatherUtils {

    private WeatherUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converts temperature in ºC to ºF
     * @param temp temperature in Celsius (ºC)
     * @return temperature converted to Fahrenheit (ºF)
     * @author Gustavo Silva
     * @since 1.0.0
     */
    public static double temperatureConverter(double temp) {
        return temp * 1.8 + 32;
    }

    /**
     * Applies the provided Statistic to the provided Stream of data.
     * @param statistic the Statistic to be applied
     * @param weatherStream the stream of data the Statistic will be applied to
     * @param function the WeatherEntity getter method for the Metric we want to apply the Statistic to
     * @return the value after the statistic was applied to the data
     * @see Statistic
     * @see codes.dasilva.theweatherchannel.constant.Metric
     * @see WeatherEntity
     * @see ToDoubleFunction
     * @see Stream#mapToDouble(ToDoubleFunction)
     * @author Gustavo Silva
     * @since 1.0.0
     */
    public static Double applyStatistic(Statistic statistic, Stream<WeatherEntity> weatherStream, ToDoubleFunction<WeatherEntity> function) {
        return switch (statistic) {
            case SUM -> weatherStream.mapToDouble(function).sum();
            case MIN -> weatherStream.mapToDouble(function).min().getAsDouble();
            case MAX -> weatherStream.mapToDouble(function).max().getAsDouble();
            default -> weatherStream.mapToDouble(function).average().getAsDouble();
        };
    }

    /**
     * Converts windSpeed in kph to mph
     * @param speed windSpeed in kph
     * @return windSpeed converted to mph
     * @author Gustavo Silva
     * @since 1.0.0
     */
    public static double windSpeedConverter(double speed) {
        return speed / 1.609344;
    }

}
