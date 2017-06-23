package com.ittx.java1608.redis.spring;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

public class UserDaoImplTest {
	RedisTemplate<String, Object> redisTemplate;

	@Before
	public void setUp() throws Exception {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-redis.xml");
		redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
	}

	@Test
	public void testAddKey() {
		// 添加一个 key
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		value.set("lp", "hello word");
		// 获取 这个 key 的值
		System.out.println(value.get("lp"));
	}

	@Test
	public void testHashMap() {
		// 添加 一个 hash集合
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "lp");
		map.put("age", "26");
		hash.putAll("lpMap", map);
		// 获取 map
		System.out.println(hash.entries("lpMap"));
	}

	@Test
	public void testList() {
		// 添加 一个 list 列表
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush("lpList", "lp");
		list.rightPush("lpList", "26");
		// 输出 list
		System.out.println(list.range("lpList", 0, 1));
	}

	@Test
	public void testSet() {
		// 添加 一个 set 集合
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add("lpSet", "lp");
		set.add("lpSet", "26");
		set.add("lpSet", "178cm");
		// 输出 set 集合
		System.out.println(set.members("lpSet"));
	}

	@Test
	public void testOperationSet() {
		// 添加有序的 set 集合
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add("lpZset", "lp", 0);
		zset.add("lpZset", "26", 1);
		zset.add("lpZset", "178cm", 2);
		// 输出有序 set 集合
		System.out.println(zset.rangeByScore("lpZset", 0, 2));
	}

}
