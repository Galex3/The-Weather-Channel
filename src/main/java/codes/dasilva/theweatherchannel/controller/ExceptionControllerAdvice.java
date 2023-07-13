package codes.dasilva.theweatherchannel.controller;

import codes.dasilva.theweatherchannel.exception.WeatherNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WeatherNotFoundException.class)
    private ResponseEntity<String> handleWeatherNotFoundException(WeatherNotFoundException e) {
        log.error("Weather not found", e);
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

}
