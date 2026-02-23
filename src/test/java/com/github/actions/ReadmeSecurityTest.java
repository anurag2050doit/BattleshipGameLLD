package com.github.actions;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class ReadmeSecurityTest {

    @Test
    void testReadmeContainsSecurityBestPractices() throws Exception {
        String readmeContent = Files.readString(Paths.get("README.md"));
        assertTrue(readmeContent.contains("Environment Variables"), "README.md should include guidance on using environment variables.");
        assertTrue(readmeContent.contains("Secrets Management"), "README.md should include guidance on using secrets management tools.");
    }
}