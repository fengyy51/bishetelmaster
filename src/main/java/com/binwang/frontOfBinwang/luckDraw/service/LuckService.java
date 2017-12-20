package com.binwang.frontOfBinwang.luckDraw.service;

import com.binwang.frontOfBinwang.luckDraw.bean.PrizeModel;
import com.binwang.frontOfBinwang.luckDraw.bean.PrizeParam;
import com.binwang.frontOfBinwang.luckDraw.bean.WinModel;

import java.util.List;
import java.util.Map;

/**
 * Created by yy on 17/7/20.
 */
public interface LuckService {
    PrizeParam getPrizeParam(long id);

    List<String> getPrizeInfo(String actName);

    Map<String, Object> getWinInfo(int collectId);

    long handleWin(String openId, long prizeId, int collectId);

    Boolean judgeDraw(String openId);

    //获取奖券详情
    WinModel getDetail(long id);

    //获取奖券列表
    List<WinModel> getList(String openId);

    //核销奖券
    Boolean handleUse(long id);

}
