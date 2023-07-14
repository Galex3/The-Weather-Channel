package codes.dasilva.theweatherchannel.controller;

import codes.dasilva.theweatherchannel.model.Weather;
import codes.dasilva.theweatherchannel.model.WeatherDTO;
import codes.dasilva.theweatherchannel.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/weather/{weatherUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<Weather> getWeatherById(@PathVariable String weatherUuid) {
        return status(HttpStatus.OK).body(weatherService.getWeatherByUuid(weatherUuid));
    }

    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<Weather>> getAllWeather() {
        return status(HttpStatus.OK).body(weatherService.getAllWeather());
    }

    @PostMapping(value = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<Weather> createWeather(@RequestBody WeatherDTO dto) {
        return status(HttpStatus.CREATED).body(weatherService.createWeather(dto));
    }

    @PutMapping(value = "/weather/{weatherUuid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<Weather> updateWeather(@PathVariable String weatherUuid, @RequestBody WeatherDTO dto) {
        return status(HttpStatus.OK).body(weatherService.updateWeather(weatherUuid, dto));
    }

    @DeleteMapping(value = "/weather/{weatherUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<Weather> deleteWeather(@PathVariable String weatherUuid) {
        return status(HttpStatus.OK).body(weatherService.deleteWeather(weatherUuid));
    }

    /*@GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<Weather> getWeather(@RequestParam List<String> sensor) {
        // TODO
        //return status(HttpStatus.OK).body(weatherService.getWeather(weatherUuid));
        return null;
    }*/

}
