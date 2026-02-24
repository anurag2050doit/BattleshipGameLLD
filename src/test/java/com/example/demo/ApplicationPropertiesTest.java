package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationPropertiesTest {

    @Test
    void testEnvironmentVariableSubstitution() {
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");
        String apiKey = System.getenv("API_KEY");

        assertNotNull(dbUsername, "DB_USERNAME environment variable should not be null");
        assertNotNull(dbPassword, "DB_PASSWORD environment variable should not be null");
        assertNotNull(apiKey, "API_KEY environment variable should not be null");
    }

    @Test
    void testSensitiveDataNotHardcoded() {
        String propertiesContent = "db.username=${DB_USERNAME}\ndb.password=${DB_PASSWORD}\napi.key=${API_KEY}";

        assertFalse(propertiesContent.contains("hardcoded_username"), "Sensitive data should not be hardcoded");
        assertFalse(propertiesContent.contains("hardcoded_password"), "Sensitive data should not be hardcoded");
        assertFalse(propertiesContent.contains("hardcoded_api_key"), "Sensitive data should not be hardcoded");
    }
}