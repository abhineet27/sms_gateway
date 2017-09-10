/**
 * 
 */
package com.plivo.smsgateway.server.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.plivo.smsgateway.repo.AccountRepository;

/**
 * @author abhineet
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);
	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	@Autowired
	private AccountRepository accountRepo;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.anyRequest().authenticated()
				.and().httpBasic()
				.authenticationEntryPoint(authEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		accountRepo.findAll().forEach(a->{try {
			auth.inMemoryAuthentication().withUser(a.getUsername()).password(a.getAuthId()).roles("");
		} catch (Exception e) {
			logger.error("Error configuring basic auth!",e);
			e.printStackTrace();
		}});
		
	}
}
