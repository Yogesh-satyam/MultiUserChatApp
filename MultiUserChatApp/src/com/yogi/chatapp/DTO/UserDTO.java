/*
 * Copyright (c) 2022. This file's all copyrights are reserved to Yogesh Satyam .
 */

package com.yogi.chatapp.DTO;

public class UserDTO {
    private String userid, email, mobile, city;
    private char[] password, oldPassword, newPassword;
    private char status;

    public UserDTO(String userid) {
        this.userid = userid;
    }

    /**
     * This Constructor was made for future use case in server2
     * @param userid UserName of Client
     * @param status Status of Client
     */
    public UserDTO(String userid, char status) {
        this.userid = userid;
        this.status = status;
    }

    public char getStatus() {
        return status;
    }
    public void setStatus(char status) {
        this.status = status;
    }

    public UserDTO(String userid, char[] password) {
        this.userid = userid;
        this.password = password;
    }

    public UserDTO(String userid, char[] password, String email, String mobile, String city) {
        this.userid = userid;
        this.email = email;
        this.mobile = mobile;
        this.city = city;
        this.password = password;
    }

    public UserDTO(String mobile, char[] oldPassword, char[] newPassword) {
        this.mobile = mobile;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public char[] getOldPassword() {
        return oldPassword;
    }

    public char[] getNewPassword() {
        return newPassword;
    }

    public String getUserid() {
        return userid;
    }

//    public void setUserid(String userid) {
//        this.userid = userid;
//    }

    public char[] getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCity() {
        return city;
    }
}
