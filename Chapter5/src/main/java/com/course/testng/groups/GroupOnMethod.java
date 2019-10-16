package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {

    @Test(groups = "server")
    public void testserver1(){
        System.out.println("这是服务端的测试方法1");
    }

    @Test(groups = "server")
    public void testserver2(){
        System.out.println("这是服务端的测试方法2");
    }

    @Test(groups = "client")
    public void testclient1(){
        System.out.println("这是客户端的测试方法1");
    }

    @Test(groups = "client")
    public void testclient2(){
        System.out.println("这是客户端的测试方法2");
    }

    @BeforeGroups("client")
    public void beforeServerGroup(){
        System.out.println("before server group这是客户端组执行前的方法");
    }
    @AfterGroups("client")
    public void afterServerGroup(){
        System.out.println("after server group这是客户端组执行后的方法");
    }
}
