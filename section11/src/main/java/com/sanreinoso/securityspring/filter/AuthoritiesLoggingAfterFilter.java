package com.sanreinoso.securityspring.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.info("User: {} - Authorities: {}", auth.getName(), auth.getAuthorities().toString());
        }
        chain.doFilter(request, response);
    }
}
