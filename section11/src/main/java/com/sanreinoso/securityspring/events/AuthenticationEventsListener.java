package com.sanreinoso.securityspring.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEventsListener {

    @EventListener
    void onSuccess(AuthenticationSuccessEvent successEvent) {
        log.info("Authentication success for user: {}", successEvent.getAuthentication().getName());
    }

    @EventListener
    void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
        log.error("Authentication failure for user: {} - Error: {}",
                failureEvent.getAuthentication().getName(), failureEvent.getException().getMessage());
    }

}
