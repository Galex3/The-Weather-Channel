package codes.dasilva.theweatherchannel.repository;

import codes.dasilva.theweatherchannel.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {

    // TODO

}
