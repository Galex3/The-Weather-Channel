package codes.dasilva.theweatherchannel.model;

import codes.dasilva.theweatherchannel.constant.Statistic;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * A SensorDataModel is the object returned when querying the endpoint "/sensor-data". It stores some of the request
 * params and the manipulated data according to the not-stored params.
 * @see codes.dasilva.theweatherchannel.constant.Metric
 * @see Statistic
 * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
 * @author Gustavo Silva
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SensorDataModel implements Serializable {

    /**
     * Universal Unique Identifier to identify the class.
     * @since 1.0.0
     */
    @Serial
    private static final long serialVersionUID = 987654L;

    /**
     * Sensor name.
     * @since 1.0.0
     */
    @NotBlank
    private String sensor;

    /**
     * Represents which Statistic was used to perform the calculations for the relevant Metrics.
     * @since 1.0.0
     * @see codes.dasilva.theweatherchannel.constant.Metric
     */
    @NotNull
    private Statistic statistic;

    /**
     * Temperature in Celsius or Fahrenheit.
     * @since 1.0.0
     */
    @NotNull
    private double temperature; // Did not include @DecimalMin because the SUM of several negative temperatures would be a tiny number

    /**
     * Humidity in percentage.
     * @since 1.0.0
     */
    @DecimalMin(value = "0")
    private double humidity;

    /**
     * Wind speed in kph or mph.
     * @since 1.0.0
     */
    @DecimalMin(value = "0")
    private double windSpeed;

    /**
     * Represents the date queried WeatherEntity must at least be from, inclusive.
     * @since 1.0.0
     */
    @NotNull
    private Date startDate;

    /**
     * Represents the date queried WeatherEntity must at most be from, inclusive.
     * @since 1.0.0
     */
    @NotNull
    private Date endDate;

}
