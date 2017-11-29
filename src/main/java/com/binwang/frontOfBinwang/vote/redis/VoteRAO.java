package com.binwang.frontOfBinwang.vote.redis;

/**
 * Created by owen on 17/8/21.
 */
public interface VoteRAO {
    Boolean judgeAuthOpenId(String openId);

    Boolean judgeIsVote(String openId);

    void reduceVoteTime(String openId);
}
