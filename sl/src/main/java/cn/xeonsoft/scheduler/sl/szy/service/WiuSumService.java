package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;

import java.util.Date;
import java.util.List;

public interface WiuSumService {

    Integer findCount(Date tm,String sttdrcd);

    void saveSumw(Date tm,Float w,String sttdrcd);

    void saveSumw( List<DayW> wiuSum, String sttdrcd);

    Integer findLifeCount(Date tm,String sttdrcd);

    void updateLifew(Date tm,Float w, String sttdrcd);

    void saveLifew(String id,Date tm,Float w, String sttdrcd);

    List<DayW> findHourSum( Date startDate, Date endDate);

    List<DayW> findDaySum( Date startDate,  Date endDate);

    List<DayW> findMonthSum( Date startDate,  Date endDate);

    List<DayW> findYearSum( Date startDate,  Date endDate);

    List<DayW> findSum( Date startDate,  Date endDate);

}
