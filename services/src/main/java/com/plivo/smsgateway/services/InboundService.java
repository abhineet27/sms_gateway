/**
 * 
 */
package com.plivo.smsgateway.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Response inbound(Message message) {
		Response response = null;
		try{
			response = messageValidator.validateInboundMessage(message,true);
			if(null != response){
				return response;
			}
			String from = message.getFrom();
			String to = message.getTo();
			String text = message.getText();
			String key = from+to;
			//stop logic
			if(text.contains("STOP") || text.contains("STOP\n") || text.contains("STOP\r") || text.contains("STOP\r\n")){
				redisService.setValue(key, "STOP");
			}
			//increment counter
			/*if(null == redisService.getCacheValue(from) || redisService.getCacheValue(from) < 50){
				redisService.incrementValue(from);
			}else{
				response = new Response("","limit reached for from "+from);
				return response;
			}*/
			response = new Response("inbound sms ok","");
			return response;
		}catch(Exception e){
			logger.error("Exception processing inbound sms",e);
			response = new Response("","unknown failure");
			return response;
		}
		
	}
}
