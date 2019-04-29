package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.StationPumpWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 河道日水量统计 存入wr_st_dayw_r
 */
@Component("stationPumpWService")
@Transactional
public class StationPumpWServiceImpl implements StationPumpWService {

    @Autowired
    private StationPumpWRepository stationPumpWRepository;

    @Override
    public Integer findRecordCount( DayW dayW ) {
        return this.stationPumpWRepository.findRecordCount(dayW);
    }

    public void updateRecord( DayW dayW ) {
        this.stationPumpWRepository.updateRecord(dayW);
    }

    public void batchSave( List<DayW> dayWList,String sttdrcd ){
        for(DayW dayW : dayWList){
            saveRecord(dayW,sttdrcd);
        }
    }
    @Override
    public void saveRecord( DayW dayW,String sttdrcd ) {
        dayW.setSttdrcd(sttdrcd);
        //瞬时流量，转化为水量
        dayW.setDayW(dayW.getDayW()*15*60);
        if(findRecordCount(dayW)>0){
            this.stationPumpWRepository.updateRecord(dayW);
        }else{
            this.stationPumpWRepository.saveRecord(dayW);
        }
    }

    @Override
    public List<DayW> findSum(Date startDate, Date endDate) {
        return stationPumpWRepository.findSum(startDate,endDate);
    }

    @Override
    public List<DayW> findHourSum(Date startDate, Date endDate) {
        return stationPumpWRepository.findHourSum(startDate,endDate);
    }

    @Override
    public List<DayW> findDaySum(Date startDate, Date endDate) {
        return stationPumpWRepository.findDaySum(startDate,endDate);
    }

    @Override
    public List<DayW> findMonthSum( Date startDate, Date endDate) {
        return stationPumpWRepository.findMonthSum(startDate,endDate);
    }

    @Override
    public List<DayW> findYearSum(Date startDate, Date endDate) {
        return stationPumpWRepository.findYearSum(startDate,endDate);
    }
}
