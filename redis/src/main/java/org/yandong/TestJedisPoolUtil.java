package org.yandong;

import redis.clients.jedis.Jedis;

public class TestJedisPoolUtil {

    public static void main(String[] args) {
        Jedis jedis1 = JedisPoolUtil.getJedis();
        Jedis jedis2 = JedisPoolUtil.getJedis();
        System.out.println(jedis1 == jedis2);
    }
}
