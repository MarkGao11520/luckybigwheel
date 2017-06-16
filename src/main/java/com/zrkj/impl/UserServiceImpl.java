package com.zrkj.impl;

import com.zrkj.mapper.UserMapper;
import com.zrkj.pojo.User;
import com.zrkj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
@Service
@Scope("prototype")
public class UserServiceImpl implements IUserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User obtainUserById(Integer id) {
        return userMapper.getUserByID(id);
    }

    @Override
    public List<User> obtainUserList() {
        List<User> list = userMapper.getUserList();
        if(list==null||list.size()==0)
            return Collections.emptyList();
        else
            return list;
    }

    @Override
    public Map<String, Object> doCreateUser(User user) {
        Map<String, Object> map = new HashMap<String,Object>();
        if(userMapper.getUserByOpenId(user.getOpenId())!=null){
            map.put("result",-2); //openId已经存在
        }else {
            if (user.getOpenId() == null)
                map.put("result", -1); //openId不能为空
            else {
                try {
                    map.put("result", userMapper.doCreateUser(user));
                    map.put("uid", user.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("result", 0);
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> doUpdatePrize(User user) {
        Map<String, Object> map = new HashMap<String,Object>();
        if(user.getId()==null||user.getPrize()==null||user.getPrize().equals("")||user.getPrize()==""){
            map.put("result",-1); //id或者奖品不能为空
        }else{
            try {
                map.put("result",userMapper.doUpdateUser(user));
            }catch (Exception e){
                e.printStackTrace();
                map.put("result",0);
            }
        }
        return map;
    }
}
