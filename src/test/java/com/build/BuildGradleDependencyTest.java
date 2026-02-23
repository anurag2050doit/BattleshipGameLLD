package com.build;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class BuildGradleDependencyTest {

    @Test
    void testDependenciesHaveExactVersions() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("build.gradle"));
        Pattern dependencyPattern = Pattern.compile(".*:\d+\.\d+\.\d+.*");

        boolean allDependenciesHaveVersions = lines.stream()
            .filter(line -> line.trim().startsWith("implementation") || line.trim().startsWith("testImplementation"))
            .allMatch(line -> dependencyPattern.matcher(line).find());

        assertTrue(allDependenciesHaveVersions, "All dependencies must specify exact versions.");
    }

    @Test
    void testNoDynamicVersionsUsed() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("build.gradle"));
        boolean hasDynamicVersions = lines.stream()
            .anyMatch(line -> line.contains("+") && (line.contains("implementation") || line.contains("testImplementation")));

        assertFalse(hasDynamicVersions, "Dynamic versions (e.g., 1.0.+) should not be used.");
    }

    @Test
    void testJUnitVersionIsUpToDate() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("build.gradle"));
        boolean junitVersionCorrect = lines.stream()
            .anyMatch(line -> line.contains("org.junit.jupiter:junit-jupiter:5.9.3"));

        assertTrue(junitVersionCorrect, "JUnit version should be 5.9.3.");
    }
}