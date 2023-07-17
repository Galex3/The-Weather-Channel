package codes.dasilva.theweatherchannel.service.impl;

import codes.dasilva.theweatherchannel.exception.WeatherNotFoundException;
import codes.dasilva.theweatherchannel.model.WeatherModel;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import codes.dasilva.theweatherchannel.persistence.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class) // https://stackoverflow.com/a/68701517
class WeatherServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherServiceImpl weatherServiceImpl;

    @BeforeEach
    void setUp() {
        weatherServiceImpl = new WeatherServiceImpl(weatherRepository);
    }

    @Test
    @DisplayName("Should create a document")
    void testCreateWeather() {
        when(weatherRepository.save(Mockito.any())).thenReturn(null);
        // No need to declare weatherDTO as final because it's a Record
        WeatherModel weatherDTO = new WeatherModel("sensor1", 23.4f, (byte) 12, 32.3f);
        WeatherEntity weather = weatherServiceImpl.createWeather(weatherDTO);
        assertEquals(weatherDTO.sensor(), weather.getSensor());
    }

    @Test
    @DisplayName("Should fail getting weather: 404 Not Found")
    void testUpdateWeatherFail() throws RuntimeException {
        final String id = UUID.randomUUID().toString();
        when(weatherRepository.findById(id)).thenReturn(Optional.empty());
        WeatherNotFoundException thrown = assertThrows(
                WeatherNotFoundException.class,
                () -> weatherServiceImpl.getWeatherByUuid(id),
                "Expected WeatherNotFoundException"
        );
        assertTrue(thrown.getMessage().contains("not found"));
    }

}