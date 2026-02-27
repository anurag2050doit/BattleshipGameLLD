package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.File;

class LogbackConfigurationTest {

    @Test
    void testDefaultLogLevel() throws Exception {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        context.reset();
        configurator.doConfigure(new File("src/main/resources/logback.xml"));

        String logLevel = context.getLogger("ROOT").getLevel().toString();
        assertEquals("INFO", logLevel, "Default log level should be INFO");
    }

    @Test
    void testDefaultLogPattern() throws Exception {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        context.reset();
        configurator.doConfigure(new File("src/main/resources/logback.xml"));

        String logPattern = context.getProperty("LOG_PATTERN");
        assertEquals("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n", logPattern, "Default log pattern should match");
    }

    @Test
    void testEnvironmentVariableOverrides() throws Exception {
        System.setProperty("LOG_LEVEL", "DEBUG");
        System.setProperty("LOG_PATTERN", "%d{HH:mm:ss} %-5level %msg%n");

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        context.reset();
        configurator.doConfigure(new File("src/main/resources/logback.xml"));

        String logLevel = context.getLogger("ROOT").getLevel().toString();
        String logPattern = context.getProperty("LOG_PATTERN");

        assertEquals("DEBUG", logLevel, "Log level should be overridden by environment variable");
        assertEquals("%d{HH:mm:ss} %-5level %msg%n", logPattern, "Log pattern should be overridden by environment variable");
    }
}