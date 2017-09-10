/**
 * 
 */
package com.plivo.smsgateway.server.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import com.plivo.smsgateway.server.Application;

/**
 * @author abhineet
 *
 */
@Configuration
public class ServletConfig extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
