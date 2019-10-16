package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@Api(value = "v1",description = "用户管理系统")
@RequestMapping(value = "v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    public boolean login(@RequestBody User user, HttpServletResponse response){
        Cookie cookie;
        int i = template.selectOne("login",user);
        log.info("获取结果是"+i);
        if (i == 1){
            log.info("登录的用户是"+user.getUserName());
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return true;
        }
        return false;

    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    public boolean addUser(@RequestBody User user, HttpServletRequest request){
        Boolean b = verifyCookies(request);
        int result = 0;
        if (b){
            result = template.insert("addUser",user);
            if (result > 0){
                log.info("添加用户成功");
                log.info("添加用户的数量是"+result);
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "getUserInfoList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表接口",httpMethod = "POST")
    public List<User> getUserInfoList(@RequestBody User user,HttpServletRequest request){
        Boolean b = verifyCookies(request);
        List<User> list = null;
        if (b){
            log.info("cookie验证通过");
            list = template.selectList("getUserInfoList",user);
            log.info("getUserInfoList获取到的用户数量为"+list.size());
            log.info("获取到的用户列表为"+list.toString());
        }
        return list;
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "更新/删除用户信息",httpMethod = "POST")
    public int updateUserInfo(@RequestBody User user,HttpServletRequest request){
        boolean b = verifyCookies(request);
        int i = 0;
        if (b){
            log.info("cookie验证通过");
            i = template.update("updateUserInfo",user);
            log.info("共更新用户数量"+i);
            return i;
        }
        log.info("更新用户失败");
        return i;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("cookie信息为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")
                    &&cookie.getValue().equals("true")){
                log.info("cookie验证通过");
                return true;
            }
        }
        return false;
    }
}
