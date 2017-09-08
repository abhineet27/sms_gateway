/**
 * 
 */
package com.plivo.smsgateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.plivo.smsgateway.domain.Message;
import com.plivo.smsgateway.domain.Response;
import com.plivo.smsgateway.services.util.MessageValidator;

/**
 * @author abhineet
 *
 */
@Service
public class InboundService {

	@Autowired
	private MessageValidator messageValidator;
	
	public Response inbound(Message message) {
		Response response = messageValidator.validateInboundMessage(message);
		if(null != response){
			return response;
		}
		response = new Response("inbound sms ok","");
		return response;
	}
}
