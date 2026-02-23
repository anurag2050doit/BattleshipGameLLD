package com.github.actions;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class GitIgnoreTest {

    @Test
    void testGitIgnoreContainsApplicationProperties() throws Exception {
        String gitIgnoreContent = Files.readString(Paths.get(".gitignore"));
        assertTrue(gitIgnoreContent.contains("application.properties"), "application.properties should be listed in .gitignore.");
    }
}