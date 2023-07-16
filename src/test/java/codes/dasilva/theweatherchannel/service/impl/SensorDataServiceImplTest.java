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
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // https://stackoverflow.com/a/68701517
class SensorDataServiceImplTest {

    @Mock
    private final WeatherRepository weatherRepository = mock(WeatherRepository.class);

    @InjectMocks
    private final SensorDataServiceImpl sensorDataServiceImpl = new SensorDataServiceImpl(weatherRepository);

    @Mock
    private final WeatherEntity weather = new WeatherEntity();

    @BeforeEach
    void setUp() {
        weather.setWeatherUuid(UUID.randomUUID().toString());
        weather.setSensor("sensor1");
        weather.setHumidity((byte) 12);
        weather.setTemperature(23.4f);
        weather.setWindSpeed(32.3f);
        weather.setTimestamp(Timestamp.valueOf("2023-07-14 20:41:17"));
        weather.setValid(true);
        when(weatherRepository.findAllThatMatchCriteria(
                Set.of("sensor1"),
                Timestamp.valueOf("2023-07-13 20:41:17"),
                Timestamp.valueOf("2023-07-31 20:41:17")
        )).thenReturn(List.of(weather));
    }

    @Test
    @DisplayName("Should get Sensor Data")
    void testGetSensorData() {
        List<SensorDataModel> sensorDataList = sensorDataServiceImpl.getSensorData(
                Set.of("sensor1"),
                EnumSet.of(Metric.TEMPERATURE),
                Statistic.AVG,
                Timestamp.valueOf("2023-07-13 20:41:17"),
                Timestamp.valueOf("2023-07-31 20:41:17"),
                false
        );
        assertEquals(1, sensorDataList.size());
    }

}