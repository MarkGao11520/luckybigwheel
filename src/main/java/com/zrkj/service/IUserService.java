package com.zrkj.service;

import com.zrkj.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public interface IUserService {
    User obtainUserById(Integer id);

    List<User> obtainUserList(HttpSession session);

    Map<String,Object> doCreateUser(HttpServletRequest request);

    Map<String,Object> doCreateUser(User user,HttpServletRequest request,Integer qrid);

    Map<String,Object> doUpdateUser(User user,HttpSession session);
}
