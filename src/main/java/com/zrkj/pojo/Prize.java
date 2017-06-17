package com.zrkj.pojo;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public class Prize {
    private Integer id;
    private Byte isdel;
    private String prizeName;
    private Double prizeRate;
    private String prizePic;
    private Byte isUse;


    public Byte getIsdel() {
        return isdel;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }

    public Byte getIsUse() {
        return isUse;
    }

    public void setIsUse(Byte isUse) {
        this.isUse = isUse;
    }
}
