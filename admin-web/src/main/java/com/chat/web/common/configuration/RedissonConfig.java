//package com.chat.web.common.configuration;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
///**
// * @author franky
// * @description redisson 配置
// * @date 2019/1/13
// */
//@Configuration
//public class RedissonConfig {
//
//    @Value("${redis.host}")
//    private String host;
//
//    @Value("${redis.port}")
//    private String port;
//
//    @Value("${redis.password}")
//    private String password;
//
//
//    @Bean
//    public RedissonClient getRedisson(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://" + host +":" + port).setPassword(password).setConnectionMinimumIdleSize(8);
//        //主从模式
////        config.useMasterSlaveServers().setMasterAddress("redis://" + host +":" + port).setPassword(password).addSlaveAddress(new String[]{"",""});
//        //哨兵模式
////        config.useSentinelServers()
//        // 集群模式
////        String[] serverArray = redisProperties.getClusterNodes().split(",");
////        for(int i = 0 ;i < serverArray.length; i++) {
////            serverArray[i] = "redis://" + serverArray[i];
////        }
////        config.useClusterServers().addNodeAddress(serverArray);
//        RedissonClient redissonClient = Redisson.create(config);
//        return redissonClient;
//    }
//
//    @Bean("springSessionDefaultRedisSerializer")
//    public RedisSerializer<Object> defaultRedisSerializer(){
//        return valueSerializer();
//    }
//
//    private RedisSerializer<Object> valueSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }
//
//}
