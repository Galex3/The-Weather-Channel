package codes.dasilva.theweatherchannel.service.impl;

import codes.dasilva.theweatherchannel.constant.Metric;
import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.model.SensorDataModel;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import codes.dasilva.theweatherchannel.persistence.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // https://stackoverflow.com/a/68701517
class SensorDataServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private SensorDataServiceImpl sensorDataServiceImpl;

    private final List<WeatherEntity> weatherList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        sensorDataServiceImpl = new SensorDataServiceImpl(weatherRepository);
        weatherList.clear();
        weatherList.add(WeatherEntity.builder()
                .weatherUuid(UUID.randomUUID().toString())
                .sensor("sensor1").humidity((byte) 12)
                .temperature(23.4f).windSpeed(32.3f)
                .timestamp(Timestamp.valueOf("2023-07-14 20:41:17"))
                .valid(true).build()
        );
    }

    @Test
    @DisplayName("Should get sensor data and temperature metric")
    void testGetSensorData() {
        Date startDate = Timestamp.valueOf("2023-07-13 00:00:00");
        Date endDate = Timestamp.valueOf("2023-07-31 00:00:00");
        Set<String> sensors = new HashSet<>(List.of("sensor1", "Sensor1"));
        when(weatherRepository.findAllThatMatchCriteria(
                sensors,
                startDate,
                endDate
        )).thenReturn(weatherList);
        List<SensorDataModel> sensorDataList = sensorDataServiceImpl.getSensorData(
                sensors,
                EnumSet.of(Metric.TEMPERATURE),
                Statistic.AVG,
                startDate,
                endDate,
                false
        );
        assertEquals(sensors.size(), sensorDataList.size());
        assertEquals(0, sensorDataList.get(0).getHumidity());
        assertEquals(weatherList.get(0).getTemperature(), sensorDataList.get(0).getTemperature());
    }

    @Test
    @DisplayName("Should get multiple sensor data from a single sensor with MAX Humidity/WindSpeed")
    void testGetMultipleSensorDataMax() {
        Date startDate = Timestamp.valueOf("2023-07-13 00:00:00");
        Date endDate = Timestamp.valueOf("2023-07-31 00:00:00");
        Set<String> sensors = new HashSet<>(List.of("sensor1"));
        weatherList.add(WeatherEntity.builder()
                .weatherUuid(UUID.randomUUID().toString())
                .sensor("sensor1").humidity((byte) 100)
                .temperature(23.4f).windSpeed(11f)
                .timestamp(Timestamp.valueOf("2023-07-14 20:41:17"))
                .valid(true).build()
        );
        when(weatherRepository.findAllThatMatchCriteria(
                sensors,
                startDate,
                endDate
        )).thenReturn(weatherList);
        List<SensorDataModel> sensorDataList = sensorDataServiceImpl.getSensorData(
                sensors,
                EnumSet.of(Metric.HUMIDITY, Metric.WIND_SPEED),
                Statistic.MAX,
                startDate,
                endDate,
                false
        );
        assertEquals(sensors.size(), sensorDataList.size());
        assertEquals(0, sensorDataList.get(0).getTemperature());
        assertEquals(weatherList.get(0).getWindSpeed(), sensorDataList.get(0).getWindSpeed());
        assertEquals(weatherList.get(1).getHumidity(), sensorDataList.get(0).getHumidity());
    }

    @Test
    @DisplayName("Should get multiple sensor data from multiple sensors with SUM Temperature")
    void testGetMultipleSensorDataSum() {
        Date startDate = Timestamp.valueOf("2023-07-13 00:00:00");
        Date endDate = Timestamp.valueOf("2023-07-31 00:00:00");
        Set<String> sensors = new HashSet<>(List.of("sensor1", "sensor2"));
        weatherList.add(WeatherEntity.builder()
                .weatherUuid(UUID.randomUUID().toString())
                .sensor("sensor2").humidity((byte) 100)
                .temperature(23.4f).windSpeed(11f)
                .timestamp(Timestamp.valueOf("2023-07-14 20:41:17"))
                .valid(true).build()
        );
        weatherList.add(WeatherEntity.builder()
                .weatherUuid(UUID.randomUUID().toString())
                .sensor("sensor1").humidity((byte) 100)
                .temperature(23.4f).windSpeed(11f)
                .timestamp(Timestamp.valueOf("2023-07-14 20:41:17"))
                .valid(true).build()
        );
        when(weatherRepository.findAllThatMatchCriteria(
                sensors,
                startDate,
                endDate
        )).thenReturn(weatherList);
        List<SensorDataModel> sensorDataList = sensorDataServiceImpl.getSensorData(
                sensors,
                EnumSet.of(Metric.TEMPERATURE),
                Statistic.SUM,
                startDate,
                endDate,
                false
        );
        assertEquals(sensors.size(), sensorDataList.size());
        assertEquals(0, sensorDataList.get(0).getWindSpeed());
        assertEquals(0, sensorDataList.get(0).getHumidity());
        assertEquals(weatherList.get(0).getTemperature() + weatherList.get(2).getTemperature(), sensorDataList.get(1).getTemperature());
        assertEquals(weatherList.get(1).getTemperature(), sensorDataList.get(0).getTemperature());
    }

    @Test
    @DisplayName("Should fail getting sensor data: 200 OK with empty body")
    void testGetSensorDataFail() {
        Date startDate = Timestamp.valueOf("2023-07-13 00:00:00");
        Date endDate = Timestamp.valueOf("2023-07-31 00:00:00");
        Set<String> sensors = new HashSet<>(List.of("sensor1"));
        when(weatherRepository.findAllThatMatchCriteria(
                sensors,
                startDate,
                endDate
        )).thenReturn(Collections.emptyList());
        List<SensorDataModel> sensorDataList = sensorDataServiceImpl.getSensorData(
                sensors,
                EnumSet.of(Metric.TEMPERATURE),
                Statistic.AVG,
                startDate,
                endDate,
                false
        );
        assertNotEquals(sensors.size(), sensorDataList.size());
        assertEquals(0, sensorDataList.size());
    }

}