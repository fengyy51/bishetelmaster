package com.binwang.frontOfBinwang.activity.bean;

public class RegItemModel {
    private Long id;
    private String title;
    private String username;
    private String regDeadLine;
    private String reg;
    private String regItem;

    public RegItemModel(){}
    public RegItemModel(Long id,String reg,String regItem,String title,String regDeadLine,String username){
        this.id=id;
        this.reg=reg;
        this.regItem=regItem;
        this.title=title;
        this.regDeadLine=regDeadLine;
        this.username=username;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegItem(String regItem) {
        this.regItem = regItem;
    }

    public String getRegItem() {
        return regItem;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getReg() {
        return reg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegDeadLine() {
        return regDeadLine;
    }

    public void setRegDeadLine(String regDeadLine) {
        this.regDeadLine = regDeadLine;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

