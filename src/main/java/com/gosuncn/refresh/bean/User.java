package com.gosuncn.refresh.bean;

/**
 * @Author: chenxihua
 * @Date: 2019/3/28:11:06
 * @Version 1.0
 * 这是一个辅助工具类
 **/
public class User {

    private String username;

    private String ip;

    private long createTime;

    public User() {
    }

    public User(String username, String ip, long createTime) {
        this.username = username;
        this.ip = ip;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
