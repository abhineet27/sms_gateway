/**
 * 
 */
package com.plivo.smsgateway.services.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plivo.smsgateway.domain.Account;
import com.plivo.smsgateway.domain.Message;
import com.plivo.smsgateway.domain.PhoneNumber;
import com.plivo.smsgateway.domain.Response;
import com.plivo.smsgateway.repo.AccountRepository;
import com.plivo.smsgateway.repo.PhoneNumberRepo;

/**
 * @author abhineet
 *
 */
@Service
public class MessageValidator {

	@Value("${message.from.min.length}")
	private Integer fromMinLength;
	
	@Value("${message.from.max.length}")
	private Integer fromMaxLength;
	
	@Value("${message.to.min.length}")
	private Integer toMinLength;
	
	@Value("${message.to.max.length}")
	private Integer toMaxLength;
	
	@Value("${message.text.min.length}")
	private Integer textMinLength;
	
	@Value("${message.text.max.length}")
	private Integer textMaxLength;
	
	@Autowired
	private PhoneNumberRepo phoneNumberRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	public Response validateInboundMessage(final Message message,final String userName, final boolean isInbound){
		Response response = null;
		if(message.getFrom() == null){
			response = new Response("", "from is missing");
			return response;
		}
		if(message.getFrom().trim().length() < fromMinLength || message.getFrom().trim().length() > fromMaxLength){
			response = new Response("", "from is invalid");
			return response;
		}
		if(message.getTo() == null){
			response = new Response("", "to is missing");
			return response;
		}
		if(message.getTo().trim().length() < toMinLength || message.getTo().trim().length() > toMaxLength){
			response = new Response("", "to is invalid");
			return response;
		}
		if(message.getText() == null){
			response = new Response("", "text is missing");
			return response;
		}
		if(message.getText().trim().length() < textMinLength || message.getText().trim().length() > textMaxLength){
			response = new Response("", "text is invalid");
			return response;
		}
		Account account = accountRepo.findByUsername(userName);
		if(isInbound){
			PhoneNumber phoneNumber = phoneNumberRepo.findByNumber(message.getTo().trim());
			if(null == phoneNumber || account.getId() != phoneNumber.getAccountId()){
				response = new Response("", "to parameter not found");
				return response;
			}
		}else{
			PhoneNumber phoneNumber = phoneNumberRepo.findByNumber(message.getFrom().trim());
			if(null == phoneNumber || account.getId() != phoneNumber.getAccountId()){
				response = new Response("", "from parameter not found");
				return response;
			}
		}
		
		return response;
	}
}
