package com.binwang.frontOfBinwang.vote.bean;

/**
 * Created by think on 2017/7/13.
 */
public class ProductInfo {
    private int id;
    private String name;
    private String brandName;
    private String brandImgUrl = "";
    private String productFirst;
    private String productImgUrls;
    private String intro;

    public ProductInfo() {
    }

    public void setProductFirst(String productFirst) {
        this.productFirst = productFirst;
    }

    public String getProductFirst() {
        return productFirst;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductImgUrls() {
        return productImgUrls;
    }

    public void setProductImgUrls(String productImgUrls) {
        this.productImgUrls = productImgUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImgUrl() {
        return brandImgUrl;
    }

    public void setBrandImgUrl(String brandImgUrl) {
        this.brandImgUrl = brandImgUrl;
    }



    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}

