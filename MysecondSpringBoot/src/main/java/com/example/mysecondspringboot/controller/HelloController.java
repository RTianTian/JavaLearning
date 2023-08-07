package com.example.mysecondspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @ResponseBody//给浏览器发送请求
    @RequestMapping("/hello")
    public String hello(){
        return "hello world quick";
    }
}
