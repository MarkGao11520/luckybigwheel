package com.zrkj.web;

import com.zrkj.pojo.Qr;
import com.zrkj.service.IQrService;
import com.zrkj.tools.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/20.
 */
@RestController
@Scope("prototype")
@RequestMapping("qrController")
public class QrController {
    @Autowired
    IQrService iQrService;

    @RequestMapping("downLoadZip")
    public void downLoadZip(HttpServletRequest request, HttpServletResponse response,Integer start,Integer end){
        List<File> files = iQrService.downLoadZip(start,end);
        response.setCharacterEncoding("utf-8");
        try {
            if(end>iQrService.getQrList((byte) 0).size()||start<1){
                response.getWriter().append("创建压缩包失败，3秒后自动调回");
                response.setHeader("refresh","3;URL=../qr");
            }else{
                ZipUtil.downLoadFiles(files,request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("doCreateQrBatch")
    public Map<String,Object> doCreateQrBatch(Integer num){
        Map<String,Object> map =new HashMap<String,Object>();
        try {
            return iQrService.doCreateQrBatch(num);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result",0);
            return map;
        }
    }

    @RequestMapping("getSize")
    public int getSize(){
        return iQrService.getQrList((byte) 0).size();
    }

    @RequestMapping("getQrList")
    public List<Qr> getQrList(Byte isF){
        return iQrService.getQrList(isF);
    }
}
