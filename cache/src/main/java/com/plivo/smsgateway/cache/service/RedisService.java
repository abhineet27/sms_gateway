/**
 * 
 */
package com.plivo.smsgateway.cache.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author abhineet
 *
 */
@Service
public class RedisService {

	private Lock lock = new ReentrantLock();
	
	@Value("${stop.expiry.time}")
	private Integer stopExpiryTime;
	
	@Value("${rate.limit.expiry.time}")
	private Integer rateLimitExpiryTime;
	
    @Autowired
    private RedisTemplate< String, Object > template;

    @Autowired
    private RedisTemplate< String, Integer > redisCacheTemplate;
    
    public Object getValue(final String key) {
        return template.opsForValue().get(key);
    }

    public void setValue(final String key, final String value) {
        template.opsForValue().set(key, value);

        // set a expire for a message
        template.expire(key, stopExpiryTime, TimeUnit.HOURS);
    }

    public Integer getCacheValue(final String key){
    	lock.lock();
    	Integer val = redisCacheTemplate.opsForValue().get(key);
    	lock.unlock();
    	return val;
    }
    
    public void incrementValue(final String key){
    	lock.lock();
    	if(null == redisCacheTemplate.boundValueOps(key).get()){
    		redisCacheTemplate.opsForValue().set(key, 1);
    		redisCacheTemplate.expire(key, rateLimitExpiryTime, TimeUnit.HOURS);
    	}else{
    		redisCacheTemplate.opsForValue().increment(key, 1);
    	}
    	lock.unlock();
    }
}
