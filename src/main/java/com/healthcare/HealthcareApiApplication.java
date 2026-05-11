package com.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthcareApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthcareApiApplication.class, args);
    }

    // Browser auto-open removed:
    // 1. Caused CodeQL "Executing command with relative path" alerts
    // 2. Runtime.exec() with relative paths is a security risk
    // 3. Not needed — access app manually at http://localhost:9090
}