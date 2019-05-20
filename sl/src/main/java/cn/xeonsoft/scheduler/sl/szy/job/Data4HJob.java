package cn.xeonsoft.scheduler.sl.szy.job;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.bo.WrStatB;
import cn.xeonsoft.scheduler.sl.szy.service.DataService;
import cn.xeonsoft.scheduler.sl.szy.service.StationTmService;
import cn.xeonsoft.scheduler.sl.szy.service.WrStatBService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import cn.xeonsoft.scheduler.utils.DateUtils;
import java.util.Date;
import java.util.List;

/**
 * 纳污能力计算  暂定4h计算一次
 */
public class Data4HJob extends QuartzJobBean {

    @Autowired
    private DataService dataService;
    @Autowired
    private StationTmService stationTmService;
    //@Autowired
    //private WrStatBService wrStatBService;

    @Override
    protected void executeInternal( JobExecutionContext context) throws JobExecutionException {
        String beginDate = DateUtils.getBeforeDateTime(new Date(),-4);
        String endDate= DateUtils.formatDateTime(new Date());

        //List<WrStatB> statList = wrStatBService.list("WQ");
        List<StationTm> stationList = stationTmService.listByRiver(beginDate,endDate);
        //保存31条河流纳污能力计算结果
        dataService.saveRiverResult(stationList);

        //洱海水质纳污能力计算结果保存
        StationTm station = stationTmService.getByErhai(beginDate,endDate);
        if(null!=station){
            dataService.saveErhaiResult(station);
        }


    }


}
