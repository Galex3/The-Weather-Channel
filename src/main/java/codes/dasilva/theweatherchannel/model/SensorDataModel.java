package codes.dasilva.theweatherchannel.model;

import codes.dasilva.theweatherchannel.constant.Statistic;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SensorDataModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 987654L;

    @Id
    @NotBlank
    private String sensor;

    @NotNull
    private Statistic statistic;

    private double temperature;

    @DecimalMin(value = "0")
    private double humidity;

    @DecimalMin(value = "0")
    private double windSpeed;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

}
