package org.yandong;

import redis.clients.jedis.Jedis;

public class Test1 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.184.128", 6379);
        String pong = jedis.ping();
        System.out.println("pong = " + pong);
    }
}
