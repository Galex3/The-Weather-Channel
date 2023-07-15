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

@RestController
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

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
