package com.zrkj.pojo;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public class User {
    private Integer id;
    private byte isdel;
    private String openId;
    private Integer redEnvelope;
    private List<Record> redEnvelopeRecord;
    private List<Record> integralRecord;
    private List<Record> records;
    private Integer integral;
    private String name;
    private String phone;
    private String nikeName;
    private String headImgUrl;
    private Integer storeId;


    public User() {
        this.integral=0;
        this.redEnvelope=0;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Record> getRedEnvelopeRecord() {
        return redEnvelopeRecord;
    }

    public void setRedEnvelopeRecord(List<Record> redEnvelopeRecord) {
        this.redEnvelopeRecord = redEnvelopeRecord;
    }

    public List<Record> getIntegralRecord() {
        return integralRecord;
    }

    public void setIntegralRecord(List<Record> integralRecord) {
        this.integralRecord = integralRecord;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(Integer redEnvelope) {
        this.redEnvelope = redEnvelope;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
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


}
