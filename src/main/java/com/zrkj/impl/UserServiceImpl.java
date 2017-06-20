package com.zrkj.impl;

import com.alibaba.fastjson.JSON;
import com.zrkj.mapper.RecordMapper;
import com.zrkj.mapper.UserMapper;
import com.zrkj.pojo.Record;
import com.zrkj.pojo.User;
import com.zrkj.pojo.WeixinOauth2Token;
import com.zrkj.service.IUserService;
import com.zrkj.tools.AdvancedUtil;
import com.zrkj.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
@Service
@Scope("prototype")
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RecordMapper recordMapper;

    @Override
    public User obtainUserById(Integer id) {
        User user = userMapper.getUserByID(id);
        List<Record> records;
        user.setIntegralRecord((records=recordMapper.getRecordByUidAndType(id,2))==null?Collections.emptyList():records);
        user.setRedEnvelopeRecord((records=recordMapper.getRecordByUidAndType(id,1))==null?Collections.emptyList():records);
        return user;
    }

    @Override
    public List<User> obtainUserList(HttpSession session) {
        Integer sid = Tools.obtainPrincipal().getId();
        List<User> list = userMapper.getUserList(sid);
        if (list == null || list.size() == 0)
            return Collections.emptyList();
        else
            return list;
    }

    @Override
    public Map<String, Object> doCreateUser(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            request.setCharacterEncoding("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            map.put("result", -2);  //设置编码错误
            return map;
        }

        String code = request.getParameter("code");
        Integer storeId = request.getParameter("storeId")==null?null:Integer.parseInt(request.getParameter("storeId"));
        if (!"authdeny".equals(code)) {
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxbac97923056c9446", "09294bfe0e5177c2e5e50f18ea207075", code);
            String accessToken = weixinOauth2Token.getAccessToken();
            String openId = weixinOauth2Token.getOpenId();
            User user = AdvancedUtil.getUserInfo(accessToken, openId);
            User u;
            if ((u = userMapper.getUserByOpenId(user.getOpenId())) != null) {
                map.put("result", 2); //openId已经存在
                map.put("user", u);
            } else {
                if (user.getOpenId() == null)
                    map.put("result", -1); //openId不能为空
                else {
                    try {
                        user.setStoreId(storeId);
                        map.put("result", userMapper.doCreateUser(user));
                        map.put("user", user);
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("uid", user.getId());
                        httpSession.setAttribute("sid", storeId);
                    } catch (Exception e) {
                        e.printStackTrace();
                        map.put("result", 0);   //插入数据库错误
                    }
                }
            }
        } else {
            map.put("result", -3);  //认证错误
        }

        return map;
    }

    @Override
    public Map<String, Object> doCreateUser(User user,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User u;
        if ((u = userMapper.getUserByOpenId(user.getOpenId())) != null) {
            map.put("result", 2); //openId已经存在
            map.put("user", u);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("uid", u.getId());
            httpSession.setAttribute("sid", u.getStoreId());
        } else {
            if (user.getOpenId() == null)
                map.put("result", -1); //openId不能为空
            else {
                try {
                    map.put("result", userMapper.doCreateUser(user));
                    map.put("user", user);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("uid", user.getId());
                    httpSession.setAttribute("sid", user.getStoreId());
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("result", 0);   //插入数据库错误
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> doUpdateUser(User user,HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();

            try {
                user.setId((Integer) session.getAttribute("uid"));
                map.put("result", userMapper.doUpdateUser(user));
            } catch (Exception e) {
                e.printStackTrace();
                map.put("result", 0);
            }
        return map;
    }
}
