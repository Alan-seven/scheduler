package cn.xeonsoft.scheduler.sl.szy.job;

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
        saveSumq(DateInterval.DAY,DateInterval.HOUR);
        saveSumq(DateInterval.DAY,DateInterval.DAY);
        saveSumq(DateInterval.MONTH,DateInterval.MONTH);
        saveSumq(DateInterval.YEAR,DateInterval.YEAR);
    }

    private void saveSumq( DateInterval dataInterval, DateInterval dataInterval2){
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
