package com.example.demo.desacoplamiento.config;

import com.example.demo.desacoplamiento.interceptors.ApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApiInterceptor apiInterceptor;

    // Inyectamos nuestro interceptor
    public WebConfig(ApiInterceptor apiInterceptor) {
        this.apiInterceptor = apiInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registramos el interceptor en Spring
        // Le indicamos que actúe sobre cualquier ruta que empiece con /api/
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/**"); 
    }
}
