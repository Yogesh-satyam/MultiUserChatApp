/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.utils;

import java.util.ResourceBundle;

public class ConfigReader {
    ConfigReader() {
    }

    private static final ResourceBundle rb = ResourceBundle.getBundle("config");

    public static String getValue(String key) {
        return rb.getString(key);
    }
    public static String getPort(String key){
        ResourceBundle rb=ResourceBundle.getBundle("port");
        return rb.getString(key);
    }

//    public static void main(String[] args) {
//        getValue("")
//    }
}
