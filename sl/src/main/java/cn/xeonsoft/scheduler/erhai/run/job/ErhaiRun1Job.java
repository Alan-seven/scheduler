package cn.xeonsoft.scheduler.erhai.run.job;

import cn.xeonsoft.scheduler.erhai.run.bo.*;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisYerbRepository;
import cn.xeonsoft.scheduler.erhai.run.service.*;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErhaiRun1Job extends QuartzJobBean {
    public static final String ADDVCD_DALI = "532901000000";
    @Autowired
    private StatisDistDrService statisDistDrService;
    @Autowired
    private ErhaiRunService erhaiRunService;
    @Autowired
    private InWService inWService;
    @Autowired
    private ErhaiService erhaiService;
    @Autowired
    private StatisOuterUseService statisOuterUseService;
    @Autowired
    private StatisXierheService statisXierheService;
    @Autowired
    private StatisYerbService statisYerbService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
        Date endDate = DateUtils.getEndDate(DateInterval.DAY);
        List<Dali> daliList = statisDistDrService.list(DateInterval.HOUR.getType()+"",startDate,endDate,ADDVCD_DALI);
        //天然入湖、净入湖没有小时级数据
        Float firstZ = erhaiService.findFirstZ(DateInterval.DAY.getType()+"",startDate,endDate);
        List<OuterUse> outerUseList = statisOuterUseService.list(DateInterval.DAY.getType()+"",startDate,endDate);
        List<Xierhe> xierheList = statisXierheService.list(DateInterval.DAY.getType()+"",startDate,endDate);
        List<Yerb> yerbList = statisYerbService.list(DateInterval.DAY.getType()+"",startDate,endDate);

        List<ErhaiRun> erhaiRunList = new ArrayList<>();
        ErhaiRun erhaiRun = new ErhaiRun();
    }
}
