package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {
    public static SqlSession getSqlSession() throws IOException {
        //获取资源配置文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //加载配置文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //返回sql session，他就能执行配置文件中的sql语句了
        SqlSession sqlSession = factory.openSession();
        return sqlSession;
    }
}
