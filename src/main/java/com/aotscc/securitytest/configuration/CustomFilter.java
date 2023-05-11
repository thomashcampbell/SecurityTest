package com.aotscc.securitytest.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomFilter
        extends GenericFilterBean {

    public CustomFilter(HttpSecurity httpSecurity) {

    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        chain.doFilter(request, response);
    }
}