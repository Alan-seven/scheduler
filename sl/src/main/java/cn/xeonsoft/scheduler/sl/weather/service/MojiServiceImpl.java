package cn.xeonsoft.scheduler.sl.weather.service;

import cn.xeonsoft.scheduler.sl.rain.bo.MoJi;
import cn.xeonsoft.scheduler.sl.weather.respository.MojiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Component("mojiService")
@Transactional
public class MojiServiceImpl implements MojiService {

    @Autowired
    private MojiRepository mojiRepository;
    @Override
    public Integer findCount(Date tm, String tp) {
        return mojiRepository.findCount(tm,tp);
    }

    @Override
    public void save(Date tm, String tp, String result) {
        if(findCount(tm,tp)<=0){
            mojiRepository.save(tm,tp,result);
        }

    }

    @Override
    public MoJi find(Date tm, String tp) {
        return mojiRepository.find(tm,tp);
    }
}
