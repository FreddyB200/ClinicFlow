package com.medical.appointments.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration class for Spring DevTools.
 * This enables hot reloading in development mode.
 */
@Configuration
@Profile("dev")
public class DevToolsConfig {
    // The presence of this class enables Spring DevTools in dev profile
    // No additional configuration needed as Spring Boot auto-configures DevTools
}
