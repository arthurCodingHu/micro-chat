package com.chat.web.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Map;

/**
 * @author franky
 * @date 2019/1/21 20:32
 */
@Service
public class JedisService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME_PX = "PX";
    private static final String SET_WITH_EXPIRE_TIME_EX = "EX";

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.get(key);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    public String set(String key, String value) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.set(key, value);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    public Long hSet(String key, String field, String value) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.hset(key, field, value);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    public String hGet(String key, String field) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.hget(key, field);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    public Map<String, String> hGetAll(String key) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.hgetAll(key);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }


    public String set(String key, String value,String nxxx,String expx,long time) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.set(key, value,nxxx,expx,time);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    /**
     *
     * @param key
     * @param value 需要增加的数
     * @return
     */
    public Long incrBy(String key, Long value) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.incrBy(key, value);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    /**
     *
     * @param key
     * @param value 需要減去的数
     * @return
     */
    public Long decrBy(String key, Long value) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.decrBy(key, value);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public Long del(String key) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.del(key);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    public Boolean exists(String key) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.exists(key);
            } catch (Throwable throwable) {
                throw throwable;
            }finally {
                if(null != jedis) {
                    jedis.close();
                }
            }
        }catch (Exception e){
            logger.error("redis命令exists执行失败+key:" + key);
            return null;
        }
    }

    /**
     * list 集合添加
     *
     * @param lKey
     * @param lValue
     * @return
     */
    public Long setList(String lKey, String lValue) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.lpush(lKey, lValue);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行lpush失败+key:" + lKey);
            return null;
        }
    }

    /**
     * list集合从右边弹出
     *
     * @param lKey
     * @return
     */
    public String rpopList(String lKey) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.rpop(lKey);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行lpop失败+key:" + lKey);
            return null;
        }
    }

    /**
     * list集合从左边弹出
     *
     * @param lKey
     * @return
     */
    public String lpopList(String lKey) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                return jedis.lpop(lKey);
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行lpop失败+key:" + lKey);
            return null;
        }
    }

    public String setExpire(String key, String value, int expire) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                String returnValue = jedis.set(key, value);
                jedis.expire(key, expire);
                return returnValue;
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return null;
        }
    }

    public boolean expire(String key, int expire) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                jedis.expire(key, expire);
                return true;
            } catch (Throwable throwable) {
                throw throwable;
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error("redis命令执行失败+key:" + key);
            return false;
        }
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME_EX, expireTime);
                if (LOCK_SUCCESS.equals(result)) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                logger.error("尝试给redis加锁失败");
                return false;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 释放分布式锁 通过lua实现
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        try {
            Jedis jedis = this.jedisPool.getResource();
            try {
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
                if (RELEASE_SUCCESS.equals(result)) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                logger.error("尝试解锁失败,requestId=" + requestId);
                return false;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            return false;
        }


    }

}
