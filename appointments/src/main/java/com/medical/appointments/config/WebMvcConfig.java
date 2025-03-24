package com.medical.appointments.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for web MVC settings
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Configure view controllers for simple redirects
     * 
     * @param registry the ViewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirect root to web home page
        registry.addRedirectViewController("/", "/api/web");
    }
} 