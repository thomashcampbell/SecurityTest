package com.aotscc.securitytest.configuration;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


public class ActuatorRequestMatcher
        implements RequestMatcher {

    private final AntPathRequestMatcher antPathRequestMatcher;

    public ActuatorRequestMatcher() {
        this.antPathRequestMatcher = new AntPathRequestMatcher("/actuator/**");
    }

    @Override
    public boolean matches(HttpServletRequest request) {

        // Exclude "/actuator/health" and "/actuator/info"
        if (antPathRequestMatcher.matches(request)) {
            String requestURI = request.getRequestURI();

            if ("/actuator/health".equals(requestURI) || "/actuator/info".equals(requestURI)) {
                return false; // Allow access
            } else {
                return true; // Exclude from matching
            }
        }

        return false; // Default to not matching
    }
}