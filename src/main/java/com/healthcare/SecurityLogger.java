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
                return ip;
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
                username, getClientIP()
        );
    }

    // Failed login — brute force detection
    public void logFailedLogin(String username) {
        logger.warn(
                "[SECURITY][LOGIN_FAILED] User: {} | IP: {} | Status: BLOCKED",
                username, getClientIP()
        );
    }

    // Unauthorized access
    public void logUnauthorizedAccess(String endpoint) {
        logger.warn(
                "[SECURITY][UNAUTHORIZED] Endpoint: {} | IP: {} | Status: BLOCKED",
                endpoint, getClientIP()
        );
    }

    // Session created
    public void logSessionCreated(String username) {
        logger.info(
                "[SECURITY][SESSION_CREATED] User: {} | IP: {}",
                username, getClientIP()
        );
    }

    // Session destroyed (logout)
    public void logSessionDestroyed(String username) {
        logger.info(
                "[SECURITY][SESSION_DESTROYED] User: {} | IP: {} | Status: LOGGED_OUT",
                username, getClientIP()
        );
    }

    // API request
    public void logApiRequest(String method, String endpoint) {
        logger.info(
                "[SECURITY][API_REQUEST] Method: {} | Endpoint: {} | IP: {}",
                method, endpoint, getClientIP()
        );
    }

    // Patient data access — HIPAA compliance
    public void logPatientDataAccess(String username, String action, int patientId) {
        logger.info(
                "[SECURITY][PATIENT_DATA] User: {} | Action: {} | PatientID: {} | IP: {}",
                username, action, patientId, getClientIP()
        );
    }

    // Suspicious activity
    public void logSuspiciousActivity(String detail) {
        logger.error(
                "[SECURITY][SUSPICIOUS] Detail: {} | IP: {}",
                detail, getClientIP()
        );
    }
}