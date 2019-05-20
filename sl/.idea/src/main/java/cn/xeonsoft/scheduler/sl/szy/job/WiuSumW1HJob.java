package cn.xeonsoft.scheduler.sl.szy.job;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.WiuSumService;
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
 * 统计所有水厂的小时，日，月，年用水量
 * 从wr_day_w_r 统计数据存入statis_wiu_sumw
 */

public class WiuSumW1HJob  extends QuartzJobBean {

    @Autowired
    private WiuSumService wiuSumService;

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
                pumpSum = wiuSumService.findHourSum(beginDate,endDate);
                wiuSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
            case DAY:
                pumpSum = wiuSumService.findDaySum(beginDate,endDate);
                wiuSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
            case FIVEDAYS:
                pumpSum = wiuSumService.findSum(beginDate,endDate);
                Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
                for(DayW dayw:pumpSum){
                    if(null==dayw){
                        continue;
                    }
                    wiuSumService.saveSumw(tm,dayw.getDayW(),DateInterval.FIVEDAYS.getType()+"");
                }
                break;
            case TENDAYS:
                pumpSum = wiuSumService.findSum(beginDate,endDate);
                Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
                for(DayW dayw:pumpSum){
                    if(null==dayw){
                        continue;
                    }
                    wiuSumService.saveSumw(tm2,dayw.getDayW(),DateInterval.TENDAYS.getType()+"");
                }
                break;
            case MONTH:
                pumpSum = wiuSumService.findMonthSum(beginDate,endDate);
                wiuSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
            case YEAR:
                pumpSum = wiuSumService.findYearSum(beginDate,endDate);
                wiuSumService.saveSumw(pumpSum,dataInterval2.getType()+"");
                break;
        }
    }
}
