package com.github.actions;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class ReadmeUpdateTest {

    @Test
    void testReadmeMentionsCi() throws Exception {
        String content = Files.readString(Paths.get("README.md"));
        assertTrue(content.contains("Continuous Integration"), "README should mention Continuous Integration");
        assertTrue(content.contains("GitHub Actions"), "README should mention GitHub Actions");
        assertTrue(content.contains("main branch"), "README should specify the main branch for CI");
    }
}