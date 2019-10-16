package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class configFile {
    public static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri = "";
        switch (name){
            case LOGIN:
                uri = bundle.getString("login.uri");
                break;
            case ADDUSER:
                uri = bundle.getString("addUser.uri");
                break;
            case GETUSERINFO:
                uri = bundle.getString("getUserInfo.uri");
                break;
            case GETUSERLIST:
                uri = bundle.getString("getUserList.uri");
                break;
            case UPDATEUSERINFO:
                uri = bundle.getString("updateUserInfo.uri");
                break;
        }
        String testUrl = address + uri;
        return testUrl;
    }
}
