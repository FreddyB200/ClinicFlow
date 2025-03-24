package com.medical.appointments.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ConfigValidatorTest {

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