package codes.dasilva.theweatherchannel.persistence.repository;

import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends MongoRepository<WeatherEntity, String> {

    // TODO
    @Query(value = "{'sensor':  ?0}")
    Optional<WeatherEntity> findWeatherEntitiesBySensor();

}
