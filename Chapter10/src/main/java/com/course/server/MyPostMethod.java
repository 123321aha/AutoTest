package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post方法")
@RequestMapping(value = "/v1")
public class MyPostMethod {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功可获得cookie",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){
        if (username.equals("zhangsan")&&password.equals("123456")){
            response.addCookie(new Cookie("login","true"));
            return "登录成功，已获取cookie";
        }
        return "用户名密码错误";
    }

    @RequestMapping(value = "/getuserlist",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表接口",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){
        Cookie[] cookies = request.getCookies();
        User u;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("login")
             && cookie.getValue().equals("true")
             && user.getUsername().equals("zhangsan")
             && user.getPassword().equals("123455")){
                u = new User();
                u.setName("zhangsan");
                u.setAge("18");
                u.setSex("man");
                return u.toString();
            }
        }
        return "参数不合法";

    }
}
