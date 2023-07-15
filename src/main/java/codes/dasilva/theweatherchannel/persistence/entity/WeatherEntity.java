package codes.dasilva.theweatherchannel.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    @Min(-100)
    @Max(100)
    private float temperature;

    @NotEmpty
    @Min(0)
    @Max(100)
    private byte humidity;

    @NotEmpty
    @Min(0)
    @Max(500)
    private float windSpeed;

    @CreatedDate
    private Date timestamp;

    private boolean valid;

}
