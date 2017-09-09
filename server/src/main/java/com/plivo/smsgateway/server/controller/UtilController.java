/**
 * 
 */
package com.plivo.smsgateway.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plivo.smsgateway.services.UtilService;

/**
 * @author abhineet
 *
 */
@RestController
public class UtilController extends BaseController {
	
	@Autowired
	private UtilService utilService;
	
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String hello() {
        return "Greetings from sms gateway!";
    }
    
    @RequestMapping(path="/util/value",method = RequestMethod.GET)
	public ResponseEntity<String> getRedisValue(@RequestParam(value="key",required=true) String key){
		return new ResponseEntity<>(utilService.getRedisValue(key),HttpStatus.OK);
	}
}
