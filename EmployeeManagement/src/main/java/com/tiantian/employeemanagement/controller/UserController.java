package com.tiantian.employeemanagement.controller;

import com.tiantian.employeemanagement.entity.User;
import com.tiantian.employeemanagement.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author 天天
 * @date 2023/8/29 14:34
 * @description 用户登录注册等相关接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        User one = userMapper.findUserByUsernameAndPassword(user);
        if (Objects.isNull(one)) {
            return "失败";
        }
        //session.setAttribute（键对值，value值）  同hashMap()中的push，set方法一样
        session.setAttribute("user", one);//将one存到user里面，user属于一个类型值同String一样
        return "成功";
    }

    @PostMapping("/register")
    public String register(User user, String rePassword) {
        System.out.println("用户注册输入信息：" + user);
        System.out.println("用户注册输入的第二次密码：" + rePassword);

        String password = user.getPassword();
        //StringUtils.hasLength 如果字符序列不为null值，且字符序列大于0，则返回true值
        if (!StringUtils.hasLength(password)) {
            return "注册失败";
        } else if (!StringUtils.hasLength(rePassword)) {
            return "注册失败(请再次输入密码)";
        } else if (!rePassword.equals(password)) {
            return "注册失败（密码不一致）";
        }
        String phone = user.getMobilePhone();
        User findUser = userMapper.findPhone(phone);
        if (findUser != null) {
            return "注册失败（手机号已存在）";
        } else {
            userMapper.insert(user);
            return "注册成功";
        }
    }

}
