package codes.dasilva.theweatherchannel.config;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Got it from <a href="https://www.baeldung.com/spring-boot-enum-mapping">Baeldung</a>
 * Essentially makes enums case-insensitive
 */
@Configuration
public class EnumMappingConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        ApplicationConversionService.configure(registry);
    }

}
