package codes.dasilva.theweatherchannel.utils;

import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static codes.dasilva.theweatherchannel.utils.WeatherUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherUtilsTest {

    private final List<WeatherEntity> weatherList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        weatherList.add(WeatherEntity.builder()
                .weatherUuid(UUID.randomUUID().toString())
                .sensor("sensor1")
                .timestamp(Timestamp.valueOf("2023-07-13 20:41:17"))
                .valid(true)
                .temperature(50)
                .windSpeed(30)
                .humidity((byte) 20)
                .build()
        );
        WeatherEntity weather = new WeatherEntity();
        BeanUtils.copyProperties(weatherList.get(0), weather);
        weatherList.add(weather);
        weatherList.get(1).setTemperature(60);
        weatherList.get(1).setWindSpeed(50);
        weatherList.get(1).setHumidity((byte) 40);
    }

    @Test
    @DisplayName("Should convert Celsius to Fahrenheit")
    void testTemperatureConverter() {
        double temp = temperatureConverter(0);
        assertEquals(32, temp);
    }

    @Test
    @DisplayName("Should get the average temperature between the provided weathers")
    void testApplyStatisticDefault() {
        double def = applyStatistic(Statistic.AVG, weatherList.stream(), WeatherEntity::getTemperature);
        assertEquals(55, def);
    }

    @Test
    @DisplayName("Should get the max humidity between the provided weathers")
    void testApplyStatisticMax() {
        double def = applyStatistic(Statistic.MAX, weatherList.stream(), WeatherEntity::getHumidity);
        assertEquals(40, def);
    }

    @Test
    @DisplayName("Should get the min wind speed between the provided weathers")
    void testApplyStatisticMin() {
        double def = applyStatistic(Statistic.MIN, weatherList.stream(), WeatherEntity::getWindSpeed);
        assertEquals(30, def);
    }

    @Test
    @DisplayName("Should get the sum of all temperatures between the provided weathers")
    void testApplyStatisticSum() {
        double def = applyStatistic(Statistic.SUM, weatherList.stream(), WeatherEntity::getTemperature);
        assertEquals(110, def);
    }

    @Test
    @DisplayName("Should convert kph to mph")
    void testWindSpeedConverter() {
        double windSpeed = windSpeedConverter(1.609344);
        assertEquals(1, windSpeed);
    }
}