package com.zrkj.mapper;

import com.zrkj.pojo.Prize;
import com.zrkj.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public interface PrizeMapper {

    @Select("select * from tb_prize where id = #{id}")
    Prize getPrizeByID(Integer id);

    @Select("select * from tb_prize where prizeName = #{prizeName}")
    Prize getPrizeByPrizeName(String prizeName);

    @Select("select * from tb_prize")
    List<Prize> getPrizeList();

    @Insert("<script>insert into tb_prize(prizeName<if test=\"prizeRate!=null\">,prizeRate</if><if test=\"prizePic!=null\">,prizePic</if>)" +
            "values(#{prizeName}<if test=\"prizeRate!=null\">,#{prizeRate}</if><if test=\"prizePic!=null\">,#{prizePic}</if>)</script>")
    @Options(useGeneratedKeys = true)
    int doCreatePrize(Prize prize);

    @Update("<script>update tb_prize <set> " +
            "<if test=\"isdel!=null\">isdel=#{isdel},</if>" +
            "<if test=\"prizeName!=null\">prizeName=#{prizeName},</if>" +
            "<if test=\"prizeRate!=null\">prizeRate=#{prizeRate},</if>" +
            "<if test=\"prizePic!=null\">prizePic=#{prizePic},</if>" +
            "<if test=\"isUse!=null\">isUse=#{isUse},</if></set>" +
            "where id = #{id}</script>")
    int doUpdatePrize(Prize prize);
}
