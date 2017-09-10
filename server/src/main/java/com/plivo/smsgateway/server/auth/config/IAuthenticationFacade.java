/**
 * 
 */
package com.plivo.smsgateway.server.auth.config;

import org.springframework.security.core.Authentication;

/**
 * @author abhineet
 *
 */
public interface IAuthenticationFacade {

	Authentication getAuthentication();
}
