/**
 * 
 */
package com.plivo.smsgateway.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author abhineet
 *
 */
@RestController
public class HelloController extends BaseController {
	
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String hello() {
        return "Greetings from sms gateway!";
    }
}
