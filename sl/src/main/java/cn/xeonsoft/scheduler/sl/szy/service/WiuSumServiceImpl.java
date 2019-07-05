package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.respository.WiuSumRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        if(findCount(tm,sttdrcd)>0){
            updateSumw(tm,w,sttdrcd);
        }else{
            wiuSumRepository.saveSumw(tm,w,sttdrcd);
        }
        //数据保存到河道外用水统计表中
        if(findLifeCount(tm,sttdrcd)>0){
            updateLifew(tm,w,sttdrcd);
        }else{
            String id = UUID.randomUUID().toString().replace("-","");
            saveLifew(id,tm,w,sttdrcd);
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
    public Integer findLifeCount(Date tm, String sttdrcd) {
        return wiuSumRepository.findLifeCount(tm,sttdrcd);
    }

    @Async
    @Override
    public void updateLifew( Date tm, Float w, String sttdrcd ) {
        wiuSumRepository.updateLifew(tm,w,sttdrcd);
    }
    @Async
    @Override
    public void saveLifew( String id, Date tm, Float w, String sttdrcd ) {
        wiuSumRepository.saveLifew(id,tm,w,sttdrcd);
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
