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

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather/{weatherUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<WeatherEntity> getWeatherById(@PathVariable String weatherUuid) {
        return status(HttpStatus.OK).body(weatherService.getWeatherByUuid(weatherUuid));
    }

    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<WeatherEntity>> getAllWeather() {
        return status(HttpStatus.OK).body(weatherService.getAllWeather());
    }

    @PostMapping(value = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<WeatherEntity> createWeather(@RequestBody WeatherModel dto) {
        return status(HttpStatus.CREATED).body(weatherService.createWeather(dto));
    }

    @PutMapping(value = "/weather/{weatherUuid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<WeatherEntity> updateWeather(@PathVariable String weatherUuid, @RequestBody WeatherModel dto) {
        return status(HttpStatus.OK).body(weatherService.updateWeather(weatherUuid, dto));
    }

    @DeleteMapping(value = "/weather/{weatherUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<WeatherEntity> deleteWeather(@PathVariable String weatherUuid) {
        return status(HttpStatus.OK).body(weatherService.deleteWeather(weatherUuid));
    }

}
