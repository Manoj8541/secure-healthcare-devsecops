package com.healthcare;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEventListener {

    private final SecurityLogger securityLogger;

    public SecurityEventListener(SecurityLogger securityLogger) {
        this.securityLogger = securityLogger;
    }

    // Fires on successful login
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        securityLogger.logSuccessfulLogin(username);
    }

    // Fires on failed login
    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        String username = event.getAuthentication().getName();
        securityLogger.logFailedLogin(username);
    }

    // Fires on logout/session destroy
    @EventListener
    public void onSessionDestroyed(HttpSessionDestroyedEvent event) {
        event.getSecurityContexts().forEach(context -> {
            String username = context.getAuthentication().getName();
            securityLogger.logSessionDestroyed(username);
        });
    }
}