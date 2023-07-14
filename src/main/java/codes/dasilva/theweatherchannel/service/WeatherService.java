package codes.dasilva.theweatherchannel.service;

import codes.dasilva.theweatherchannel.model.Weather;
import codes.dasilva.theweatherchannel.model.WeatherDTO;

import java.util.List;

public interface WeatherService {

    Weather getWeatherByUuid(String weatherUuid);

    List<Weather> getAllWeather();

    Weather createWeather(WeatherDTO entity);

    Weather updateWeather(String weatherUuid, WeatherDTO entity);

    Weather deleteWeather(String weatherUuid);

}
