package de.wi2020sebgroup1.nachhilfe.accounting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class AccountingConfig {
	
	@SuppressWarnings("deprecation")
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    jedisConFactory.setHostName("redis-15342.c302.asia-northeast1-1.gce.cloud.redislabs.com");
	    jedisConFactory.setPort(15342);
	    jedisConFactory.setClientName("default");
	    jedisConFactory.setPassword("NkVhpRfJNWTSNYBkG6jtjxlAidQZNzvL");
	    return jedisConFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}

}
