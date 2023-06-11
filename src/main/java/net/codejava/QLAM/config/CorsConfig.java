package net.codejava.QLAM.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer CorsConfiguerer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry register)
            {
                register.addMapping("/**").allowedMethods("GET", "POST", "PUTCH", "PUT", "DELETE").allowedOrigins("http://localhost:3000").allowedHeaders("*");
            }
        };
    }
}
