package com.healthcare;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ===== CSRF Protection =====
                // Disabled for simplicity but in production MUST be enabled
                .csrf(csrf -> csrf.disable())

                // ===== SECURITY HEADERS =====
                // Protects against XSS, Clickjacking, Sniffing
                .headers(headers -> headers
                        // Prevents browser from storing page in cache
                        .cacheControl(cache -> cache.disable())
                        // Prevents clickjacking attacks
                        .frameOptions(frame -> frame.deny())
                        // Prevents MIME type sniffing
                        .contentTypeOptions(content -> {})
                        // XSS Protection
                        .xssProtection(xss -> {})
                        // Strict Transport Security (HTTPS only)
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .maxAgeInSeconds(31536000)
                        )
                        // Referrer Policy
                        .referrerPolicy(referrer -> referrer
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER)
                        )
                )

                // ===== URL AUTHORIZATION =====
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/login").permitAll()
                        .anyRequest().authenticated()
                )

                // ===== LOGIN CONFIG =====
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                // ===== LOGOUT CONFIG =====
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        // Destroy session completely
                        .invalidateHttpSession(true)
                        // Clear authentication object
                        .clearAuthentication(true)
                        // Delete session cookie from browser
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                // ===== SESSION HARDENING =====
                .sessionManagement(session -> session
                        // Session expires after 10 minutes of inactivity
                        .invalidSessionUrl("/login")
                        // Prevent Session Fixation Attack
                        .sessionFixation(fixation -> fixation.newSession())
                        // Only 1 session per user allowed
                        .maximumSessions(1)
                        // Block new login if session already exists
                        .maxSessionsPreventsLogin(false)
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password123"))
                .roles("ADMIN")
                .build();

        UserDetails doctor = User.builder()
                .username("doctor")
                .password(passwordEncoder().encode("doctor123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, doctor);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt with strength 12 = very strong hashing
        return new BCryptPasswordEncoder(12);
    }

    // Required for maximumSessions to work properly
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}