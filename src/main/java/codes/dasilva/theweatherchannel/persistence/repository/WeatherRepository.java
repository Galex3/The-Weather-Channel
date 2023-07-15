package codes.dasilva.theweatherchannel.persistence.repository;

import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface WeatherRepository extends MongoRepository<WeatherEntity, String> {

    @Query("{ 'sensor' : { $in: ?0 }, 'timestamp' : { $gte: ?1, $lte: ?2 }, 'valid' : true }")
    List<WeatherEntity> findAllThatMatchCriteria(Set<String> sensors, Date startDate, Date endDate);

}
