package com.yogi.chatapp.DTO;

public class UserDTO {
    private String userid, email, mobile, city;
    private char[] password, oldPassword, newPassword;


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

    public void setOldPassword(char[] oldPassword) {
        this.oldPassword = oldPassword;
    }

    public char[] getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(char[] newPassword) {
        this.newPassword = newPassword;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public char[] getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
