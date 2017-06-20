package com.zrkj.mapper;

import com.zrkj.pojo.Prize;
import com.zrkj.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public interface PrizeMapper {

    @Select("select * from tb_prize where id = #{id}")
    Prize getPrizeByID(Integer id);

    @Select("select * from tb_prize where prizeName = #{prizeName} and storeId = #{storeId}")
    Prize getPrizeByPrizeName(@Param("prizeName") String prizeName, @Param("storeId") Integer storeId);

    @Select("select * from tb_prize where storeId = #{storeId} ")
    List<Prize> getPrizeList(Integer storeId);

    @Select("select * from tb_prize where isUse = 0 and  storeId = #{storeId}")
    List<Prize> getPrizeListInUse(Integer storeId);

    @Select("select prizeRate from tb_prize where isUse = 0 and  storeId = #{storeId}")
    List<Double> getRateList(Integer storeId);

    @Select("select count(id) from tb_prize where isUse = 0 and  storeId = #{storeId}")
    int getUseNum(Integer storeId);

    @Insert("<script>insert into tb_prize" +
            "(prizeName,type,storeId" +
            "<if test=\"value!=null\">,value</if>" +
            "<if test=\"prizeRate!=null\">,prizeRate</if>" +
            "<if test=\"prizePic!=null\">,prizePic</if>)" +
            "values(#{prizeName},#{type},#{storeId}" +
            "<if test=\"value!=null\">,#{value}</if>" +
            "<if test=\"prizeRate!=null\">,#{prizeRate}</if>" +
            "<if test=\"prizePic!=null\">,#{prizePic}</if>)</script>")
    @Options(useGeneratedKeys = true)
    int doCreatePrize(Prize prize);

    @Update("<script>update tb_prize <set> " +
            "<if test=\"isdel!=null\">isdel=#{isdel},</if>" +
            "<if test=\"prizeName!=null\">prizeName=#{prizeName},</if>" +
            "<if test=\"prizeRate!=null\">prizeRate=#{prizeRate},</if>" +
            "<if test=\"prizePic!=null\">prizePic=#{prizePic},</if>" +
            "<if test=\"isUse!=null\">isUse=#{isUse},</if>" +
            "<if test=\"value!=null\">value=#{value},</if>" +
            "<if test=\"type!=null\">type=#{type},</if>" +
            "<if test=\"storeId!=null\">storeId=#{storeId},</if></set>" +
            "where id = #{id}</script>")
    int doUpdatePrize(Prize prize);
}
