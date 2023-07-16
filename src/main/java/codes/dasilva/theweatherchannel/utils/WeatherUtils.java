package codes.dasilva.theweatherchannel.utils;

import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;

import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public class WeatherUtils {

    private WeatherUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param temp temperature in Celsius (ºC)
     * @return temperature converted to Fahrenheit (ºF)
     */
    public static double temperatureConverter(double temp) {
        return temp * 1.8 + 32;
    }

    public static Double applyStatistic(Statistic statistic, Stream<WeatherEntity> weatherStream, ToDoubleFunction<WeatherEntity> function) {
        return switch (statistic) {
            case SUM -> weatherStream.mapToDouble(function).sum();
            case MIN -> weatherStream.mapToDouble(function).min().getAsDouble();
            case MAX -> weatherStream.mapToDouble(function).max().getAsDouble();
            default -> weatherStream.mapToDouble(function).average().getAsDouble();
        };
    }

    /**
     * @param speed windSpeed in kph
     * @return windSpeed converted to mph
     */
    public static double windSpeedConverter(double speed) {
        return speed / 1.609344;
    }

}
