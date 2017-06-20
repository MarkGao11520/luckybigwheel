package com.zrkj.web;

import com.alibaba.fastjson.JSON;
import com.zrkj.pojo.Prize;
import com.zrkj.pojo.Store;
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
import javax.servlet.http.HttpSession;
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
    public List<User>  getUserList(HttpSession session){
        return iUserService.obtainUserList(session);
    }

    @RequestMapping("getPrizeList")
    public List<Prize>  getPrizeList(){
        return iPrizeService.obtainPrizeList();
    }


    @RequestMapping("getUseNum")
    public Map<String,Object> getUseNum(){
        return iPrizeService.obtainUseNum();
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

    @RequestMapping("getConStore")
    public Store getConStore(){
        return Tools.obtainPrincipal();
    }

    @RequestMapping("doCancelAfterVerification")
    public Map<String,Object> doCancelAfterVerification(String code){
        return iPrizeService.doCancelAfterVerification(code);
    }
}
