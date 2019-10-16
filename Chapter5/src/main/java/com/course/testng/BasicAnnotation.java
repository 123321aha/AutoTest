package com.course.testng;


import org.testng.annotations.*;

public class BasicAnnotation {

    //最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }

    //在测试用例前后执行执行的方法
    @BeforeMethod
    public void beforeMethod(){
       System.out.println("BeforeMethod 这是在测试用例之前执行的方法");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMeyhod 这是在测试用例之后执行的方法");

    }


    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass 这是在类运行之前运行的方法");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("afterClass 这是在类运行之后运行的方法");
    }


    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforesuite 测试套件");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite 测试套件");
    }

}

