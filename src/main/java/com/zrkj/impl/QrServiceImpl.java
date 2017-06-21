package com.zrkj.impl;

import com.zrkj.mapper.QrMapper;
import com.zrkj.pojo.Qr;
import com.zrkj.service.IQrService;
import com.zrkj.tools.QrCode;
import com.zrkj.tools.Tools;
import com.zrkj.tools.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/6/20.
 */
@Service
@Scope("prototype")
public class QrServiceImpl implements IQrService{
    public static String URL = "http://123.150.108.173:8080/userController/wheel?openId=231324324&storeId=1";
    public static String PATH = "/Users/gaowenfeng/desktop/qr/";
    @Autowired
    QrMapper qrMapper;

    @Override
    @Transactional
    public Map<String, Object> doCreateQrBatch(int num) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        Integer sid = Tools.obtainPrincipal().getId();
        List<Qr> list = new ArrayList<Qr>();
        for (int i =0;i<num;i++){
            list.add(new Qr(sid));
        }
        if(qrMapper.doCreateBatch(list)==num) {
            for (int i = 0; i < list.size() ; i++) {
                String fileName = UUID.randomUUID().toString();
                list.get(i).setUrl(URL+"&qrid="+list.get(i).getId());
                list.get(i).setFile_url(QrCode.genQRImage(PATH, fileName, URL+"&qrid="+list.get(i).getId()));
            }
            if(qrMapper.doUpdateBatch(list)==num)
                map.put("result",1);
        }else
            throw new RuntimeException("插入失败");

        return map;
    }

    @Override
    public List<File> downLoadZip(int startNum, int endNum) {
        Integer sid = Tools.obtainPrincipal().getId();
        List<Qr> list = qrMapper.getQrListByNum(startNum-1,endNum,sid);
        List<File> files = new ArrayList<File>();
        for(Qr qr:list){
            files.add(new File(qr.getFile_url()));
        }
        return files;
    }

    @Override
    public List<Qr> getQrList(Byte isF) {
        return qrMapper.getQrList(Tools.obtainPrincipal().getId(),isF);
    }

    @Override
    public boolean doCheckIsFailure(Integer id) {
        if(qrMapper.getQrByID(id).getIsFailure()==0)
        return false;
        else
            return true;
    }
}
