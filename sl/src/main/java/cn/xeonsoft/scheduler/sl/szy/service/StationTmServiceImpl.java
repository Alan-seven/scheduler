package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.respository.StationTmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component("stationTmService")
@Transactional
public class StationTmServiceImpl implements  StationTmService{

    @Autowired
    private StationTmRepository stationTmRepository;

    public List<StationTm> listByRiver( String beginDate,String endDate){
        return stationTmRepository.listByRiver( beginDate, endDate);
    }

    public StationTm getByErhai( String beginDate,String endDate){
        return stationTmRepository.getByErhai( beginDate, endDate);
    }

    @Override
    public Integer findCount(String stcd, Date tm) {
        return stationTmRepository.findCount(stcd,tm);
    }

    @Override
    public void update(String stcd, String wqg, Date tm) {
        stationTmRepository.update(stcd,wqg,tm);
    }

    @Override
    public void save(String id, String stcd, Date tm, String wqg) {
        stationTmRepository.save(id,stcd,tm,wqg);
    }
}
