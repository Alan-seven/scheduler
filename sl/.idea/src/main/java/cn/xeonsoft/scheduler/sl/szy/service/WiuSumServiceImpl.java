package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.WiuSumRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component("wiuSumService")
@Transactional
public class WiuSumServiceImpl implements WiuSumService{

    @Autowired
    private WiuSumRepository wiuSumRepository;

    @Override
    public Integer findCount( Date tm, String sttdrcd ) {
        return wiuSumRepository.findCount(tm,sttdrcd);
    }

    private void updateSumw(Date tm, Float w,  String sttdrcd) {
        wiuSumRepository.updateSumw(tm,w,sttdrcd);
    }

    @Override
    public void saveSumw( Date tm, Float w, String sttdrcd ) {
        wiuSumRepository.saveSumw(tm,w,sttdrcd);
        if(findCount(tm,sttdrcd)>0){
            updateSumw(tm,w,sttdrcd);
        }else{
            wiuSumRepository.saveSumw(tm,w,sttdrcd);
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
        return wiuSumRepository.findHourSum(startDate,endDate);
    }

    @Override
    public List<DayW> findDaySum( Date startDate, Date endDate ) {
        return wiuSumRepository.findDaySum(startDate,endDate);
    }

    @Override
    public List<DayW> findMonthSum( Date startDate, Date endDate ) {
        return wiuSumRepository.findMonthSum(startDate,endDate);
    }

    @Override
    public List<DayW> findYearSum( Date startDate, Date endDate ) {
        return wiuSumRepository.findYearSum(startDate,endDate);
    }

    @Override
    public List<DayW> findSum( Date startDate, Date endDate ) {
        return wiuSumRepository.findSum(startDate,endDate);
    }
}
