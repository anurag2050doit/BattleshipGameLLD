package com.github.actions;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class GitIgnoreTest {

    @Test
    void testGitIgnoreContainsSensitiveFiles() throws Exception {
        String gitIgnoreContent = Files.readString(Paths.get(".gitignore"));

        assertTrue(gitIgnoreContent.contains("*.properties"), "*.properties should be included in .gitignore");
        assertTrue(gitIgnoreContent.contains("*.secrets"), "*.secrets should be included in .gitignore");
    }
}