package com.medical.appointments.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the ConfigValidator class.
 * 
 * These tests verify that the ConfigValidator properly validates
 * required environment variables in the production profile.
 * 
 * @author Freddy Johan Bautista Baquero
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class ConfigValidatorTest {

    /**
     * Tests that the ConfigValidator throws an IllegalStateException
     * when required environment variables are missing.
     * 
     * This test verifies that the validation logic correctly identifies
     * missing environment variables in the production environment.
     */
    @Test
    void validateConfigVars_shouldCheckForRequiredEnvironmentVariables() {
        // Direct test that skips mocking - just verifies it has required logic
        ConfigValidator configValidator = new ConfigValidator();
        
        // Should throw exception because environment variables are not set in test environment
        assertThrows(IllegalStateException.class, () -> {
            configValidator.validateConfigVars();
        });
    }
}