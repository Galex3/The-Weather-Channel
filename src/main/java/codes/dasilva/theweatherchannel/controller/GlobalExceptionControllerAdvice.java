package codes.dasilva.theweatherchannel.controller;

import codes.dasilva.theweatherchannel.exception.WeatherNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

/**
 * Exception controller that handles specific exceptions' behaviour application-wide.
 * @author Gustavo Silva
 * @since 1.0.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Handles WeatherNotFoundException when thrown.
     * @param e Exception of type WeatherNotFoundException
     * @return JSON response with 404 NOT FOUND and detailed message
     * @see WeatherNotFoundException
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @ExceptionHandler(WeatherNotFoundException.class)
    private ProblemDetail handleWeatherNotFoundException(WeatherNotFoundException e) {
        log.error(e.getMessage(), e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Weather Not Found");
        problemDetail.setType(URI.create("http://localhost:8080/errors/not-found")); // Placeholder
        return problemDetail;
    }

    /**
     * Handles ConstraintViolationException when thrown.
     * @param e Exception of type ConstraintViolationException
     * @return JSON response with 400 BAD REQUEST and detailed message
     * @see ConstraintViolationException
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @ExceptionHandler(ConstraintViolationException.class)
    private ProblemDetail handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Invalid Weather");
        problemDetail.setType(URI.create("http://localhost:8080/errors/bad-request")); // Placeholder
        return problemDetail;
    }

}
