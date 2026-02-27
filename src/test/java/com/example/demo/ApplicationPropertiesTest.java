package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationPropertiesTest {

    @Test
    void testEnvironmentVariablePlaceholders() {
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        assertNotNull(dbUrl, "DB_URL environment variable should not be null");
        assertNotNull(dbUsername, "DB_USERNAME environment variable should not be null");
        assertNotNull(dbPassword, "DB_PASSWORD environment variable should not be null");
    }

    @Test
    void testInvalidEnvironmentVariables() {
        System.clearProperty("DB_URL");
        System.clearProperty("DB_USERNAME");
        System.clearProperty("DB_PASSWORD");

        assertNull(System.getenv("DB_URL"), "DB_URL should be null when not set");
        assertNull(System.getenv("DB_USERNAME"), "DB_USERNAME should be null when not set");
        assertNull(System.getenv("DB_PASSWORD"), "DB_PASSWORD should be null when not set");
    }
}