package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;

import java.util.Date;
import java.util.List;

public interface PumpSumService {

    Integer findCount(Date tm,String sttdrcd);

    void saveSumw(Date tm,Float w,String sttdrcd);

    void saveSumw( List<DayW> pumpSum, String sttdrcd);

    List<DayW> findHourSum( Date startDate,  Date endDate);

    List<DayW> findDaySum( Date startDate,  Date endDate);

    List<DayW> findMonthSum( Date startDate,  Date endDate);

    List<DayW> findYearSum( Date startDate,  Date endDate);

    List<DayW> findSum( Date startDate,  Date endDate);

    Integer findFarmCount(Date tm,String sttdrcd);

    void updateFarmw(Date tm,Float w, String sttdrcd);

    void saveFarmw(String id,Date tm,Float w, String sttdrcd);
}
