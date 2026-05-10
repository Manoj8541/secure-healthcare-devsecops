package com.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HealthcareApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthcareApiApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        try {
            String url = "http://localhost:9090";
            String os = System.getProperty("os.name").toLowerCase();

            Runtime runtime = Runtime.getRuntime();

            if (os.contains("win")) {
                // Windows
                runtime.exec(new String[]{"cmd", "/c", "start", url});
            } else if (os.contains("mac")) {
                // Mac
                runtime.exec(new String[]{"open", url});
            } else {
                // Linux
                runtime.exec(new String[]{"xdg-open", url});
            }

            System.out.println("Browser opened at: " + url);

        } catch (Exception e) {
            System.out.println("Could not open browser: " + e.getMessage());
        }
    }
}