package com.zrkj.service;

import com.zrkj.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public interface IUserService {
    User obtainUserById(Integer id);

    List<User> obtainUserList();

    Map<String,Object> doCreateUser(User user);

    Map<String,Object> doUpdatePrize(User user);
}
