package codes.dasilva.theweatherchannel.repository;

import codes.dasilva.theweatherchannel.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {

    @Query(value = "{'weatherUuid' :  ?0}")
    Optional<Weather> findOneById(String weatherUuid);

}
