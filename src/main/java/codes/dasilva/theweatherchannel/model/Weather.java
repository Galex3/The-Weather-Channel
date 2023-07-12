package codes.dasilva.theweatherchannel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

// TODO
@Document(collection = "weather")
@JsonIgnoreProperties(ignoreUnknown = true)
public record Weather() implements Serializable {}
