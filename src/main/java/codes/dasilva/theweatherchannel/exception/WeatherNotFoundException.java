package codes.dasilva.theweatherchannel.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class WeatherNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    private final HttpStatus statusCode;

    public WeatherNotFoundException(HttpStatus statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public WeatherNotFoundException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public WeatherNotFoundException(String message, Throwable throwable, HttpStatus statusCode) {
        super(message, throwable);
        this.statusCode = statusCode;
    }

    public WeatherNotFoundException(Throwable throwable, HttpStatus statusCode) {
        super(throwable);
        this.statusCode = statusCode;
    }

}
