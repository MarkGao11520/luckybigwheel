package com.zrkj.service;

import com.zrkj.pojo.Prize;
import com.zrkj.pojo.Record;
import com.zrkj.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
public interface IPrizeService {
    Prize obtainPrizeById(Integer id);

    List<Prize> obtainPrizeList();

    Map<String,Object> obtainPrizeListInUse(Integer storeId);

    Map<String,Object> obtainUseNum();

    Map<String,Object> doCreatePrize(Prize prize);

    Map<String,Object> doUpdatePrize(Prize prize);

    Map<String,Object> doCreateRecord(HttpSession session,String prizeName);

    Map<String,Object> doCancelAfterVerification(String code);

}
