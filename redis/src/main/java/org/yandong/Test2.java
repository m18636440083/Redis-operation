package org.yandong;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test2 {

    public void testString() {
        Jedis jedis = new Jedis("192.168.184.128", 6379);
        jedis.set("key1", "v1");
        jedis.set("key2", "v2");
        jedis.set("key3", "v3");

        Set<String> set = jedis.keys("*");
        Iterator<String> iterator = set.iterator();
        for (set.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            System.out.println(key + "->" + jedis.get(key));
        }

        Boolean key1 = jedis.exists("key1"); // 查看key1是否存在
        System.out.println(key1);
        System.out.println(jedis.ttl("key1"));

        /*
            string数据类型
         */
        System.out.println("------------------String类型----------------");
        jedis.mset("key4", "v4", "key5", "v5");
        System.out.println(jedis.mget("key1", "key2", "key3", "key4", "key5"));
        System.out.println("----------------------------------");
    }

    public void testList() {
        Jedis jedis = new Jedis("192.168.184.128", 6379);
         /*
            list类型
         */
        System.out.println("------------list类型----------------------");
        jedis.lpush("list01", "l1", "l2", "l3", "l4", "l5");
        List<String> list01 = jedis.lrange("list01", 0, -1);
        for (String s : list01) {
            System.out.println(s);
        }
    }

    public void testSet() {
        Jedis jedis = new Jedis("192.168.184.128", 6379);

        /*
            set类型
         */
        System.out.println("----------------------set类型-----------------");
        jedis.sadd("temp", "zz01");
        jedis.sadd("temp", "zz02");
        jedis.sadd("temp", "zz03");
        Set<String> temp = jedis.smembers("temp");
        Iterator<String> iterator1 = temp.iterator();
        while (iterator1.hasNext()) {
            String s = iterator1.next();
            System.out.println(s);
        }
        jedis.srem("temp", "zz01"); // 删除操作
        System.out.println(jedis.smembers("temp").size());
    }

    public void testHash() {
        Jedis jedis = new Jedis("192.168.184.128", 6379);
        jedis.hset("user1", "username", "pick");
        String s = jedis.hget("user1", "username");
        System.out.println(s);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", "nick");
        map.put("address", "chengdu");
        map.put("phone", "111111");
        jedis.hmset("user2", map);
        List<String> user = jedis.hmget("user2", "username", "address", "phone");
        for (String s1 : user) {
            System.out.println(s1);
        }


    }

    public void testZset() {
        Jedis jedis = new Jedis("192.168.184.128", 6379);
        jedis.zadd("class", 10d, "no1");
        jedis.zadd("class", 20d, "no2");
        jedis.zadd("class", 30d, "no3");
        jedis.zadd("class", 40d, "no4");
        jedis.zadd("class", 50d, "no5");
        jedis.zadd("class", 60d, "no6");
        Set<String> aClass = jedis.zrange("class", 0, -1);
        Iterator<String> iterator = aClass.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }

    }
    public static void main(String[] args) {
        // new Test2().testString();
        // new Test2().testList();
        // new Test2().testSet();
        // new Test2().testHash();
        new Test2().testZset();
    }
}
