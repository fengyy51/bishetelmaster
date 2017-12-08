package com.binwang.frontOfBinwang.vote.redis;

/**
 * Created by yy .
 */
public interface VoteRAO {
    Boolean judgeAuthOpenId(String openId);

    Boolean judgeIsVote(String openId);

    void reduceVoteTime(String openId);
}
