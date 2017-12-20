package com.binwang.frontOfBinwang.luckDraw.bean;

public class PrizeParam {
    private Long id;
    private String name;
    private String begin;
    private String end;
    private int prizeNum;
    private String shareNum;
    private int prizeMaxNum;
    private String prizeDecoration;
    public PrizeParam(){}
    public PrizeParam(Long id,String name,String begin,String end,int prizeMaxNum,int prizeNum,String shareNum,String prizeDecoration){
        this.id=id;
        this.name=name;
        this.begin=begin;
        this.end=end;
        this.prizeNum=prizeNum;
        this.shareNum=shareNum;
        this.prizeMaxNum=prizeMaxNum;
        this.prizeDecoration=prizeDecoration;

    }
    public String getShareNum() {
        return shareNum;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrizeNum(int prizeNum) {
        this.prizeNum = prizeNum;
    }

    public void setPrizeMaxNum(int prizeMaxNum) {
        this.prizeMaxNum = prizeMaxNum;
    }

    public void setPrizeDecoration(String prizeDecoration) {
        this.prizeDecoration = prizeDecoration;
    }

    public String getPrizeDecoration() {
        return prizeDecoration;
    }

    public int getPrizeNum() {
        return prizeNum;
    }

    public int getPrizeMaxNum() {
        return prizeMaxNum;
    }
}

