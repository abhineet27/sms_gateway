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
	
	public Response validateInboundMessage(Message message, boolean isInbound){
		Response response = null;
		if(message.getFrom() == null){
			response = new Response("", "from is missing");
			return response;
		}
		if(message.getFrom().length() < 6 || message.getFrom().length() > 16){
			response = new Response("", "from is invalid");
			return response;
		}
		if(message.getTo() == null){
			response = new Response("", "to is missing");
			return response;
		}
		if(message.getTo().length() < 6 || message.getTo().length() > 16){
			response = new Response("", "to is invalid");
			return response;
		}
		if(message.getText() == null){
			response = new Response("", "text is missing");
			return response;
		}
		if(message.getText().length() < 1 || message.getText().length() > 120){
			response = new Response("", "text is invalid");
			return response;
		}
		if(isInbound){
			PhoneNumber phoneNumber = phoneNumberRepo.findByNumber(message.getTo());
			if(null == phoneNumber){
				response = new Response("", "to parameter not found");
				return response;
			}
		}else{
			PhoneNumber phoneNumber = phoneNumberRepo.findByNumber(message.getFrom());
			if(null == phoneNumber){
				response = new Response("", "from parameter not found");
				return response;
			}
		}
		
		return response;
	}
}
