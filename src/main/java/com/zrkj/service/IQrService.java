package com.zrkj.service;

import com.zrkj.pojo.Qr;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/20.
 */
public interface IQrService {
    Map<String,Object> doCreateQrBatch(int num) throws Exception;
    List<File> downLoadZip(int startNum, int endNum);
    List<Qr> getQrList(Byte isF);
    boolean doCheckIsFailure(Integer id);
}
