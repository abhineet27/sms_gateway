/**
 * 
 */
package com.plivo.smsgateway.cache.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author abhineet
 *
 */
@Service
public class RedisService {

	private Lock lock = new ReentrantLock();
	
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
        template.expire(key, 1, TimeUnit.MINUTES);
    }

    public Integer getCacheValue(final String key){
    	return redisCacheTemplate.opsForValue().get(key);
    }
    
    public void incrementValue(final String key){
    	lock.lock();
    	if(null == redisCacheTemplate.boundValueOps(key).get()){
    		redisCacheTemplate.opsForValue().set(key, 1);
    		redisCacheTemplate.expire(key, 1, TimeUnit.MINUTES);
    	}else{
    		redisCacheTemplate.opsForValue().increment(key, 1);
    	}
    	lock.unlock();
    }
}
