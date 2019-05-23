package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.StationPumpWRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
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
    public Integer findRecordCount(String stcd, Date tm,  String sttdrcd ) {
        return this.stationPumpWRepository.findRecordCount(stcd,tm,sttdrcd);
    }

    public void updateRecord( Float w, String stcd,Date tm,String sttdrcd) {
        this.stationPumpWRepository.updateRecord(w,stcd,tm,sttdrcd);
    }

    public void batchSave( List<DayW> dayWList,String sttdrcd ){
        for(DayW entity : dayWList){
            if(null==entity){
                continue;
            }
            Date _tm = DateUtils.parseDate(entity.getTm());
            saveRecord(_tm,entity.getStcd(),entity.getDayW(),sttdrcd);
        }
    }
    @Override
    public void saveRecord(Date tm,String stcd,Float w,String sttdrcd) {
        //瞬时流量，转化为水量
       // w = w*15*60;
        if(findRecordCount(stcd,tm,sttdrcd)>0){
            updateRecord(w,stcd,tm,sttdrcd);
        }else{
            this.stationPumpWRepository.saveRecord(tm,stcd,w,sttdrcd);
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
