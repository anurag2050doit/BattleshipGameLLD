package com.github.actions;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class ReadmeSecurityTest {

    @Test
    void testReadmeContainsSecurityBestPractices() throws Exception {
        String readmeContent = Files.readString(Paths.get("README.md"));

        assertTrue(readmeContent.contains("Environment Variables"), "README should mention environment variables for managing secrets");
        assertTrue(readmeContent.contains("Secrets Management"), "README should mention secrets management tools");
        assertTrue(readmeContent.contains("Git Ignore"), "README should mention adding sensitive files to .gitignore");
    }
}