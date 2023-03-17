package test.jedis;

import redis.clients.jedis.Jedis;

public class JedisUtil {
	public static void main(String[] args) {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("192.168.202.128");
		System.out.println("连接成功");
		// 设置 redis 字符串数据
		jedis.set("myInfo", "www.runoob.com");
		// 获取存储的数据并输出
		System.out.println("redis 存储的字符串为: " + jedis.get("zpq"));
	}
}
