package codes.dasilva.theweatherchannel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Weather implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @TextIndexed
    private String weatherUuid;

    private String sensor;

    private float temperature;

    private byte humidity;

    private float windSpeed;

    @CreatedDate
    private Date timestamp;

    private boolean valid;

}
