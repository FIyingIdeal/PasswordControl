package com.flyingideal.model;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Administrator on 2015/12/3.
 */
public class UserModel {
    private int userId;
    private String userName;
    private String password;
    private String comment;
    private String passwordSalt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public void setEncodedPasswordHash(String encodedPasswordHash) {
        this.password = encodedPasswordHash;
    }

    public void setEncodedPasswordSalt(String encodePasswordSalt) {
        this.passwordSalt = encodePasswordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt, byte[] passwordHash) {
        setEncodedPasswordSalt(Base64.encodeBase64String(passwordSalt));
        setEncodedPasswordHash(Base64.encodeBase64String(passwordHash));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", comment='" + comment + '\'' +
                ", passwordsalt='" + passwordSalt + '\'' +
                '}';
    }
}
