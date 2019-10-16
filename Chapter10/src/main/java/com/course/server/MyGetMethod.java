package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getcookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "获取cookie成功";
    }
    /**
     * 要求客户端必须携带cookie访问
     * */
    @RequestMapping(value = "/getwithcookies",method = RequestMethod.GET)
    @ApiOperation(value = "必须携带cookie访问的方法",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "必须携带cookie信息才能访问！!";
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("login")&&
                    cookie.getValue().equals("true")){
                return "访问成功！！";
            }
            return "cookie信息验证不通过";

        }
        return "！！！";
    }

    /**
     * 开发一个需要参数才能访问的get请求
     * 第一种实现方式 url：key=value&key=value
     * 模拟获取商品列表
     */
    @RequestMapping(value = "/getwithparam",method = RequestMethod.GET)
    @ApiOperation(value = "携带参数访问的方法1",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> m = new HashMap<>();
        m.put("毛衣",200);
        m.put("裤子",300);
        m.put("鞋子",400);
        return m;
    }

    /**
     * 第二种实现方式
     * url IP:port/path/value/value
     */
    @RequestMapping(value = "/getwithparam2/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "携带参数访问的方法2",httpMethod = "GET")
    public Map<String,Integer> getList2(@PathVariable Integer start,
                                        @PathVariable Integer end){
        Map<String,Integer> m = new HashMap<>();
        m.put("毛衣",400);
        m.put("裤子",500);
        m.put("鞋子",600);
        return m;

    }


}
