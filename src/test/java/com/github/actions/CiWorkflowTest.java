package com.github.actions;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class CiWorkflowTest {

    @Test
    void testCiWorkflowFileExists() throws Exception {
        assertTrue(Files.exists(Paths.get(".github/workflows/ci.yml")), "CI workflow file should exist");
    }

    @Test
    void testCiWorkflowContent() throws Exception {
        String content = Files.readString(Paths.get(".github/workflows/ci.yml"));
        assertTrue(content.contains("name: Java CI"), "Workflow should have the correct name");
        assertTrue(content.contains("java-version: '11'"), "Workflow should use Java 11");
        assertTrue(content.contains("./gradlew build"), "Workflow should include Gradle build step");
        assertTrue(content.contains("./gradlew test"), "Workflow should include Gradle test step");
    }
}