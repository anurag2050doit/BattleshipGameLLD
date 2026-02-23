package com.github.actions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReadmeUpdateTest {

    @Test
    void testReadmeContainsExtendingGameSection() {
        String readmeContent = "..."; // Load README content as a string
        assertTrue(readmeContent.contains("## Extending the Game"), "README should contain 'Extending the Game' section.");
    }

    @Test
    void testAddingNewFeaturesGuide() {
        String readmeContent = "..."; // Load README content as a string
        assertTrue(readmeContent.contains("### Adding New Features"), "README should contain 'Adding New Features' guide.");
    }

    @Test
    void testModifyingStrategiesGuide() {
        String readmeContent = "..."; // Load README content as a string
        assertTrue(readmeContent.contains("### Modifying Strategies"), "README should contain 'Modifying Strategies' guide.");
    }

    @Test
    void testCustomizingBattlefieldGuide() {
        String readmeContent = "..."; // Load README content as a string
        assertTrue(readmeContent.contains("### Customizing the Battlefield"), "README should contain 'Customizing the Battlefield' guide.");
    }
}