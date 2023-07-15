package codes.dasilva.theweatherchannel.exception;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class WeatherNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    private static final String WEATHER_NOT_FOUND = "Weather with id: '%s' not found";

    public WeatherNotFoundException(String id) {
        super(String.format(WEATHER_NOT_FOUND, id));
    }

}
