package codes.dasilva.theweatherchannel.service.impl;

import codes.dasilva.theweatherchannel.constant.Metric;
import codes.dasilva.theweatherchannel.constant.Statistic;
import codes.dasilva.theweatherchannel.model.SensorDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * I gave up on this since I couldn't make it work. All metrics came back as 0
 */
@Service
@Slf4j
public class SensorDataServiceImplNotWorking /*implements SensorDataService*/ {

    private final MongoTemplate mongoTemplate;

    public SensorDataServiceImplNotWorking(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    //@Override
    public List<SensorDataModel> getSensorData(List<String> sensors, List<Metric> metrics, Statistic statistic, Date startDate, Date endDate, boolean fahrenheit) {
        // Build the aggregation pipeline
        Aggregation aggregation = buildAggregationPipeline(getCriteria(sensors, startDate, endDate), metrics, statistic);
        // Execute the aggregation query
        AggregationResults<SensorDataModel> aggregationResults = mongoTemplate.aggregate(aggregation, "weather", SensorDataModel.class);
        List<SensorDataModel> sensorData = aggregationResults.getMappedResults();
        log.info(Arrays.toString(sensorData.toArray()));
        return sensorData;
    }

    private Aggregation buildAggregationPipeline(Criteria criteria, List<Metric> metrics, Statistic statistic) {
        // Build the match operation based on the provided parameters
        MatchOperation matchOperation = Aggregation.match(criteria);

        if (Objects.isNull(statistic)) {
            statistic = Statistic.AVG;
        }

        // Build the group operation based on the metrics and statistic
        GroupOperation groupOperation = Aggregation.group("sensor");
        for (Metric metric : metrics) {
            switch (statistic) {
                case MAX -> groupOperation.max(getMetricField(metric)).as(getMetricField(metric));
                case MIN -> groupOperation.min(getMetricField(metric)).as(getMetricField(metric));
                case SUM -> groupOperation.sum(getMetricField(metric)).as(getMetricField(metric));
                default -> groupOperation.avg(getMetricField(metric)).as(getMetricField(metric));
            }
        }

        // Build the aggregation pipeline
        return Aggregation.newAggregation(matchOperation, groupOperation);
    }

    private Criteria getCriteria(List<String> sensors, Date startDate, Date endDate) {
        Criteria criteria = new Criteria();
        criteria.and("sensor").in(sensors).and("valid").is(true);
        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            criteria.and("timestamp").gte(startDate).lte(endDate);
        }
        return criteria;
    }

    private String getMetricField(Metric metric) {
        return switch (metric) {
            case TEMPERATURE -> "temperature";
            case HUMIDITY -> "humidity";
            case WIND_SPEED -> "windSpeed";
        };
    }

}
