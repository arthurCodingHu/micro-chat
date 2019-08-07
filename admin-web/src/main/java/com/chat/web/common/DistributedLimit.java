package com.chat.web.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisNoScriptException;

import java.util.Collections;

/**
 * @author franky
 * @description 分布式限流
 * @date 2019-04-18
 */
@Slf4j
@Component
public class DistributedLimit {

    private static final Long RELEASE_FAILURE = 0L;

    public static String limitScript_lua = null;

    @Autowired
    private JedisPool jedisPool;

    public Boolean distributedLock(String key, String limit) {
        Jedis jedis = this.jedisPool.getResource();
        String limitScript = initScript();
        Long result;
        try {
            result = (Long) jedis.eval(limitScript, Collections.singletonList(key), Collections.singletonList(limit));
        } catch (JedisNoScriptException e) {
            log.error("error jedisNoScriptException, try again .....");
//            ScriptCommon.storeScript(limitScript, key, jedisCluster);
            result = (Long) jedis.eval(limitScript, Collections.singletonList(key), Collections.singletonList(limit));
        } catch (Exception e) {
            log.error("error", e);
            return false;
        } finally {
            jedis.close();
        }
        if (RELEASE_FAILURE == result) {
            return false;
        }
        return true;
    }

    private static String initScript() {
        if (null == limitScript_lua) {
            StringBuilder limitScript = new StringBuilder();
            //lua 下标从 1 开始
            // 限流 key
            limitScript.append("local key = KEYS[1] ");
            //限流大小
            limitScript.append("local limit = tonumber(ARGV[1]) ");

            // 获取当前流量大小
            limitScript.append("local currentLimit = tonumber(redis.call('get', key) or \"0\") ");

            limitScript.append("if currentLimit + 1 > limit then ");
            // 达到限流大小 返回
            limitScript.append("return 0; ");
            //如果是第一次设置limit值，则设置该值的过期时间
            limitScript.append("elseif currentLimit == 0 then ");

            // 没有达到阈值 value + 1
            limitScript.append("redis.call(\"INCRBY\", key, 1) ");
            // EXPIRE后边的单位是秒
            limitScript.append("redis.call(\"EXPIRE\", key, 5) ");
            limitScript.append("return currentLimit + 1 ");

            //否则直接将limit值加1
            limitScript.append("else ");
            limitScript.append("redis.call(\"INCRBY\", key, 1) ");
            limitScript.append("return currentLimit + 1 ");
            limitScript.append("end ");
            limitScript_lua = limitScript.toString();
        }
        return limitScript_lua;
    }

}
