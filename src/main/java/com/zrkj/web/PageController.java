package com.zrkj.web;

import com.zrkj.pojo.Prize;
import com.zrkj.pojo.User;
import com.zrkj.service.IPrizeService;
import com.zrkj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
@RestController
@Scope("prototype")
@RequestMapping("pageController")
public class PageController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IPrizeService iPrizeService;

    @RequestMapping("getUserList")
    public List<User>  getUserList(){
        return iUserService.obtainUserList();
    }

    @RequestMapping("getPrizeList")
    public List<Prize>  getPrizeList(){
        return iPrizeService.obtainPrizeList();
    }

    @RequestMapping("addPrize")
    public Map<String,Object> addPrize(Prize prize){
        return iPrizeService.doCreatePrize(prize);
    }

    @RequestMapping("updatePrzie")
    public Map<String,Object> updatePrzie(Prize prize){
        return iPrizeService.doUpdatePrize(prize);
    }
}
