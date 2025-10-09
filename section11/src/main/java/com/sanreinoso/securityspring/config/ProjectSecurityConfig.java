package com.sanreinoso.securityspring.config;

import com.sanreinoso.securityspring.exceptionhandling.CustomAccessDeniedHandler;
import com.sanreinoso.securityspring.exceptionhandling.CustomAuthenticationEntryPoint;
import com.sanreinoso.securityspring.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization","X-Total-Count"));
                config.setMaxAge(3600L);
                return config;
            }
        }));

        http.sessionManagement(smc -> smc
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .invalidSessionUrl("/invalidSession")
                    .maximumSessions(10).maxSessionsPreventsLogin(true))
            .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) //Only HTTP
            .csrf(csrfConfig -> csrfConfig
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                    .ignoringRequestMatchers("/contact", "/register"))
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTGeneratorTokenFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTValidationTokenFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
                    .requestMatchers("/myBalance", "/myCards", "myLoans").hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE", "VIEWCARDS", "VIEWLOANS")
                    .requestMatchers("/user").authenticated()
                .requestMatchers("/register","/contact", "/notices", "/error", "/invalidSession").permitAll()
                .requestMatchers("/actuator/**").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(basicConfig
                -> basicConfig.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}