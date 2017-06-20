package com.zrkj.impl;

import com.alibaba.fastjson.JSON;
import com.zrkj.mapper.PrizeMapper;
import com.zrkj.mapper.RecordMapper;
import com.zrkj.mapper.UserMapper;
import com.zrkj.pojo.Prize;
import com.zrkj.pojo.Record;
import com.zrkj.pojo.User;
import com.zrkj.service.IPrizeService;
import com.zrkj.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
@Service
@Scope("prototype")
public class PrizeServiceImpl implements IPrizeService {
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Prize obtainPrizeById(Integer id) {
        return prizeMapper.getPrizeByID(id);
    }

    @Override
    public List<Prize> obtainPrizeList() {

        return prizeMapper.getPrizeList(Tools.obtainPrincipal().getId());
    }

    @Override
    public Map<String, Object> obtainPrizeListInUse(Integer storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Prize> prizes = prizeMapper.getPrizeListInUse(storeId);
        List<String> prizeList = new ArrayList<String>();
        List<String> colorList = new ArrayList<String>();
        List<String> picList = new ArrayList<String>();
        int rateNum = 0;
        int k = 0;
        for (int i = 0; i < prizes.size(); i++) {
            int rate = (int) (prizes.get(i).getPrizeRate() * 10);
            rateNum += rate;
            for (int j = 0; j < rate; j++) {
                prizeList.add(prizes.get(i).getPrizeName());
                picList.add(prizes.get(i).getPrizePic());
                if (k == 0 || k % 2 == 0) {
                    colorList.add("#FFF4D6");
                } else {
                    colorList.add("#FFFFFF");
                }
                k++;
            }

        }
        for (int i = k; i < 10; i++) {
            prizeList.add("谢谢参与");
            if (k == 0 || k % 2 == 0) {
                colorList.add("#FFF4D6");
            } else {
                colorList.add("#FFFFFF");
            }
        }
        map.put("colorList", colorList);
        map.put("prizeList", prizeList);
        map.put("picList", picList);
        return map;
    }

    @Override
    public Map<String, Object> obtainUseNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("useNum", prizeMapper.getUseNum(Tools.obtainPrincipal().getId()));
        List<Double> rates = prizeMapper.getRateList(Tools.obtainPrincipal().getId());
        Double rateNum = 0.0;
        for (int i = 0; i < rates.size(); i++) {
            rateNum += rates.get(i);
        }
        map.put("rateNum", rateNum);
        return map;
    }

    @Override
    public Map<String, Object> doCreatePrize(Prize prize) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (prizeMapper.getPrizeByPrizeName(prize.getPrizeName(), Tools.obtainPrincipal().getId()) != null) {
            map.put("result", -2); //该奖品已经存在
        } else {
            if (prize.getPrizeName() == null)
                map.put("result", -1); //奖品名不能为空
            else {
                try {
                    prize.setStoreId(Tools.obtainPrincipal().getId());
                    map.put("result", prizeMapper.doCreatePrize(prize));
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("result", 0);
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> doUpdatePrize(Prize prize) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (prize.getId() == null) {
            map.put("result", -1); //id不能为空
        } else {
            try {
                prize.setStoreId(Tools.obtainPrincipal().getId());
                map.put("result", prizeMapper.doUpdatePrize(prize));
            } catch (Exception e) {
                e.printStackTrace();
                map.put("result", 0);
            }
        }
        return map;

    }

    @Override
    @Transactional
    public Map<String, Object> doCreateRecord(HttpSession session, String prizeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Record record = new Record();
        Integer sid = (Integer) session.getAttribute("sid");
        Integer uid = (Integer) session.getAttribute("uid");
        Prize prize = prizeMapper.getPrizeByPrizeName(prizeName, sid);
        record.setPrizeId(prize.getId());
        record.setUserId(uid);
        record.setType(prize.getType());
        record.setCrateTime(Tools.dataLongToString(new Date().getTime()));
        if (recordMapper.doCreateRecore(record) == 1) {
            Calendar calendar = Calendar.getInstance();
            Integer id = record.getId();
            String code = id.toString().length() == 4 ? id.toString() : id.toString().length() == 3 ? "0" + id : id.toString().length() == 2 ? "00" + id : "000" + id;
            StringBuffer sb = new StringBuffer();
            sb.append("ZRKJ").append(calendar.getWeekYear()).append(code);
            record.setRecordCode(sb.toString());
            if (recordMapper.doUpdateRecprd(record) == 1) {
                if (prize.getType() > 0) {
                    User user = userMapper.getUserByID(uid);
                    if (prize.getType() == 1) {
                        user.setRedEnvelope(user.getRedEnvelope() + prize.getValue());
                    } else if (prize.getType() == 2) {
                        user.setIntegral(user.getIntegral() + prize.getValue());
                    }
                    if (userMapper.doUpdateUser(user) == 1) {
                        map.put("result", 1);
                    }else {
                        throw new RuntimeException("更改用户失败");
                    }
                }else
                map.put("result", 1);
            } else {
                throw new RuntimeException("插入编号失败");
            }
        } else {
            throw new RuntimeException("插入纪录失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> doCancelAfterVerification(String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map.put("result",recordMapper.doUpdateRecordStateByCode(code));
        }catch (Exception e){
            e.printStackTrace();
            map.put("result",-1);
        }finally {
            return map;
        }
    }
}
