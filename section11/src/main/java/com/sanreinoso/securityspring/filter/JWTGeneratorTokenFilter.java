package com.sanreinoso.securityspring.filter;

import com.sanreinoso.securityspring.constants.AppConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTGeneratorTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Environment env = getEnvironment();
            if (env != null) {
                String secret = env.getProperty("JWT_SECRET", AppConstants.JWT_SECRET_DEFAULT);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                String token = Jwts.builder().issuer("sreinosh").subject("JWT Token")
                        .claim("username", auth.getName())
                        .claim("authorities", auth.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date(System.currentTimeMillis() + 3600_000))
                        .signWith(secretKey)
                        .compact();
                response.setHeader(AppConstants.JWT_TOKEN_HEADER, token);
            }

    }
        filterChain.doFilter(request, response);
    }

    /**
     * Decides if the filter should not be applied to a given request. In this case not applied to /user endpoint
     * @param request current HTTP request
     * @return true if the filter should not be applied, false otherwise
     * @throws ServletException exception
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user");
    }
}
