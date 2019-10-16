package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("class111中的stu111");
    }

    public void stu2(){
        System.out.println("class111中的stu222");
    }
}
