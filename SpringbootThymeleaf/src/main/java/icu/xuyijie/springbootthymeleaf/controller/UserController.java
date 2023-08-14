package icu.xuyijie.springbootthymeleaf.controller;

import icu.xuyijie.springbootthymeleaf.dto.UserDto;
import icu.xuyijie.springbootthymeleaf.entity.User;
import icu.xuyijie.springbootthymeleaf.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String register(UserDto userDto, Model model) {
        System.out.println(userDto);
        int i = userMapper.insert(userDto);
        if (i == 1) {
            model.addAttribute("msg", "注册成功，请登录");
        } else {
            model.addAttribute("msg", "注册失败");
        }
        return "index";
    }
}
