package com.zrkj.mapper;

import com.zrkj.pojo.Store;
import org.apache.ibatis.annotations.Select;

/**
 * Created by gaowenfeng on 2017/6/19.
 */
public interface StoreMapper {

    @Select("select * from tb_store where userName=#{uname}")
    Store findStoreByUname(String uname);
}
