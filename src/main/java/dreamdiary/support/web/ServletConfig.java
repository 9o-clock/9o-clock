package dreamdiary.support.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequiredArgsConstructor
@EnableWebMvc
@Configuration
public class ServletConfig implements WebMvcConfigurer {
    private final ObjectMapper objectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html", "favicon.ico")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/docs/**")
                .addResourceLocations("classpath:/static/docs/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public DispatcherServlet dispatcherServlet () {
        final DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}
