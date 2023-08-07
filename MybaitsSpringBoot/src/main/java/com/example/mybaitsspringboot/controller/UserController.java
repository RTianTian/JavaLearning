package com.example.mybaitsspringboot.controller;

import com.example.mybaitsspringboot.domain.User;
import com.example.mybaitsspringboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//相当于@ResponseBody和@Controller的合体，@ResponseBody是用于返回json格式数据
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getAll")
    public List<User> getAllUser() {
        return userMapper.findAll();
    }

    @RequestMapping("/getUser")
    public String getUser(@RequestParam("name") String name) {
        User user = userMapper.findOneByName(name);
        return user.toString();
    }

    @RequestMapping("/getUserJson")
    public User getUserJson(@RequestParam("name") String name) {
        return userMapper.findOneByName(name);
    }
    @RequestMapping("/get3")
    public List<User> get3(@RequestParam("id") String id){
      return userMapper.findById(id);
    }
    @RequestMapping("/get4")
    public  Boolean get4(@RequestParam("id") String id){
        return userMapper.deleteId(id);
    }
    @RequestMapping("/getupdate")
    public Boolean getupdate(@RequestParam("id") String id){
        return  userMapper.updateId(id);
    }

    @RequestMapping("getdelete2")
    public Boolean getdelete2(@RequestParam("username") String username){
        return userMapper.deleteId2(username);
    }

}
