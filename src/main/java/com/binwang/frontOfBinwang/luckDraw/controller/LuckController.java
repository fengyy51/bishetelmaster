package com.binwang.frontOfBinwang.luckDraw.controller;

import com.binwang.frontOfBinwang.luckDraw.bean.WinModel;
import com.binwang.frontOfBinwang.luckDraw.service.LuckService;
import com.binwang.frontOfBinwang.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yy on 17/7/20.
 */
@Controller
@RequestMapping("/luck")
public class LuckController {
    @Resource
    private LuckService luckService;


    @RequestMapping("/get-win-info")
    @ResponseBody
    public Object getWin(@RequestParam("collectId") int collectId) {
        try {
            Map<String, Object> m = luckService.getWinInfo(collectId);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取信息出错");
        }
    }

    @RequestMapping("/is-has-draw")
    @ResponseBody
    public Object judgeDraw(@RequestParam("openId") String openId) {
        try {
            Map<String, Object> m = new HashMap<>();
            if (System.currentTimeMillis() > drawDeadLine) {
                m.put("msg", "抽奖已截止");
                m.put("result", false);
                return ResponseUtil.okJSON(m);
            } else {
                Boolean res = luckService.judgeDraw(openId);
                if (res)
                    m.put("msg", "正常");
                else
                    m.put("msg", "出错");
                m.put("result", res);
                return ResponseUtil.okJSON(m);
            }
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取是否可以抽奖信息失败");
        }
    }

    @Value("${fbinwang.draw.deadline}")
    private long drawDeadLine;

    @RequestMapping(value = "/post-draw", method = RequestMethod.POST)
    @ResponseBody
    public Object handlePost(@RequestParam("openId") String openId,
                             @RequestParam("prizeId") long prizeId,
                             @RequestParam("collectId") int collectId) {
        try {
            if (System.currentTimeMillis() > drawDeadLine) {
                return ResponseUtil.errorJSON("抽奖已截止");
            } else {
                long resId = luckService.handleWin(openId, prizeId, collectId);
                Map<String, Long> m = new HashMap<>();
                m.put("id", resId);
                return ResponseUtil.okJSON(m);
            }
        } catch (Exception e) {
            return ResponseUtil.errorJSON("出错，请重试");
        }
    }

    @RequestMapping("/win-detail")
    @ResponseBody
    public Object getDetail(@RequestParam("id") long id) {
        try {
            WinModel res = luckService.getDetail(id);
            return ResponseUtil.okJSON(res);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取奖券详情失败");
        }
    }

    @RequestMapping("/list/win-detail")
    @ResponseBody
    public Object getList(@RequestParam("openId") String openId) {
        try {
            List<WinModel> l = luckService.getList(openId);
            Map<String, Object> m = new HashMap<>();
            m.put("list", l);
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("获取奖券列表失败");
        }
    }

    @RequestMapping(value = "/win-handle-use", method = RequestMethod.POST)
    @ResponseBody
    public Object doHandle(@RequestParam("id") long id) {
        try {
            Map<String, Boolean> m = new HashMap<>();
            m.put("result", luckService.handleUse(id));
            return ResponseUtil.okJSON(m);
        } catch (Exception e) {
            return ResponseUtil.errorJSON("核销奖券出错");
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "hello luckDraw!!!!!!";
    }
}
