package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StationPumpWService {

    Integer findRecordCount(String stcd, Date tm,  String sttdrcd  );

    void updateRecord(Float w, String stcd,Date tm,String sttdrcd );

    void saveRecord( Date tm,String stcd,Float w,String sttdrcd );

    void batchSave( List<DayW> dayWList,String sttdrcd );
    /**
     * 得到任意时间段级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayW> findSum(Date startDate, Date endDate);
    /**
     * 得到小时级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayW> findHourSum(Date startDate, Date endDate);
    /**
     * 得到日级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayW> findDaySum( Date startDate, Date endDate);

    /**
     * 得到月级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayW> findMonthSum(Date startDate, Date endDate);

    /**
     * 得到年级的合计
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayW> findYearSum(Date startDate, Date endDate);

}