package codes.dasilva.theweatherchannel.constant;

/**
 * Represents the possible statistics that can be queried using the <a href="http://localhost:8080/api/v1/sensor-data">API</a>.
 * It is also returned within SensorDataModel.
 * @author Gustavo Silva
 * @since 1.0.0
 * @see codes.dasilva.theweatherchannel.model.SensorDataModel
 */
public enum Statistic {
    /**
     * Instructs the application to get the minimum value of a specified (list of) Metric in WeatherEntity.
     * @see Metric
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @see codes.dasilva.theweatherchannel.model.SensorDataModel
     * @since 1.0.0
     */
    MIN,
    /**
     * Instructs the application to get the maximum value of a specified (list of) Metric in WeatherEntity.
     * @see Metric
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @see codes.dasilva.theweatherchannel.model.SensorDataModel
     * @since 1.0.0
     */
    MAX,
    /**
     * Instructs the application to get the sum of all values of a specified (list of) Metric in WeatherEntity.
     * @see Metric
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @see codes.dasilva.theweatherchannel.model.SensorDataModel
     * @since 1.0.0
     */
    SUM,
    /**
     * Instructs the application to get the average value of a specified (list of) Metric in WeatherEntity.
     * @see Metric
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @see codes.dasilva.theweatherchannel.model.SensorDataModel
     * @since 1.0.0
     */
    AVG
}
