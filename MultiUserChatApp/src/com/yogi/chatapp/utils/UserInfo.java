/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.utils;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private UserInfo() {
    }
    private static String user_id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        UserInfo.user_id = user_id;
    }
    public static UserInfo getUserInfoObjet(String user_id){
        UserInfo userInfo=new UserInfo();
        userInfo.setName(user_id);
        return userInfo;
    }
}
