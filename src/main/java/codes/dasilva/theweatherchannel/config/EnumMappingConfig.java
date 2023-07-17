package codes.dasilva.theweatherchannel.config;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Converts a string into an enum in a case-insensitive manner.
 * Got it from <a href="https://www.baeldung.com/spring-boot-enum-mapping">Baeldung</a>.
 * @author Gustavo Silva
 * @since 1.0.0
 */
@Configuration
public class EnumMappingConfig implements WebMvcConfigurer {

    /**
     * Configures the application to use the given FormatterRegistry.
     * @param registry of type FormatterRegistry. Cannot be null
     * @author Gustavo Silva
     * @since 1.0.0
     */
    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        ApplicationConversionService.configure(registry);
    }

}
