package org.yandong;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis连接池
 */
public class JedisPoolUtil {

    private JedisPoolUtil() {
    }

    private volatile static JedisPool jedisPool = null;
    private volatile static Jedis jedis = null;
    // 返回一个连接池
    private static JedisPool getInstance(){
        // 双层检测锁（企业中用的非常频繁）
        if(jedisPool == null){
            // 第一层：检测体温
            synchronized (JedisPoolUtil.class){
                // 排队进站
                if(jedisPool == null) {
                    //第二层：查看健康码
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(1000);
                    // 资源池中的最大连接数
                    config.setMaxIdle(30);
                    // 资源池允许的最大空闲连接数
                    config.setMaxWaitMillis(60*1000);
                    // 当资源池连接用尽后，调用者 的最大等待时间（单位为毫秒）
                    config.setTestOnBorrow(true);
                    //向资源池借用连接时是否做连接有效 性检测(业务量很大时候建议设置为false，减少一次ping的开销)
                    jedisPool = new JedisPool( config, "192.168.184.128",6379);
                }
            }
        }
        return jedisPool;
    }
    // 返回jedis对象
    public static Jedis getJedis(){
        if(jedis == null){
            jedis = getInstance().getResource();
        }return jedis;
    }
}
