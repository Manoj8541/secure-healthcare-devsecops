package com.healthcare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SecurityLogger {

    private static final Logger logger =
            LoggerFactory.getLogger(SecurityLogger.class);

    // ─── Sanitize external input to prevent log injection ───────────────────
    // Strips newlines, carriage returns, tabs that attackers use to
    // forge fake log entries. CodeQL CWE-117 fix.
    private String sanitize(String input) {
        if (input == null) {
            return "null";
        }
        // Remove characters used in log injection attacks
        return input
                .replace("\n", "_")
                .replace("\r", "_")
                .replace("\t", "_")
                .replaceAll("[\\p{Cntrl}]", "_")  // all control characters
                .trim();
    }

    // Get client IP address
    private String getClientIP() {
        try {
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder
                            .getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String ip = request.getHeader("X-Forwarded-For");
                if (ip == null || ip.isEmpty()) {
                    ip = request.getRemoteAddr();
                }
                // Sanitize IP too — X-Forwarded-For is user-controlled
                return sanitize(ip);
            }
        } catch (Exception e) {
            return "unknown";
        }
        return "unknown";
    }

    // Successful login
    public void logSuccessfulLogin(String username) {
        logger.info(
                "[SECURITY][LOGIN_SUCCESS] User: {} | IP: {} | Status: ALLOWED",
                sanitize(username), getClientIP()
        );
    }

    // Failed login — brute force detection
    public void logFailedLogin(String username) {
        logger.warn(
                "[SECURITY][LOGIN_FAILED] User: {} | IP: {} | Status: BLOCKED",
                sanitize(username), getClientIP()
        );
    }

    // Unauthorized access
    public void logUnauthorizedAccess(String endpoint) {
        logger.warn(
                "[SECURITY][UNAUTHORIZED] Endpoint: {} | IP: {} | Status: BLOCKED",
                sanitize(endpoint), getClientIP()
        );
    }

    // Session created
    public void logSessionCreated(String username) {
        logger.info(
                "[SECURITY][SESSION_CREATED] User: {} | IP: {}",
                sanitize(username), getClientIP()
        );
    }

    // Session destroyed (logout)
    public void logSessionDestroyed(String username) {
        logger.info(
                "[SECURITY][SESSION_DESTROYED] User: {} | IP: {} | Status: LOGGED_OUT",
                sanitize(username), getClientIP()
        );
    }

    // API request
    public void logApiRequest(String method, String endpoint) {
        logger.info(
                "[SECURITY][API_REQUEST] Method: {} | Endpoint: {} | IP: {}",
                sanitize(method), sanitize(endpoint), getClientIP()
        );
    }

    // Patient data access — HIPAA compliance
    public void logPatientDataAccess(String username, String action, int patientId) {
        logger.info(
                "[SECURITY][PATIENT_DATA] User: {} | Action: {} | PatientID: {} | IP: {}",
                sanitize(username), sanitize(action), patientId, getClientIP()
        );
    }

    // Suspicious activity
    public void logSuspiciousActivity(String detail) {
        logger.error(
                "[SECURITY][SUSPICIOUS] Detail: {} | IP: {}",
                sanitize(detail), getClientIP()
        );
    }
}