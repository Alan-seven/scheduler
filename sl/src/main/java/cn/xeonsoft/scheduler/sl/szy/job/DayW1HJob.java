package cn.xeonsoft.scheduler.sl.szy.job;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.DayWService;
import cn.xeonsoft.scheduler.utils.DateInterval;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import cn.xeonsoft.scheduler.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 泵站水量统计
 */
public class DayW1HJob extends QuartzJobBean {

    @Autowired
    private DayWService dayWService;

    @Override
    protected void executeInternal( JobExecutionContext jobExecutionContext ) throws JobExecutionException {
        //saveSumq(DateInterval.HOUR);
        saveSumq(DateInterval.DAY);
        //saveSumq(DateInterval.FIVEDAYS);
        //saveSumq(DateInterval.TENDAYS);
        saveSumq(DateInterval.MONTH);
        saveSumq(DateInterval.YEAR);
    }

    private void saveSumq(DateInterval dataInterval){
        Date beginDate = DateUtils.getBeginDate(dataInterval);
        Date endDate = DateUtils.getEndDate(dataInterval);
        List<DayW> dayWList = new ArrayList<>();
        switch (dataInterval){
            case HOUR:
                dayWList = dayWService.findHourSum(beginDate,endDate);
                dayWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
            case DAY:
                dayWList = dayWService.findDaySum(beginDate,endDate);
                dayWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
            case FIVEDAYS:
                dayWList = dayWService.findSum(beginDate,endDate);
                Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
                for(DayW dayw:dayWList){
                    dayWService.saveRecord(dayw,DateInterval.FIVEDAYS.getType()+"");
                }
                break;
            case TENDAYS:
                dayWList = dayWService.findSum(beginDate,endDate);
                Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
                for(DayW dayw:dayWList){
                    dayWService.saveRecord(dayw,DateInterval.TENDAYS.getType()+"");
                }
                break;
            case MONTH:
                dayWList = dayWService.findMonthSum(beginDate,endDate);
                dayWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
            case YEAR:
                dayWList = dayWService.findYearSum(beginDate,endDate);
                dayWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
        }
    }
}
