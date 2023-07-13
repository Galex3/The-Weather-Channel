package codes.dasilva.theweatherchannel.service.impl;

import codes.dasilva.theweatherchannel.exception.WeatherNotFoundException;
import codes.dasilva.theweatherchannel.model.Weather;
import codes.dasilva.theweatherchannel.model.WeatherDTO;
import codes.dasilva.theweatherchannel.repository.WeatherRepository;
import codes.dasilva.theweatherchannel.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public Weather createWeather(WeatherDTO dto) {
        Weather weather = Weather.builder().weatherUuid(UUID.randomUUID().toString())
                .sensor(dto.sensor())
                .temperature(dto.temperature())
                .humidity(dto.humidity())
                .windSpeed(dto.windSpeed())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .valid(true)
                .build();
        weatherRepository.save(weather);
        log.info(String.format("Weather created with id: %s", weather.getWeatherUuid()));
        return weather;
    }

    @Override
    public Weather getWeatherByUuid(String weatherUuid) {
        Optional<Weather> weather = weatherRepository.findOneById(weatherUuid);
        if (weather.isPresent()) {
            log.info(String.format("Weather created with id: %s", weather.get().getWeatherUuid()));
            return weather.get();
        }
        throw new WeatherNotFoundException(String.format("Weather not found for id: %s", weatherUuid), HttpStatus.NOT_FOUND);
    }

}
