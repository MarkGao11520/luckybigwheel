package com.zrkj.mapper;

import com.zrkj.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public interface UserMapper {

    @Select("select * from tb_user where id = #{id}")
    User getUserByID(Integer id);

    @Select("select * from tb_user where openId = #{openId}")
    User getUserByOpenId(String openId);

    @Select("select * from tb_user where storeId = #{storeId}")
    @Results(
            @Result(column = "id",property = "records",many = @Many(select = "com.zrkj.mapper.RecordMapper.getRecordByUid"))
    )
    List<User> getUserList(Integer storeId);

    @Insert("<script>insert into tb_user" +
            "(" +
            "<if test=\"openId!=null\">openId,</if>" +
            "<if test=\"redEnvelope!=null\">redEnvelope,</if>" +
            "<if test=\"integral!=null\">integral,</if>" +
            "<if test=\"name!=null\">name,</if>" +
            "<if test=\"phone!=null\">phone,</if>" +
            "<if test=\"nikeName!=null\">nikeName,</if>" +
            "<if test=\"headImgUrl!=null\">headImgUrl,</if>" +
            "<if test=\"storeId!=null\">storeId</if>" +
            ") " +
            "values" +
            "(" +
            "<if test=\"openId!=null\">#{openId},</if>" +
            "<if test=\"redEnvelope!=null\">#{redEnvelope},</if>" +
            "<if test=\"integral!=null\">#{integral},</if>" +
            "<if test=\"name!=null\">#{name},</if>" +
            "<if test=\"phone!=null\">#{phone},</if>" +
            "<if test=\"nikeName!=null\">#{nikeName},</if>" +
            "<if test=\"headImgUrl!=null\">#{headImgUrl},</if>" +
            "<if test=\"storeId!=null\">#{storeId}</if>" +
            ")</script>")
    @Options(useGeneratedKeys = true)
    int doCreateUser(User user);

    @Update("<script>update tb_user " +
            "<set> " +
            "<if test=\"openId!=null\">openId=#{openId},</if> " +
            "<if test=\"redEnvelope!=null\">redEnvelope=#{redEnvelope},</if> " +
            "<if test=\"integral!=null\">integral=#{integral},</if> " +
            "<if test=\"name!=null\">name=#{name},</if> " +
            "<if test=\"phone!=null\">phone=#{phone},</if> " +
            "<if test=\"nikeName!=null\">nikeName=#{nikeName},</if> " +
            "<if test=\"headImgUrl!=null\">headImgUrl=#{headImgUrl},</if> " +
            "<if test=\"storeId!=null\">storeId=#{storeId},</if> " +
            "</set>" +
            "where id = #{id}</script>")
    int doUpdateUser(User user);
}
