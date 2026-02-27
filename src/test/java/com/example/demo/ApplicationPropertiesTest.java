package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

class ApplicationPropertiesTest {

    @Test
    void testDefaultProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/application.properties"));

        assertEquals("INFO", properties.getProperty("LOG_LEVEL"), "Default LOG_LEVEL should be INFO");
        assertEquals("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n", properties.getProperty("LOG_PATTERN"), "Default LOG_PATTERN should match");
    }
}