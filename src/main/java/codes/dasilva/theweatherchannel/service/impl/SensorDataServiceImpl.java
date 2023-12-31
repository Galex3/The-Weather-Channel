package codes.dasilva.theweatherchannel.service.impl;

import codes.dasilva.theweatherchannel.constant.Metric;
import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.model.SensorDataModel;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import codes.dasilva.theweatherchannel.persistence.repository.WeatherRepository;
import codes.dasilva.theweatherchannel.service.SensorDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static codes.dasilva.theweatherchannel.utils.WeatherUtils.applyStatistic;
import static codes.dasilva.theweatherchannel.utils.WeatherUtils.temperatureConverter;

/**
 * Implementation of the Service that declares the methods to be used by the SensorData controller.
 * @author Gustavo Silva
 * @since 1.0.0
 */
@Service
@Slf4j
public class SensorDataServiceImpl implements SensorDataService {

    private final WeatherRepository weatherRepository;

    /**
     * Default constructor for SensorDataServiceImpl.
     * Used for <a href="https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html#beans-constructor-injection">Constructor-based Dependency Injection</a>.
     * @param weatherRepository WeatherRepository to be injected
     * @see WeatherRepository
     * @author Gustavo Silva
     * @since 1.0.0
     */
    public SensorDataServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public List<SensorDataModel> getSensorData(Set<String> sensors, EnumSet<Metric> metrics, Statistic statistic, Date startDate, Date endDate, Boolean fahrenheit) {
        final List<WeatherEntity> weatherData = weatherRepository.findAllThatMatchCriteria(sensors, startDate, endDate);
        if (weatherData.isEmpty()) {
            return Collections.emptyList();
        }
        // So it doesn't include a non-existent sensor when existing ones are also included in query
        sensors.retainAll(weatherData.stream().map(WeatherEntity::getSensor).collect(Collectors.toSet()));
        final List<SensorDataModel> sensorData = new ArrayList<>();
        for (String sensor : sensors) {
            SensorDataModel sensorDataModel = SensorDataModel.builder()
                    .sensor(sensor)
                    .statistic(statistic)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
            for (Metric metric : metrics) {
                final Stream<WeatherEntity> weatherStream = weatherData.stream().filter(weather -> weather.getSensor().equals(sensor));
                switch (metric) {
                    case TEMPERATURE -> {
                        final double temp = applyStatistic(statistic, weatherStream, WeatherEntity::getTemperature);
                        sensorDataModel.setTemperature(Boolean.TRUE.equals(fahrenheit) ? temperatureConverter(temp) : temp);
                    }
                    case HUMIDITY ->
                            sensorDataModel.setHumidity(applyStatistic(statistic, weatherStream, WeatherEntity::getHumidity));
                    case WIND_SPEED ->
                            sensorDataModel.setWindSpeed(applyStatistic(statistic, weatherStream, WeatherEntity::getWindSpeed));
                }
            }
            sensorData.add(sensorDataModel);
        }
        log.info("SensorData: " + Arrays.toString(sensorData.toArray()));
        return sensorData;
    }

}
