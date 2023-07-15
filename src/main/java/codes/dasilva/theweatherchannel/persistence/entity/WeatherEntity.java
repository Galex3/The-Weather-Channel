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

@Document(collection = "weather")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WeatherEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @TextIndexed
    private String weatherUuid;

    @NotBlank
    @Pattern(regexp = "^[\\w]{1,100}$") // Disabling symbols enhances security
    private String sensor;

    @NotNull
    @DecimalMin(value = "-100")
    @DecimalMax(value = "100")
    private float temperature;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private byte humidity;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "500")
    private float windSpeed;

    @CreatedDate
    private Date timestamp;

    private boolean valid;

}
