package cn.xeonsoft.scheduler.sl.szy.job;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.WiuWService;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 统计各水厂测站的小时，日，月，年用水量
 * 从wr_day_w_r 统计数据存入statis_wiu_w
 */
public class WiuW1HJob  extends QuartzJobBean {

    @Autowired
    private WiuWService wiuWService;

    @Override
    protected void executeInternal( JobExecutionContext jobExecutionContext ) throws JobExecutionException {
        saveSumw(DateInterval.DAY);
        saveSumw(DateInterval.FIVEDAYS);
        saveSumw(DateInterval.TENDAYS);
        saveSumw(DateInterval.MONTH);
        saveSumw(DateInterval.YEAR);
    }

    private void saveSumw(DateInterval dataInterval){
        Date beginDate = DateUtils.getBeginDate(dataInterval);
        Date endDate = DateUtils.getEndDate(dataInterval);
        List<DayW> dayWList = new ArrayList<>();
        switch (dataInterval){
            case HOUR:
                dayWList = wiuWService.findHourSum(beginDate,endDate);
                wiuWService.saveRecord(dayWList,dataInterval.getType()+"");
                break;
            case DAY:
                dayWList = wiuWService.findDaySum(beginDate,endDate);
                wiuWService.saveRecord(dayWList,dataInterval.getType()+"");
                break;
            case FIVEDAYS:
                dayWList = wiuWService.findSum(beginDate,endDate);
                Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
                for(DayW entity:dayWList){
                    if(null==entity){
                        continue;
                    }
                    wiuWService.saveRecord(tm,entity.getStcd(),entity.getDayW(),DateInterval.FIVEDAYS.getType()+"");
                }
                break;
            case TENDAYS:
                dayWList = wiuWService.findSum(beginDate,endDate);
                Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
                for(DayW entity:dayWList){
                    if(null==entity){
                        continue;
                    }
                    wiuWService.saveRecord(tm2,entity.getStcd(),entity.getDayW(),DateInterval.TENDAYS.getType()+"");
                }
                break;
            case MONTH:
                dayWList = wiuWService.findMonthSum(beginDate,endDate);
                wiuWService.saveRecord(dayWList,dataInterval.getType()+"");
                break;
            case YEAR:
                dayWList = wiuWService.findYearSum(beginDate,endDate);
                wiuWService.saveRecord(dayWList,dataInterval.getType()+"");
                break;
        }
    }
}
