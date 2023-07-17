package codes.dasilva.theweatherchannel.constant;

/**
 * Represents the possible metrics that can be queried using the <a href="http://localhost:8080/api/v1/sensor-data">API</a>.
 * @author Gustavo Silva
 * @since 1.0.0
 */
public enum Metric {
    /**
     * Represents the temperature field in WeatherEntity and SensorDataModel
     * @since 1.0.0
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @see codes.dasilva.theweatherchannel.model.SensorDataModel
     */
    TEMPERATURE,
    /**
     * Represents the humidity field in WeatherEntity and SensorDataModel
     * @since 1.0.0
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @see codes.dasilva.theweatherchannel.model.SensorDataModel
     */
    HUMIDITY,
    /**
     * Represents the windSpeed field in WeatherEntity and SensorDataModel
     * @since 1.0.0
     * @see codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity
     * @see codes.dasilva.theweatherchannel.model.SensorDataModel
     */
    WIND_SPEED
}
