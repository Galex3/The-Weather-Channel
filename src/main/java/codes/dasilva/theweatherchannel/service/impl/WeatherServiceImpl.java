package codes.dasilva.theweatherchannel.service.impl;

import codes.dasilva.theweatherchannel.exception.WeatherNotFoundException;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import codes.dasilva.theweatherchannel.model.WeatherModel;
import codes.dasilva.theweatherchannel.persistence.repository.WeatherRepository;
import codes.dasilva.theweatherchannel.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public List<WeatherEntity> getAllWeather() {
        return weatherRepository.findAll();
    }

    @Override
    public WeatherEntity getWeatherByUuid(String weatherUuid) {
        Optional<WeatherEntity> weather = weatherRepository.findById(weatherUuid);
        if (weather.isPresent()) {
            log.info(String.format("Weather with id='%s' retrieved", weather.get().getWeatherUuid()));
            return weather.get();
        }
        throw new WeatherNotFoundException(weatherUuid);
    }

    @Override
    public WeatherEntity createWeather(WeatherModel dto) {
        WeatherEntity weather = WeatherEntity.builder().weatherUuid(UUID.randomUUID().toString())
                .sensor(dto.sensor())
                .temperature(dto.temperature())
                .humidity(dto.humidity())
                .windSpeed(dto.windSpeed())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .valid(true)
                .build();
        weatherRepository.save(weather);
        log.info(String.format("Weather created with id='%s'", weather.getWeatherUuid()));
        return weather;
    }

    @Override
    public WeatherEntity updateWeather(String weatherUuid, WeatherModel dto) {
        Optional<WeatherEntity> weather = weatherRepository.findById(weatherUuid);
        if (weather.isPresent()) {
            weather.get().setSensor(dto.sensor());
            weather.get().setHumidity(dto.humidity());
            weather.get().setTemperature(dto.temperature());
            weather.get().setWindSpeed(dto.windSpeed());
            weather.get().setTimestamp(new Timestamp(System.currentTimeMillis()));
            weatherRepository.save(weather.get());
            log.info(String.format("Weather with id='%s' updated", weather.get().getWeatherUuid()));
            return weather.get();
        }
        throw new WeatherNotFoundException(weatherUuid);
    }

    @Override
    public WeatherEntity deleteWeather(String weatherUuid) {
        Optional<WeatherEntity> weather = weatherRepository.findById(weatherUuid);
        if (weather.isPresent()) {
            weather.get().setValid(false);
            weatherRepository.save(weather.get());
            log.info(String.format("Weather with id='%s' softly deleted", weather.get().getWeatherUuid()));
            return weather.get();
        }
        throw new WeatherNotFoundException(weatherUuid);
    }

}
