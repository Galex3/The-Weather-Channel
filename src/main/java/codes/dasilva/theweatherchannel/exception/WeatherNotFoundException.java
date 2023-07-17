package codes.dasilva.theweatherchannel.exception;

import java.io.Serial;
import java.io.Serializable;

/**
 * WeatherNotFoundException is thrown when the database can't find a specific document.
 * @author Gustavo Silva
 * @since 1.0.0
 */
public class WeatherNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    private static final String WEATHER_NOT_FOUND = "Weather with id: '%s' not found";

    /**
     * Default constructor for WeatherNotFoundException.
     * @param id weatherUuid that does not exist
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    public WeatherNotFoundException(String id) {
        super(String.format(WEATHER_NOT_FOUND, id));
    }

}
