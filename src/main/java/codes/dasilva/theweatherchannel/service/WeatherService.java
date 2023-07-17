package codes.dasilva.theweatherchannel.service;

import codes.dasilva.theweatherchannel.model.WeatherModel;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;

import java.util.List;

/**
 * Service that declares the methods to be used by the Weather controller.
 * @author Gustavo Silva
 * @since 1.0.0
 */
public interface WeatherService {

    /**
     * Gets the specified WeatherEntity from the database.
     * @param weatherUuid the WeatherEntity ID to be queried
     * @return the WeatherEntity with the provided weatherUuid
     * @throws codes.dasilva.theweatherchannel.exception.WeatherNotFoundException if a WeatherEntity with the provided weatherUuid does not exist
     * @see WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    WeatherEntity getWeatherByUuid(String weatherUuid);

    /**
     * Gets all WeatherEntity from the database.
     * @return a list of all WeatherEntity
     * @see WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    List<WeatherEntity> getAllWeather();

    /**
     * Creates a new WeatherEntity on the database.
     * @param dto the WeatherModel provided by the end-user to generate the new WeatherEntity
     * @return the WeatherEntity generated from the param
     * @see WeatherEntity
     * @see WeatherModel
     * @author Gustavo Silva
     * @since 1.0.0
     */
    WeatherEntity createWeather(WeatherModel dto);

    /**
     * Updates an existing WeatherEntity on the database.
     * @param weatherUuid the WeatherEntity ID to be queried
     * @param dto the WeatherModel provided by the end-user to update the WeatherEntity
     * @return the updated WeatherEntity based on the params
     * @throws codes.dasilva.theweatherchannel.exception.WeatherNotFoundException if a WeatherEntity with the provided weatherUuid does not exist
     * @see WeatherEntity
     * @see WeatherModel
     * @author Gustavo Silva
     * @since 1.0.0
     */
    WeatherEntity updateWeather(String weatherUuid, WeatherModel dto);

    /**
     * Soft deletes an existing WeatherEntity on the database; sets the WeatherEntity's valid property to false.
     * @param weatherUuid the WeatherEntity ID to be queried
     * @return the "deleted" WeatherEntity based on the param
     * @throws codes.dasilva.theweatherchannel.exception.WeatherNotFoundException if a WeatherEntity with the provided weatherUuid does not exist
     * @see WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    WeatherEntity deleteWeather(String weatherUuid);

}
