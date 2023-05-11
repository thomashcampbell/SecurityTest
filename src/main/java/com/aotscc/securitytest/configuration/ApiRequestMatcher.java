package com.aotscc.securitytest.configuration;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class ApiRequestMatcher
        implements RequestMatcher {

    private final AntPathRequestMatcher antPathRequestMatcher;

    public ApiRequestMatcher() {
        this.antPathRequestMatcher = new AntPathRequestMatcher("/**");
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (antPathRequestMatcher.matches(request)) {
            return true;
        }

        return false; // Default to not matching
    }
}