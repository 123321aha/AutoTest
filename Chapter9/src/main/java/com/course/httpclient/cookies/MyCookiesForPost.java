package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String testurl = this.url+bundle.getString("getCookies.uri");
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();
        result = EntityUtils.toString(client.execute(get).getEntity());
        System.out.println(result);

        //获取cookie信息
        this.store = client.getCookieStore();
        List<Cookie> list = store.getCookies();
        for (Cookie cookie:list){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name="+name+";  cookie value="+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void postWithCookie() throws IOException {
        String result;
        //拼接url
        String testurl = this.url+bundle.getString("test.postwithcookies.uri");
        //声明client对象
        DefaultHttpClient client = new DefaultHttpClient();
        //声明post方法
        HttpPost post = new HttpPost(testurl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //添加头信息
        post.setHeader("content-type","application/json");
        //设置cookies
        client.setCookieStore(this.store);
        //执行post请求,获取响应
        HttpResponse response = client.execute(post);
        //响应结果处理
        result = EntityUtils.toString(response.getEntity());
        //处理校验响应结果
        int  statuscode = response.getStatusLine().getStatusCode();
        if (statuscode == 200){
            //响应结果转化为json
            JSONObject json = new JSONObject(result);
            System.out.println(json.toString());
            Assert.assertEquals("success",json.getString("huhansan"));
            Assert.assertEquals("1",json.getString("status"));
        }

    }

}
