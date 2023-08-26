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
import org.springframework.web.bind.annotation.ResponseBody;

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
//            因为有了Model 所以才有会从页面上输出  attributeValue值
            model.addAttribute("msg", "账号或密码错误");
            return "index";
        }
//    session.setAttribute（键对值，value值）  同hashMap()中的push，set方法一样
    session.setAttribute("user", one);//将one存到user里面，user属于一个类型值同String一样
        return "redirect:/employee";
    }
    @ResponseBody
    @PostMapping("/login2")
    public String login2(User user, HttpSession session) {
        User one = userMapper.findUserByUsernameAndPassword(user);
        if (Objects.isNull(one)) {
//            因为有了Model 所以才有会从页面上输出  attributeValue值

            return "失败";
        }
//    session.setAttribute（键对值，value值）  同hashMap()中的push，set方法一样
        session.setAttribute("user", one);//将one存到user里面，user属于一个类型值同String一样
        return "成功";
    }


    @PostMapping("/register")
    public String register(User user, String rePassword, Model model) {
        System.out.println("用户注册输入信息：" + user);
        System.out.println("用户注册输入的第二次密码：" + rePassword);

        String password = user.getPassword();
//        StringUtils.hasLength 如果字符序列不为null值，且字符序列大于0，则返回true值
        if (!StringUtils.hasLength(password)) {
            model.addAttribute("registerMsg", "密码为空");
            return "index";
        } else if (!StringUtils.hasLength(rePassword)) {
//       model.addAttribute(Map attribute)如果变量已存在，则覆盖
//       model.mergeAttribute(Map attribute)如果变量已存在，则忽略
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
@ResponseBody
    @PostMapping("/register2")
    public String register2(User user, String rePassword) {
        System.out.println("用户注册输入信息：" + user);
        System.out.println("用户注册输入的第二次密码：" + rePassword);

        String password = user.getPassword();
//        StringUtils.hasLength 如果字符序列不为null值，且字符序列大于0，则返回true值
        if (!StringUtils.hasLength(password)) {

            return "注册失败";
        } else if (!StringUtils.hasLength(rePassword)) {
//       model.addAttribute(Map attribute)如果变量已存在，则覆盖
//       model.mergeAttribute(Map attribute)如果变量已存在，则忽略

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



