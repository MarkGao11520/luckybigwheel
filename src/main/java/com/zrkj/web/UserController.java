package com.zrkj.web;

import com.zrkj.pojo.User;
import com.zrkj.pojo.WeixinOauth2Token;
import com.zrkj.service.IPrizeService;
import com.zrkj.service.IQrService;
import com.zrkj.service.IUserService;
import com.zrkj.tools.AdvancedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/20.
 */
@Controller
@Scope("prototype")
@RequestMapping("userController")
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IPrizeService iPrizeService;
    @Autowired
    IQrService iQrService;

    @RequestMapping("doRecord")
    @ResponseBody
    public Map<String,Object> doRecord(String prize,HttpSession session)
    {
        return iPrizeService.doCreateRecord(session,prize);
    }

    @RequestMapping(value = "wheel")
    public ModelAndView wheel(ModelAndView mv, HttpServletRequest request,User user,Integer qrid) throws UnsupportedEncodingException {
        if(!iQrService.doCheckIsFailure(qrid)){
            Map<String,Object> map = iUserService.doCreateUser(user,request,qrid);
            if((int)map.get("result")>0)
                mv.addObject("user",(User)map.get("User"));
            else
                throw new RuntimeException("创建新用户失败,错误码："+(int)map.get("result"));
            mv.setViewName("wheel");
        }else {
            mv.setViewName("out");
        }
        return mv;
    }

    @RequestMapping("getPrizeListInUse")
    @ResponseBody
    public Map<String,Object>  getPrizeListInUse(HttpSession session){
        Integer storeId = (Integer) session.getAttribute("sid");
        return iPrizeService.obtainPrizeListInUse(storeId);
    }

    @RequestMapping("getUserInfo")
    @ResponseBody
    public User getUserInfo(HttpSession session){
        return iUserService.obtainUserById((Integer) session.getAttribute("uid"));
    }

    @RequestMapping("doUpdateUser")
    @ResponseBody
    public Map<String,Object> doUpdateUser(User user,HttpSession session){
        return iUserService.doUpdateUser(user, session);
    }
}
