/**
 * 
 */
package com.plivo.smsgateway.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plivo.smsgateway.cache.service.RedisService;
import com.plivo.smsgateway.domain.Message;
import com.plivo.smsgateway.domain.Response;
import com.plivo.smsgateway.services.util.MessageValidator;

/**
 * @author abhineet
 *
 */
@Service
public class InboundService {

	private static final Logger logger = LoggerFactory.getLogger(InboundService.class);
	
	@Autowired
	private MessageValidator messageValidator;
	
	@Autowired
	private RedisService redisService;
	
	@Value("#{'${stop.word.list}'.split(',')}")
	private List<String> stopWordList;
	
	public Response inbound(final Message message, final String userName) {
		Response response = null;
		try{
			response = messageValidator.validateInboundMessage(message, userName, true);
			if(null != response){
				return response;
			}
			String from = message.getFrom();
			String to = message.getTo();
			String text = message.getText();
			String key = from+to;
			//stop logic
			boolean containsStop = stopWordList.stream().anyMatch(w->text.contains(w));
			if(containsStop){
				redisService.setValue(key, "STOP");
			}
			response = new Response("inbound sms ok","");
			return response;
		}catch(Exception e){
			logger.error("Exception processing inbound sms",e);
			response = new Response("","unknown failure");
			return response;
		}
		
	}
}
