package codes.dasilva.theweatherchannel.service;

import codes.dasilva.theweatherchannel.constant.Metric;
import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.model.SensorDataModel;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public interface SensorDataService {

    List<SensorDataModel> getSensorData(
            Set<String> sensors,
            EnumSet<Metric> metrics,
            Statistic statistic,
            Date startDate,
            Date endDate,
            boolean fahrenheit
    );

}
