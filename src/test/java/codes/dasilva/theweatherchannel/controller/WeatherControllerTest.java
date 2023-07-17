package codes.dasilva.theweatherchannel.controller;

import codes.dasilva.theweatherchannel.model.WeatherModel;
import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import codes.dasilva.theweatherchannel.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(WeatherController.class)
@ExtendWith(MockitoExtension.class) // https://stackoverflow.com/a/68701517
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String weatherUuid = UUID.randomUUID().toString();

    private static final String ENDPOINT = "/weather";

    private static final char ENDPOINT_SEPARATOR = '/';

    private final WeatherEntity weather = new WeatherEntity();

    @BeforeEach
    void setUp() {
        weather.setWeatherUuid(weatherUuid);
        weather.setSensor("sensor1");
        weather.setHumidity((byte) 12);
        weather.setTemperature(23.4f);
        weather.setWindSpeed(32.3f);
        weather.setTimestamp(Timestamp.valueOf("2023-07-14 20:41:17"));
        weather.setValid(true);
    }

    @Test
    @DisplayName("Should get the specified weather")
    void testGetWeather() throws Exception {
        when(weatherService.getWeatherByUuid(weatherUuid)).thenReturn(weather);
        MockHttpServletRequestBuilder builder = get(ENDPOINT + ENDPOINT_SEPARATOR + weatherUuid)
                .characterEncoding(Charset.defaultCharset())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(weather.getSensor())));
    }

    @Test
    @DisplayName("Should update the specified weather")
    void testUpdateWeather() throws Exception {
        WeatherModel dto = new WeatherModel("sensor2", 1f, (byte) 1, 1f);
        BeanUtils.copyProperties(dto, weather);
        when(weatherService.updateWeather(weatherUuid, dto)).thenReturn(weather);
        MockHttpServletRequestBuilder builder = put(ENDPOINT + ENDPOINT_SEPARATOR + weatherUuid)
                .characterEncoding(Charset.defaultCharset())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dto));
        this.mockMvc.perform(builder).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sensor", is(dto.sensor())));
    }

    @Test
    @DisplayName("Should create a new weather")
    void testCreateWeather() throws Exception {
        WeatherModel dto = new WeatherModel("sensor1", 1f, (byte) 1, 1f);
        BeanUtils.copyProperties(dto, weather);
        when(weatherService.createWeather(dto)).thenReturn(weather);
        MockHttpServletRequestBuilder builder = post(ENDPOINT)
                .characterEncoding(Charset.defaultCharset())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dto));
        this.mockMvc.perform(builder).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(dto.sensor())));
    }

    @Test
    @DisplayName("Should delete the specified weather")
    void testDeleteWeather() throws Exception {
        when(weatherService.deleteWeather(weatherUuid)).thenReturn(weather);
        weather.setValid(false);
        MockHttpServletRequestBuilder builder = delete(ENDPOINT + ENDPOINT_SEPARATOR + weatherUuid)
                .characterEncoding(Charset.defaultCharset())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valid", is(weather.isValid())));
    }

}
