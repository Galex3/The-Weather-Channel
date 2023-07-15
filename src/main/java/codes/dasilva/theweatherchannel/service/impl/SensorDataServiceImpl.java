package codes.dasilva.theweatherchannel.service.impl;

import codes.dasilva.theweatherchannel.model.SensorDataModel;
import codes.dasilva.theweatherchannel.persistence.repository.WeatherRepository;
import codes.dasilva.theweatherchannel.service.SensorDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SensorDataServiceImpl implements SensorDataService {

    private final WeatherRepository weatherRepository;

    public SensorDataServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public List<SensorDataModel> getSensorData() {
        // TODO
        return Collections.emptyList();
    }

}
