package codes.dasilva.theweatherchannel.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * A WeatherModel is a Record that represents the data the end-user provides to generate a WeatherEntity.
 * It follows the DTO (Data Transfer Object) pattern.
 * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
 * @author Gustavo Silva
 * @since 1.0.0
 */
public record WeatherModel(
        @NotBlank(message = "'sensor' can only contain alphanumeric characters and underscores (_)")
        @Pattern(regexp = "^[\\w]{1,100}$")
        String sensor,
        @NotNull
        @DecimalMin(value = "-100")
        @DecimalMax(value = "100")
        float temperature,
        @NotNull
        @DecimalMin(value = "0")
        @DecimalMax(value = "100")
        byte humidity,
        @NotNull
        @DecimalMin(value = "0")
        @DecimalMax(value = "500")
        float windSpeed
) {}