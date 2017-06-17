package com.zrkj.impl;

import com.zrkj.mapper.PrizeMapper;
import com.zrkj.pojo.Prize;
import com.zrkj.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Map<String, Object> obtainPrizeListInUse() {
        Map<String, Object> map = new HashMap<String,Object>();
        List<Prize> prizes = prizeMapper.getPrizeListInUse();
        List<String> prizeList = new ArrayList<String>();
        List<String> colorList = new ArrayList<String>();
        List<String> picList = new ArrayList<String>();
        int rateNum = 0;
        int k = 0 ;
        for(int i=0;i<prizes.size();i++){
            int rate = (int) (prizes.get(i).getPrizeRate()*10);
            rateNum+=rate;
            for(int j=0;j<rate;j++){
                prizeList.add(prizes.get(i).getPrizeName());
                picList.add(prizes.get(i).getPrizePic());
                if(k==0||k%2==0){
                    colorList.add("#FFF4D6");
                }else {
                    colorList.add("#FFFFFF");
                }
                k++;
            }

        }
        for(int i=k;i<10;i++){
            prizeList.add("谢谢参与");
            if(k==0||k%2==0){
                colorList.add("#FFF4D6");
            }else {
                colorList.add("#FFFFFF");
            }
        }
        map.put("colorList",colorList);
        map.put("prizeList",prizeList);
        map.put("picList",picList);
        return map;
    }

    @Override
    public Map<String,Object> obtainUseNum() {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("useNum",prizeMapper.getUseNum());
        List<Double> rates = prizeMapper.getRateList();
        Double rateNum = 0.0;
        for(int i=0;i<rates.size();i++){
            rateNum+=rates.get(i);
        }
        map.put("rateNum",rateNum);
        return map;
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
