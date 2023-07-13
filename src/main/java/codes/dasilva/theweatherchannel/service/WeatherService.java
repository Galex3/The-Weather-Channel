package codes.dasilva.theweatherchannel.service;

import codes.dasilva.theweatherchannel.model.Weather;
import codes.dasilva.theweatherchannel.model.WeatherDTO;

public interface WeatherService {

    Weather createWeather(WeatherDTO entity);

    Weather getWeatherByUuid(String weatherUuid);

}
