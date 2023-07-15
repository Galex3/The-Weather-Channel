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
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static codes.dasilva.theweatherchannel.utils.WeatherUtils.celsiusToFahrenheit;

@Service
@Slf4j
public class SensorDataServiceImpl implements SensorDataService {

    private final WeatherRepository weatherRepository;

    public SensorDataServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }
    // So it doesn't include a non-existent sensor when existing ones are also included in query

    @Override
    public List<SensorDataModel> getSensorData(Set<String> sensors, EnumSet<Metric> metrics, Statistic statistic, Date startDate, Date endDate, boolean fahrenheit) {
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
                        sensorDataModel.setTemperature(fahrenheit ? celsiusToFahrenheit(temp) : temp);
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

    private Double applyStatistic(Statistic statistic, Stream<WeatherEntity> weatherStream, ToDoubleFunction<WeatherEntity> function) {
        return switch (statistic) {
            case SUM -> weatherStream.mapToDouble(function).sum();
            case MIN -> weatherStream.mapToDouble(function).min().getAsDouble();
            case MAX -> weatherStream.mapToDouble(function).max().getAsDouble();
            default -> weatherStream.mapToDouble(function).average().getAsDouble();
        };
    }

}
