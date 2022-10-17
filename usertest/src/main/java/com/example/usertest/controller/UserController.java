package com.example.usertest.controller;

import com.example.usertest.entity.Response;
import com.example.usertest.entity.UserBean;
import com.example.usertest.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author NJQ-PC
 */
@RestController
@RequestMapping("/api")
@Component
public class UserController {
    @Resource
    UserMapper userMapper;

    @CrossOrigin//解决跨域问题 方法之二
    @PostMapping("/login")
    public Response login(@RequestBody UserBean userBean) {
        String name = userBean.getUsername();
        String password = userBean.getPassword();
        if (userMapper.login(name, password)!=null) {
            return new Response(200, "登录成功！", "");
        } else {
            return new Response(500, "登录失败,用户名密码错误！", "");
        }
    }

    @CrossOrigin//解决跨域问题 方法之二
    @PostMapping("/register")
    public Response register(@RequestBody UserBean userBean){
        String username = userBean.getUsername();
        String password = userBean.getPassword();
        if (userMapper.register(username,password)){
            return new Response(200, "注册成功！", "");
        }else {
            return new Response(200, "注册失败！", "");
        }
    }

}
