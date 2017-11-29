package com.binwang.frontOfBinwang.common.service;

import com.binwang.frontOfBinwang.common.bean.WechatSdkConfig;

/**
 * Created by owen on 17/7/19.
 */
public interface WechatSDK {
    WechatSdkConfig getConfig(String url);

    Boolean judgeSubscribe(String openId);

}
