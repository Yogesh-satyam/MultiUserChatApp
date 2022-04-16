package com.yogi.chatapp.utils;

import java.security.NoSuchAlgorithmException;

public class MatchPassword {
    public static boolean matchPwd(String currentPassword,String oldPassword) throws NoSuchAlgorithmException {
        oldPassword=Encryption.passwordEncrypt(oldPassword);
        return currentPassword.equals(oldPassword);
    }
}
