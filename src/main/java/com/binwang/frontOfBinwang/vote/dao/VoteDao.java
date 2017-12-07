package com.binwang.frontOfBinwang.vote.dao;

import com.binwang.frontOfBinwang.vote.bean.VoteInfo;
import com.binwang.frontOfBinwang.vote.bean.ProductInfo;
import com.binwang.frontOfBinwang.vote.bean.VoteParam;
import com.binwang.frontOfBinwang.vote.bean.VoteRecord;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by think on 2017/7/13.
 */
@Repository
@Mapper
public interface VoteDao {
    @Select("select act_name as actName,begin,end,pro_num as proNum,vote_num as voteNum,share_num as shareNum,vote_max_num as voteMaxNum,vote_decoration as voteDecoration,pro_approved as proApproved from vote_params where act_id=#{actId}")
    VoteParam getVoteParam(@Param("actId")long actId);
    @Select("select a.id,a.vote_num as voteNum,a.item_id as itemId,b.product_first as productFirst,b.product_img_urls as productImgUrls from f_vote a,f_collect b where a.item_id=b.id and b.is_ok = 1 order by a.vote_num DESC")
    List<VoteInfo> getVoteInfo();

    @Select("select id,reg_item as productInfo from f_user_act where act_id=#{actId} and is_ok=1")
    List<ProductInfo> getProductInfo(@Param("actId")long actId);

    @Insert("INSERT INTO f_vote (item_id,vote_num) VALUES(#{itemId},1) ON DUPLICATE KEY UPDATE " +
            "vote_num=vote_num+1")
    int setVoteNum(@Param("itemId") int itemId);

    @Update("UPDATE f_collect SET product_first=#{productFirst} WHERE id=#{id}")
    int addProductFirst(@Param("id") long id, @Param("productFirst") String productFirst);

    @Insert("INSERT INTO f_vote_record (ip,addtime,record,user_agent) VALUES(#{ip},unix_timestamp(),#{record},#{userAgent})")
    void insertVoteRecord(VoteRecord voteRecord);
}
