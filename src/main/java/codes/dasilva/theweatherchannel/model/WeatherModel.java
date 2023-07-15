package codes.dasilva.theweatherchannel.model;

public record WeatherModel(
        String sensor,
        float temperature,
        byte humidity,
        float windSpeed
) {}