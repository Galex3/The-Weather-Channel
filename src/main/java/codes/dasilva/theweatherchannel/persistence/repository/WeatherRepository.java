package codes.dasilva.theweatherchannel.persistence.repository;

import codes.dasilva.theweatherchannel.persistence.entity.WeatherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * WeatherRepository represents the interface between the application and the database. It's where we define custom queries
 * and/or use the ones provided by MongoRepository.
 * @see WeatherEntity
 * @see MongoRepository
 * @author Gustavo Silva
 * @since 1.0.0
 */
@Repository
public interface WeatherRepository extends MongoRepository<WeatherEntity, String> {

    /**
     * Defines a query to retrieve all valid sensors that match the params.
     * @param sensors set of sensors' names
     * @param startDate minimum date to get sensors from (inclusive)
     * @param endDate maximum date to get sensors from (inclusive)
     * @return list of WeatherEntity
     * @see WeatherEntity
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @Query("{ 'sensor' : { $in: ?0 }, 'timestamp' : { $gte: ?1, $lte: ?2 }, 'valid' : true }")
    List<WeatherEntity> findAllThatMatchCriteria(Set<String> sensors, Date startDate, Date endDate);

}
