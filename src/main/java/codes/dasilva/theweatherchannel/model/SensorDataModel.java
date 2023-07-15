package codes.dasilva.theweatherchannel.model;

import codes.dasilva.theweatherchannel.constant.Statistic;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotBlank
    private String sensor;

    @NotEmpty
    private Statistic statistic;

    @NotEmpty
    @Min(-100)
    private double temperature;

    @NotEmpty
    @Min(0)
    private long humidity;

    @NotEmpty
    @Min(0)
    private double windSpeed;

    @NotEmpty
    private Date startDate;

    @NotEmpty
    private Date endDate;

}
