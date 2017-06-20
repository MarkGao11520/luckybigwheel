package com.zrkj.mapper;

import com.zrkj.pojo.Record;
import com.zrkj.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/20.
 */
public interface RecordMapper {
    @Insert("<script>insert into tb_record" +
            "(" +
            "<if test=\"recordCode!=null\">recordCode,</if>" +
            "<if test=\"prizeId!=null\">prizeId,</if>" +
            "<if test=\"userId!=null\">userId,</if>" +
            "<if test=\"crateTime!=null\">crateTime,</if>" +
            "<if test=\"type!=null\">type</if>" +
            ") " +
            "values" +
            "(" +
            "<if test=\"recordCode!=null\">#{recordCode},</if>" +
            "<if test=\"prizeId!=null\">#{prizeId},</if>" +
            "<if test=\"userId!=null\">#{userId},</if>" +
            "<if test=\"crateTime!=null\">#{crateTime},</if>" +
            "<if test=\"type!=null\">#{type}</if>" +
            ")</script>")
    @Options(useGeneratedKeys = true)
    int doCreateRecore(Record record);

    @Update("<script>update tb_record " +
            "<set> " +
            "<if test=\"recordCode!=null\">recordCode=#{recordCode},</if> " +
            "<if test=\"prizeId!=null\">prizeId=#{prizeId},</if> " +
            "<if test=\"userId!=null\">userId=#{userId},</if> " +
            "<if test=\"state!=null\">state=#{state},</if> " +
            "<if test=\"type!=null\">type=#{type}</if> " +
            "</set>" +
            "where id = #{id}</script>")
    int doUpdateRecprd(Record record);

    @Select("<script>" +
            "select * " +
            "from tb_record " +
            "where userId = #{uid} " +
            "<if test=\"type!=null\">and type = #{type}</if> order by state" +
            "</script> ")
    @Results(
            @Result(column = "prizeId",property = "prize",one = @One(select = "com.zrkj.mapper.PrizeMapper.getPrizeByID"))
    )
    List<Record> getRecordByUidAndType(@Param("uid") Integer uid, @Param("type") Integer type);

    @Select("<script>" +
            "select * " +
            "from tb_record " +
            "where userId = #{uid} order by state" +
            "</script> ")
    @Results(
            @Result(column = "prizeId",property = "prize",one = @One(select = "com.zrkj.mapper.PrizeMapper.getPrizeByID"))
    )
    List<Record> getRecordByUid(@Param("uid") Integer uid);

    @Update("update tb_record set state = 1 where recordCode = #{code}")
    int doUpdateRecordStateByCode(String code);
}
