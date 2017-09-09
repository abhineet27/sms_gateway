/**
 * 
 */
package com.plivo.smsgateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plivo.smsgateway.cache.service.RedisService;

/**
 * @author abhineet
 *
 */
@Service
public class UtilService {

	@Autowired
	private RedisService redisService;
	
	public String getRedisValue(String key){
		return (String)redisService.getValue(key);
	}
}
