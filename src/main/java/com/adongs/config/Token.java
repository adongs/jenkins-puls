package com.adongs.config;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/26 6:22 下午
 * @modified By
 */
public class Token {

    private String token;

    private long expireDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }
}
