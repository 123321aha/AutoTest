package com.course.httpclient.cookies;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
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
//        HttpResponse response = client.execute(get);
//        HttpEntity entity = response.getEntity();
//        result = EntityUtils.toString(entity);
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
    public void getWithCookie() throws IOException {
        String result;
        String testurl = this.url+bundle.getString("test.getwithcookie.uri");
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        int statuscode = response.getStatusLine().getStatusCode();
        if (statuscode == 200){
            result = EntityUtils.toString(entity);
            System.out.println(result);
        }
    }
}
