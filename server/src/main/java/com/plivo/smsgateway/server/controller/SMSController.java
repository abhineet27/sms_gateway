/**
 * 
 */
package com.plivo.smsgateway.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plivo.smsgateway.domain.Message;
import com.plivo.smsgateway.domain.Response;
import com.plivo.smsgateway.services.InboundService;

/**
 * @author abhineet
 *
 */
@RestController
public class SMSController extends BaseController {

	@Autowired
	private InboundService inboundService;
	
	@RequestMapping(path="/inbound/sms",method = RequestMethod.POST)
	public ResponseEntity<Response> inbound(Message message){
		
		return new ResponseEntity<Response>(inboundService.inbound(message),HttpStatus.OK);
	}
}
