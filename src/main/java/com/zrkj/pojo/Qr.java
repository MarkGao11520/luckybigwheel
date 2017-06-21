package com.zrkj.pojo;

/**
 * Created by gaowenfeng on 2017/6/20.
 */
public class Qr {
    private Integer id;
    private Integer storeId;
    private Byte isFailure;
    private String url;
    private String file_url;



    public Qr(Integer storeId) {
        this.storeId = storeId;
    }

    public Qr() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Byte getIsFailure() {
        return isFailure;
    }

    public void setIsFailure(Byte isFailure) {
        this.isFailure = isFailure;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}
