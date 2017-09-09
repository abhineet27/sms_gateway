/**
 * 
 */
package com.plivo.smsgateway.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author abhineet
 *
 */
@Configuration
public class RedisCacheConfig {
	@Value("${redis.host}")
	private String redisHost;
	
	@Value("${redis.port}")
	private Integer redisPort;
	
	@SuppressWarnings("deprecation")
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
	    jedisConFactory.setHostName(redisHost);
	    jedisConFactory.setPort(redisPort);
	    return jedisConFactory;
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    template.setKeySerializer( new StringRedisSerializer() );
	    template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	    template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	    return template;
	}
	
	@Bean
	public RedisTemplate<String, Integer> redisCacheTemplate() {
	    RedisTemplate<String, Integer> template = new RedisTemplate<String, Integer>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    template.setKeySerializer( new StringRedisSerializer() );
	    template.setHashValueSerializer( new GenericToStringSerializer< Integer >( Integer.class ) );
	    template.setValueSerializer( new GenericToStringSerializer< Integer >( Integer.class ) );
	    return template;
	}
}
