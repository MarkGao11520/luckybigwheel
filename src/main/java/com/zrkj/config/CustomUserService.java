package com.zrkj.config;

import com.alibaba.fastjson.JSON;
import com.zrkj.mapper.StoreMapper;
import com.zrkj.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
public class CustomUserService implements UserDetailsService{
    @Autowired
    StoreMapper storeMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Store user = storeMapper.findStoreByUname(s);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
//        user.setRoles(basUserMapper.findUserRoleByUserName(s));
        return user;
    }
}
