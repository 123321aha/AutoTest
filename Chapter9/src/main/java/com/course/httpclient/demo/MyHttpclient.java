package com.course.httpclient.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpclient {

    @Test
    public void test1() throws IOException {
        //用来存放执行结果
        String result;
        //发送get请求的方法
        HttpGet get = new HttpGet("http://www.baidu.com");
        //用来执行get方法
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        //获取响应的整个实体
        HttpEntity entity = response.getEntity();
        //将实体转化为字符串
        result = EntityUtils.toString(entity);
        System.out.println(result);
    }

    @Test
    public void test2(){

    }

}
