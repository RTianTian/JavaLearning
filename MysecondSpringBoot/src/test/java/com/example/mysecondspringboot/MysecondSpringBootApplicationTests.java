package com.example.mysecondspringboot;

import com.example.mysecondspringboot.bean.Dog;
import com.example.mysecondspringboot.bean.person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MysecondSpringBootApplicationTests {
    @Autowired
    person person1;

    @Test
    public void contextLoads() {
        System.out.println(person1);
    }

}
