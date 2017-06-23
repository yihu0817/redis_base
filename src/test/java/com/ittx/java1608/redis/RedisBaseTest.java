package com.ittx.java1608.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisBaseTest {

	@Test
	public void test() {
		Jedis jedis = new Jedis("www.warmtel.com",6379);
		jedis.auth("java1608_123");
		jedis.set("sex", "男");
		
		String sex = jedis.get("sex");
		System.out.println(sex);
	}

}
