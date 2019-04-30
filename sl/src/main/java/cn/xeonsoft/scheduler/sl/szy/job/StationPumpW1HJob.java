package cn.xeonsoft.scheduler.sl.szy.job;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.StationPumpWService;
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
 * 泵站各测站水量统计
 */
public class StationPumpW1HJob extends QuartzJobBean {

    @Autowired
    private StationPumpWService stationPumpWService;

    @Override
    protected void executeInternal( JobExecutionContext jobExecutionContext ) throws JobExecutionException {
        saveSumq(DateInterval.HOUR);
        saveSumq(DateInterval.DAY);
        saveSumq(DateInterval.FIVEDAYS);
        saveSumq(DateInterval.TENDAYS);
        saveSumq(DateInterval.MONTH);
        saveSumq(DateInterval.YEAR);
    }

    private void saveSumq(DateInterval dataInterval){
        Date beginDate = DateUtils.getBeginDate(dataInterval);
        Date endDate = DateUtils.getEndDate(dataInterval);
        List<DayW> dayWList = new ArrayList<>();
        switch (dataInterval){
            case HOUR:
                dayWList = stationPumpWService.findHourSum(beginDate,endDate);
                stationPumpWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
            case DAY:
                dayWList = stationPumpWService.findDaySum(beginDate,endDate);
                stationPumpWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
            case FIVEDAYS:
                dayWList = stationPumpWService.findSum(beginDate,endDate);
                Date tm1 = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
                for(DayW entity:dayWList){
                    if(null==entity){
                        continue;
                    }
                    stationPumpWService.saveRecord(tm1,entity.getStcd(),entity.getDayW(),DateInterval.FIVEDAYS.getType()+"");
                }
                break;
            case TENDAYS:
                dayWList = stationPumpWService.findSum(beginDate,endDate);
                Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
                for(DayW entity:dayWList){
                    if(null==entity){
                        continue;
                    }
                    stationPumpWService.saveRecord(tm2,entity.getStcd(),entity.getDayW(),DateInterval.TENDAYS.getType()+"");
                }
                break;
            case MONTH:
                dayWList = stationPumpWService.findMonthSum(beginDate,endDate);
                stationPumpWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
            case YEAR:
                dayWList = stationPumpWService.findYearSum(beginDate,endDate);
                stationPumpWService.batchSave(dayWList,dataInterval.getType()+"");
                break;
        }
    }
}
