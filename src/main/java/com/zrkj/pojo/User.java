package com.zrkj.pojo;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public class User {
    private Integer id;
    private byte isdel;
    private String openId;
    private String prize;

    public User() {
    }

    public User(String openId) {
        this.openId = openId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsdel(byte isdel) {
        this.isdel = isdel;
    }

    public byte getIsdel() {
        return isdel;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
