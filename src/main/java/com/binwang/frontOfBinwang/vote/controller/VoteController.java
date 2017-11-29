package com.binwang.frontOfBinwang.vote.controller;

import com.binwang.frontOfBinwang.utils.ResponseUtil;
import com.binwang.frontOfBinwang.vote.bean.ProductInfo;
import com.binwang.frontOfBinwang.vote.bean.VoteInfo;
import com.binwang.frontOfBinwang.vote.service.VoteService;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by think on 2017/7/13.
 */
@Controller
@RequestMapping("/vote")
public class VoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteController.class);

    @Resource
    private VoteService voteService;

    @Value("${fbinwang.vote.post.tokens}")
    private int voteTokens;

    @RequestMapping("/get-vote-product-info")
    @ResponseBody
    public Object getProductInfo() {
        try {
            List<ProductInfo> res = voteService.getProductInfo();
            for (int i = 0; i < res.size(); i++) {
                if (res.get(i).getProductFirst().isEmpty()) {
                    String[] chrstr = res.get(i).getProductImgUrls().split("@@@");
                    res.get(i).setProductFirst(chrstr[0]);
                }
            }
            System.out.print("获取投票信息");
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error("获取商品初始数据出错");
            return ResponseUtil.errorJSON("获取基本信息失败");
        }
    }

    @RequestMapping("/get-vote-number-info")
    @ResponseBody
    public Object getVoteNumInfo() {
        try {
            List<VoteInfo> res = voteService.getVoteInfo();
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            LOGGER.error("获取投票初始数据出错");
            return ResponseUtil.errorJSON("获取基本信息失败");
        }
    }

    @Value("${fbinwang.vote.deadline}")
    private long voteDeadline;


    @RequestMapping(value = "/post-vote-number-info", method = RequestMethod.POST)
    @ResponseBody
    public Object postVoteNumInfo(String str, HttpServletRequest req) {
        try {
            RateLimiter limiter = RateLimiter.create(voteTokens);
            if (!limiter.tryAcquire(1, TimeUnit.SECONDS)) {
                return ResponseUtil.errorJSON("请稍后再试");
            } else {
                if (System.currentTimeMillis() > voteDeadline) {
                    Map<String, Object> mm = new HashMap<>();
                    mm.put("result", false);
                    mm.put("msg", "投票已截止");
                    return ResponseUtil.okJSON(mm);
                } else {
                    String ip = req.getRemoteAddr();
                    String userAgent = req.getHeader("user-agent");
                    Map<String, Object> m = voteService.postInfo(str, ip, userAgent);
                    return ResponseUtil.okJSON(m);
                }
            }
        } catch (Exception e) {
            return ResponseUtil.errorJSON("数据提交失败");
        }
    }
}
