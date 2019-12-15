package com.cn.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: MuYaHai
 * Date: 2019/12/5, Time: 18:50
 */
@Component
@Slf4j
public class JedisUtil {

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取jedis
     *
     * @return
     */
    private Jedis getRedis() {
        return jedisPool.getResource();
    }

    /**
     * 设置
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        //方便关闭
        Jedis jedis = null;
        try {
            jedis = getRedis();
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            return null;
        } finally {
            close(jedis);
        }

    }

    /**
     * 设值并加上过期时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public String set(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getRedis();
            return jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} expireTime:{} error", key, value, expireTime, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getRedis();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getRedis();
            return jedis.del(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getRedis();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            log.error("exists key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 单独设计过期时间
     * @param key
     * @param expireTime
     * @return
     */
    public Long expire(String key,int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getRedis();
            return jedis.expire(key, expireTime);
        } catch (Exception e) {
            log.error("expire key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getRedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("ttl key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }
    /**
     *关闭jedis
     * @param jedis
     */
    public void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
