package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.DayWRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    public void updateRecord( DayW dayW ) {
        this.dayWRepository.updateRecord(dayW);
    }

    public void batchSave( List<DayW> dayWList,String sttdrcd ){
        for(DayW dayW : dayWList){
            saveRecord(dayW,sttdrcd);
        }
    }
    @Override
    public void saveRecord( DayW dayW,String sttdrcd ) {
        dayW.setSttdrcd(sttdrcd);
        if(findRecordCount(dayW)>0){
            this.dayWRepository.updateRecord(dayW);
        }else{
            this.dayWRepository.saveRecord(dayW);
        }
    }

    @Override
    public List<DayW> findSum(Date startDate, Date endDate) {
        return dayWRepository.findSum(startDate,endDate);
    }

    @Override
    public List<DayW> findHourSum(Date startDate, Date endDate) {
        return dayWRepository.findHourSum(startDate,endDate);
    }

    @Override
    public List<DayW> findDaySum(Date startDate, Date endDate) {
        return dayWRepository.findDaySum(startDate,endDate);
    }

    @Override
    public List<DayW> findMonthSum( Date startDate, Date endDate) {
        return dayWRepository.findMonthSum(startDate,endDate);
    }

    @Override
    public List<DayW> findYearSum(Date startDate, Date endDate) {
        return dayWRepository.findYearSum(startDate,endDate);
    }
}
