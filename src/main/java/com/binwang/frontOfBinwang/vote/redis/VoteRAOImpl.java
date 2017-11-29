package com.binwang.frontOfBinwang.vote.redis;

import com.binwang.frontOfBinwang.helper.RedisFather;
import com.binwang.frontOfBinwang.utils.HandleDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by owen on 17/8/21.
 */
@Repository
public class VoteRAOImpl extends RedisFather implements VoteRAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteRAOImpl.class);

    public VoteRAOImpl() {
        super.LOGGER = LOGGER;
    }

    private final String AUTH_PRE = "fbinwang158:refresh_token:";
    private final String VOTE_TIME_PRE = "fbinwang158:vote:per:";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Value("${fbinwang.vote.time}")
    private int votePerTime;

    @Override
    public Boolean judgeAuthOpenId(String openId) {
        String key = AUTH_PRE + openId;
        return super.hasStringValue(key);
    }

    @Override
    public Boolean judgeIsVote(String openId) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = VOTE_TIME_PRE + openId;
        if (redisTemplate.hasKey(key)) {
            int newTime = Integer.parseInt(ops.get(key));
            if (newTime > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            ops.set(key, votePerTime + "", (HandleDateUtil.getTimesnight() - System.currentTimeMillis()) / 1000, TimeUnit.SECONDS);
            return true;
        }
    }

    @Override
    public void reduceVoteTime(String openId) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = VOTE_TIME_PRE + openId;
        ops.increment(key, -1);
    }
}
