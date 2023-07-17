package codes.dasilva.theweatherchannel.service;

import codes.dasilva.theweatherchannel.constant.Metric;
import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.model.SensorDataModel;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Service that declares the methods to be used by the SensorData controller.
 * @author Gustavo Silva
 * @since 1.0.0
 */
public interface SensorDataService {

    /**
     * Gets the SensorDataModel that match the provided params.
     * @param sensors Required set of sensor names. Cannot be empty
     * @param metrics Required set of Metrics enums. Cannot be empty
     * @param statistic Optional Statistic enum. Default Statistic applied is average (AVG)
     * @param startDate Optional start date (inclusive). Default date is 2023-07-01
     * @param endDate Optional end date (inclusive). Default date is 2023-07-31
     * @param fahrenheit Optional flag to convert the temperature to Fahrenheit
     * @return a list of SensorDataModels. An empty list means no document matched the provided params
     * @throws jakarta.validation.ConstraintViolationException if there is an invalid param
     * @see SensorDataModel
     * @see Statistic
     * @see Metric
     * @author Gustavo Silva
     * @since 1.0.0
     */
    List<SensorDataModel> getSensorData(
            Set<String> sensors,
            EnumSet<Metric> metrics,
            Statistic statistic,
            Date startDate,
            Date endDate,
            boolean fahrenheit
    );

}
