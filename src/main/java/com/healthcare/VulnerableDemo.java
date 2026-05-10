package com.healthcare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

@RestController
public class VulnerableDemo {

    private static final String AWS_KEY =
            "AKIAIOSFODNN7EXAMPLE";
    private static final String AWS_SECRET =
            "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    private static final String GITHUB_TOKEN =
            "ghp_exampleTokenThatShouldNeverBeCommitted";
    private static final String DB_PASS =
            "SuperSecret123!";

    @GetMapping("/demo/sql")
    public String sqlInjection(
            @RequestParam String name) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hospital",
                    "root", DB_PASS
            );
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM patients "
                    + "WHERE name = '" + name + "'";
            stmt.executeQuery(query);
            return "Query: " + query;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/demo/cmd")
    public String commandInjection(
            @RequestParam String cmd) {
        try {
            Runtime.getRuntime().exec(cmd);
            return "Executed: " + cmd;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/demo/file")
    public String pathTraversal(
            @RequestParam String filename) {
        try {
            File file = new File(
                    "/app/data/" + filename
            );
            Scanner scanner = new Scanner(file);
            StringBuilder content =
                    new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
            scanner.close();
            return content.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}