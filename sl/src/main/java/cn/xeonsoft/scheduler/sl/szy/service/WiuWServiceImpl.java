package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.WiuWRespository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component("wiuWService")
@Transactional
public class WiuWServiceImpl implements WiuWService {

    @Autowired
    private WiuWRespository wiuWRespository;

    @Override
    public Integer findRecordCount( String stcd,Date tm,String sttdrcd) {
        return this.wiuWRespository.findRecordCount(stcd,tm,sttdrcd);
    }

    @Override
    public void updateRecord( Date tm,String stcd,Float w,String sttdrcd) {
        this.wiuWRespository.updateRecord(tm,stcd,w,sttdrcd);
    }

    @Override
    public void saveRecord( Date tm,String stcd,Float w,String sttdrcd) {
        if(findRecordCount(stcd,tm,sttdrcd)>0){
           updateRecord(tm,stcd,w,sttdrcd);
        }else{
            this.wiuWRespository.saveRecord(tm,stcd,w,sttdrcd);
        }
    }

    @Override
    public void saveRecord( List<DayW> dayWList,String sttdrcd ) {
        for(DayW dayw : dayWList){
            String stcd = dayw.getStcd();
            String tm = dayw.getTm();
            Date _tm  = DateUtils.parseDate(tm);
            if(null==_tm){
                continue;
            }
            Float w = dayw.getDayW();
            //瞬时流量，转化为水量
            // dayW.setDayW(dayW.getDayW()*15*60);
            if(findRecordCount(stcd,_tm,sttdrcd)>0){
                this.wiuWRespository.updateRecord(_tm,stcd,w,sttdrcd);
            }else{
                this.wiuWRespository.saveRecord(_tm,stcd,w,sttdrcd);
            }
        }

    }

    @Override
    public List<DayW> findHourSum( Date startDate, Date endDate ) {
        return wiuWRespository.findHourSum(startDate, endDate);
    }

    @Override
    public List<DayW> findDaySum( Date startDate, Date endDate ) {
        return wiuWRespository.findDaySum(startDate, endDate);
    }

    @Override
    public List<DayW> findMonthSum( Date startDate, Date endDate ) {
        return wiuWRespository.findMonthSum(startDate, endDate);
    }

    @Override
    public List<DayW> findYearSum( Date startDate, Date endDate ) {
        return wiuWRespository.findYearSum(startDate, endDate);
    }

    @Override
    public List<DayW> findSum( Date startDate, Date endDate ) {
        return wiuWRespository.findSum(startDate, endDate);
    }
}
