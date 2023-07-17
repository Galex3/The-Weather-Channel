package codes.dasilva.theweatherchannel.controller;

import codes.dasilva.theweatherchannel.model.WeatherModel;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import codes.dasilva.theweatherchannel.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

/**
 * Controller to query weather data.
 * @author Gustavo Silva
 * @since 1.0.0
 */
@RestController
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * Default constructor for WeatherController.
     * Used for <a href="https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html#beans-constructor-injection">Constructor-based Dependency Injection</a>.
     * @param weatherService WeatherService to be injected
     * @see WeatherService
     * @author Gustavo Silva
     * @since 1.0.0
     */
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Gets the specified WeatherEntity from the database.
     * @param weatherUuid the WeatherEntity ID to be queried
     * @return the WeatherEntity with the provided weatherUuid
     * @throws codes.dasilva.theweatherchannel.exception.WeatherNotFoundException if a WeatherEntity with the provided weatherUuid does not exist
     * @see WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @GetMapping(value = "/weather/{weatherUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<WeatherEntity> getWeatherByUuid(@PathVariable String weatherUuid) {
        return status(HttpStatus.OK).body(weatherService.getWeatherByUuid(weatherUuid));
    }

    /**
     * Gets all WeatherEntity from the database.
     * @return list of all WeatherEntity
     * @see WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<WeatherEntity>> getAllWeather() {
        return status(HttpStatus.OK).body(weatherService.getAllWeather());
    }

    /**
     * Creates a new WeatherEntity on the database.
     * @param dto the WeatherModel provided by the end-user to generate the new WeatherEntity
     * @return the WeatherEntity generated from the param
     * @see WeatherEntity
     * @see WeatherModel
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @PostMapping(value = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<WeatherEntity> createWeather(@RequestBody WeatherModel dto) {
        return status(HttpStatus.CREATED).body(weatherService.createWeather(dto));
    }

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
    @PutMapping(value = "/weather/{weatherUuid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<WeatherEntity> updateWeather(@PathVariable String weatherUuid, @RequestBody WeatherModel dto) {
        return status(HttpStatus.OK).body(weatherService.updateWeather(weatherUuid, dto));
    }

    /**
     * Soft deletes an existing WeatherEntity on the database; sets the WeatherEntity's valid property to false.
     * @param weatherUuid the WeatherEntity ID to be queried
     * @return the "deleted" WeatherEntity based on the param
     * @throws codes.dasilva.theweatherchannel.exception.WeatherNotFoundException if a WeatherEntity with the provided weatherUuid does not exist
     * @see WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @DeleteMapping(value = "/weather/{weatherUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<WeatherEntity> deleteWeather(@PathVariable String weatherUuid) {
        return status(HttpStatus.OK).body(weatherService.deleteWeather(weatherUuid));
    }

}
