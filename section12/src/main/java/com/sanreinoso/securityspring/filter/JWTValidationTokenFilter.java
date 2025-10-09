package com.sanreinoso.securityspring.filter;

import com.sanreinoso.securityspring.constants.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class JWTValidationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJwt = request.getHeader(AppConstants.JWT_TOKEN_HEADER);
        if (tokenJwt != null) {
            try {
                log.debug("Validating token: {}", tokenJwt);
                Environment env = getEnvironment();
                if (env != null) {
                    String secret = env.getProperty("JWT_SECRET", AppConstants.JWT_SECRET_DEFAULT);
                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                    if (secretKey != null) {
                        this.setSecurityContext(tokenJwt, secretKey);
                    }
                }
            } catch (Exception ex) {
                    throw new BadCredentialsException("Invalid token received: " + ex);
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
        return request.getServletPath().equals("/user");
    }

    private void setSecurityContext(String tokenJwt, SecretKey secretKey) {
        Claims claims = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(tokenJwt).getPayload();

        String username = String.valueOf(claims.get("username"));
        String authorities = String.valueOf(claims.get("authorities"));
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

        Authentication auth  = new UsernamePasswordAuthenticationToken(username, null, authorityList);

        // Set the security context
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.debug("Authenticated user: {}, authorities: {}", username, authorities);
    }
}
