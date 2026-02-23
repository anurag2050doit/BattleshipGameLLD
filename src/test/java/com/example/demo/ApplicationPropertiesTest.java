package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationPropertiesTest {

    @Test
    void testEnvironmentVariableSubstitution() {
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        assertNotNull(dbUsername, "DB_USERNAME environment variable should be set.");
        assertNotNull(dbPassword, "DB_PASSWORD environment variable should be set.");
    }

    @Test
    void testApplicationPropertiesIgnored() {
        boolean isIgnored = new java.io.File("src/main/resources/application.properties").exists();
        assertFalse(isIgnored, "application.properties should not be committed to the repository.");
    }
}