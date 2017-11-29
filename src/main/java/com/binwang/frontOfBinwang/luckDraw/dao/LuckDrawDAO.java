package com.binwang.frontOfBinwang.luckDraw.dao;

import com.binwang.frontOfBinwang.luckDraw.bean.WinCalDO;
import com.binwang.frontOfBinwang.luckDraw.bean.WinModel;
import com.binwang.frontOfBinwang.luckDraw.bean.WinUserDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by owen on 17/7/20.
 */
@Repository
@Mapper
public interface LuckDrawDAO {
    @Select("select id as prizeId,ratio,num,type from prize where relation_id = #{collectId} and num > 0")
    List<WinCalDO> getCalList(@Param("collectId") int collectId);

    @Update("update prize set num = num -1 where id = #{prizeId} and relation_id = #{collectId} and num > 0 ")
    int updateNum(@Param("prizeId") long prizeId, @Param("collectId") int collectId);


    int insertWinInfo(WinUserDO winUserDO);


    @Select("select b.prize_id as prizeId,a.name as name,a.info as info,a.type as type,a.duijiang_time as duijiangTime," +
            "a.duijiang_loc as duijiangLoc,b.code as code,b.is_use as isUse from prize a " +
            "right join f_user_prize b on a.id = b.prize_id where b.id = #{id} limit 1")
    WinModel getWinDetail(@Param("id") long id);

    @Select("select b.id as id,b.prize_id as prizeId,a.name as name,a.info as info,a.type as type,a.duijiang_time as duijiangTime," +
            "a.duijiang_loc as duijiangLoc,b.code as code,b.is_use as isUse from prize a " +
            "right join f_user_prize b on a.id = b.prize_id where b.open_id = #{openId}")
    List<WinModel> getWinList(@Param("openId") String openId);

    @Update("update f_user_prize set is_use = 1 where id = #{id} and is_use = 0")
    int handleUse(@Param("id") long id);
}
