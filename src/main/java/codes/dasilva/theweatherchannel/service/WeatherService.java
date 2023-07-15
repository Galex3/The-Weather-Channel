package codes.dasilva.theweatherchannel.service;

import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import codes.dasilva.theweatherchannel.model.WeatherModel;

import java.util.List;

public interface WeatherService {

    WeatherEntity getWeatherByUuid(String weatherUuid);

    List<WeatherEntity> getAllWeather();

    WeatherEntity createWeather(WeatherModel dto);

    WeatherEntity updateWeather(String weatherUuid, WeatherModel dto);

    WeatherEntity deleteWeather(String weatherUuid);

}
