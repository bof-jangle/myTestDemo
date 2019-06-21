package xyz.jangle.redis.test;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MainTest {
	
	public static final String key = "20190621";

	public static void main(String[] args) {
		
		JedisPoolConfig poolcfg = new JedisPoolConfig();
		
		//最大空闲连接数, 默认8个
		poolcfg.setMaxIdle(200);
		//最大连接数, 默认8个
		poolcfg.setMaxTotal(1000);
		//最小空闲连接数, 默认0
		poolcfg.setMinIdle(50);
		
		JedisPool pool = new JedisPool(poolcfg, "localhost",6379);
		
		Jedis jedis = pool.getResource();
		
		UserVo u = new UserVo();
		u.setId(1);
		u.setName("aName");
		String aObj = JSON.toJSONString(u);
		jedis.set(key, aObj);
		
		String aObjValue = jedis.get(key);
		System.out.println(jedis.get(key));
		UserVo uReturn = JSON.parseObject(aObjValue, UserVo.class);
		System.out.println(uReturn.getId());
		System.out.println(uReturn.getName());
		
		//删除aObj
//		jedis.del("aObj");
		
		jedis.close();
		pool.close();

	}

}
