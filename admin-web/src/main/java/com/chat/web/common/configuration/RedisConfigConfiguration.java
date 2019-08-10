package com.chat.web.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@EnableAutoConfiguration
@Configuration
public class RedisConfigConfiguration {

    @Value("${redis.maxTotal}")
    private String maxTotal;

    @Value("${redis.maxIdle}")
    private String maxIdle;

    @Value("${redis.maxWaitMillis}")
    private String maxWaitMillis;

    @Value("${redis.testOnBorrow}")
    private String testOnBorrow;

    @Value("${redis.testOnReturn}")
    private String testOnReturn;

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.timeout}")
    private String timeout;

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.valueOf(maxTotal));
        jedisPoolConfig.setMaxIdle(Integer.valueOf(maxIdle));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(maxWaitMillis));
        jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        jedisPoolConfig.setTestOnReturn(Boolean.valueOf(testOnReturn));
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,Integer.valueOf(port),Integer.valueOf(timeout),password);
        return jedisPool;
    }


//    @Bean
//    public JedisPoolConfig poolConfigBean(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(Integer.valueOf(maxIdle));
//        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(maxWaitMillis));
//        jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
//        return jedisPoolConfig;
//    }
//
//    @Bean
//    public JedisConnectionFactory connectionFactoryBean(JedisPoolConfig poolConfigBean){
//
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
//        connectionFactory.setHostName(host);
//        connectionFactory.setPassword(password);
//        connectionFactory.setPort(Integer.valueOf(port));
//        connectionFactory.setPoolConfig(poolConfigBean);
//        return connectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String,String> stringRedisTemplate(JedisConnectionFactory connectionFactoryBean) {
//        RedisTemplate<String, String> stringRedisTemplate = new RedisTemplate();
//        stringRedisTemplate.setConnectionFactory(connectionFactoryBean);
//        RedisSerializerUtils.customizeRedisTemplate(stringRedisTemplate, RedisSerializerUtils.STRING_REDIS_SERIALIZER);
//        return stringRedisTemplate;
//    }
//
//    @Bean
//    public RedisTemplate<String,Object> beanRedisTemplate(JedisConnectionFactory connectionFactoryBean) {
//        RedisTemplate<String, Object> beanRedisTemplate = new RedisTemplate();
//        beanRedisTemplate.setConnectionFactory(connectionFactoryBean);
//        RedisSerializerUtils.customizeRedisTemplate(beanRedisTemplate, RedisSerializerUtils.FST_REDIS_SERIALIZER);
//        return beanRedisTemplate;
//    }

}
