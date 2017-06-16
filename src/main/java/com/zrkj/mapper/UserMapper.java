package com.zrkj.mapper;

import com.zrkj.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public interface UserMapper {

    @Select("select * from tb_user where id = #{id}")
    User getUserByID(Integer id);

    @Select("select * from tb_user where openId = #{openId}")
    User getUserByOpenId(String openId);

    @Select("select * from tb_user")
    List<User> getUserList();

    @Insert("insert into tb_user(openId) values(#{openId})")
    @Options(useGeneratedKeys = true)
    int doCreateUser(User user);

    @Update("update tb_user set prize=#{prize} where id = #{id}")
    int doUpdateUser(User user);
}
