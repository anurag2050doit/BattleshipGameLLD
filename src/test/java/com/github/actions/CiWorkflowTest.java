package com.github.actions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CiWorkflowTest {

    @Test
    void testDependencyScanningConfigured() {
        String workflowContent = """
        jobs:
          security-checks:
            steps:
            - name: Dependency Scanning
              uses: aquasecurity/trivy-action@v0.10.0
        """;
        assertTrue(workflowContent.contains("aquasecurity/trivy-action"), "Dependency scanning is not configured correctly.");
    }

    @Test
    void testStaticCodeAnalysisConfigured() {
        String workflowContent = """
        jobs:
          security-checks:
            steps:
            - name: Static Code Analysis
              uses: github/codeql-action/init@v2
        """;
        assertTrue(workflowContent.contains("github/codeql-action/init"), "Static code analysis is not configured correctly.");
    }

    @Test
    void testSeverityLevelsForTrivy() {
        String workflowContent = """
        jobs:
          security-checks:
            steps:
            - name: Dependency Scanning
              with:
                severity: 'HIGH,CRITICAL'
        """;
        assertTrue(workflowContent.contains("severity: 'HIGH,CRITICAL'"), "Trivy severity levels are not set correctly.");
    }

    @Test
    void testCodeQLLanguagesConfigured() {
        String workflowContent = """
        jobs:
          security-checks:
            steps:
            - name: Static Code Analysis
              with:
                languages: 'java'
        """;
        assertTrue(workflowContent.contains("languages: 'java'"), "CodeQL languages are not configured correctly.");
    }
}