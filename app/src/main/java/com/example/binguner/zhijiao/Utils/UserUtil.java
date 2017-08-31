package com.example.binguner.zhijiao.Utils;

/**
 * Created by binguner on 2017/8/30.
 */

public class UserUtil {

    private String cookie;

    public void saveUserCookieId(String cookie){
        this.cookie = cookie;
    }
    public  String getCookieId(){
        return cookie;
    }
}
