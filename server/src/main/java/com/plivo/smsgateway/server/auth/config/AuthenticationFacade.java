/**
 * 
 */
package com.plivo.smsgateway.server.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author abhineet
 *
 */
@Component
public class AuthenticationFacade implements IAuthenticationFacade {
 
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
