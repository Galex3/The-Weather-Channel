package codes.dasilva.theweatherchannel.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents the data being saved on the collection "weather" in MongoDB.
 * @author Gustavo Silva
 * @since 1.0.0
 */
@Document(collection = "weather")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WeatherEntity implements Serializable {

    /**
     * Universal Unique Identifier to identify the class.
     * @since 1.0.0
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Universal Unique Identifier to identify the WeatherEntity.
     * @since 1.0.0
     */
    @Id
    @TextIndexed
    private String weatherUuid;

    /**
     * Sensor name.
     * @since 1.0.0
     */
    @NotBlank(message = "'sensor' can only contain alphanumeric characters and underscores (_)")
    @Pattern(regexp = "^[\\w]{1,100}$") // Disabling symbols enhances security
    private String sensor;

    /**
     * Temperature in Celsius.
     * @since 1.0.0
     */
    @NotNull
    @DecimalMin(value = "-100")
    @DecimalMax(value = "100")
    private float temperature;

    /**
     * Humidity in percentage.
     * @since 1.0.0
     */
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private byte humidity;

    /**
     * Wind speed in kph.
     * @since 1.0.0
     */
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "500")
    private float windSpeed;

    /**
     * The date when this WeatherEntity was created
     * @since 1.0.0
     */
    @NotNull
    @CreatedDate
    private Date timestamp;

    /**
     * Boolean that represents whether this WeatherEntity is (not) valid.
     * @since 1.0.0
     */
    @NotNull
    private boolean valid;

}
