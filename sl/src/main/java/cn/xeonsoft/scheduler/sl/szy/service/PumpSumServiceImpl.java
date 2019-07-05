package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.PumpSumRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 河道日水量统计 存入wr_st_dayw_r
 */
@Component("pumpSumService")
@Transactional
public class PumpSumServiceImpl implements  PumpSumService {

    @Autowired
    private PumpSumRepository pumpSumRepository;

    @Override
    public Integer findCount( Date tm, String sttdrcd ) {
        return pumpSumRepository.findCount(tm,sttdrcd);
    }

    private void updateSumw(Date tm, Float w,  String sttdrcd) {
        pumpSumRepository.updateSumw(tm,w,sttdrcd);
    }

    @Override
    public Integer findFarmCount( Date tm, String sttdrcd ) {
        return pumpSumRepository.findFarmCount(tm,sttdrcd);
    }

    @Override
    public void saveSumw( Date tm, Float w, String sttdrcd ) {
        //瞬时流量转换为水量
        //w = w*15*60;
        if(findCount(tm,sttdrcd)>0){
            updateSumw(tm,w,sttdrcd);
        }else{
            pumpSumRepository.saveSumw(tm,w,sttdrcd);
        }
        //保存到河道外用水
        if(findFarmCount(tm,sttdrcd)>0){
            updateFarmw(tm,w,sttdrcd);
        }else{
            String id = UUID.randomUUID().toString().replace("-","");
            saveFarmw(id,tm,w,sttdrcd);
        }
    }

    @Override
    public void saveSumw( List<DayW> pumpSum, String sttdrcd ) {
        for(DayW dayw : pumpSum){
            String tm = dayw.getTm();
            Date _tm  = DateUtils.parseDate(tm);
            if(null==_tm){
                continue;
            }
            Float day_w = dayw.getDayW();
            saveSumw(_tm,day_w,sttdrcd);
        }

    }

    @Override
    public List<DayW> findHourSum( Date startDate, Date endDate ) {
        return pumpSumRepository.findHourSum(startDate,endDate);
    }

    @Override
    public List<DayW> findDaySum( Date startDate, Date endDate ) {
        return pumpSumRepository.findDaySum(startDate,endDate);
    }

    @Override
    public List<DayW> findMonthSum( Date startDate, Date endDate ) {
        return pumpSumRepository.findMonthSum(startDate,endDate);
    }

    @Override
    public List<DayW> findYearSum( Date startDate, Date endDate ) {
        return pumpSumRepository.findYearSum(startDate,endDate);
    }

    @Override
    public List<DayW> findSum( Date startDate, Date endDate ) {
        return pumpSumRepository.findSum(startDate,endDate);
    }

    @Async
    @Override
    public void updateFarmw( Date tm, Float w, String sttdrcd ) {
        pumpSumRepository.updateFarmw(tm,w,sttdrcd);
    }

    @Async
    @Override
    public void saveFarmw( String id, Date tm, Float w, String sttdrcd ) {
        pumpSumRepository.saveFarmw(id,tm,w,sttdrcd);
    }
}
