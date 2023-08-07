package com.tiantian.springrest.controller;

import com.tiantian.springrest.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController  //打印出来是字符串----如：/get2中打印出来是index
//@Controller //打印出来是字符串里面的代码，是一个网页----会跳转到index.html下
//@ResponseBody
@RequestMapping("/user")
public class UserController {
//@RequestParam 有改名作用，将a换成ccc
    @RequestMapping("/get")
    public String getString(@RequestParam(value = "ccc", required = false) String a) {
        return a;
    }

//    @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写
//    get是把参数数据队列加到提交表单的ACTION属性所指的URL中，值和表单内各个字段一一对应，在URL中可以看到。
//    get是从服务器上获取数据。
    @GetMapping("/get2")
    public String getString2( String index) {
        return "index";
    }
//http://127.0.0.1:8080/user/getnew?a=4   可在浏览器输出界面，显示出4
    @RequestMapping("/getnew")
    public String getString21(String a){
        return a;
    }


//
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String getString3(String a) {
        return a;
    }

//    @PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。
//
//get方式的安全性较Post方式要差些，包含机密信息的话，建议用Post数据提交方式；
//
//post是向服务器传送数据。

    @PostMapping("/post2")
    public String getString4(String a) {
        return a;
    }

    @GetMapping("/saveUser0")
    public User saveUser1(int id, String name) {
        System.out.println(id);
        System.out.println(name);
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    /**
     * Get方法可以接受 param 和 formData 数据
     * 例如：/saveUser?id=1&name=天天，formData格式浏览器无法发送，要使用postman或者前端代码来发送
     *
     * @param user 参数
     * @return 返回值
     */
    @GetMapping("/saveUser")
    public User saveUser1111111(User user) {
        return user;
    }

    //POST请求可以接受任意格式数据
    @PostMapping("/saveUser2")
    public User saveUser1111(User user) {
        return user;
    }

    //POST请求加了这个@RequestBody注解，仅可以接受json格式数据，即：key：value
    @PostMapping("/saveUser3")
    @ResponseBody  //将java对象转为json格式的数据
    public User saveUser11121(@RequestBody User user) {
        return user;
    }

    @PostMapping("saveUser4")
    public User saveUser411(User user){
        return user;
    }
//    @PathVariable 就是为了获取id的值，从而返回id
//    http://127.0.0.1:8080/user/delete/1 在浏览器这样输出
    @GetMapping("/delete/{id}")
    public String saveUser1112(@PathVariable Integer id) {
        return "id为" + id + "的用户已删除";
    }

    @PostMapping("/delete2/{id}")
    public String saveUser1113(@PathVariable Integer id) {
        return "id为" + id + "的用户已删除";
    }
//这个是用postman里面的Body里的raw方法，而上面那些方法用的是formData格式
    @PostMapping("/login")
    public User saveUser1113(@RequestBody User user) {
        return user;
    }


}
