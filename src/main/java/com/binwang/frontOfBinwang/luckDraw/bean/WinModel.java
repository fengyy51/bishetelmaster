package com.binwang.frontOfBinwang.luckDraw.bean;

/**
 * Created by owen on 17/7/20.
 */
public class WinModel {
    private long id;
    private long prizeId;
    private String name;
    private String info;
    private int type;
    private String duijiangTime;
    private String duijiangLoc;
    private String code;
    private int isUse;

    public WinModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(long prizeId) {
        this.prizeId = prizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDuijiangTime() {
        return duijiangTime;
    }

    public void setDuijiangTime(String duijiangTime) {
        this.duijiangTime = duijiangTime;
    }

    public String getDuijiangLoc() {
        return duijiangLoc;
    }

    public void setDuijiangLoc(String duijiangLoc) {
        this.duijiangLoc = duijiangLoc;
    }
}