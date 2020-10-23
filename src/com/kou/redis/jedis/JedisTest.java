package com.kou.redis.jedis;

/**
 * @author JIAJUN KOU
 */
import com.kou.redis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 快速入门
 */

public class JedisTest {

    //1.
    @Test
    public void test1(){
        Jedis jedis=new Jedis("localhost",6379);
        jedis.set("age","20");
        jedis.setex("actioncode",20,"hehhe");
        String age = jedis.get("age");
        System.out.println(age);
        jedis.close();

    }
    //2.hash类型
    @Test
    public void test2(){
        Jedis jedis=new Jedis("localhost",6379);

        //jedis.lpush("person","kou","wang","yeye");

        jedis.hset("user","name","lishi");
        jedis.hset("user","age","20");
        jedis.hset("user","gender","男");

        String name = jedis.hget("user", "name");
        System.out.println(name);


        //获取所有map数据

        Map<String, String> user = jedis.hgetAll("user");

        Set<String> set = user.keySet();
        for (String key : set) {
            String value = user.get(key);
            System.out.println(key+":"+value);
        }


        jedis.close();

    }
    //3.list类型
    @Test
    public void test3(){
        Jedis jedis=new Jedis("localhost",6379);

        jedis.lpush("per","kou","wang","huang");
        jedis.rpush("per","a","b","c");
        //获取
        List<String> person = jedis.lrange("per", 0, -1);
        System.out.println(person);

        String per = jedis.lpop("per");
        System.out.println(per);

        String per1 = jedis.rpop("per");
        System.out.println(per1);
        jedis.close();


    }

    //set集合操作

    @Test
    public void test4(){
        Jedis jedis=new Jedis("localhost",6379);

        jedis.sadd("set","java","python","数学");
        Set<String> set = jedis.smembers("set");
        System.out.println(set);

        jedis.close();
    }

    //set集合操作

    @Test
    public void test5(){
        Jedis jedis=new Jedis("localhost",6379);

       jedis.zadd("score",3,"王后");
       jedis.zadd("score",100,"kou");
       jedis.zadd("score",5,"houmian");

        Set<String> score = jedis.zrange("score", 0, -1);
        System.out.println(score);
        jedis.close();
    }

    /**
     * jedis连接池的使用
     */
    @Test
    public void test7(){
        //创建一个配置对象
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);


        JedisPool jedisPool=new JedisPool(jedisPoolConfig,"localhost",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("heheh","hwangwnagwnag");

        String s = jedis.get("heheh");
        System.out.println(s);

        jedis.close();

    }

    @Test
    public void test8(){
        Jedis jedis = JedisPoolUtils.getJedis();
        String s = jedis.set("hello", "world");
        System.out.println(s);

        jedis.close();


    }

}
