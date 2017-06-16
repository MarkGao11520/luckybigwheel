package com.zrkj.impl;

import com.zrkj.mapper.PrizeMapper;
import com.zrkj.pojo.Prize;
import com.zrkj.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
@Service
@Scope("prototype")
public class PrizeServiceImpl implements IPrizeService{
    @Autowired
    PrizeMapper prizeMapper;

    @Override
    public Prize obtainPrizeById(Integer id) {
        return prizeMapper.getPrizeByID(id);
    }

    @Override
    public List<Prize> obtainPrizeList() {
        return prizeMapper.getPrizeList();
    }

    @Override
    public Map<String, Object> doCreatePrize(Prize prize) {
        Map<String, Object> map = new HashMap<String,Object>();
        if(prizeMapper.getPrizeByPrizeName(prize.getPrizeName())!=null){
            map.put("result",-2); //该奖品已经存在
        }else {
            if(prize.getPrizeName()==null)
                map.put("result",-1); //奖品名不能为空
            else
            {
                try {
                    map.put("result",prizeMapper.doCreatePrize(prize));
                }catch (Exception e){
                    e.printStackTrace();
                    map.put("result",0);
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> doUpdatePrize(Prize prize) {
        Map<String, Object> map = new HashMap<String,Object>();
        if(prize.getId()==null){
            map.put("result",-1); //id不能为空
        }else{
            try {
                map.put("result",prizeMapper.doUpdatePrize(prize));
            }catch (Exception e){
                e.printStackTrace();
                map.put("result",0);
            }
        }
        return map;

    }
}
