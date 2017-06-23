package com.ittx.java1608.redis.spring1;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ittx.java1608.redis.dao.RedisService;

public class RedisServiceTest {
	ApplicationContext app = new ClassPathXmlApplicationContext("spring-redis.xml");
	RedisService redisService = (RedisService) app.getBean("redisService");

	@Test
	public void del() {
		redisService.set("username", "admin");
		long result = redisService.del("username");
		Assert.assertEquals(1L, result);
	}

	@Test
	public void set() {
		redisService.set("age", "23");
	}

	@Test
	public void get() {
		redisService.set("name", "张三");
		String result = redisService.get("name");
		System.out.println(result);
	}

}