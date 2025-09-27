package com.sanreinoso.securityspring.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;


public class RequestValidationBeforeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.nonNull(header) && header.startsWith("Basic ")) {
            String base64Credentials = header.substring("Basic ".length());
            String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
            int colonIndex = credentials.indexOf(":");
            String email = colonIndex != -1 ? credentials.substring(0, colonIndex) : credentials;

            if (email.toLowerCase().contains("test")) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
