package cn.xeonsoft.scheduler.sl.szy.job;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.PumpSumService;
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
 * 泵站合计小时，日，月，年的数据，存入statis_pump_sum
 */
public class PumpSum1HJob extends QuartzJobBean {

    @Autowired
    private PumpSumService pumpSumService;

    @Override
    protected void executeInternal( JobExecutionContext jobExecutionContext ) throws JobExecutionException {

        saveSumw(DateInterval.DAY,DateInterval.HOUR);
        saveSumw(DateInterval.DAY,DateInterval.DAY);
        saveSumw(DateInterval.FIVEDAYS,DateInterval.FIVEDAYS);
        saveSumw(DateInterval.TENDAYS,DateInterval.TENDAYS);
        saveSumw(DateInterval.MONTH,DateInterval.MONTH);
        saveSumw(DateInterval.YEAR,DateInterval.YEAR);
    }

    private void saveSumw( DateInterval dataInterval, DateInterval dataInterval2){
        Date beginDate = DateUtils.getBeginDate(dataInterval);
        Date endDate = DateUtils.getEndDate(dataInterval);
        List<DayW> pumpSum = new ArrayList<>();
        switch (dataInterval2){
            case HOUR:
                pumpSum = pumpSumService.findHourSum(beginDate,endDate);
                pumpSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
            case DAY:
                pumpSum = pumpSumService.findDaySum(beginDate,endDate);
                pumpSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
            case FIVEDAYS:
                pumpSum = pumpSumService.findSum(beginDate,endDate);
                Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
                for(DayW dayw:pumpSum){
                    if(null==dayw){
                        continue;
                    }
                    pumpSumService.saveSumw(tm,dayw.getDayW(),DateInterval.FIVEDAYS.getType()+"");
                }
                break;
            case TENDAYS:
                pumpSum = pumpSumService.findSum(beginDate,endDate);
                Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
                for(DayW dayw:pumpSum){
                    if(null==dayw){
                        continue;
                    }
                    pumpSumService.saveSumw(tm2,dayw.getDayW(),DateInterval.TENDAYS.getType()+"");
                }
                break;
            case MONTH:
                pumpSum = pumpSumService.findMonthSum(beginDate,endDate);
                pumpSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
            case YEAR:
                pumpSum = pumpSumService.findYearSum(beginDate,endDate);
                pumpSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
        }
    }
}
