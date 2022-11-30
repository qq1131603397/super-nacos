package com.hz.redis.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.hz.redis.entity.ResultVO;
import com.hz.redis.entity.Student;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： pt
 * @date： 2022/11/28 10:40
 * @discription redis测试类
 * StringRedisTemplate默认采用的是String的序列化策略，保存的key和value都是采用此策略序列化保存的。
 * RedisTemplate默认采用的是JDK的序列化策略，保存的key和value都是采用此策略序列化保存的。
 * StringRedisTemplate存储的是明文字符串
 * RedisTemplate存储的是字节数组，效率更快
 */
@RestController
@RequestMapping("redis")
public class RedisTestController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("redisTemplateSet")
    public ResultVO redisTemplateSet(@RequestBody Student student) {
        redisTemplate.opsForValue().set(student.getId(), student);
        return ResultVO.success(student);
    }

    @GetMapping("redisTemplateGet")
    public ResultVO testRedisTemplateGet(@RequestParam("id") String id) {
        Student student = (Student) redisTemplate.opsForValue().get(id);
        return ResultVO.success(student);
    }

    @GetMapping("stringRedisTemplateSet")
    public ResultVO testRedisTemplateGet(@RequestParam("id") String id, @RequestParam("name") String name) {
        stringRedisTemplate.opsForValue().set(id, name);
        return ResultVO.success(String.format("id: %s  name: %s", id, name));
    }

    @GetMapping("stringRedisTemplateGet")
    public ResultVO stringRedisTemplateGet(@RequestParam("id") String id) {
        String name = stringRedisTemplate.opsForValue().get(id);
        return ResultVO.success(name);
    }

    @GetMapping("jedisSet")
    public ResultVO jedisSet() {
        long start = System.currentTimeMillis();
        Jedis jedis = new Jedis("192.168.179.128", 6379);
        jedis.auth("qatest");
        jedis.select(1);
        Pipeline pip = jedis.pipelined();
        try {
            //预处理操作，将要存放的数据一起放到管道里面
            for (int i = 0; i < 10000; i++) {
                pip.set("jedis" + i, String.valueOf(i));
            }
            //真正的处理操作，将要存放的数据一次执行处理完
            pip.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pip.close();
        }
        return ResultVO.success(String.format("Jedis批量存储耗时：%d ms", System.currentTimeMillis() - start));
    }

    @GetMapping("jedisGet")
    public ResultVO jedisGet() {
        long start = System.currentTimeMillis();
        Jedis jedis = new Jedis("192.168.179.128", 6379);
        jedis.auth("qatest");
        jedis.select(1);
        Pipeline pip = jedis.pipelined();
        try {
            //预处理操作，将要存放的数据一起放到管道里面
            for (int i = 0; i < 1000; i++) {
                pip.get("jedis" + i);
            }
            //真正的处理操作，将要存放的数据一次执行处理完
            List<Object> resultList = pip.syncAndReturnAll();
            System.out.println(resultList.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pip.close();
        }
        return ResultVO.success(String.format("Jedis批量读取耗时：%d ms", System.currentTimeMillis() - start));
    }

    @GetMapping("stringRedisTemplateBatch")
    public ResultVO stringRedisTemplateBatch() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            stringRedisTemplate.opsForValue().set("jedis" + i, String.valueOf(i));
        }
        return ResultVO.success(String.format("stringRedisTemplate批量存储耗时：%d ms", System.currentTimeMillis() - start));
    }

    @GetMapping("redisTemplateBatch")
    public ResultVO redisTemplateBatch() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            redisTemplate.opsForValue().set("jedis" + i, String.valueOf(i));
        }
        return ResultVO.success(String.format("redisTemplateBatch批量存储耗时：%d ms", System.currentTimeMillis() - start));
    }

    @GetMapping("redisBloomFilter")
    public ResultVO redisBloomFilter() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.179.128:6379");
        config.useSingleServer().setPassword("qatest");
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("");
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%
        bloomFilter.tryInit(100000000L,0.03);
        //将号码10086插入到布隆过滤器中
//        bloomFilter.add("10086");

        //判断下面号码是否在布隆过滤器中
        System.out.println(bloomFilter.contains("123456"));//false
        System.out.println(bloomFilter.contains("10086"));//true
        return ResultVO.success("");
    }
}
