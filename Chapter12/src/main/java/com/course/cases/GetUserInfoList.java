package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoList {

    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户列表")
    public void getUserListInfo() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = sqlSession.selectOne("getUserinfoListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
        JSONArray resultJson = getJsonResult(getUserListCase);

        List<User> list = sqlSession.selectList("getUserInfoList",getUserListCase);
        JSONArray users = new JSONArray(list);
        Assert.assertEquals(users.length(),resultJson.length());

        for (int i = 0;i < resultJson.length();i++){
            JSONObject expect = users.getJSONObject(i);
            JSONObject actual = resultJson.getJSONObject(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }

    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("username",getUserListCase.getUserName());
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        post.setHeader("content-type","application");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        JSONArray array = new JSONArray(result);
        return array;
    }
}
