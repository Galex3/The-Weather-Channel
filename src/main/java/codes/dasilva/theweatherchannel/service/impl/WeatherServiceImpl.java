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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private static final String WEATHER_NOT_FOUND = "Weather with id: '%s' not found";

    @Override
    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

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
        log.info(String.format("Weather created with id='%s'", weather.getWeatherUuid()));
        return weather;
    }

    @Override
    public Weather getWeatherByUuid(String weatherUuid) {
        Optional<Weather> weather = weatherRepository.findById(weatherUuid);
        if (weather.isPresent()) {
            log.info(String.format("Weather with id='%s' retrieved", weather.get().getWeatherUuid()));
            return weather.get();
        }
        throw new WeatherNotFoundException(String.format(WEATHER_NOT_FOUND, weatherUuid), HttpStatus.NOT_FOUND);
    }

    @Override
    public Weather updateWeather(String weatherUuid, WeatherDTO dto) {
        Optional<Weather> weather = weatherRepository.findById(weatherUuid);
        if (weather.isPresent()) {
            weather.get().setSensor(dto.sensor());
            weather.get().setHumidity(dto.humidity());
            weather.get().setTemperature(dto.temperature());
            weather.get().setWindSpeed(dto.windSpeed());
            weather.get().setTimestamp(new Timestamp(System.currentTimeMillis()));
            // If I had a lot more properties, I would use something like BeanUtils.copyProperties(dto, weather.get())
            weatherRepository.save(weather.get());
            log.info(String.format("Weather with id='%s' updated", weather.get().getWeatherUuid()));
            return weather.get();
        }
        throw new WeatherNotFoundException(String.format(WEATHER_NOT_FOUND, weatherUuid), HttpStatus.NOT_FOUND);
    }

    @Override
    public Weather deleteWeather(String weatherUuid) {
        Optional<Weather> weather = weatherRepository.findById(weatherUuid);
        if (weather.isPresent()) {
            weather.get().setValid(false);
            weatherRepository.save(weather.get());
            log.info(String.format("Weather with id='%s' softly deleted", weather.get().getWeatherUuid()));
            return weather.get();
        }
        throw new WeatherNotFoundException(String.format(WEATHER_NOT_FOUND, weatherUuid), HttpStatus.NOT_FOUND);
    }

}
