package com.github.actions;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class GitIgnoreTest {

    @Test
    void testApplicationPropertiesIgnored() throws Exception {
        String gitignoreContent = Files.readString(Paths.get(".gitignore"));
        assertTrue(gitignoreContent.contains("application.properties"), "application.properties should be listed in .gitignore");
    }
}