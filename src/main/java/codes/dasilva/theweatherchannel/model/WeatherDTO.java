package codes.dasilva.theweatherchannel.model;

public record WeatherDTO(
        String sensor,
        float temperature,
        byte humidity,
        float windSpeed
) {}