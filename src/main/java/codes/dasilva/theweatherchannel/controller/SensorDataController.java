package codes.dasilva.theweatherchannel.controller;

import codes.dasilva.theweatherchannel.constant.Metric;
import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.model.SensorDataModel;
import codes.dasilva.theweatherchannel.service.SensorDataService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.status;

/**
 * Controller to query sensor data.
 * @author Gustavo Silva
 * @since 1.0.0
 */
@RestController
public class SensorDataController {

    private final SensorDataService sensorDataService;

    /**
     * Default constructor for SensorDataController.
     * Used for <a href="https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html#beans-constructor-injection">Constructor-based Dependency Injection</a>.
     * @param sensorDataService SensorDataService to be injected
     * @see SensorDataService
     * @author Gustavo Silva
     * @since 1.0.0
     */
    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

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
    @GetMapping(value = "/sensor-data", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<SensorDataModel>> getSensorData(
            @RequestParam @NotEmpty Set<String> sensors,
            @RequestParam @NotEmpty EnumSet<Metric> metrics,
            @RequestParam(name = "stat", defaultValue = "AVG", required = false) Statistic statistic,
            @RequestParam(defaultValue = "2023-07-01", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(defaultValue = "2023-07-31", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(name = "usa", defaultValue = "false", required = false) boolean fahrenheit
    ) {
        return status(HttpStatus.OK).body(sensorDataService.getSensorData(sensors, metrics, statistic, startDate, endDate, fahrenheit));
    }

}
