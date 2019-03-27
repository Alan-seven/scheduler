package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.DayWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 河道日水量统计 存入wr_st_dayw_r
 */
@Component("dayWService")
@Transactional
public class DayWServiceImpl implements  DayWService{

    @Autowired
    private DayWRepository dayWRepository;

    @Override
    public Integer findRecordCount( DayW dayW ) {
        return this.dayWRepository.findRecordCount(dayW);
    }

    @Override
    public void updateRecord( DayW dayW ) {
        this.dayWRepository.updateRecord(dayW);
    }

    @Override
    public void saveRecord( DayW dayW ) {
        this.dayWRepository.saveRecord(dayW);
    }
}
