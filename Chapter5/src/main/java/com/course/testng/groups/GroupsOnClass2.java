package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupsOnClass2 {

    public void stu1(){
        System.out.println("class222中的stu111");
    }


    public void stu2(){
        System.out.println("class222中的stu222");
    }

}
