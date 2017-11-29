package com.binwang.frontOfBinwang.vote.bean;

/**
 * Created by think on 2017/7/13.
 */

public class VoteInfo {
    private int id;
    private int voteNum;
    private String productFirst;
    private int itemId;
    private String productImgUrls;

    public VoteInfo(){
    }

    public String getProductImgUrls() {
        return productImgUrls;
    }

    public void setProductImgUrls(String productImgUrls) {
        this.productImgUrls = productImgUrls;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getProductFirst() {
        return productFirst;
    }

    public void setProductFirst(String productFirst) {
        this.productFirst = productFirst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }
}
