package com.example.myfourthspringboot;
// UserService的测试类

import jakarta.annotation.security.RunAs;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//测试类，写入测试类的对象
/*@SpringBootTest(classes = MyFourthSpringBootApplication.class),因为这个Test类跟那个UserServe在同一个包下，所以括号里内容可省略
* 但要是换包了，就得写上，要不找不到路径*/
@SpringBootTest()

public class UserServiceTest {
    @Autowired
    private UserService userService;
    /*因为是测试类，所以需要加test注解*/
@Test
    public void testAdd(){
        userService.add();
    }
}
