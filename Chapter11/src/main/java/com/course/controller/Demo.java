package com.course.controller;


import com.course.model.LoginCase;
import com.course.model.User;
import com.course.model.UserLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.CookieStore;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "v1",description = "这是我第一个版本的demo")
@RequestMapping(value = "/v1")
public class Demo {

    //首先获取一个执行sql的对象
    //这个注解是，启动即加载，demo启动，就被加载
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value = "登录",httpMethod = "POST")
    public int login(@RequestBody LoginCase loginCase, HttpServletResponse response){
        int result = template.selectOne("login",loginCase);
        Cookie cookie;
        if (result == 1){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
        }
        return result;
    }


    @RequestMapping(value = "/getusercount",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getCount");

    }

    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        int result = template.insert("addUser",user);
        return result;

    }


    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    @ApiOperation(value = "更新用户")
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }

    @RequestMapping(value = "/deleteuser",method = RequestMethod.POST)
    @ApiOperation(value = "删除用户")
    public int deleteUser(@RequestBody User user){
        return template.update("deleteUser",user);
    }


}
