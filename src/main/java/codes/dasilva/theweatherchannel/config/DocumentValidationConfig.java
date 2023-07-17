package codes.dasilva.theweatherchannel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuration class that enforces field validation on MongoDB documents.
 * @author Gustavo Silva
 * @since 1.0.0
 */
@Configuration
public class DocumentValidationConfig {

    /**
     * Enforces field validation on MongoDB documents. Got it from <a href="https://stackoverflow.com/a/22583492">StackOverflow</a>.
     * @param factory of type LocalValidatorFactoryBean
     * @return the ValidatingMongoEventListener
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean factory) {
        return new ValidatingMongoEventListener(factory);
    }

}
