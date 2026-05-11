package com.healthcare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class VulnerableDemo {

    // Hardcoded secrets
    // Detected by Gitleaks
    private static final String AWS_KEY =
        "AKIAIOSFODNN7EXAMPLE";
    private static final String AWS_SECRET =
        "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    private static final String GITHUB_TOKEN =
        "ghp_exampleTokenThatShouldNeverBeCommitted";
    private static final String DB_PASSWORD =
        "SuperSecret123!";

    // SQL Injection
    // CodeQL pattern: java/sql-injection
    @GetMapping("/demo/sql")
    public String sqlInjection(
            HttpServletRequest request) throws Exception {
        String userInput = request.getParameter("name");
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost/db",
            "root",
            DB_PASSWORD
        );
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT * FROM users WHERE name = '"
                + userInput + "'"
        );
        return "done";
    }

    // XSS Vulnerability
    // CodeQL pattern: java/xss
    @GetMapping("/demo/xss")
    public void xssVulnerability(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String userInput = request.getParameter("input");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("Hello " + userInput);
        writer.println("</body></html>");
    }

    // Path Traversal
    // CodeQL pattern: java/path-injection
    @GetMapping("/demo/file")
    public String pathTraversal(
            HttpServletRequest request) throws IOException {
        String filename = request.getParameter("file");
        File file = new File(
            "/var/data/" + filename
        );
        BufferedReader reader = new BufferedReader(
            new FileReader(file)
        );
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    // Command Injection
    // CodeQL pattern: java/command-line-injection
    @GetMapping("/demo/exec")
    public String commandInjection(
            HttpServletRequest request) throws IOException {
        String cmd = request.getParameter("cmd");
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd);
        return "Executed";
    }

    // Insecure Random
    // CodeQL pattern: java/insecure-randomness
    @GetMapping("/demo/random")
    public String insecureRandom() {
        java.util.Random random = new java.util.Random();
        int sessionId = random.nextInt();
        return "Session: " + sessionId;
    }

    // Hardcoded Crypto Key
    // CodeQL pattern: java/hardcoded-credential-api-call
    private static final byte[] SECRET_KEY =
        "MyHardcodedKey123".getBytes();

    @GetMapping("/demo/crypto")
    public String hardcodedCrypto() {
        return "Using hardcoded key: "
            + new String(SECRET_KEY);
    }
}
