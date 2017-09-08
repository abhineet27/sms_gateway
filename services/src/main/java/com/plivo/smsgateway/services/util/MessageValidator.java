/**
 * 
 */
package com.plivo.smsgateway.services.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plivo.smsgateway.domain.Message;
import com.plivo.smsgateway.domain.PhoneNumber;
import com.plivo.smsgateway.domain.Response;
import com.plivo.smsgateway.repo.PhoneNumberRepo;

/**
 * @author abhineet
 *
 */
@Service
public class MessageValidator {

	@Autowired
	private PhoneNumberRepo phoneNumberRepo;
	
	public Response validateInboundMessage(Message message){
		Response response = null;
		if(message.getTo() == null){
			response = new Response("", "to is missing");
			return response;
		}
		if(message.getTo().length() < 6 || message.getTo().length() > 16){
			response = new Response("", "to is invalid");
			return response;
		}
		PhoneNumber phoneNumber = phoneNumberRepo.findByNumber(message.getTo());
		if(null == phoneNumber){
			response = new Response("", "to parameter not found");
			return response;
		}
		return response;
	}
}
