package com.soveldaja.kassa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // apply to all routes
                .allowedOrigins("http://localhost:5173") // allow frontend origin
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true); // optional, needed if using cookies/auth
    }
}