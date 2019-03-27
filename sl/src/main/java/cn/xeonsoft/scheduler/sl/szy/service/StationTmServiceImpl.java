package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.respository.StationTmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("stationTmService")
@Transactional
public class StationTmServiceImpl implements  StationTmService{

    @Autowired
    private StationTmRepository stationTmRepository;

    public List<StationTm> list( String beginDate,String endDate){
        return stationTmRepository.list( beginDate, endDate);
    }
}
