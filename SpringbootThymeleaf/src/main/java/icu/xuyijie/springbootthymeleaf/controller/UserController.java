package icu.xuyijie.springbootthymeleaf.controller;

import icu.xuyijie.springbootthymeleaf.entity.User;
import icu.xuyijie.springbootthymeleaf.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * @author 徐一杰
 * @date 2023/7/22 13:52
 * @description 用户相关API
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        User one = userMapper.findUserByUsernameAndPassword(user);
        if (Objects.isNull(one)) {
            model.addAttribute("msg", "账号或密码错误");
            return "index";
        }
        session.setAttribute("user", one);
        return "redirect:/employee";
    }

    @PostMapping("/register")
    public String register(User user, String rePassword, Model model) {
        System.out.println("用户注册输入信息：" + user);
        System.out.println("用户注册输入的第二次密码：" + rePassword);

        String password = user.getPassword();
        if (!StringUtils.hasLength(password)) {
            model.addAttribute("registerMsg", "密码为空");
            return "index";
        } else if (!StringUtils.hasLength(rePassword)) {
            model.addAttribute("registerMsg", "请再次输入密码");
            return "index";
        } else if (!rePassword.equals(password)) {
            model.addAttribute("registerMsg", "两次密码输入不一致，请重新输入");
            return "index";
        }
        String phone = user.getMobilePhone();
        User findUser = userMapper.findPhone(phone);
        if (findUser != null) {
            model.addAttribute("registerMsg", "手机号已存在，请重新注册");
        } else {
            userMapper.insert(user);
            model.addAttribute("msg", "注册成功，请登录");
        }
        return "index";
    }

}



