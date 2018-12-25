package com.zhefan.yummy.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheUtil {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 批量删除对应的value
	 *
	 * @param keys 数组Key
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 *
	 * @param pattern pattern
	 */
	@SuppressWarnings("unchecked")
	public void removePattern(final String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 *
	 * @param key key
	 */
	@SuppressWarnings("unchecked")
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 *
	 * @param key key
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 *
	 * @param key key
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	public Object get(final String key) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		return operations.get(key);
	}

	/**
	 * 写入缓存 默认缓存：7200秒 2小时
	 *
	 * @param key   key
	 * @param value value
	 * @return boolean
	 */
	public boolean set(final String key, Object value) {
		return set(key, value, 7200L);
	}

	/**
	 * 写入缓存
	 *
	 * @param key        key
	 * @param value      value
	 * @param expireTime 过期时间
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean set(final String key, Object value, Long expireTime) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		operations.set(key, value);
		return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}
}
