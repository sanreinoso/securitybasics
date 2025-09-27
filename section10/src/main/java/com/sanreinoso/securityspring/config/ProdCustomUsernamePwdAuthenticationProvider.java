package com.sanreinoso.securityspring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class ProdCustomUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService customUserDetails;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String userPwd = authentication.getCredentials().toString();
        UserDetails userDetails = customUserDetails.loadUserByUsername(authentication.getName());

        if (passwordEncoder.matches(userPwd, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, userPwd, userDetails.getAuthorities());
        }
        else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
