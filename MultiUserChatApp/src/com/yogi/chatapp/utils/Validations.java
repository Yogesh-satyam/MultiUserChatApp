/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.utils;

import java.security.NoSuchAlgorithmException;

public class Validations {
    public static boolean matchPwd(String currentPassword, String oldPassword) throws NoSuchAlgorithmException {
        oldPassword = Encryption.passwordEncrypt(oldPassword);
        return currentPassword.equals(oldPassword);
    }
}
