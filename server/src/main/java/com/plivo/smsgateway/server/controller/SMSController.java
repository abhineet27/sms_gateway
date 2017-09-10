/**
 * 
 */
package com.plivo.smsgateway.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.plivo.smsgateway.domain.Message;
import com.plivo.smsgateway.domain.Response;
import com.plivo.smsgateway.server.auth.config.IAuthenticationFacade;
import com.plivo.smsgateway.services.InboundService;
import com.plivo.smsgateway.services.OutboundService;

/**
 * @author abhineet
 *
 */
@RestController
public class SMSController extends BaseController {

	@Autowired
	private InboundService inboundService;
	@Autowired
	private OutboundService outboundService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@RequestMapping(path="/inbound/sms",method = RequestMethod.POST)
	public ResponseEntity<Response> inbound(@RequestBody Message message){
		String userName = authenticationFacade.getAuthentication().getName();
		return new ResponseEntity<Response>(inboundService.inbound(message, userName),HttpStatus.OK);
	}
	
	@RequestMapping(path="/outbound/sms",method = RequestMethod.POST)
	public ResponseEntity<Response> outbound(@RequestBody Message message){
		String userName = authenticationFacade.getAuthentication().getName();
		return new ResponseEntity<Response>(outboundService.outbound(message, userName),HttpStatus.OK);
	}
}
