package org.yandong;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class Affair {

    Jedis jedis = new Jedis("192.168.184.128", 6379);
    int yue = Integer.parseInt(jedis.get("yue"));
    int zhichu = 20;

    public void xiaofei() throws InterruptedException {
        jedis.watch("yue"); // 监控余额
        Thread.sleep(5000); // 模拟网络延迟
        if (yue < zhichu) {
            jedis.unwatch(); //解除监控
            System.out.println("余额不足");
        } else {
            Transaction transaction = jedis.multi(); // 启动事务
            transaction.incrBy("zhichu", zhichu);
            transaction.decrBy("yue", zhichu);
            transaction.exec(); // 执行事务
            System.out.println("余额为：" + jedis.get("yue"));
            System.out.println("支出为：" + jedis.get("zhichu"));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Affair().xiaofei();
    }
}
