package com.binwang.frontOfBinwang.vote.service;


import com.binwang.frontOfBinwang.utils.HandleDateUtil;
import com.binwang.frontOfBinwang.vote.bean.ProductInfo;
import com.binwang.frontOfBinwang.vote.bean.VoteInfo;
import com.binwang.frontOfBinwang.vote.bean.VoteRecord;
import com.binwang.frontOfBinwang.vote.dao.VoteDao;
import com.binwang.frontOfBinwang.vote.redis.VoteRAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/**
 * Created by think on 2017/7/13.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteServiceImpl.class);

    @Resource
    private VoteDao voteDAO;

    @Resource
    private VoteRAO voteRAO;

    //写记录线程
    private final ExecutorService writeVoteRecordPool = Executors.newFixedThreadPool(8);

    private class WriteVoteRecord implements Runnable {
        private VoteRecord voteRecord;

        public WriteVoteRecord(VoteRecord voteRecord) {
            this.voteRecord = voteRecord;
        }

        @Override
        public void run() {
            voteDAO.insertVoteRecord(voteRecord);
        }
    }

    @Override
    public int add(long id, String str) {
        return voteDAO.addProductFirst(id, str);
    }

    @Override
    public List<ProductInfo> getProductInfo() {
        return voteDAO.getProductInfo();
    }

    @Override
    @Transactional
    public Map<String, Object> postInfo(String str, String ip,String userAgent) {
        String[] s = str.split("@@@");
        long jsCurTime = Long.parseLong(s[1]);
        String openId = s[2];
        Map<String, Object> m = new HashMap<>();
        //授权每日限制
        if(StringUtils.isEmpty(openId)){
            m.put("result",true);
            m.put("msg","投票成功");
            return m;
        }
        if(!voteRAO.judgeAuthOpenId(openId)){
            m.put("result",true);
            m.put("msg","投票成功");
            return m;
        }
        if(!voteRAO.judgeIsVote(openId)) {
            m.put("result",true);
            m.put("msg","投票成功");
            return m;
        }
        if (jsCurTime < HandleDateUtil.getTimesmorning() || jsCurTime > HandleDateUtil.getTimesnight()) {
            m.put("result", false);
            m.put("msg", "请确保当日投票");
            return m;
        }
        VoteRecord vr = new VoteRecord(ip,"," + s[0],userAgent);
        writeVoteRecordPool.execute(new WriteVoteRecord(vr));
        String[] a = s[0].split(",");
        List<String> b = java.util.Arrays.asList(a);
        List<Integer> list = new ArrayList<Integer>();
        for (Iterator<String> it = b.iterator(); it.hasNext(); ) {
            list.add(Integer.parseInt(it.next()));
        }
        for (Integer i : list) {
            int res = voteDAO.setVoteNum(i);
            if (res <= 0) {
                LOGGER.error("投票计数出错，itemId：" + i);
                throw new RuntimeException("出错");
            }
        }
        voteRAO.reduceVoteTime(openId);
        m.put("result", true);
        m.put("msg", "投票成功");
        return m;
    }

    @Override
    public List<VoteInfo> getVoteInfo() {
        List<VoteInfo> l = voteDAO.getVoteInfo();
        List<VoteInfo> newL = new ArrayList<>();
        for (VoteInfo v : l) {
            if (StringUtils.isEmpty(v.getProductFirst())) {
                v.setProductFirst(v.getProductImgUrls().split("@@@")[0]);
            }
            newL.add(v);
        }
        return newL;
    }
}
