package codes.dasilva.theweatherchannel.controller;

import codes.dasilva.theweatherchannel.constant.Metric;
import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.model.SensorDataModel;
import codes.dasilva.theweatherchannel.service.SensorDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SensorDataController.class)
class SensorDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorDataService sensorDataService;

    private final String endpoint = "/sensor-data";

    private final SensorDataModel sensorData = new SensorDataModel();

    @BeforeEach
    void setUp() {
        sensorData.setSensor("sensor1");
        sensorData.setStatistic(Statistic.AVG);
        sensorData.setTemperature(1);
        sensorData.setWindSpeed(1);
        sensorData.setHumidity(1);
        sensorData.setStartDate(Timestamp.valueOf("2023-07-01 00:00:00"));
        sensorData.setEndDate(Timestamp.valueOf("2023-07-31 00:00:00"));
    }

    @Test
    @DisplayName("Should return a list of SensorDataModels that match the given criteria (MAX)")
    void testGetSensorData() throws Exception {
        // TEST FAILS. Body is an empty list, not sure why...
        when(sensorDataService.getSensorData(Set.of("sensor1"),
                EnumSet.of(Metric.TEMPERATURE),
                null,
                null,
                null,
                false
        )).thenReturn(List.of(sensorData));
        MockHttpServletRequestBuilder builder = get(endpoint)
                .characterEncoding(Charset.defaultCharset())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("sensors", "sensor1")
                .param("metrics", Metric.TEMPERATURE.name());
        this.mockMvc.perform(builder).andDo(print())
                .andExpect(status().isOk()) // Passes
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Passes
                .andExpect(jsonPath("$[0].temperature", is(sensorData.getTemperature()))); // Fails
    }

    @Test
    @DisplayName("Should return a Bad Request (400): Missing sensors")
    void testGetSensorDataMissingSensors() throws Exception {
        when(sensorDataService.getSensorData(null,null, null, null, null, false
        )).thenReturn(List.of(sensorData));
        MockHttpServletRequestBuilder builder = get(endpoint)
                .characterEncoding(Charset.defaultCharset())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("sensors")));
    }

    @Test
    @DisplayName("Should return a Bad Request (400): Missing metrics")
    void testGetSensorDataMissingMetrics() throws Exception {
        when(sensorDataService.getSensorData(null,null, null, null, null, false
        )).thenReturn(List.of(sensorData));
        MockHttpServletRequestBuilder builder = get(endpoint)
                .characterEncoding(Charset.defaultCharset())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("sensors", "sensor1");
        this.mockMvc.perform(builder).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("metrics")));
    }

}