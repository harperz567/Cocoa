// This is a config class
package cocoa.config;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import cocoa.common.JacksonObjectMapper;
@Slf4j
@Configuration
// We need this comment to indicate this is a config class
/**
 * WebMvcConfig is a custom configuration class that extends Spring's WebMvcConfigurationSupport.
 * It allows customization of static resource mappings and message converters.
 */
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * Configures static resource mappings.
     *
     * This method maps specific URL paths to corresponding static resource directories,
     * enabling the application to serve frontend assets correctly.
     *
     * @param registry The ResourceHandlerRegistry used to add resource handler mappings.
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Start static resource mapping");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");

    }
    /**
     * Extends the list of HTTP message converters to customize JSON serialization and deserialization.
     *
     * This method adds a customized MappingJackson2HttpMessageConverter that uses a custom JacksonObjectMapper
     * to control the format of JSON processing.
     *
     * @param converters A list of HttpMessageConverter objects to which the custom converter is added.
     */
    @Override
    protected void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0,messageConverter);
    }
}
