package com.example.binguner.zhijiao.Entity;

/**
 * Created by binguner on 2017/8/25.
 */

public class LoginBean
{
    /**
     * cookie : JScESSIvONID=dadgsnrfSPURzOq6mvnDyy4v
     * message : 登陆成功
     */

    private String username;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String cookie) {
        this.username = cookie;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
