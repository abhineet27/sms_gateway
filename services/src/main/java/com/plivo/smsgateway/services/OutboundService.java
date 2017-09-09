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
public class OutboundService {

	private static final Logger logger = LoggerFactory.getLogger(OutboundService.class);
	
	@Autowired
	private MessageValidator messageValidator;
	@Autowired
	private RedisService redisService;
	
	public Response outbound(Message message) {
		Response response = null;
		try{
			response = messageValidator.validateInboundMessage(message,false);
			if(null != response){
				return response;
			}
			String from = message.getFrom();
			String to = message.getTo();
			//stop check
			String key = to+from;
			if(null != redisService.getValue(key)){
				response = new Response("","sms from "+from+" to "+to+" blocked by STOP request");
				return response;
			}
			//increment counter
			if(null == redisService.getCacheValue(from) || redisService.getCacheValue(from) < 50){
				redisService.incrementValue(from);
			}else{
				response = new Response("","limit reached for from "+from);
				return response;
			}
			response = new Response("outbound sms ok","");
			return response;
		}catch(Exception e){
			logger.error("Exception processing outbound sms",e);
			response = new Response("","unknown failure");
			return response;
		}
		
	}
}
