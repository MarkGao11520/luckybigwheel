package com.zrkj.web;

import com.zrkj.pojo.Prize;
import com.zrkj.pojo.User;
import com.zrkj.service.IPrizeService;
import com.zrkj.service.IUserService;
import com.zrkj.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("uploadPrizeImage")
    public Map<String,String> uploadPrizeImage(HttpServletRequest request, MultipartFile prizeImage){
        return Tools.uploadFile(request,"prize",prizeImage);
    }
}
