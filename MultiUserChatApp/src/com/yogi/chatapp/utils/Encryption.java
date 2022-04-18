/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
    static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException {
        String encryptedPassword;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(plainPassword.getBytes());
        byte[] encrypt = messageDigest.digest();
        encryptedPassword = new String(encrypt);
        return encryptedPassword;
    }

}
