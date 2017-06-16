package com.zrkj.pojo;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public class Prize {
    private Integer id;
    private byte isdel;
    private String prizeName;
    private Double prizeRate;
    private String prizePic;
    private byte isUse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte getIsdel() {
        return isdel;
    }

    public void setIsdel(byte isdel) {
        this.isdel = isdel;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Double getPrizeRate() {
        return prizeRate;
    }

    public void setPrizeRate(Double prizeRate) {
        this.prizeRate = prizeRate;
    }

    public String getPrizePic() {
        return prizePic;
    }

    public void setPrizePic(String prizePic) {
        this.prizePic = prizePic;
    }

    public byte getIsUse() {
        return isUse;
    }

    public void setIsUse(byte isUse) {
        this.isUse = isUse;
    }
}
