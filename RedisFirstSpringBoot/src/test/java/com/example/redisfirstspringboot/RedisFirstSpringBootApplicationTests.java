package com.example.redisfirstspringboot;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisFirstSpringBootApplicationTests {
@Resource
private RedisTemplate redisTemplate;
    @Test
  public   void testSet() {
//        存入数据
        redisTemplate.boundValueOps(key:"name").set("zhangsan");
    }

}
