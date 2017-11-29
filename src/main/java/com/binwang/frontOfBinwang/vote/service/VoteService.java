package com.binwang.frontOfBinwang.vote.service;

import com.binwang.frontOfBinwang.vote.bean.VoteInfo;
import com.binwang.frontOfBinwang.vote.bean.ProductInfo;


import java.util.List;
import java.util.Map;

/**
 * Created by think on 2017/7/13.
 */
public interface VoteService {
    List<VoteInfo> getVoteInfo();
    List<ProductInfo> getProductInfo();
    Map<String,Object> postInfo(String str,String ip,String userAgent);
    int add(long id,String str);

}

